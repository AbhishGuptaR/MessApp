package com.example.android.sociohack;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public int finalprice=0;
    public Cursor c;
    private Context context;
    public ArrayList<itemPOJO> list;
    private List<MyData> my_data;
    MyDB  myDB ;


    public CartAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        list=new ArrayList<itemPOJO>();
        this.my_data = my_data;
        myDB = new MyDB(context);
        c=myDB.getReadableDatabase().rawQuery("select * from menu",null);
        while(c.moveToNext()){
            itemPOJO it=new itemPOJO();
            Log.e("DB Error",c.getString(c.getColumnIndex("item")));
            it.setItem(c.getString(c.getColumnIndex("item")));
            it.setPrz(c.getString(c.getColumnIndex("price")));
            //Log.e("Qprice",c.getString(c.getColumnIndex("price")));
            it.setQty(c.getString(c.getColumnIndex("qty")));
            it.setIdd(c.getString(c.getColumnIndex("id")));
            if(!c.getString(c.getColumnIndex("qty")).equals("0"))
            list.add(it);
        }
        Iterator<itemPOJO> it=list.iterator();
        while(it.hasNext()){
            Log.e("Object",it.next().toString());
        }


    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartcard,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("outpost",position+"");
        if (position>=0){
            Log.e("Postion ",position+"");
            holder.cartitem.setText(list.get(position).getItem());
            holder.cartquantity.setText(list.get(position).getQty());
            long tot=Integer.valueOf(list.get(position).getPrz().replace(" ",""))*Integer.valueOf(list.get(position).getQty());
            finalprice+=tot;
            holder.cartprice.setText(String.valueOf(tot));
        }

    Log.e("AdapterlaFinalPrice:",String.valueOf(finalprice));
        String fprice = String.valueOf(finalprice);
        Intent intent = new Intent("custom-message");
        //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
        intent.putExtra("finalprice",fprice);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }





    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView cartitem,cartquantity,cartprice;

        public ViewHolder(View itemView) {
            super(itemView);

            cartitem = (TextView) itemView.findViewById(R.id.cartitem);
            cartquantity = (TextView) itemView.findViewById(R.id.cartquantity);
            cartprice = (TextView) itemView.findViewById(R.id.cartprice);

        }

    }



}


