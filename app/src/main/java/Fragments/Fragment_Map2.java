package Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.cardsgame.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map2 extends Fragment implements OnMapReadyCallback {
    private MapView map_main;
    private GoogleMap map;

    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView - Fragment_List");

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        findViews(view);
        map_main.onCreate(savedInstanceState);

        initGoogleMap(savedInstanceState);

        return view;
    }

    private void findViews(View view) {
        map_main = view.findViewById(R.id.map_main);

        }

    private void initGoogleMap(Bundle savedInstanceState){
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        map_main.onCreate(mapViewBundle);

        map_main.getMapAsync( this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
        LatLng sydney = new LatLng(-33.852, 151.211);

        map.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
    }
    @Override
    public void onPause() {
        map_main.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        map_main.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map_main.onLowMemory();
    }

}
