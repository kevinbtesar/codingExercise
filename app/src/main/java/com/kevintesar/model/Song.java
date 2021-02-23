package com.kevintesar.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by frank on 12/13/16.
 */

public class Song {

    @SerializedName("ArtistName")
    @Expose
    private String artistName;
    @SerializedName("TrackName")
    @Expose
    private String trackName;
    @SerializedName("ReleaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("PrimaryGenreName")
    @Expose
    private String primaryGenreName;
    @SerializedName("TrackPrice")
    @Expose
    private String trackPrice;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;

    /**
     * No args constructor for use in serialization
     *
     */
    public Song() {}

    /**
     *
     * @param artistName
     * @param trackName
     * @param releaseDate
     * @param primaryGenreName
     * @param trackPrice
     * @param imageUrl
     */
    public Song(String artistName, String trackName, String releaseDate, String primaryGenreName, String trackPrice, String imageUrl) {
        super();
        this.artistName = artistName;
        this.trackName = trackName;
        this.releaseDate = releaseDate;
        this.primaryGenreName = primaryGenreName;
        this.trackPrice = trackPrice;
        this.imageUrl = imageUrl;
    }

    /**
     *
     * @return
     * The Artist Name
     */
    public String getArtistName() {
        return artistName;
    }
    /**
     *
     * @param artistName
     * The Title
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     *
     * @return
     * The Track Name
     */
    public String getTrackName() {
        return trackName;
    }
    /**
     *
     * @param trackName
     * The Year
     */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     *
     * @return
     * The Release Date
     */
    public String getReleaseDate() {
        return releaseDate;
    }
    /**
     *
     * @param releaseDate
     * The Release Date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     *
     * @return
     * The primary genre name
     */
    public String getPrimaryGenreName() {
        return primaryGenreName;
    }
    /**
     *
     * @param primaryGenreName
     * The Director
     */
    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    /**
     *
     * @return
     * The track price
     */
    public String getTrackPrice() {
        return trackPrice;
    }
    /**
     *
     * @param trackPrice
     * The track price
     */
    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }


    /**
     *
     * @return
     * The image url
     */
    public String getImageUrl() {
        return imageUrl;
    }
    /**
     *
     * @param imageUrl
     * The image url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}

