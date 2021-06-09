package com.kagwisoftwares.nyt.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.kagwisoftwares.nyt.R;

public class MovieFragment extends Fragment {

    private static MovieFragment instance;

    private MovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Movies review");
        return view;
    }

    public static MovieFragment getMovieFragInstance() {
        if (instance == null)
            return new MovieFragment();
        return instance;
    }
}
