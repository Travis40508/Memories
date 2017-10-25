package com.example.travistressler.memories.MemoryMapFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travistressler.memories.R;
import com.example.travistressler.memories.Util.Database.ImageDatabase;
import com.example.travistressler.memories.Util.Database.ImageEntity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by travistressler on 10/24/17.
 */

public class MemoryMapFragment extends Fragment implements MemoryMapView {

    private MemoryMapPresenter presenter;
    @BindView(R.id.map_view)
    public MapView mapView;
    private ImageDatabase database;
    private GoogleMap googleMapView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memory_map, container, false);
        ButterKnife.bind(this, view);
        database = ImageDatabase.getDatabase(getContext());
        presenter = new MemoryMapPresenter();
        presenter.attachView(this);
        mapView.onCreate(savedInstanceState);
        return view;
    }

    public static MemoryMapFragment newInstance() {
        Bundle args = new Bundle();
        MemoryMapFragment fragment = new MemoryMapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        presenter.onResume();
        mapView.onResume();
        super.onResume();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void loadMap() {
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMapView = googleMap;
                googleMapView.getUiSettings().setMyLocationButtonEnabled(true);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMapView.setMyLocationEnabled(true);

                try {
                    MapsInitializer.initialize(MemoryMapFragment.this.getActivity());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Updates the location and zoom of the MapView
                List<ImageEntity> imageEntityList = database.imageDao().getallImages();
                for(ImageEntity image : imageEntityList) {
                    if(image.getLat() != 0) {
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(image.getLat(), image.getLng())));
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(image.getLat(), image.getLng()), 8);
                        googleMapView.animateCamera(cameraUpdate);
                    }
                }
            }
        });
    }


}
