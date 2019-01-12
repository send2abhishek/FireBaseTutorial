package com.example.abhishekaryan.firebasetutorial;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.abhishekaryan.firebasetutorial.Adapter.CustomAdapter;
import com.example.abhishekaryan.firebasetutorial.utils.DataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText value;
    private Button submit;
    private DatabaseReference reference;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference=FirebaseDatabase.getInstance().getReference("Model-Data");
        name=(EditText)findViewById(R.id.activity_main_editText_name_filed);
        value=(EditText)findViewById(R.id.activity_main_editText_name_value);
        listView=(ListView)findViewById(R.id.activity_main_listview);
        submit=(Button)findViewById(R.id.activity_main_sub_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setupDataBase(name.getText().toString(),value.getText().toString());
                Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDataBase(String name, String value) {


        String id=reference.push().getKey();
        DataModel model=new DataModel(id,name,value);

        reference.child(id).setValue(model);


    }

    @Override
    protected void onStart() {
        super.onStart();

         final CustomAdapter customAdapter=new CustomAdapter(this);
         //customAdapter.AddItem(new DataModel("123","Abhishek","aryan"));


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    DataModel dataModel=dataSnapshot1.getValue(DataModel.class);
                    customAdapter.AddItem(dataModel);

                   String msg=(String)dataSnapshot1.child("email").getValue();
                    Log.d("Abhishek-aryan", "onDataChange: "+msg);
                }
                listView.setAdapter(customAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
