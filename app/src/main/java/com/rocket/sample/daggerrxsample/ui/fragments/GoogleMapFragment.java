package com.rocket.sample.daggerrxsample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rocket.sample.daggerrxsample.R;
import com.rocket.sample.daggerrxsample.model.DeliveryDetails;
import com.rocket.sample.daggerrxsample.model.LocationDetails;

/**
 *  Still in progress
 */
@Deprecated
public class GoogleMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DeliveryDetails mDeliveryDetails;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_maps, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Bundle bundle1 = getArguments();
/*        if (bundle1 != null && bundle1.containsKey(DELIVERY_DETAILS)) {
            mDeliveryDetails = getArguments().getParcelable(DELIVERY_DETAILS);
        }*/
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LocationDetails locationDetails = mDeliveryDetails.getLocationDetails();
        if (locationDetails != null) {
            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(locationDetails.getLat(), locationDetails.getLang());
            mMap.addMarker(new MarkerOptions().position(sydney).title(locationDetails.getAddress()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
}
