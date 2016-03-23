package com.nanodegree.popularmovies.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.nanodegree.popularmovies.App;
import com.nanodegree.popularmovies.BuildConfig;
import com.nanodegree.popularmovies.R;
import com.nanodegree.popularmovies.model.DiscoverMovieResponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements Toolbar.OnMenuItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.movie_recycler_view)
    RecyclerView mMovieRecyclerView;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.inflateMenu(R.menu.menu_main_fragment);
        mToolbar.setOnMenuItemClickListener(this);

        mMovieRecyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_fragment, menu);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        App.getApi()
                .getPopularMovies(BuildConfig.MOVIE_DP_API)
                .enqueue(new Callback<DiscoverMovieResponse>() {
                    @Override
                    public void onResponse(Response<DiscoverMovieResponse> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            //TODO update / show list
                        } else {
                            //TODO show error
                        }
                    }
                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        return false;
    }

    @Override
    public void onRefresh() {

    }
}
