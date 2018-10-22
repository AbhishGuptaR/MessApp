package com.example.android.sociohack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MenuActivity extends AppCompatActivity {

    public int quantity;
    private RecyclerView recyclerView;
    private ListView listView;
    private CustomAdapter customAdapter;
    private List<MyData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Today's Menu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        dataList = new ArrayList<>();
        load_data_from_server(0);


        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        customAdapter = new CustomAdapter(this,dataList);
        recyclerView.setAdapter(customAdapter);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == dataList.size()-1){
//                    load_data_from_server(dataList.get(dataList.size()-1).getId());
//                }
//
//            }
//        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.cart:
                startActivity(new Intent(this,CartActivity.class));
                return true;
            case R.id.report:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts( "mailto","abhishekguptaa3@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Error report");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Type your problem here..");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void incrementQuantity(View view){

        quantity+=1;
        display(quantity);

    }
    public void decrementQuantity(View view){
        if(quantity>0) {
            quantity -= 1;
            display(quantity);
        }
        else{
            display(0);
        }
    }
    public void display(int num){
        TextView quantity = (TextView) findViewById(R.id.quantitytext);
        quantity.setText(""+num);
    }


    private void load_data_from_server(final int id){

        @SuppressLint("StaticFieldLeak") AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                Date d=new Date();
                SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
                Log.e("Date",sdf.format(d).toString());
                Date de=new Date();
                SimpleDateFormat sddf=new SimpleDateFormat("HH");
                Log.e("Time",sddf.format(de).toString());
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url("http://10.0.2.2/sociohack/script_fetch.php?id=0"+id+"&date="+sdf.format(d).toString()+"&time="+sddf.format(de).toString()).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();

                    JSONArray jsonArray = new JSONArray(response.body().string());

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        MyData data = new MyData(jsonObject.getInt("id"),jsonObject.getString("name"),jsonObject.getString("imgsrc"),jsonObject.getInt("price"));

                       dataList.add(data);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of Content");
                }
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid) {
                customAdapter.notifyDataSetChanged();
            }
        };
        task.execute(id);

    }
}
