package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.content.Intent;
import android.os.Bundle;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateService extends AppCompatActivity {
    Button createButton;
    EditText name, rate;
    TextView serName;
    DatabaseReference serOnDat, serWasDat;
    CheckBox firstName, lastName, dateOfBirth, address, email, licenseType, pickupDate, pickupTime,returnDate,returnTime,
    maximumNofKM,areaOfTrucks,movingStartLoc, movingEndLoc,numberOfMovers,numberOfBoxes, carType;
    String nameOfSer, rateOfSer, firstN, lastN, birthD, emailC, licenseT,pickD, pickT, returnD, returnT,
    maxKM, areaT, movStartLoc, movEndLoc, nMovers,nBoxes, carT,modifyName;
    Bundle b;
    Boolean status;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_service);

        name = (EditText) findViewById(R.id.serviceName);
        rate = (EditText) findViewById(R.id.serviceRate);

        createButton = (Button) this.findViewById(R.id.createButton1);
        firstName = (CheckBox) findViewById(R.id.nameFirst);
        lastName = (CheckBox) findViewById(R.id.nameLast);
        dateOfBirth = (CheckBox) findViewById(R.id.birthDate);
        address = (CheckBox) findViewById(R.id.addressCust);
        email = (CheckBox) findViewById(R.id.emailCust);
        licenseType = (CheckBox) findViewById(R.id.typeOfLicense);
        pickupDate = (CheckBox) findViewById(R.id.dateOfPick);
        pickupTime = (CheckBox) findViewById(R.id.timeOfPick);
        returnDate = (CheckBox) findViewById(R.id.dateOfRet);
        returnTime = (CheckBox) findViewById(R.id.timeOfRet);
        maximumNofKM = (CheckBox) findViewById(R.id.maxNumKm);
        areaOfTrucks = (CheckBox) findViewById(R.id.truckArea);
        movingStartLoc = (CheckBox) findViewById(R.id.startMovLoc);
        movingEndLoc = (CheckBox) findViewById(R.id.endMovLoc);
        numberOfMovers = (CheckBox) findViewById(R.id.moverNum);
        numberOfBoxes = (CheckBox) findViewById(R.id.boxesNum);
        carType = (CheckBox) findViewById(R.id.typeOfCar);


        serName = (TextView) findViewById(R.id.serName);
        serOnDat = FirebaseDatabase.getInstance().getReference().child("Services");

        b = getIntent().getExtras();
        status = b != null;

        if (!status) {
            serName.setText("Add a new Service");
            createButton.setText("Create service");
        } else {
            edit();
        }


        createButton.setOnClickListener(view -> {
            createButton.setText("Creating");
            nameOfSer = name.getText().toString().trim();
            rateOfSer = rate.getText().toString().trim();
            if (nameOfSer.length() <= 2) {
                Toast.makeText(CreateService.this, "Service name is too short!", Toast.LENGTH_LONG).show();
                return;
            }
            if (!nameOfSer.matches("[a-zA-Z][a-zA-Z ]+")) {
                Toast.makeText(CreateService.this, "Service name should contain letters only", Toast.LENGTH_LONG).show();
                return;
            }
            if(!rateOfSer.matches("[0-9]+")){
                Toast.makeText(CreateService.this, "Service rate should contain numbers only.", Toast.LENGTH_LONG).show();
            }
            int serRate = Integer.parseInt(rateOfSer);
            if(serRate < 0){
                Toast.makeText(CreateService.this, "Service rate should not be negative.", Toast.LENGTH_LONG).show();
            }

            if (!status) {
                Query q = serOnDat.orderByChild("serName").equalTo(nameOfSer);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists() && snapshot.hasChildren()) {
                            Toast.makeText(CreateService.this, "Duplicate error", Toast.LENGTH_LONG).show();

                        } else {
                            serOnDat.push().setValue(settingUpSer(nameOfSer, rateOfSer));
                            Toast.makeText(CreateService.this, "Successful ON CREATING", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            } else {
                serWasDat.setValue(settingUpSer(nameOfSer, rateOfSer));
                Toast.makeText(CreateService.this, "Modification Successful", Toast.LENGTH_SHORT).show();

            }
            Intent intent = new Intent(CreateService.this, AdministratorPage.class);
            startActivity(intent);
        });
    }



    private void edit(){
        serName.setText("Modify service");
        //createButton.setText("Modify");
        modifyName = b.getString("serName");
        name.setText(modifyName);

        Query q = serOnDat.orderByChild("serName").equalTo(modifyName);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot s1 : snapshot.getChildren()){
                        serWasDat = s1.getRef();
                        name.setText(s1.child("serName").getValue().toString());
                        rate.setText(s1.child("rate").getValue().toString());
                        firstName.setChecked(Integer.parseInt(s1.child("firstName").getValue().toString()) == 1);
                        lastName.setChecked(Integer.parseInt(s1.child("lastName").getValue().toString()) == 1);
                        dateOfBirth.setChecked(Integer.parseInt(s1.child("birthDate").getValue().toString()) == 1);
                        address.setChecked(Integer.parseInt(s1.child("address").getValue().toString()) == 1);
                        email.setChecked(Integer.parseInt(s1.child("email").getValue().toString())==1);
                        licenseType.setChecked(Integer.parseInt(s1.child("licenseType").getValue().toString())==1);
                        pickupDate.setChecked(Integer.parseInt(s1.child("pickUpDate").getValue().toString())==1);
                        pickupTime.setChecked(Integer.parseInt(s1.child("pickUpTime").getValue().toString())==1);
                        returnDate.setChecked(Integer.parseInt(s1.child("returnDate").getValue().toString())==1);
                        returnTime.setChecked(Integer.parseInt(s1.child("returnTime").getValue().toString())==1);
                        maximumNofKM.setChecked(Integer.parseInt(s1.child("maxNumOfKM").getValue().toString())==1);
                        areaOfTrucks.setChecked(Integer.parseInt(s1.child("areaOfTrucks").getValue().toString())==1);
                        movingStartLoc.setChecked(Integer.parseInt(s1.child("movingStartLoc").getValue().toString())==1);
                        movingEndLoc.setChecked(Integer.parseInt(s1.child("movingEndLoc").getValue().toString())==1);
                        numberOfMovers.setChecked(Integer.parseInt(s1.child("numberOfMovers").getValue().toString())==1);
                        numberOfBoxes.setChecked(Integer.parseInt(s1.child("numberOfBoxes").getValue().toString())==1);
                        carType.setChecked(Integer.parseInt(s1.child("typeOfCar").getValue().toString())==1);
                        Toast.makeText(CreateService.this,"Successfully created",Toast.LENGTH_LONG).show();



                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private TypeService settingUpSer(String serviceName, String serviceRate){
        if(firstName.isChecked()){
            firstN = "1";
        }else{
            firstN = "0";
        }
        if(lastName.isChecked()){
            lastN = "1";
        }else{
            lastN = "0";
        }
        if(dateOfBirth.isChecked()){
            birthD = "1";
        }else{
            birthD= "0";
        }
        if(email.isChecked()){
            emailC = "1";
        }else{
            emailC = "0";
        }
        if(licenseType.isChecked()){
            licenseT = "1";
        }else{
            licenseT = "0";
        }
        if(pickupDate.isChecked()){
            pickD = "1";
        }else{
            pickD = "0";
        }
        if(pickupTime.isChecked()){
            pickT= "1";
        }else{
            pickT = "0";
        }
        if(returnDate.isChecked()){
            returnD = "1";
        }else{
            returnD = "0";
        }
        if(returnTime.isChecked()){
            returnT = "1";
        }else{
            returnT = "0";
        }
        if(maximumNofKM.isChecked()){
            maxKM = "1";
        }else{
            maxKM = "0";
        }
        if(areaOfTrucks.isChecked()){
            areaT = "1";
        }else{
            areaT = "0";
        }
        if(movingStartLoc.isChecked()){
            movStartLoc = "1";
        }else{
            movStartLoc = "0";
        }
        if(movingEndLoc.isChecked()){
            movEndLoc = "1";
        }else{
            movEndLoc = "0";
        }
        if(numberOfMovers.isChecked()){
            nMovers = "1";
        }else{
            nMovers= "0";
        }
        if(numberOfBoxes.isChecked()){
            nBoxes = "1";
        }else{
            nBoxes = "0";
        }
        if(carType.isChecked()){
            carT = "1";
        }else{
            carT = "0";
        }
        return new TypeService(serviceName, serviceRate,firstN,lastN,birthD,emailC,licenseT,pickD,pickT,returnD,returnT,maxKM,areaT,movStartLoc,movEndLoc,nMovers,nBoxes,carT);

    }

}
