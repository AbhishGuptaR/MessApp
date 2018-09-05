package com.example.android.sociohack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CartActivity extends AppCompatActivity  {
    public MyDB myDB;
    private TextView item,qty,prz;
    private RecyclerView recyclerView;
    public CartAdapter cartAdapter;
    private List<MyData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Cart");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = (RecyclerView) findViewById(R.id.cartrecylerview);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);



        cartAdapter = new CartAdapter(this,dataList);
        recyclerView.setAdapter(cartAdapter);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));

        Button pay = (Button) findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this,"Payments option will be integrated soon.",Toast.LENGTH_LONG).show();
            }
        });






//item=(TextView)findViewById(R.id.cartitem);
//        qty=(TextView)findViewById(R.id.cartquantity);
//        prz=(TextView)findViewById(R.id.cartprice);
//        myDB=new MyDB(this);
//        Cursor c=myDB.getMenu();
//        if(c!=null) {
//            while (c.moveToNext()) {
//                item.setText(c.getString(c.getColumnIndex("item")));
//                qty.setText(c.getString(c.getColumnIndex("qty")));
//                prz.setText(c.getString(c.getColumnIndex("price")));
//            }
//        }
    }
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String fprice = intent.getStringExtra("finalprice");
            TextView finalcost = (TextView) findViewById(R.id.cartfinalprice);
            finalcost.setText(""+fprice);
        }
    };

}
