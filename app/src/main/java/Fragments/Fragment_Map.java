package Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.cardsgame.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import Callbacks.Callback_Map;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {
    //private MapView map_main;
    private GoogleMap map;
    private Callback_Map callBack_map;

    public void setCallBack_bottom(Callback_Map callBack_map) {
        this.callBack_map = callBack_map;
    }

    public void addMarker(double lat, double lon) {
        ///add marker to map
        LatLng current = new LatLng(lat, lon);
        map.addMarker(new MarkerOptions()
                .position(current)
                .title("New Marker"));
        map.moveCamera(CameraUpdateFactory.newLatLng(current));
    }



    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_Map");
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //initiate map
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_main);
        if(supportMapFragment != null)
            supportMapFragment.getMapAsync(this);
        return view;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        map = googleMap;
        map.clear();
        //map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //map.setMyLocationEnabled(true);
    }

    @Override
    public void onPause() {
       // map_main.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        //map_main.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //map_main.onLowMemory();
    }

}
