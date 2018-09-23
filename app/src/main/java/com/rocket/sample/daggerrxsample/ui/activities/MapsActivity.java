package com.rocket.sample.daggerrxsample.ui.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rocket.sample.daggerrxsample.R;
import com.rocket.sample.daggerrxsample.model.DeliveryDetails;
import com.rocket.sample.daggerrxsample.model.LocationDetails;
import com.rocket.sample.daggerrxsample.utils.Constants;
import com.rocket.sample.daggerrxsample.utils.ImageLoaderUtils;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private DeliveryDetails mDeliveryDetails;
    private RelativeLayout mDeliveryDescriptionContainer;
    private TextView mDescription;
    private ImageView mDeliveryImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        initViews();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(Constants.DELIVERY_DETAILS)) {
            mDeliveryDetails = bundle.getParcelable(Constants.DELIVERY_DETAILS);
        }
        updateDeliveryDescription();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initViews() {
        mDeliveryDescriptionContainer = findViewById(R.id.footer_layout);
        mDescription = findViewById(R.id.description);
        mDeliveryImage = findViewById(R.id.imageView);
        findViewById(R.id.progressBarContainer).setVisibility(View.GONE);
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
            LatLng markerLatLang = new LatLng(locationDetails.getLat(), locationDetails.getLang());
            mMap.addMarker(new MarkerOptions().position(markerLatLang).title(locationDetails.getAddress()));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerLatLang, 14f));
            mMap.setOnMarkerClickListener(this);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
    mDeliveryDescriptionContainer.setVisibility((mDeliveryDescriptionContainer.getVisibility() == View.VISIBLE ?
                    View.GONE : View.VISIBLE));

        return false;
    }

    private void updateDeliveryDescription() {
        if (mDeliveryDetails != null) {
            mDescription.setText(mDeliveryDetails.getDescription());
            ImageLoaderUtils.setThumbnailImage(mDeliveryImage, mDeliveryDetails.getImageUrl(), R.mipmap.ic_launcher);
        }
    }
}
