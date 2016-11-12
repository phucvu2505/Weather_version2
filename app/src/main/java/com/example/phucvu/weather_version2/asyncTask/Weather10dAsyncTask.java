package com.example.phucvu.weather_version2.asyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by phucvu on 11/12/16.
 */

public class Weather10dAsyncTask extends AsyncTask<Void, Void, String> {

    public interface CallBack{
        public void onFinish(String body);
    }

    private CallBack callBack;

    private ProgressDialog mProgressDialog;

    private double[] locationData;
    private String body;

    public Weather10dAsyncTask(Context context, double[] locationData){
        this.locationData = locationData;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Loading.......");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/forecast/daily?lat="+locationData[0]+"&lon=1"+locationData[1]+"&cnt=16&mode=json&appid=fee985a5cfe4764ab20deed98ff488aa")
                .addHeader("Accept", "application/json")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if(response.isSuccessful()){
                body = response.body().string();
                Log.e("Json", body);
                return body;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String body) {
        super.onPostExecute(body);
        mProgressDialog.dismiss();
        if(callBack!=null){
            callBack.onFinish(body);
        }
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}
