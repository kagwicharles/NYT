package com.kagwisoftwares.nyt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kagwisoftwares.nyt.UI.BooksFragment;
import com.kagwisoftwares.nyt.UI.MovieFragment;
import com.kagwisoftwares.nyt.UI.PopularFragment;
import com.kagwisoftwares.nyt.UI.TopStoryFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView btmNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFragment(TopStoryFragment.getTopStoryFragmentInstance());

        btmNav = findViewById(R.id.bottomNavigation);
        btmNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.trending:
                        openFragment(TopStoryFragment.getTopStoryFragmentInstance());
                        break;
                    case R.id.popular:
                        openFragment(PopularFragment.getPopularFragInstance());
                        break;
                    case R.id.movies:
                        openFragment(MovieFragment.getMovieFragInstance());
                        break;
                    case R.id.books:
                        openFragment(BooksFragment.getBooksFragInstance());
                        break;
                }
                return true;
            }
        });
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

}
