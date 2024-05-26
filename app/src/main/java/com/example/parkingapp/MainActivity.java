package com.example.parkingapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parkingapp.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.LinkedList;

import model.Reservation;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addReservation;
    RecyclerView myrecycler;
    FirebaseFirestore db;
    LinkedList<Reservation> reservations;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);
        addReservation = (FloatingActionButton) findViewById(R.id.fab);
        db = FirebaseFirestore.getInstance();
        reservations = new LinkedList<>();
        myrecycler = (RecyclerView) findViewById(R.id.recycler);
        ReservationAdapter Myadapter = new ReservationAdapter(reservations, MainActivity.this);
        addReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateReservation.class);
                startActivity(intent);
            }
        });

        getReservations();
        myrecycler.setAdapter(Myadapter);

    }

    private void getReservations(){
        db.collection("reservation").whereEqualTo("client", "rachidisadek@gmail.com").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                // Process each document
                                Reservation reser = new Reservation(document.getString("client"), document.getString("parking"), document.getId(), document.getBoolean("payed"), document.getString("startHour"), document.getString("endHour"), document.getString("startMinutes"), document.getString("endMinutes"), document.getString("day"), document.getString("month"), document.getString("year"));
                                reservations.add(reser);
                                Log.d("Document ID", document.getId());
                            }
                            myrecycler.setHasFixedSize(true);
                            // use a linear layout manager
                            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                            myrecycler.setLayoutManager(layoutManager);
                            // specify an adapter (see also next example)
                            ReservationAdapter myAdapter = new ReservationAdapter(reservations, MainActivity.this);
                            myrecycler.setAdapter(myAdapter);

                        } else {
                            Toast.makeText(MainActivity.this, "Erreur lors de la récupération des réservations", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}