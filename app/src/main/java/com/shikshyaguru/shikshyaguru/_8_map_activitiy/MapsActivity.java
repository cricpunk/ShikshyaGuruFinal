package com.shikshyaguru.shikshyaguru._8_map_activitiy;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginFragment;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionCallback, GoogleMap.OnMapLongClickListener {

    private Context context;
    private GoogleMap mMap;
    private static final int LOCATION_REQUEST = 500;
    ArrayList<LatLng> listPoints;
    private String[] colors = {"#7C4DFF", "#B388FF", "#651FFF"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getApplicationContext();

        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // List to add origin and destination latlang
        listPoints = new ArrayList<>();

        // Get origin and destination first
        latLangCombinations();
        // Display default mode as driving mode when activity starts
        showDirection(listPoints.get(0), listPoints.get(1), TransportMode.DRIVING);
        // Initialize floating action buttons for more features
        initFloatingActionMenu();

        RelativeLayout currentView = findViewById(R.id.root_map_activity);
        PopupCollections.simpleSnackBar(currentView, "For manual direction please long press in map to set start and end location.", LoginFragment.COLOR_GREEN);

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

        mMap.getUiSettings().setZoomControlsEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);

            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.setOnMapLongClickListener(this);

    }

    private void latLangCombinations() {

        // Creating object of GPSTracer class
        GPSTracker gpsTracker = new GPSTracker(context);

        // Get and set current location as origin latlang
        listPoints.add(new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude()));

        try {

            List<Address> addressList;
            Geocoder geocoder = new Geocoder(this);
            addressList = geocoder.getFromLocationName("Islington college, Kathmandu 44600", 5);

            System.out.println("=======================================Outside=======================================");
            for (int i = 0; i < addressList.size(); i++) {
                System.out.println("==============================================================================");
                System.out.println(addressList.get(i));
            }
            //Address address = addressList.get(0);
            listPoints.add(new LatLng(27.708687, 85.325953));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initFloatingActionMenu() {

        FloatingActionMenu floatingActionMenu = findViewById(R.id.fam_map_activity);

        FloatingActionButton drivingMode = findViewById(R.id.fab_driving_mode);
        FloatingActionButton walkingMode = findViewById(R.id.fab_walking_mode);
        FloatingActionButton bicyclingMode = findViewById(R.id.fab_bicycling_mode);
        FloatingActionButton transitMode = findViewById(R.id.fab_transit_mode);

        drivingMode.setImageResource(R.drawable.ic_map_driving);
        walkingMode.setImageResource(R.drawable.ic_map_walking);
        bicyclingMode.setImageResource(R.drawable.ic_map_cycling);
        transitMode.setImageResource(R.drawable.ic_map_transit);

        drivingMode.setOnClickListener(floatingActionButtonClickListener);
        walkingMode.setOnClickListener(floatingActionButtonClickListener);
        bicyclingMode.setOnClickListener(floatingActionButtonClickListener);
        transitMode.setOnClickListener(floatingActionButtonClickListener);

        ViewGroup.LayoutParams layoutParams = floatingActionMenu.getLayoutParams();
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        floatingActionMenu.setLayoutParams(layoutParams);
        floatingActionMenu.setClosedOnTouchOutside(true);

    }

    private View.OnClickListener floatingActionButtonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_driving_mode:
                    mMap.clear();
                    showDirection(listPoints.get(0), listPoints.get(1), TransportMode.DRIVING);
                    break;
                case R.id.fab_walking_mode:
                    mMap.clear();
                    showDirection(listPoints.get(0), listPoints.get(1), TransportMode.WALKING);
                    break;
                case R.id.fab_bicycling_mode:
                    mMap.clear();
                    showDirection(listPoints.get(0), listPoints.get(1), TransportMode.BICYCLING);
                    break;
                case R.id.fab_transit_mode:
                    mMap.clear();
                    showDirection(listPoints.get(0), listPoints.get(1), TransportMode.TRANSIT);
                default:
                    break;
            }
        }
    };


    @Override
    public void onMapLongClick(LatLng latLng) {

        // Clear map first
        if (listPoints.size() == 0) {
            mMap.clear();
        }

        //Reset marker when already 2
        if (listPoints.size() == 2) {
            listPoints.clear();
            mMap.clear();
        }

        //Save first point select
        listPoints.add(latLng);
        //Create marker
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);

        if (listPoints.size() == 1) {
            //Add first marker to the map
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_origin_marker));
        } else {
            //Add second marker to the map
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_destination_marker));
        }
        mMap.addMarker(markerOptions);

        if (listPoints.size() == 2) {
            showDirection(listPoints.get(0), listPoints.get(1), TransportMode.DRIVING);
        }

    }

    private void showDirection(LatLng origin, LatLng destination, String transportMode) {

        String serverKey = "AIzaSyDKWtTj0AZg_mFHy6_lgu9X5PGndih8VXo";
        GoogleDirection.withServerKey(serverKey)
                .from(origin)
                .to(destination)
                .transportMode(transportMode)
                .alternativeRoute(true)
                .execute(this);

    }

    // This method will triggered when direction is obtained successfully between two points
    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        if (direction.isOK()) {
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.map_origin_marker)).position(listPoints.get(0)));
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.map_destination_marker)).position(listPoints.get(1)));

            for (int i = 0; i < direction.getRouteList().size(); i++) {
                Route route = direction.getRouteList().get(i);
                String color = colors[i % colors.length];
                ArrayList<LatLng> directionPositionList = route.getLegList().get(0).getDirectionPoint();
                mMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.parseColor(color)));
            }
            setCameraWithCoordinationBounds(direction.getRouteList().get(0));

        }
    }


    // This method will triggered when direction cannot be obtained between two points
    @Override
    public void onDirectionFailure(Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
    }

    // Change map camera position
    private void setCameraWithCoordinationBounds(Route route) {
        LatLng southwest = route.getBound().getSouthwestCoordination().getCoordination();
        LatLng northeast = route.getBound().getNortheastCoordination().getCoordination();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }


}
