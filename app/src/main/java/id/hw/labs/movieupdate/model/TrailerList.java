package id.hw.labs.movieupdate.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pad on 8/6/2017.
 */

public class TrailerList {
    @SerializedName("id")
    private String id_trailer;

    @SerializedName("results")
    private List<Trailer> results;

    public String getId_trailer() {
        return id_trailer;
    }

    public List<Trailer> getResults() {
        return results;
    }
}
