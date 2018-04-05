package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;


import android.support.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shikshyaguru.shikshyaguru.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class FakeDataSource implements DataSourceInterface {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    //private static final int SIZE_OF_NEWS_COLLECTION = 10;
    private static final int SIZE_OF_INSTITUTIONS_COLLECTION = 20;
    private Random random = new Random();

    private final int[] DRAWER_MAIN_HEADER_ICONS = {
            R.drawable.ic_profile_d,
            R.drawable.ic_message_d,
            R.drawable.ic_favourites_d,
            R.drawable.ic_followers_d,
            R.drawable.ic_following_d,
            R.drawable.ic_question_d,
            R.drawable.ic_answer_d,
            R.drawable.ic_logout_d
    };

    private final String[] DRAWER_MAIN_HEADER = {
            "Profile",
            "Messages",
            "Favourites",
            "Followers",
            "Following",
            "Questions",
            "Answers",
            "Logout"
    };

    private final String[] OPTIONS = {
            "Top Categories",
            "Nepal",
            "User Choice",
            "Country",
            "Editor Choice"
    };


    private final String[] INSTITUTIONS_ICON = {
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius"
    };

    private final String[] INSTITUTIONS_RATING = {
            "4.5 *",
            "3.5 *",
            "2.5 *",
            "1.5 *"
    };

    private final String[] INSTITUTIONS_CITY_NAME = {
            "Kathmandu",
            "Pokhara",
            "Gorkha",
            "Chitwan"
    };

    private final String[] COLLEGES = {
            "College 1",
            "College 2",
            "College 3",
            "College 4",
            "College 5",
            "College 6",
            "College 7",
            "College 8",
            "College 9",
            "College 10"
    };

    private final String[] COLLEGES_ICON = {
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius",
            "R.drawable.bg_radius"
    };

    private final String[] COLLEGES_RATING = {
            "4.5 *",
            "3.5 *",
            "2.5 *",
            "1.5 *",
            "4.5 *",
            "3.5 *",
            "2.5 *",
            "1.5 *",
            "4.5 *",
            "3.5 *"
    };

    private final String[] COLLEGES_CITY_NAME = {
            "Kathmandu",
            "Pokhara",
            "Gorkha",
            "Chitwan",
            "Kathmandu",
            "Pokhara",
            "Gorkha",
            "Pokhara",
            "Gorkha",
            "Chitwan"
    };

    private final String[] SCHOOLS = {
            "Schools 1",
            "Schools 2",
            "Schools 3",
            "Schools 4",
            "Schools 5"
    };

    private final String[] UNIVERSITIES = {
            "University 1",
            "University 2",
            "University 3",
            "University 4",
            "University 5"
    };

    private final String[] INSTITUTES = {
            "Institute 1",
            "Institute 2",
            "Institute 3",
            "Institute 4",
            "Institute 5"
    };

    private final String[] CONSULTANCIES = {
            "Consultancy 1",
            "Consultancy 2",
            "Consultancy 3",
            "Consultancy 4",
            "Consultancy 5"
    };

    private final String[] ABROAD = {
            "Abroad 1",
            "Abroad 2",
            "Abroad 3",
            "Abroad 4",
            "Abroad 5"
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
            1,
            2,
            3,
            4,
            5,
            6
    };

    private final List[] RELATED_INSTITUTION_DATA = {
            getListOfCollegesData(),
            getListOfSchoolsData(),
            getListOfUniversitiesData(),
            getListOfInstitutesData(),
            getListOfConsultanciesData(),
            getListOfAbroadStudyData()
    };


    public FakeDataSource() {
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

    @Override
    public UserData getUserData() {
        return null;
    }


    public HashMap<String, String> displayAllCategory() {

        final HashMap<String, String> categories = new HashMap<>();


        Query query = mDatabase.getReference().child("clients").child("category");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    System.out.println("============================================");
                    System.out.println(postSnapshot.getKey() + " : " + postSnapshot.getValue());
                    System.out.println("============================================");

                    categories.put(postSnapshot.getKey(), postSnapshot.getValue(String.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return categories;
    }

    public void getAllData() {

        Query query = mDatabase.getReference().child("category");
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    int cat = Integer.parseInt(postSnapshot.getKey());

                    Query query1 = mDatabase.getReference().child("clients").orderByChild("category").equalTo(cat);

                    query1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            for (DataSnapshot pSnapshot : dataSnapshot.getChildren()) {

                                CollegeListItem collegeList = new CollegeListItem();

                                collegeList.setName(pSnapshot.child("name").getValue(String.class));
                                collegeList.setIcon_image(pSnapshot.child("icon_image").getValue(String.class));
                                collegeList.setCity(pSnapshot.child("address").child("city").getValue(String.class));
                                Double rating = pSnapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                                collegeList.setRating(String.valueOf(rating));

                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public FirebaseRecyclerOptions<HomePageSliderListItem> getSponsorDetail() {

        Query query = mDatabase.getReference().child("clients").orderByChild("slider_candidate").equalTo(1);

        SnapshotParser<HomePageSliderListItem> snapshotParser = new SnapshotParser<HomePageSliderListItem>() {
            @NonNull
            @Override
            public HomePageSliderListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                HomePageSliderListItem sliderListItem = snapshot.getValue(HomePageSliderListItem.class);
                assert sliderListItem != null;
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
            System.out.println(snapshot.getKey());

            return newsListItem;
            }
        };

        return new FirebaseRecyclerOptions.Builder<NewsListItem>().setQuery(query, snapshotParser).build();
    }

    @Override
    public List<CollegeListItem> getListOfCollegesData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("category").equalTo(1);

        SnapshotParser<CollegeListItem> snapshotParser = new SnapshotParser<CollegeListItem>() {
            @NonNull
            @Override
            public CollegeListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                CollegeListItem collegeList = new CollegeListItem();

                collegeList.setName(snapshot.child("name").getValue(String.class));
                collegeList.setName(snapshot.child("icon_image").getValue(String.class));
                collegeList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                collegeList.setRating(String.valueOf(rating));

                return collegeList;
            }
        };

        FirebaseRecyclerOptions<CollegeListItem> options = new FirebaseRecyclerOptions.Builder<CollegeListItem>().setQuery(query, snapshotParser).build();



        for (int i = 0; i < options.getSnapshots().size(); i++) {

            System.out.println("==================="+ i +"==========================");
            System.out.println(options.getSnapshots().get(i).getName());
            System.out.println(options.getSnapshots().get(i).getCity());
            System.out.println(options.getSnapshots().get(i).getRating());
            System.out.println(options.getSnapshots().get(i).getIcon_image());
            System.out.println("=============================================");

        }

        ArrayList<CollegeListItem> listOfData = new ArrayList<>();

        for (int i = 0; i < COLLEGES.length; i++) {

            CollegeListItem institutionsListItem = new CollegeListItem(
                    COLLEGES_ICON[i],
                    COLLEGES[i],
                    COLLEGES_RATING[i],
                    COLLEGES_CITY_NAME[i]
            );

            listOfData.add(institutionsListItem);
        }
        return listOfData;
    }

    @Override
    public List<SchoolsListItem> getListOfSchoolsData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("category").equalTo(2);

        SnapshotParser<SchoolsListItem> snapshotParser = new SnapshotParser<SchoolsListItem>() {
            @NonNull
            @Override
            public SchoolsListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                SchoolsListItem schoolList = new SchoolsListItem();

                schoolList.setName(snapshot.child("name").getValue(String.class));
                schoolList.setName(snapshot.child("icon_image").getValue(String.class));
                schoolList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                schoolList.setRating(String.valueOf(rating));

                return schoolList;
            }
        };

        FirebaseRecyclerOptions<SchoolsListItem> options = new FirebaseRecyclerOptions.Builder<SchoolsListItem>().setQuery(query, snapshotParser).build();



        for (int i = 0; i < options.getSnapshots().size(); i++) {

            System.out.println("==================="+ i +"==========================");
            System.out.println(options.getSnapshots().get(i).getName());
            System.out.println(options.getSnapshots().get(i).getCity());
            System.out.println(options.getSnapshots().get(i).getRating());
            System.out.println(options.getSnapshots().get(i).getIcon_image());
            System.out.println("=============================================");

        }

        ArrayList<SchoolsListItem> listOfData = new ArrayList<>();

        for (int i = 0; i < SIZE_OF_INSTITUTIONS_COLLECTION; i++) {

            int randOne = random.nextInt(4);
            int randTwo = random.nextInt(4);
            int randThree = random.nextInt(4);
            int randFour = random.nextInt(4);

            SchoolsListItem schoolsListItem = new SchoolsListItem(
                    INSTITUTIONS_ICON[randOne],
                    SCHOOLS[randTwo],
                    INSTITUTIONS_RATING[randThree],
                    INSTITUTIONS_CITY_NAME[randFour]
            );

            listOfData.add(schoolsListItem);
        }


        return listOfData;
    }

    @Override
    public List<UniversitiesListItem> getListOfUniversitiesData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("category").equalTo(3);

        SnapshotParser<UniversitiesListItem> snapshotParser = new SnapshotParser<UniversitiesListItem>() {
            @NonNull
            @Override
            public UniversitiesListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                UniversitiesListItem universityList = new UniversitiesListItem();

                universityList.setName(snapshot.child("name").getValue(String.class));
                universityList.setIcon_image(snapshot.child("icon_image").getValue(String.class));
                universityList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                universityList.setRating(String.valueOf(rating));

                return universityList;
            }
        };

        FirebaseRecyclerOptions<UniversitiesListItem> options = new FirebaseRecyclerOptions.Builder<UniversitiesListItem>().setQuery(query, snapshotParser).build();



        for (int i = 0; i < options.getSnapshots().size(); i++) {

            System.out.println("==================="+ i +"==========================");
            System.out.println(options.getSnapshots().get(i).getName());
            System.out.println(options.getSnapshots().get(i).getCity());
            System.out.println(options.getSnapshots().get(i).getRating());
            System.out.println(options.getSnapshots().get(i).getIcon_image());
            System.out.println("=============================================");

        }


        ArrayList<UniversitiesListItem> listOfData = new ArrayList<>();

        for (int i = 0; i < SIZE_OF_INSTITUTIONS_COLLECTION; i++) {

            int randOne = random.nextInt(4);
            int randTwo = random.nextInt(4);
            int randThree = random.nextInt(4);
            int randFour = random.nextInt(4);

            UniversitiesListItem universitiesListItem = new UniversitiesListItem(
                    INSTITUTIONS_ICON[randOne],
                    UNIVERSITIES[randTwo],
                    INSTITUTIONS_RATING[randThree],
                    INSTITUTIONS_CITY_NAME[randFour]
            );

            listOfData.add(universitiesListItem);
        }


        return listOfData;
    }

    @Override
    public List<InstitutesListItem> getListOfInstitutesData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("category").equalTo(4);

        SnapshotParser<InstitutesListItem> snapshotParser = new SnapshotParser<InstitutesListItem>() {
            @NonNull
            @Override
            public InstitutesListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                InstitutesListItem instituteList = new InstitutesListItem();

                instituteList.setName(snapshot.child("name").getValue(String.class));
                instituteList.setIcon_image(snapshot.child("icon_image").getValue(String.class));
                instituteList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                instituteList.setRating(String.valueOf(rating));

                return instituteList;
            }
        };

        FirebaseRecyclerOptions<InstitutesListItem> options = new FirebaseRecyclerOptions.Builder<InstitutesListItem>().setQuery(query, snapshotParser).build();



        for (int i = 0; i < options.getSnapshots().size(); i++) {

            System.out.println("==================="+ i +"==========================");
            System.out.println(options.getSnapshots().get(i).getName());
            System.out.println(options.getSnapshots().get(i).getCity());
            System.out.println(options.getSnapshots().get(i).getRating());
            System.out.println(options.getSnapshots().get(i).getIcon_image());
            System.out.println("=============================================");

        }

        ArrayList<InstitutesListItem> listOfData = new ArrayList<>();

        for (int i = 0; i < SIZE_OF_INSTITUTIONS_COLLECTION; i++) {

            int randOne = random.nextInt(4);
            int randTwo = random.nextInt(4);
            int randThree = random.nextInt(4);
            int randFour = random.nextInt(4);

            InstitutesListItem institutesListItem = new InstitutesListItem(
                    INSTITUTIONS_ICON[randOne],
                    INSTITUTES[randTwo],
                    INSTITUTIONS_RATING[randThree],
                    INSTITUTIONS_CITY_NAME[randFour]
            );

            listOfData.add(institutesListItem);
        }


        return listOfData;
    }

    @Override
    public List<ConsultanciesListItem> getListOfConsultanciesData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("category").equalTo(5);

        SnapshotParser<ConsultanciesListItem> snapshotParser = new SnapshotParser<ConsultanciesListItem>() {
            @NonNull
            @Override
            public ConsultanciesListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                ConsultanciesListItem consultanciesList = new ConsultanciesListItem();

                consultanciesList.setName(snapshot.child("name").getValue(String.class));
                consultanciesList.setIcon_image(snapshot.child("icon_image").getValue(String.class));
                consultanciesList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                consultanciesList.setRating(String.valueOf(rating));

                return consultanciesList;
            }
        };

        FirebaseRecyclerOptions<ConsultanciesListItem> options = new FirebaseRecyclerOptions.Builder<ConsultanciesListItem>().setQuery(query, snapshotParser).build();



        for (int i = 0; i < options.getSnapshots().size(); i++) {

            System.out.println("==================="+ i +"==========================");
            System.out.println(options.getSnapshots().get(i).getName());
            System.out.println(options.getSnapshots().get(i).getCity());
            System.out.println(options.getSnapshots().get(i).getRating());
            System.out.println(options.getSnapshots().get(i).getIcon_image());
            System.out.println("=============================================");

        }


        ArrayList<ConsultanciesListItem> listOfData = new ArrayList<>();

        for (int i = 0; i < SIZE_OF_INSTITUTIONS_COLLECTION; i++) {

            int randOne = random.nextInt(4);
            int randTwo = random.nextInt(4);
            int randThree = random.nextInt(4);
            int randFour = random.nextInt(4);

            ConsultanciesListItem consultanciesListItem = new ConsultanciesListItem(
                    INSTITUTIONS_ICON[randOne],
                    CONSULTANCIES[randTwo],
                    INSTITUTIONS_RATING[randThree],
                    INSTITUTIONS_CITY_NAME[randFour]
            );

            listOfData.add(consultanciesListItem);
        }


        return listOfData;
    }

    @Override
    public List<AbroadStudyListItem> getListOfAbroadStudyData() {

        Query query = mDatabase.getReference().child("clients").orderByChild("category").equalTo(6);

        SnapshotParser<AbroadStudyListItem> snapshotParser = new SnapshotParser<AbroadStudyListItem>() {
            @NonNull
            @Override
            public AbroadStudyListItem parseSnapshot(@NonNull DataSnapshot snapshot) {

                AbroadStudyListItem abroadList = new AbroadStudyListItem();

                abroadList.setName(snapshot.child("name").getValue(String.class));
                abroadList.setIcon_image(snapshot.child("icon_image").getValue(String.class));
                abroadList.setCity(snapshot.child("address").child("city").getValue(String.class));
                Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                abroadList.setRating(String.valueOf(rating));

                return abroadList;
            }
        };

        FirebaseRecyclerOptions<AbroadStudyListItem> options = new FirebaseRecyclerOptions.Builder<AbroadStudyListItem>().setQuery(query, snapshotParser).build();



        for (int i = 0; i < options.getSnapshots().size(); i++) {

            System.out.println("==================="+ i +"==========================");
            System.out.println(options.getSnapshots().get(i).getName());
            System.out.println(options.getSnapshots().get(i).getCity());
            System.out.println(options.getSnapshots().get(i).getRating());
            System.out.println(options.getSnapshots().get(i).getIcon_image());
            System.out.println("=============================================");

        }


        ArrayList<AbroadStudyListItem> listOfData = new ArrayList<>();

        for (int i = 0; i < SIZE_OF_INSTITUTIONS_COLLECTION; i++) {

            int randOne = random.nextInt(4);
            int randTwo = random.nextInt(4);
            int randThree = random.nextInt(4);
            int randFour = random.nextInt(4);

            AbroadStudyListItem abroadStudyListItem = new AbroadStudyListItem(
                    INSTITUTIONS_ICON[randOne],
                    ABROAD[randTwo],
                    INSTITUTIONS_RATING[randThree],
                    INSTITUTIONS_CITY_NAME[randFour]
            );

            listOfData.add(abroadStudyListItem);
        }


        return listOfData;
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


}
