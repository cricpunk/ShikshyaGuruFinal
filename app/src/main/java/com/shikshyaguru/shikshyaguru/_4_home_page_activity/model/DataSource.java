package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;


import android.support.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.DrawerInterface;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class DataSource implements DataSourceInterface {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private String uId = NavigationDrawerFragment.currentUser.getUid();

    private final int[] DRAWER_MAIN_HEADER_ICONS = {
            R.drawable.ic_nd_user_home,
            R.drawable.ic_nd_message,
            R.drawable.ic_nd_favourites,
            R.drawable.ic_nd_followers,
            R.drawable.ic_nd_followers,
            R.drawable.ic_nd_question,
            R.drawable.ic_nd_answer,
            R.drawable.ic_nd_logout
    };

    private final String[] DRAWER_MAIN_HEADER = {
            "Profile", "Messages", "Favourites", "Followers",
            "Following", "Questions", "Answers", "Logout"
    };

    private final String[] OPTIONS = {
            "Top Categories", "Nepal", "User Choice",
            "Country", "Editor Choice"
    };

    private final String[] INSTITUTIONS_HEADING = {
            "Colleges",
            "Schools",
            "Universities",
            "Institutes",
            "Consultancies",
            "Abroad"
    };

    private final int[] INSTITUTIONS_HEADING_ID = {
            1, 2, 3, 4, 5, 6
    };

    private final FirebaseRecyclerOptions[] RELATED_INSTITUTION_DATA = {
            getListOfCollegesData(),
            getListOfSchoolsData(),
            getListOfUniversitiesData(),
            getListOfInstitutesData(),
            getListOfConsultanciesData(),
            getListOfAbroadStudyData()
    };


    public DataSource() {
    }

    @Override
    public List<DrawerListItem> getListOfDrawerMainHeader() {
        ArrayList<DrawerListItem> listOfData = new ArrayList<>();

        for (int i = 0; i < DRAWER_MAIN_HEADER.length; i++) {
            DrawerListItem drawerListItem = new DrawerListItem(
                    DRAWER_MAIN_HEADER_ICONS[i],
                    DRAWER_MAIN_HEADER[i]
            );
            listOfData.add(drawerListItem);
        }

        return listOfData;
    }

    @Override
    public void getFavouriteInstitutionList(final DrawerInterface drawerInterface) {

        final DatabaseReference favRef = mDatabase.getReference().child("users").child(uId).child("favourites");

        favRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<String> favInstitution = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    favInstitution.add(snapshot.getKey());
                }

                drawerInterface.favouriteInstitutionList(favInstitution);
                drawerInterface.setUpDrawerMainHeader(getListOfDrawerMainHeader());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public List<HomePageOptionsListItem> getListOfOptions() {
        ArrayList<HomePageOptionsListItem> listOfOptionsData = new ArrayList<>();

        for (String OPTION : OPTIONS) {
            HomePageOptionsListItem homePageOptionsListItem = new HomePageOptionsListItem(
                    OPTION
            );

            listOfOptionsData.add(homePageOptionsListItem);
        }
        return listOfOptionsData;
    }

    private String slogan;
    @Override
    public String getSlogan(String id) {

        mDatabase.getReference("clients").child(id).child("profile/slogan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                slogan = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return slogan;

    }

    @Override
    public FirebaseRecyclerOptions<HomePageSliderListItem> getSponsorDetail() {

        Query query = mDatabase.getReference().child("clients").orderByChild("profile/slider_candidate").equalTo(1);

        SnapshotParser<HomePageSliderListItem> snapshotParser = new SnapshotParser<HomePageSliderListItem>() {
            @NonNull
            @Override
            public HomePageSliderListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                HomePageSliderListItem sliderListItem = new HomePageSliderListItem();
                sliderListItem.setId(snapshot.getKey());
                sliderListItem.setName(snapshot.child("profile/name").getValue(String.class));
                sliderListItem.setMain_image(snapshot.child("profile/main_image").getValue(String.class));
                sliderListItem.setSlogan(snapshot.child("profile/slogan").getValue(String.class));
                sliderListItem.setCity(snapshot.child("address").child("city").getValue(String.class));

                return sliderListItem;
            }
        };

        return new FirebaseRecyclerOptions.Builder<HomePageSliderListItem>().setQuery(query, snapshotParser).build();
    }

    @Override
    public FirebaseRecyclerOptions<NewsListItem> getNewsDetails() {

        Query query = mDatabase.getReference().child("news");

        SnapshotParser<NewsListItem> snapshotParser = new SnapshotParser<NewsListItem>() {
            @NonNull
            @Override
            public NewsListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

            NewsListItem newsListItem = snapshot.getValue(NewsListItem.class);
            assert newsListItem != null;
            newsListItem.setTime(snapshot.getKey());

            return newsListItem;
            }
        };

        return new FirebaseRecyclerOptions.Builder<NewsListItem>().setQuery(query, snapshotParser).build();
    }

    @Override
    public FirebaseRecyclerOptions<CollegeListItem> getListOfCollegesData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("profile/category").equalTo(1);

        SnapshotParser<CollegeListItem> snapshotParser = new SnapshotParser<CollegeListItem>() {
            @NonNull
            @Override
            public CollegeListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                CollegeListItem collegeList = new CollegeListItem();

                collegeList.setId(snapshot.getKey());
                collegeList.setName(snapshot.child("profile/name").getValue(String.class));
                collegeList.setIcon_image(snapshot.child("profile/icon_image").getValue(String.class));
                collegeList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                if (rating != null) {
                    collegeList.setRating(String.valueOf(rating) + "*");
                } else {
                    collegeList.setRating("n/a");
                }


                return collegeList;
            }
        };

        return new FirebaseRecyclerOptions.Builder<CollegeListItem>().setQuery(query, snapshotParser).build();
    }

    @Override
    public FirebaseRecyclerOptions<SchoolsListItem> getListOfSchoolsData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("profile/category").equalTo(2);

        SnapshotParser<SchoolsListItem> snapshotParser = new SnapshotParser<SchoolsListItem>() {
            @NonNull
            @Override
            public SchoolsListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                SchoolsListItem schoolList = new SchoolsListItem();

                schoolList.setId(snapshot.getKey());
                schoolList.setName(snapshot.child("profile/name").getValue(String.class));
                schoolList.setIcon_image(snapshot.child("profile/icon_image").getValue(String.class));
                schoolList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                if (rating != null) {
                    schoolList.setRating(String.valueOf(rating) + "*");
                } else {
                    schoolList.setRating("n/a");
                }

                return schoolList;
            }
        };

        return new FirebaseRecyclerOptions.Builder<SchoolsListItem>().setQuery(query, snapshotParser).build();
    }

    @Override
    public FirebaseRecyclerOptions<UniversitiesListItem> getListOfUniversitiesData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("profile/category").equalTo(3);

        SnapshotParser<UniversitiesListItem> snapshotParser = new SnapshotParser<UniversitiesListItem>() {
            @NonNull
            @Override
            public UniversitiesListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                UniversitiesListItem universityList = new UniversitiesListItem();

                universityList.setId(snapshot.getKey());
                universityList.setName(snapshot.child("profile/name").getValue(String.class));
                universityList.setIcon_image(snapshot.child("profile/icon_image").getValue(String.class));
                universityList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                if (rating != null) {
                    universityList.setRating(String.valueOf(rating) + "*");
                } else {
                    universityList.setRating("n/a");
                }

                return universityList;
            }
        };

        return new FirebaseRecyclerOptions.Builder<UniversitiesListItem>().setQuery(query, snapshotParser).build();
    }

    @Override
    public FirebaseRecyclerOptions<InstitutesListItem> getListOfInstitutesData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("profile/category").equalTo(4);

        SnapshotParser<InstitutesListItem> snapshotParser = new SnapshotParser<InstitutesListItem>() {
            @NonNull
            @Override
            public InstitutesListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                InstitutesListItem instituteList = new InstitutesListItem();

                instituteList.setId(snapshot.getKey());
                instituteList.setName(snapshot.child("profile/name").getValue(String.class));
                instituteList.setIcon_image(snapshot.child("profile/icon_image").getValue(String.class));
                instituteList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                if (rating != null) {
                    instituteList.setRating(String.valueOf(rating) + "*");
                } else {
                    instituteList.setRating("n/a");
                }

                return instituteList;
            }
        };

        return new FirebaseRecyclerOptions.Builder<InstitutesListItem>().setQuery(query, snapshotParser).build();
    }

    @Override
    public FirebaseRecyclerOptions<ConsultanciesListItem> getListOfConsultanciesData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("profile/category").equalTo(5);

        SnapshotParser<ConsultanciesListItem> snapshotParser = new SnapshotParser<ConsultanciesListItem>() {
            @NonNull
            @Override
            public ConsultanciesListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                ConsultanciesListItem consultanciesList = new ConsultanciesListItem();

                consultanciesList.setId(snapshot.getKey());
                consultanciesList.setName(snapshot.child("profile/name").getValue(String.class));
                consultanciesList.setIcon_image(snapshot.child("profile/icon_image").getValue(String.class));
                consultanciesList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                if (rating != null) {
                    consultanciesList.setRating(String.valueOf(rating) + "*");
                } else {
                    consultanciesList.setRating("n/a");
                }
                return consultanciesList;
            }
        };

        return new FirebaseRecyclerOptions.Builder<ConsultanciesListItem>().setQuery(query, snapshotParser).build();

    }

    @Override
    public FirebaseRecyclerOptions<AbroadStudyListItem> getListOfAbroadStudyData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("profile/category").equalTo(6);

        SnapshotParser<AbroadStudyListItem> snapshotParser = new SnapshotParser<AbroadStudyListItem>() {
            @NonNull
            @Override
            public AbroadStudyListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                AbroadStudyListItem abroadList = new AbroadStudyListItem();

                abroadList.setId(snapshot.getKey());
                abroadList.setName(snapshot.child("profile/name").getValue(String.class));
                abroadList.setIcon_image(snapshot.child("profile/icon_image").getValue(String.class));
                abroadList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                if (rating != null) {
                    abroadList.setRating(String.valueOf(rating) + "*");
                } else {
                    abroadList.setRating("n/a");
                }
                return abroadList;
            }
        };

        return new FirebaseRecyclerOptions.Builder<AbroadStudyListItem>().setQuery(query, snapshotParser).build();
    }

    @Override
    public List<ListOfTotalInstitutions> getTotalInstitutionsHeading() {

        ArrayList<ListOfTotalInstitutions> listOfTotalInstitutions = new ArrayList<>();

        for (int i = 0; i < INSTITUTIONS_HEADING.length; i++) {

            ListOfTotalInstitutions listOfTotalInstitutions1 = new ListOfTotalInstitutions(
                    INSTITUTIONS_HEADING[i],
                    INSTITUTIONS_HEADING_ID[i],
                    RELATED_INSTITUTION_DATA[i]
            );

            listOfTotalInstitutions.add(listOfTotalInstitutions1);
        }

        return listOfTotalInstitutions;
    }








