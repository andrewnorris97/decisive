package com.example.anorris.decisive;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Movie> movieList;
    private EditText mSearchMovie;
    private Spinner mVotingSpinner;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.movie_grid);
        mLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSearchMovie = (EditText) findViewById(R.id.movie_search_bar);
        mSearchMovie.setHint("Search Movie");

        mVotingSpinner = (Spinner) findViewById(R.id.voting_system_spinner);
        ArrayAdapter<CharSequence> votingSpinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.voting_systems, android.R.layout.simple_spinner_item);
        votingSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mVotingSpinner.setAdapter(votingSpinnerAdapter);


    }

    @Override
    public void onResume(){
        super.onResume();

        updateUI();
    }

    private void updateUI() {
        if (mAdapter == null){
            mAdapter = new MovieAdapter(movieList);
            mRecyclerView.setAdapter(mAdapter);
        } else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class MovieHolder extends RecyclerView.ViewHolder{
        public Movie mMovie;

        private TextView movieTitle;

        public MovieHolder(View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.movie_title);
        }

        public void bindMovie(Movie movie){
            mMovie = movie;
            movieTitle.setText("The Room");
        }
    }

    public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

        private List<Movie> mMovies;

        public MovieAdapter(List<Movie> movies) {
            mMovies = movies;
        }

        @Override
        public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View itemView = LayoutInflater.from(context).inflate(R.layout.movie_card_layout,parent,false);
            return new MovieHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MovieHolder holder, int position){
            Movie movie = mMovies.get(position);
            holder.bindMovie(movie);
        }

        @Override
        public int getItemCount(){
            return mMovies.size();
        }
    }



}
