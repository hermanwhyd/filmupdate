package id.hw.labs.movieupdate.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import id.hw.labs.movieupdate.R;

import java.util.List;

import id.hw.labs.movieupdate.activities.DetailShowsActivity;
import id.hw.labs.movieupdate.database.DBHelper;
import id.hw.labs.movieupdate.model.Shows;
import id.hw.labs.movieupdate.utils.ApiInterface;
import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Pad on 8/8/2017.
 */

public class FavoriteTVShowAdapter extends RecyclerView.Adapter<FavoriteTVShowAdapter.FavoriteHolder> {
    private List<Shows> showsList;
    private Context context;

    public FavoriteTVShowAdapter(Context context) {
        this.context = context;
        DBHelper mDbHelper = new DBHelper(context);
        showsList = mDbHelper.getAllFavoriteShow();
    }

    @Override
    public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movie,parent,false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(final FavoriteHolder holder, int position) {
        final Shows shows = showsList.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(ApiInterface.BASE_IMG_MED + shows.getPosterPath())
                .fit()
                .centerCrop()
                .into(holder.moviePoster);
        holder.title.setText(shows.getName());
        holder.rate.setText(String.valueOf(shows.getVoteAverage()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailShowsActivity.class);
                i.putExtra("shows", new GsonBuilder().create().toJson(shows));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context
                        ,holder.moviePoster,"imageTrans");
                ActivityCompat.startActivity(context,i,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return showsList.size();
    }

    class FavoriteHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView title, rate;
        public FavoriteHolder(View itemView) {
            super(itemView);
            moviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
            title = (TextView) itemView.findViewById(R.id.title);
            rate = (TextView) itemView.findViewById(R.id.rate);
        }
    }
}
