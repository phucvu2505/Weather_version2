package com.example.phucvu.weather_version2.asyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import com.example.phucvu.weather_version2.MainActivity;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by phucvu on 11/10/16.
 */

public class CurrentWeatherAsyncTask extends AsyncTask<Void, Void, String> {

    public interface CallBack{
        public void onFinish(String body);
    }


    private CallBack callBack;

    private ProgressDialog mProgressDialog;

    private Location location;
    private String body;

    public CurrentWeatherAsyncTask(Context context, Location location) {
        this.location = location;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("Loading.....");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request1 = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?lat="+location.getLatitude()+"&lon="+location.getLongitude()+"&APPID=fee985a5cfe4764ab20deed98ff488aa")
                .addHeader("Accept", "application/json")
                .build();
        Request request2 = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/forecast?lat="+location.getLatitude()+"&lon="+location.getLongitude()+"&appid=fee985a5cfe4764ab20deed98ff488aa")
                .addHeader("Accept", "application/json")
                .build();
        try{
            Response response1 = okHttpClient.newCall(request1).execute();
            if(response1.isSuccessful()){
                body = response1.body().string();
                Log.e("Json", body);
            }
            Response response2 = okHttpClient.newCall(request2).execute();
            if(response2.isSuccessful()){
                body = body+ MainActivity.STRING_CUT_JSON+response2.body().string();
                Log.e("Json", body);
                return body;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String body) {
        super.onPostExecute(body);
        mProgressDialog.dismiss();
        if(callBack != null){
            callBack.onFinish(body);
        }

    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }
}