//    public HashMap<String, String> displayAllCategory() {
//
//        final HashMap<String, String> categories = new HashMap<>();
//
//
//        Query query = mDatabase.getReference().child("clients").child("category");
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    System.out.println("============================================");
//                    System.out.println(postSnapshot.getKey() + " : " + postSnapshot.getValue());
//                    System.out.println("============================================");
//
//                    categories.put(postSnapshot.getKey(), postSnapshot.getValue(String.class));
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//        return categories;
//    }
//
//    public void getAllData() {
//
//        Query query = mDatabase.getReference().child("category");
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//
//                    int cat = Integer.parseInt(postSnapshot.getKey());
//
//                    Query query1 = mDatabase.getReference().child("clients").orderByChild("category").equalTo(cat);
//
//                    query1.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                            for (DataSnapshot pSnapshot : dataSnapshot.getChildren()) {
//
//                                CollegeListItem collegeList = new CollegeListItem();
//
//                                collegeList.setName(pSnapshot.child("name").getValue(String.class));
//                                collegeList.setIcon_image(pSnapshot.child("icon_image").getValue(String.class));
//                                collegeList.setCity(pSnapshot.child("address").child("city").getValue(String.class));
//                                Double rating = pSnapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
//                                collegeList.setRating(String.valueOf(rating));
//
//                            }
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }

}
