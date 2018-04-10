package com.example.bahroel.consumapifrominternet;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bahroel.consumapifrominternet.Model.ItunesStuff;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtValType;
    TextView txtValArtistName;
    TextView txtValKind;
    TextView txtValColName;
    TextView txtValTrackName;
    ImageView img;
    String imgUrl;
    Button btnGetJSON;
    TextView txtDownload;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValType = (TextView) findViewById(R.id.txtValType);
        txtValArtistName = (TextView) findViewById(R.id.txtValArtisName);
        txtValKind = (TextView) findViewById(R.id.txtValKind);
        txtValColName = (TextView) findViewById(R.id.txtValColName);
        txtValTrackName = (TextView) findViewById(R.id.txtValTrackName);
        img = (ImageView) findViewById(R.id.img);
        btnGetJSON = (Button) findViewById(R.id.btnGetJSON);

        btnGetJSON.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {
        JSONItunesStuffTask jsonItunesStuffTask = new JSONItunesStuffTask(MainActivity.this);
        jsonItunesStuffTask.execute();
    }


    private class JSONItunesStuffTask extends AsyncTask<String, Void, ItunesStuff>{
        Context context;
        ProgressDialog progressDialog;

        public JSONItunesStuffTask(Context context){
            this.context = context;
            progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setTitle("Download From Itunes . . .");
            progressDialog.show();
        }

        @Override
        protected ItunesStuff doInBackground(String... params) {
            ItunesStuff itunesStuff = new ItunesStuff();
            ItunesHTTPClient itunesHTTPClient = new ItunesHTTPClient();
            String data = (itunesHTTPClient.getItunesStuffData());


            try {
                itunesStuff = JsonItunesParser.getItunesStuff(data);
                imgUrl = itunesStuff.getArtistViewURL();
                bitmap = (itunesHTTPClient.getBitmapFromURL(imgUrl));
            }catch (Throwable t){
                t.printStackTrace();
            }
            return itunesStuff;
        }

        @Override
        protected void onPostExecute(ItunesStuff itunesStuff) {
            super.onPostExecute(itunesStuff);

            txtValType.setText(itunesStuff.getType());
            txtValArtistName.setText(itunesStuff.getArtistName());
            txtValKind.setText(itunesStuff.getKind());
            txtValColName.setText(itunesStuff.getCollectionName());
            txtValTrackName.setText(itunesStuff.getTrackName());
            img.setImageBitmap(bitmap);

            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }


        }
    }
}
