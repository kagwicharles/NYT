package com.kagwisoftwares.nyt.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.kagwisoftwares.nyt.R;

public class BooksFragment extends Fragment {

    private static BooksFragment instance = null;

    private BooksFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Books");

        return view;
    }

    public static BooksFragment getBooksFragInstance() {
        if (instance == null)
            return new BooksFragment();
        return instance;
    }

}
