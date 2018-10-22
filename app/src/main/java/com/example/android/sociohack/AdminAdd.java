package com.example.android.sociohack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminAdd extends AppCompatActivity {

    EditText foodname,foodprice,foodimage;
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Add Food");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);

        foodname = (EditText) findViewById(R.id.addfoodname);
        foodprice = (EditText) findViewById(R.id.addfoodprice);
        foodimage = (EditText) findViewById(R.id.addfoodurl);
        dropdown = (Spinner) findViewById(R.id.sessiondropdown);
        String[] sessions = new String[]{"Morning","Afternoon","Night"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,sessions);
        dropdown.setAdapter(adapter);

    }

    public void onSubmit(View view){
        String name = foodname.getText().toString();
        String price = foodprice.getText().toString();
        String url = foodimage.getText().toString();
        String session = dropdown.getSelectedItem().toString();
        String type = "add";

            Background_AdminAdd backgroundWorker = new Background_AdminAdd(this);
            backgroundWorker.execute(type, name, price, url, session);

            foodname.setText("");
            foodprice.setText("");
            foodimage.setText("");

    }
}
