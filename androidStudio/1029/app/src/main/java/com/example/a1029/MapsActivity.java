package com.example.a1029;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double x;
    double y;
    String title;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //지도 xy는 int가 아니고 더블
        Intent intent = getIntent();
        x=Double.parseDouble(intent.getStringExtra("x"));
        y=Double.parseDouble(intent.getStringExtra("y"));
        //이름이뜨게
        title=intent.getStringExtra("title");
        //전화번호
        phone=intent.getStringExtra("phone");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        //y,x
        LatLng latLng = new LatLng(y, x);
        //마커위에 클릭하면 이름이 뜨게 만들어줌
        mMap.addMarker(new MarkerOptions().position(latLng).title(title+phone));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //지도확대
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }
}