package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditService extends AppCompatActivity {
    RadioGroup services;
    String deletion;
    DatabaseReference d;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service);
        services = (RadioGroup) findViewById(R.id.list);
        d = FirebaseDatabase.getInstance().getReference().child("Services");
        Query q = d.orderByChild("serName");
        q.addListenerForSingleValueEvent(new ValueEventListener() {




            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot s1: snapshot.getChildren()){
                        String ser1 = s1.child("serName").getValue().toString();
                        RadioButton r = new RadioButton(EditService.this);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            r.setId(View.generateViewId());
                        }
                        r.setText(ser1);
                        services.addView(r);
                    }
                }else{
                    Toast.makeText(EditService.this, "empty database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void EditButton(View view){
        int id = services.getCheckedRadioButtonId();
        RadioButton rn = (RadioButton) findViewById(id);
        Intent intent = new Intent(EditService.this,CreateService.class);
        Bundle b = new Bundle();
        b.putString("serName",rn.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }


}


