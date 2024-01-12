package com.example.boleraapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.boleraapp.databinding.ActivityMapaGoogleBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapaGoogle extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private ActivityMapaGoogleBinding binding;
    private EditText txtLatitud, txtLongitud, txtDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapaGoogleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        txtDireccion = findViewById(R.id.txtDireccion);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(41.62578142382358, -4.76472906768322);
        this.mMap.setOnMapClickListener(this);
        this.mMap.setOnMapLongClickListener(this);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Bowling Zool"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText("" + latLng.longitude);
        //Obtenemos el nombre de la calle usando geocodificacion
        getAddressFromLatLng(latLng);

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtLatitud.setText("" + latLng.latitude);
        txtLongitud.setText("" + latLng.longitude);
        //Obtenemos el nombre de la calle usando geocodificacion
        getAddressFromLatLng(latLng);
    }

    private void getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (addresses != null && ((List<?>) addresses).size() > 0) {
                Address address = addresses.get(0);
                String streetAddress = address.getAddressLine(0);
                // Puedes utilizar el nombre de la calle como desees
                // Aquí podrías mostrarlo en un TextView o realizar otras acciones
                Log.d("Direccion", "Direccion: " + streetAddress);
                txtDireccion.setText(streetAddress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}