package com.example.bahroel.consumapifrominternet;

import com.example.bahroel.consumapifrominternet.Model.ItunesStuff;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Bahroel on 10/04/2018.
 */

public class JsonItunesParser {

    public static ItunesStuff getItunesStuff(String data) throws JSONException{
        ItunesStuff itunesStuff = new ItunesStuff();
        JSONObject itunesStuffJsonObject = new JSONObject(data);

        // get JSON from JSONArray with key "results"
        JSONArray resultJsonArray = itunesStuffJsonObject.getJSONArray("results");
        // get one object in 0 index
        JSONObject artistObject = resultJsonArray.getJSONObject(0);
        // set the value of object using the value of JSONArray
        itunesStuff.setType(getString("wrapperType",artistObject));
        itunesStuff.setKind(getString("kind",artistObject));
        itunesStuff.setArtistName(getString("artistName",artistObject));
        itunesStuff.setCollectionName(getString("collectionName",artistObject));
        itunesStuff.setArtistViewURL(getString("artworkUrl100",artistObject));
        itunesStuff.setTrackName(getString("trackName",artistObject));

        return itunesStuff;
    }

    private static JSONObject getJsonObject(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getJSONObject(tagName);
    }

    private static String getString(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }
    private static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }
}
