package com.example.bahroel.consumapifrominternet.Model;

/**
 * Created by Bahroel on 10/04/2018.
 */

public class ItunesStuff {
    String type;
    String kind;
    String artistName;
    String collectionName;
    String trackName;
    String artistViewURL;

    // setter and getter for ItunesStuff Object
    public String getType() { return type;}

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtistViewURL() {
        return artistViewURL;
    }

    public void setArtistViewURL(String artistViewURL) {
        this.artistViewURL = artistViewURL;
    }
}
