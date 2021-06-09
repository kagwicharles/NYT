package com.kagwisoftwares.nyt;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PopularListAdapter implements android.widget.ListAdapter {

        private Context context;
        private LayoutInflater inflater;
        private ArrayList<PopularArticleModel> arrayList;
        private boolean bookmarkClicked = false;


        public PopularListAdapter(Context context, ArrayList<PopularArticleModel> arrayList) {
            this.context = context;
            this.arrayList= arrayList;

            inflater = LayoutInflater.from(context);
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int position, View convertView, final ViewGroup parent) {
            PopularArticleModel articleModel = arrayList.get(position);
            if (null == convertView){
                convertView = inflater.inflate(R.layout.popular_article_item, null);

                TextView title = (TextView) convertView.findViewById(R.id.title);
                TextView abstractTxt = (TextView) convertView.findViewById(R.id.abstractTxt);
                TextView byline = (TextView) convertView.findViewById(R.id.byline);

                final ImageView like = (ImageView) convertView.findViewById(R.id.like);
                like.setOnClickListener(new View.OnClickListener() {
                    boolean likeClicked = true;
                    @Override
                    public void onClick(View view) {
                        if (likeClicked){
                            like.setColorFilter(Color.BLUE);
                            Toast.makeText(parent.getContext(), "Liked", Toast.LENGTH_SHORT).show();
                            likeClicked = false;
                        } else {
                            likeClicked = true;
                            like.setColorFilter(Color.LTGRAY);
                        }
                    }
                });

                final ImageView bookmark = (ImageView) convertView.findViewById(R.id.bookmark);
                bookmark.setOnClickListener(new View.OnClickListener() {
                    boolean bookmarked = true;
                    @Override
                    public void onClick(View view) {
                        if (bookmarked){
                            bookmark.setColorFilter(R.color.colorPrimary);
                            Toast.makeText(parent.getContext(), "Bookmarked", Toast.LENGTH_SHORT).show();
                            bookmarked = false;
                        } else {
                            bookmarked = true;
                            bookmark.setColorFilter(Color.LTGRAY);
                        }
                    }
                });

                title.setText(articleModel.getTitle());
                abstractTxt.setText(articleModel.getAbstractTxt());
                byline.setText(articleModel.getByLine());

            }
            return convertView;
        }

        @Override
        public int getItemViewType(int i) {
            return i;
        }

        @Override
        public int getViewTypeCount() {
            return arrayList.size();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int i) {
            return false;
        }
}

