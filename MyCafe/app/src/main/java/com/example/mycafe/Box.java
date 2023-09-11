package com.example.mycafe;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Box extends AppCompatActivity {

//    Context context = this;
    Button btn_box;
//    TextView t1,t2;
//    int x_last = 0;
//    String x_select;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_box);

//        btn_box = (Button)findViewById(R.id.btn_box);
//        t1 = (TextView)findViewById(R.id.editTextTextPersonName);
//        t2 = (TextView)findViewById(R.id.editTextTextPersonName2);
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance(); //連線firebase
//        DatabaseReference myRef = database.getReference("data_text");
//        final DatabaseReference myRef2 = myRef.child("data 01");
//        firebase_select(myRef2); //讀取資料

        btn_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "已送出！", Toast.LENGTH_SHORT).show();
//                x_last += 1;
//                myRef2.child(String.valueOf(x_last)).setValue(new TextString(t1.getText().toString(),t2.getText().toString()));
//                firebase_select(myRef2);
            }
        });

    }



//    private void firebase_select(DatabaseReference db) {
//        final List<Map<String, Object>> items = new ArrayList<>();
//        db.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot ds:dataSnapshot.getChildren()){
//                    TextString user_data = ds.getValue(TextString.class);
//                    Map<String,Object> item = new HashMap<String,Object>();
//                    item.put("id",ds.getKey());
//                    item.put("z1",user_data.getZ1());
//                    item.put("z2",user_data.getZ2());
//                    items.add(item);
//                    x_last = Integer.parseInt(ds.getKey());
//                }
//                SimpleAdapter SA = new SimpleAdapter(context , items, R.layout.textstring, new String[]{"id","z1","z2"},new int[]{R.id.text11,R.id.text22,R.id.text33});
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }


}
