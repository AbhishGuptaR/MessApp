package com.example.android.sociohack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Background_AdminAdd extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;

    Background_AdminAdd(Context c){
        context = c;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://10.0.2.2/sociohack/adminadd.php";
        if(type.equals("add")){
            try {
                String name = params[1];
                String price = params[2];
                String imgurl = params[3];
                String session = params[4];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("price","UTF-8")+"="+URLEncoder.encode(price,"UTF-8")+"&"
                        +URLEncoder.encode("url","UTF-8")+"="+URLEncoder.encode(imgurl,"UTF-8")+"&"
                        +URLEncoder.encode("session","UTF-8")+"="+URLEncoder.encode(session,"UTF-8");;
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while ((line = bufferedReader.readLine())!=null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("Success")) {
            Toast.makeText(context,"Added!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"Food Aldready Exists",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }
}

