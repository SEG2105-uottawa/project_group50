package com.example.byblos;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
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
public class DeletingService extends AppCompatActivity{
    RadioGroup services;
    String deletion;
    DatabaseReference d;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service_2);
        services = (RadioGroup) findViewById(R.id.list);
        d = FirebaseDatabase.getInstance().getReference().child("Services");
        Query q = d.orderByChild("serName");
        q.addListenerForSingleValueEvent(new ValueEventListener() {




            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot s1: snapshot.getChildren()){
                        String ser1 = s1.child("serName").getValue().toString();
                        RadioButton r = new RadioButton(DeletingService.this);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            r.setId(View.generateViewId());
                        }
                        r.setText(ser1);
                        services.addView(r);
                    }
                }else{
                    Toast.makeText(DeletingService.this, "empty database", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void RemoveButton(View view){
        int id = services.getCheckedRadioButtonId();
        RadioButton r = (RadioButton) findViewById(id);
        deletion = r.getText().toString();
        Query q = d.orderByChild("serName");
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot s1: snapshot.getChildren()) {
                        String ser1 = s1.child("serName").getValue().toString();
                        if(deletion.equals(ser1)){
                            String k = s1.getKey();
                            d.child(k).removeValue();
                            Intent intent = new Intent (DeletingService.this, AdministratorPage.class);
                            startActivity(intent);
                            Toast.makeText(DeletingService.this,"Deleted",Toast.LENGTH_LONG).show();
                        }

                    }
                } else {
                    Toast.makeText(DeletingService.this,"Database empty",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
