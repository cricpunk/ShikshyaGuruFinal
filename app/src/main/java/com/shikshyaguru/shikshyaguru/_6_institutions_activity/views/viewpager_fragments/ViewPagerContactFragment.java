package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_4_animation_collection.CircularReveal;

public class ViewPagerContactFragment extends Fragment implements
        View.OnFocusChangeListener,
        OnMapReadyCallback {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pager_contact, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sendMessageSection(view);
        googleMap(view);

    }


    private void sendMessageSection(View view) {
        EditText name = (EditText) view.findViewById(R.id.lbl_vp_contact_name);
        EditText phoneNumber = (EditText) view.findViewById(R.id.lbl_vp_contact_phone);
        EditText emailId = (EditText) view.findViewById(R.id.lbl_vp_contact_email);
        EditText message = (EditText) view.findViewById(R.id.lbl_vp_contact_message);
        FloatingActionButton sendMessageButton = (FloatingActionButton) view.findViewById(R.id.fab_vp_contact_send);

        name.setOnFocusChangeListener(this);
        phoneNumber.setOnFocusChangeListener(this);
        emailId.setOnFocusChangeListener(this);
        message.setOnFocusChangeListener(this);
        sendMessageButton.setImageResource(R.drawable.ic_send);
    }

    private void googleMap(View view) {
        MapView mapView = (MapView) view.findViewById(R.id.map_vp_contact);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        CircularReveal circularReveal = new CircularReveal(getActivity(), v, hasFocus);
        circularReveal.circularReveal();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(27.7084856, 85.3258456))
                .title("Islington College")
                .snippet("This is islington college slogan."));
        CameraPosition liberty = CameraPosition.builder()
                .target(new LatLng(27.7084856, 85.3258456))
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(liberty));
    }
}
