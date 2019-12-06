package com.example.inclass13_group22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.ArrayList;

public class AddTripActivity extends AppCompatActivity {
    EditText et_TripName,et_Search;
    Button btn_Search, btn_Add_Trip;
    ArrayList<String> TripIds = new ArrayList<>();
    ArrayList<String> Trip = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        et_Search=findViewById(R.id.et_Search);
        et_TripName=findViewById(R.id.et_TripName);
        btn_Add_Trip=findViewById(R.id.btn_AddTrip);
        btn_Search=findViewById(R.id.btn_Search);
        Places.initialize(getApplicationContext(), "AIzaSyCZjhO_LUGxf2U2cNDSGufD1cPWz2pF4Ww");
        final PlacesClient placesClient = Places.createClient(this);
        btn_Search.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                Log.i("test","Clicked");

                return false;
            }
        });
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("test","Clicked");

                AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
                RectangularBounds bounds = RectangularBounds.newInstance(new LatLng(-33.880490, 151.184363),new LatLng(-33.858754, 151.229596));
                FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder().setTypeFilter(TypeFilter.CITIES).setLocationBias(bounds).setSessionToken(token).setQuery(et_Search.getText().toString()).build();
                placesClient.findAutocompletePredictions(request).addOnSuccessListener(new OnSuccessListener<FindAutocompletePredictionsResponse>() {
                    @Override
                    public void onSuccess(FindAutocompletePredictionsResponse findAutocompletePredictionsResponse) {

                        for (AutocompletePrediction prediction : findAutocompletePredictionsResponse.getAutocompletePredictions()) {
//                            mResult.append(" ").append(prediction.getFullText(null) + "\n");
                            TripIds.add(prediction.getPlaceId());
                            Trip.add(prediction.getPrimaryText(null).toString());

                            //Toast.makeText(AddTripActivity.this, prediction.getPrimaryText(null) + "-" + prediction.getSecondaryText(null), Toast.LENGTH_SHORT).show();
                        }
                        Log.i("test", TripIds.toString());
                        Log.i("test", Trip.toString());
                        
                        AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddTripActivity.this, "API Call Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
