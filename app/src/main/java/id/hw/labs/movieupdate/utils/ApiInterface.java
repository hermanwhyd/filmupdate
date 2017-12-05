package id.hw.labs.movieupdate.utils;

import id.hw.labs.movieupdate.model.CastDetails;
import id.hw.labs.movieupdate.model.CastList;
import id.hw.labs.movieupdate.model.GenreList;
import id.hw.labs.movieupdate.model.MovieCredits;
import id.hw.labs.movieupdate.model.MovieList;
import id.hw.labs.movieupdate.model.ReviewList;
import id.hw.labs.movieupdate.model.Season;
import id.hw.labs.movieupdate.model.ShowCredits;
import id.hw.labs.movieupdate.model.ShowsList;
import id.hw.labs.movieupdate.model.TrailerList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pad on 8/5/2017.
 */

public interface ApiInterface {
    final String API_KEY = "12cfc7b96a01da0e9177f493264b1c45";
    public static String BASE_IMG_URL = "https://image.tmdb.org/t/p/w185";
    public static String BASE_IMG_MED = "https://image.tmdb.org/t/p/w342";
    public static String BASE_BACK_URL = "https://image.tmdb.org/t/p/w500";

    @GET("movie/popular?api_key=" + API_KEY)
    Call<MovieList> getPopular(@Query("page") int page);

    @GET("movie/top_rated?api_key=" + API_KEY)
    Call<MovieList> getTopRated(@Query("page") int page);

    @GET("movie/upcoming?api_key=" + API_KEY)
    Call<MovieList> getUpcoming(@Query("page") int page);

    @GET("movie/now_playing?api_key=" + API_KEY)
    Call<MovieList> getNowPlaying(@Query("page") int page);

    @GET("tv/airing_today?api_key=" + API_KEY)
    Call<ShowsList> getAiringToday(@Query("page") int page);

    @GET("tv/on_the_air?api_key=" + API_KEY)
    Call<ShowsList> getOnTv(@Query("page") int page);

    @GET("tv/top_rated?api_key=" + API_KEY)
    Call<ShowsList> getTopRatedShows(@Query("page") int page);

    @GET("tv/popular?api_key=" + API_KEY)
    Call<ShowsList> getPopularShows(@Query("page") int page);

    @GET("{type}/{id}/videos?api_key=" + API_KEY)
    Call<TrailerList> getTrailer(@Path("type") String type, @Path("id") int id);

    @GET("{type}/{id}/reviews?api_key=" + API_KEY)
    Call<ReviewList> getReview(@Path("type") String type, @Path("id") int id);

    @GET("{type}/{id}/credits?api_key=" + API_KEY)
    Call<CastList> getCast(@Path("type") String type, @Path("id") int id);

    @GET("genre/{type}/list?api_key=" + API_KEY)
    Call<GenreList> getGenre(@Path("type") String type);

    @GET("search/movie?api_key=" + API_KEY)
    Call<MovieList> getSearch(@Query("query") String q);

    @GET("search/tv?api_key=" + API_KEY)
    Call<ShowsList> getSearchShows(@Query("query") String q);

    @GET("tv/{id}/season/{season}?api_key=" + API_KEY)
    Call<Season> getSeason(@Path("id") int id, @Path("season") int season);

    @GET("person/{id}?api_key=" + API_KEY)
    Call<CastDetails> getCast(@Path("id") int id);

    @GET("person/{id}/movie_credits?api_key=" + API_KEY)
    Call<MovieCredits> getMovieCredit(@Path("id") int id);

    @GET("person/{id}/tv_credits?api_key=" + API_KEY)
    Call<ShowCredits> getShowCredit(@Path("id") int id);
}
