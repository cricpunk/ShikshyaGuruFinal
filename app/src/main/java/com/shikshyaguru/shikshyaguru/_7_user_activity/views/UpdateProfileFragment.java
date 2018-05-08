package com.shikshyaguru.shikshyaguru._7_user_activity.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PermissionVerifier;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;
import com.shikshyaguru.shikshyaguru._0_6_widgets.StatusBar;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDataSource;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDetails;
import com.shikshyaguru.shikshyaguru._7_user_activity.presenter.UserController;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/*
 * Created by Pankaj Koirala on 4/11/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class UpdateProfileFragment extends Fragment implements View.OnClickListener, MaterialSpinner.OnItemSelectedListener {

    private LayoutInflater inflater;
    private String uId, name, email, uName, institution, address, phone;
    private int type;

    private EditText etName, etUserName, etPhone, etEmail, etInstitution, etAddress;
    private MaterialSpinner userType;
    private ImageView ivBackground, ivCamera, ivGallery;
    private Button btnUpdateProfile;

    private boolean userTypeSelected = false;
    private String backgroundUri, backgroundImageName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout._7_2_2_update_user_profile, container, false);

        if (getArguments() != null) {

            this.uId = getArguments().getString("UID");
            this.name = getArguments().getString("NAME");
            this.email = getArguments().getString("EMAIL");
            this.uName = getArguments().getString("USER_NAME");
            this.type = getArguments().getInt("TYPE");
            this.institution = getArguments().getString("INSTITUTION");
            this.address = getArguments().getString("ADDRESS");
            this.phone = getArguments().getString("PHONE");

        }

        // Change status bar color always from inside onCreateView
        StatusBar.changeStatusBarColor(getContext(), Objects.requireNonNull(getActivity()).getWindow(), R.color.black_toolbar);

        // Setup blue_toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Toolbars.setUpToolbar(toolbar, getActivity(), "Update Profile");
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);

        initComponents(view);

        return view;
    }

    private void initComponents(View view) {

        this.etName = view.findViewById(R.id.et_name);
        this.etUserName = view.findViewById(R.id.et_user_name);
        this.etPhone = view.findViewById(R.id.et_phone);
        this.etEmail = view.findViewById(R.id.et_email);
        this.etAddress = view.findViewById(R.id.et_address);
        this.etInstitution = view.findViewById(R.id.et_institution);

        this.userType = view.findViewById(R.id.spinner_user_type);

        this.ivBackground = view.findViewById(R.id.iv_background_for_upload);
        this.ivCamera = view.findViewById(R.id.iv_camera);
        this.ivGallery = view.findViewById(R.id.iv_gallery);

        this.btnUpdateProfile = view.findViewById(R.id.btn_submit_update_profile);

        //create a list of items for the spinner.
        userType.setItems("Student", "Teacher", "Institution");
        userType.setOnItemSelectedListener(this);

        etName.setText(name);
        etUserName.setText(uName);
        etEmail.setText(email);
        etInstitution.setText(institution);
        etPhone.setText(phone);
        etAddress.setText(address);
        // While inserting type value in database index has been increased by 1 that is why it is
        // necessary to decrease by 1 from here.
        userType.setSelectedIndex(type-1);

        etUserName.setKeyListener(null);
        etEmail.setKeyListener(null);

        ivCamera.setOnClickListener(this);
        ivGallery.setOnClickListener(this);
        btnUpdateProfile.setOnClickListener(this);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Objects.requireNonNull(getActivity()).onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_camera:
                if (PermissionVerifier.isCameraPermissionGranted(getActivity()) && PermissionVerifier.isExternalStoragePermissionGranted(getActivity())) {
                    EasyImage.openCamera(UpdateProfileFragment.this, 0);
                }
                break;

            case R.id.iv_gallery:
                if (PermissionVerifier.isCameraPermissionGranted(getActivity()) && PermissionVerifier.isExternalStoragePermissionGranted(getActivity())) {
                    EasyImage.openGallery(UpdateProfileFragment.this, 0);
                }
                break;

            case R.id.btn_submit_update_profile:

                if (userTypeSelected) {

                    UserController controller = new UserController(new UserDataSource());
                    UserDetails userDetails = new UserDetails();

                    HashMap<String, Object> userData = new HashMap<>();

                    userData.put("type", userType.getSelectedIndex() + 1);

                    //userDetails.setType(userType.getSelectedIndex() + 1);

                    if (!etName.getText().toString().trim().equals("")) {
                        userDetails.setName(etName.getText().toString());
                        userData.put("name", etName.getText().toString());
                    }

                    if (!etPhone.getText().toString().trim().equals("")) {
                        userDetails.setPhone(etPhone.getText().toString());
                        userData.put("phone", etPhone.getText().toString());
                    }

                    if (!etInstitution.getText().toString().trim().equals("")) {
                        userDetails.setInstitution(etInstitution.getText().toString());
                        userData.put("institution", etInstitution.getText().toString());
                    }

                    if (!etAddress.getText().toString().trim().equals("")) {
                        userDetails.setAddress(etAddress.getText().toString());
                        userData.put("address", etAddress.getText().toString());
                    }

                    if (backgroundImageName != null) {
                        userDetails.setBg_image(backgroundUri);
                        userData.put("bg_image", backgroundUri);
                    }

                    controller.updateUserProfile(userData, backgroundImageName, getContext());

                } else {
                    PopupCollections.tooltipMessage(Objects.requireNonNull(getActivity()), userType, "Please select user type").show();
                }
                break;

        }

    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        switch (position) {
            case 0:
                // Student
                userTypeSelected = true;
                //Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                // Teacher
                userTypeSelected = true;
                //Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
                break;
            case 2:
                // Institution
                userTypeSelected = true;
                //Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {

            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
                e.printStackTrace();
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

                Picasso.get()
                        .load(imageFile.toURI().toString())
                        .fit()
                        .centerCrop()
                        .into(ivBackground);
                backgroundUri = imageFile.toURI().toString();
                backgroundImageName = imageFile.getName();

                //Toast.makeText(getContext(), imageFile.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(getContext());
                    if (photoFile != null) photoFile.delete();
                }
            }

        });

    }



}
