package com.kagwisoftwares.nyt.UI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.kagwisoftwares.nyt.ArticleModel;
import com.kagwisoftwares.nyt.ListAdapter;
import com.kagwisoftwares.nyt.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class TopStoryFragment extends Fragment {

    private ListView articlesList;
    private ArrayList<ArticleModel> articlesArray;
    private SwipeRefreshLayout pullToRefresh;
    private ShimmerFrameLayout shimmerFrameLayout;
    private static TopStoryFragment instance = null;

    private TopStoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_story, container, false);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("NYT Top Stories");

        //Start shimmering
        shimmerFrameLayout = view.findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();

        articlesArray = new ArrayList<>();
        articlesList = (ListView) view.findViewById(R.id.articleslist);
        pullToRefresh = view.findViewById(R.id.pullToRefresh);
        new GetArticles().execute();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetArticles().execute();
            }
        });
        return view;
    }


    public class GetArticles extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            pullToRefresh.setRefreshing(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // All your networking logic
            // should be here
            try{
                // Create URL
                URL nytEndpoint = new URL("https://api.nytimes.com/svc/news/v3/content/all/all.json?api-key=OPLnKIM85F5jvpiqRw0PONC0NY4V8dDu");
                // Create connection
                HttpsURLConnection myConnection = (HttpsURLConnection) nytEndpoint.openConnection();

                if (myConnection.getResponseCode() == 200) {
                    // Success
                    // Further processing here
                    InputStream responseBody = myConnection.getInputStream();
                    //InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                    String response = convertStreamToString(responseBody);
                    if (response != null) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);

                            //Get JSON array node
                            JSONArray articles = jsonObject.getJSONArray("results");

                            //Looping through all results
                            for (int i=0;i<articles.length();i++) {
                                JSONObject c = articles.getJSONObject(i);
                                String title = c.getString("title");
                                Log.d("TITLE", title);

                                String abstractTxt = c.getString("abstract");

                                String imageUrl = null;
                                if (c.has("thumbnail_standard")) {
                                    imageUrl = c.getString("thumbnail_standard");
                                    Log.d("IMAGEURL", imageUrl);
                                }else {
                                    imageUrl = null;
                                }
                                String byline = c.getString("byline");

                                //Add article to articles list
                                articlesArray.add(new ArticleModel(title, abstractTxt, imageUrl, byline));
                            }
                        }catch (final JSONException e) {

                        }
                    }
                    myConnection.disconnect();
                }


            }catch(IOException e){

            }
            return  null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (articlesList == null) {
                Toast.makeText(getContext(), "List is empty!", Toast.LENGTH_SHORT).show();
            }
            ListAdapter listAdapter = new ListAdapter(getContext(), articlesArray);
            articlesList.setAdapter(listAdapter);
            pullToRefresh.setRefreshing(false);
            shimmerFrameLayout.stopShimmer();
            shimmerFrameLayout.setVisibility(View.GONE);
        }
    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }catch (IOException E) {

        }finally {
            try{
                is.close();
            }catch (IOException e) {

            }
        }
        return sb.toString();
    }

    public static TopStoryFragment getTopStoryFragmentInstance() {
        if (instance == null)
            instance = new TopStoryFragment();
        return instance;
    }

}
