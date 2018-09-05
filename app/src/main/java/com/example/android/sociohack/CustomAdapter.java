package com.example.android.sociohack;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Context context;
    private List<MyData> my_data;
MyDB myDB;
    public CustomAdapter(Context context, List<MyData> my_data) {

        myDB=new MyDB(context);
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menucard,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//holder.getAdapterPosition();
        holder.textView.setText(my_data.get(position).getName());
        holder.priceView.setText((" "+my_data.get(position).getPrice()));
        holder.id.setText((" "+my_data.get(position).getId()));

        Glide.with(context).load(my_data.get(position).getImgurl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public int quantity=0;
        public ImageView imageView;
        public TextView textView;
        public TextView qtv;
        public TextView priceView;
        public Button inc,dec;
public TextView id;
        public ViewHolder(final View itemView) {

            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.food_image);
            textView = (TextView) itemView.findViewById(R.id.food_name);
            priceView = (TextView) itemView.findViewById(R.id.price);
            id=(TextView)itemView.findViewById(R.id.iddd);
            qtv=(TextView)itemView.findViewById(R.id.quantitytext);
            inc=(Button)itemView.findViewById(R.id.increment);
            dec=(Button)itemView.findViewById(R.id.decrement);
            inc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity+=1;
                    qtv.setText(quantity+"");

                    //Toast.makeText(itemView.getContext(),qtv.getText().toString(),Toast.LENGTH_LONG).show();
                    myDB.insertMenu(id.getText().toString(),textView.getText().toString(),priceView.getText().toString(), String.valueOf(quantity));
                }
            });
            dec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(quantity>0) {
                        quantity -= 1;
                    }
                    else{
                        quantity=0;
                    }
                    qtv.setText(quantity+"");
                    //Toast.makeText(itemView.getContext(),qtv.getText().toString(),Toast.LENGTH_LONG).show();
                    myDB.insertMenu(id.getText().toString(),textView.getText().toString(),priceView.getText().toString(), String.valueOf(quantity));
                }
            });
        }
    }
}
