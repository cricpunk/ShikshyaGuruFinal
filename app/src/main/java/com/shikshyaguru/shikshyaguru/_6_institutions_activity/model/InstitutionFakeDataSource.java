package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

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
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class InstitutionFakeDataSource implements InstitutionDataSourceInterface {

    private final int SIZE = 10;
    private Random random = new Random();
    private int randOne;
    private int randTwo;
    private int randThree;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();


    private final String[] RATING = {"4.2", "3.5", "4.8"};
    private final String[] REVIEW_HEADING = {"Very nice", "Worthy joining", "Useless college"};
    private final String[] REVIEW = {String.valueOf(R.string.review), String.valueOf(R.string.review), String.valueOf(R.string.review)};
    private final String[] USER_NAME = {"Pankaj Koirala", "Bidhya Sapkota", "Neymar Jr."};
    private final String[] REVIEW_DATE = {"7 Aug, 2016", "17 Aug, 2016", "24 Jan, 2017"};
    private final int[] LIKE_COUNT = {125, 569, 920};
    private final int[] DISLIKE_COUNT = {25, 69, 20};

    @Override
    public FirebaseRecyclerOptions<InstitutionHomeNewsAndEventsData> getInstitutionHomeNewsAndEventData(String id) {

        Query query = mDatabase.getReference().child("clients").child(id).child("app_home").child("news_and_events");

        SnapshotParser<InstitutionHomeNewsAndEventsData> snapshotParser = new SnapshotParser<InstitutionHomeNewsAndEventsData>() {
            @NonNull
            @Override
            public InstitutionHomeNewsAndEventsData parseSnapshot(@NonNull DataSnapshot snapshot) {

                InstitutionHomeNewsAndEventsData newsListItem = snapshot.getValue(InstitutionHomeNewsAndEventsData.class);
                assert newsListItem != null;
                newsListItem.setId(snapshot.getKey());

                return newsListItem;
            }
        };

        return new FirebaseRecyclerOptions.Builder<InstitutionHomeNewsAndEventsData>().setQuery(query, snapshotParser).build();
    }

    @Override
    public FirebaseRecyclerOptions<InstitutionHomeIntroData> getInstitutionHomeIntroData(String id) {

        Query query = mDatabase.getReference().child("clients").child(id).child("app_home").child("message_to_users");

        SnapshotParser<InstitutionHomeIntroData> snapshotParser = new SnapshotParser<InstitutionHomeIntroData>() {
            @NonNull
            @Override
            public InstitutionHomeIntroData parseSnapshot(@NonNull DataSnapshot snapshot) {

                InstitutionHomeIntroData introData = snapshot.getValue(InstitutionHomeIntroData.class);
                assert introData != null;
                introData.setId(snapshot.getKey());

                return introData;
            }
        };

        return new FirebaseRecyclerOptions.Builder<InstitutionHomeIntroData>().setQuery(query, snapshotParser).build();

    }

    @Override
    public List<InstitutionReviewsData> getInstitutionReviewData() {
        ArrayList<InstitutionReviewsData> listOfData = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {

            randOne = random.nextInt(3);
            randTwo = random.nextInt(3);
            randThree = random.nextInt(3);
            int randFour = random.nextInt(3);
            int randFive = random.nextInt(3);
            int randSix = random.nextInt(3);
            int randSeven = random.nextInt(3);

            InstitutionReviewsData institutionReviewsData = new InstitutionReviewsData(
                    RATING[randOne],
                    REVIEW_HEADING[randTwo],
                    REVIEW[randThree],
                    USER_NAME[randFour],
                    REVIEW_DATE[randFive],
                    LIKE_COUNT[randSix],
                    DISLIKE_COUNT[randSeven]

            );

            listOfData.add(institutionReviewsData);
        }

        return listOfData;
    }

    @Override
    public InstitutionRatingsData getInstitutionRatingsData() {
        InstitutionRatingsData ratingsData = new InstitutionRatingsData();
        int fiveStarRating = 152399;
        int fourStarRating = 25020;
        int threeStarRating = 252;
        int twoStarRating = 98;
        int oneStarRating = 89;
        int totalRatings = fiveStarRating + fourStarRating + threeStarRating + twoStarRating + oneStarRating;
        int totalReviews = 59846;

        ratingsData.setOverallRating(4.6);
        ratingsData.setEducationRating(4.3);
        ratingsData.setInfrastructureRating(4.0);
        ratingsData.setTeachersRating(4.5);
        ratingsData.setManagementRating(3.9);
        ratingsData.setFiveStar(fiveStarRating);
        ratingsData.setFourStar(fourStarRating);
        ratingsData.setThreeStar(threeStarRating);
        ratingsData.setTwoStar(twoStarRating);
        ratingsData.setOneStar(oneStarRating);
        ratingsData.setTotalRating(totalRatings);
        ratingsData.setTotalReviews(totalReviews);

        return ratingsData;
    }

    @Override
    public FirebaseRecyclerOptions<InstitutionTeachersData> getTeachersData(String id) {

        Query query = mDatabase.getReference().child("clients").child(id).child("app_teachers").child("teachers");

        SnapshotParser<InstitutionTeachersData> snapshotParser = new SnapshotParser<InstitutionTeachersData>() {
            @NonNull
            @Override
            public InstitutionTeachersData parseSnapshot(@NonNull DataSnapshot snapshot) {

                InstitutionTeachersData teachersData = snapshot.getValue(InstitutionTeachersData.class);
                assert teachersData != null;
                teachersData.setId(snapshot.getKey());

                return teachersData;
            }
        };

        return new FirebaseRecyclerOptions.Builder<InstitutionTeachersData>().setQuery(query, snapshotParser).build();

    }

    @Override
    public FirebaseRecyclerOptions<InstitutionStaffData> getStaffData(String id) {

        Query query = mDatabase.getReference().child("clients").child(id).child("app_staff").child("staff");

        SnapshotParser<InstitutionStaffData> snapshotParser = new SnapshotParser<InstitutionStaffData>() {
            @NonNull
            @Override
            public InstitutionStaffData parseSnapshot(@NonNull DataSnapshot snapshot) {

                InstitutionStaffData staffData = snapshot.getValue(InstitutionStaffData.class);
                assert staffData != null;
                staffData.setId(snapshot.getKey());

                return staffData;
            }
        };

        return new FirebaseRecyclerOptions.Builder<InstitutionStaffData>().setQuery(query, snapshotParser).build();

    }

    @Override
    public InstitutionProgrammesData getInstitutionProgrammesData() {
        InstitutionProgrammesData programmesData = new InstitutionProgrammesData();

        // 1,2,3 in programmesCoursesName represent programmesLevelName id. 1 = +2, 2 = Bachelors, 3 = Masters.
        String[] programmesLevelName = {"+2", "Bachelors", "Masters"};
        HashMap<String, String[]> programmesCourses = new HashMap<>();

        String[] programmesCoursesName0 = {"Management", "Science"};
        String[] programmesCoursesName1 = {"BIM", "BBA", "BScCSIT", "BBS"};
        String[] programmesCoursesName2 = {"MIM", "MBA", "MBS"};

        programmesCourses.put(programmesLevelName[0], programmesCoursesName0);
        programmesCourses.put(programmesLevelName[1], programmesCoursesName1);
        programmesCourses.put(programmesLevelName[2], programmesCoursesName2);

        programmesData.setProgrammesLevelName(programmesLevelName);
        programmesData.setProgrammesCoursesName(programmesCourses);

        return programmesData;
    }

    @Override
    public InstitutionProgrammesCoursesData getInstitutionCoursesData() {
        String[] compulsorySubjectXi = { "Compulsory English", "Compulsory Nepali", "Accountancy" };
        String[] compulsorySubjectXii = { "Compulsory English", "Business Math", "Accountancy" };
        List<String[]> subjectOptionsCollectionXi = new ArrayList<>();
        //List<String[]> subjectOptionsCollectionXii = new ArrayList<>();

        String[] option1 = { "Hotel Management", "Computer Science" };
        String[] option2 = { "Hotel Management", "Economics" };
        String[] option3 = { "Business Studies", "Computer Science" };
        String[] option4 = { "Economics", "Computer Science" };
        String[] option5 = { "Business Studies", "Economics" };
        String[] option6 = { "Travel and Tourism", "Economics", "Extra one" };

        subjectOptionsCollectionXi.add(option1);
        subjectOptionsCollectionXi.add(option2);
        subjectOptionsCollectionXi.add(option3);
        subjectOptionsCollectionXi.add(option4);
        subjectOptionsCollectionXi.add(option5);
        subjectOptionsCollectionXi.add(option6);

        InstitutionProgrammesCoursesData coursesData = new InstitutionProgrammesCoursesData();
        coursesData.setCompulsorySubjectsXi(compulsorySubjectXi);
        coursesData.setCompulsorySubjectsXii(compulsorySubjectXii);
        coursesData.setSubjectOptionsCollectionXi(subjectOptionsCollectionXi);
        coursesData.setSubjectOptionsCollectionXii(subjectOptionsCollectionXi);

        return coursesData;
    }

    @Override
    public FirebaseRecyclerOptions<InstitutionManagementData> getInstitutionManagementData(String id) {

        Query query = mDatabase.getReference().child("clients").child(id).child("app_management").child("members");

        SnapshotParser<InstitutionManagementData> snapshotParser = new SnapshotParser<InstitutionManagementData>() {
            @NonNull
            @Override
            public InstitutionManagementData parseSnapshot(@NonNull DataSnapshot snapshot) {

                InstitutionManagementData managementData = snapshot.getValue(InstitutionManagementData.class);
                assert managementData != null;
                managementData.setId(snapshot.getKey());

                return managementData;
            }
        };

        return new FirebaseRecyclerOptions.Builder<InstitutionManagementData>().setQuery(query, snapshotParser).build();
    }

    @Override
    public List<InstitutionStudentAlumniData> getListOfStudentAlumniData() {
        return null;
    }

    @Override
    public FirebaseRecyclerOptions<InstitutionGalleryData> getInstitutionGalleryData(String id) {

        Query query = mDatabase.getReference().child("clients").child(id).child("app_gallery");

        SnapshotParser<InstitutionGalleryData> snapshotParser = new SnapshotParser<InstitutionGalleryData>() {

            HashMap<String, ArrayList<String>> categoryWithImages = new HashMap<>();
            HashMap<String, ArrayList<String>> categoryWithDescription = new HashMap<>();
            HashMap<String, ArrayList<String>> categoryWithIds = new HashMap<>();

            @NonNull
            @Override
            public InstitutionGalleryData parseSnapshot(@NonNull DataSnapshot snapshot) {

                ArrayList<String> images = new ArrayList<>();
                ArrayList<String> description = new ArrayList<>();
                ArrayList<String> ids = new ArrayList<>();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    String image = postSnapshot.child("image_url").getValue(String.class);
                    String desc = postSnapshot.child("image_description").getValue(String.class);
                    String time = postSnapshot.getKey();

                    images.add(image);
                    description.add(desc);
                    ids.add(time);

                    categoryWithImages.put(snapshot.getKey(), images);
                    categoryWithDescription.put(snapshot.getKey(), description);
                    categoryWithIds.put(snapshot.getKey(), ids);

                }

                InstitutionGalleryData galleryData = new  InstitutionGalleryData();
                galleryData.setCategoryWithImages(categoryWithImages);
                galleryData.setCategoryWithDescription(categoryWithDescription);
                galleryData.setCategoryWithIds(categoryWithIds);

                return galleryData;
            }
        };

        return new FirebaseRecyclerOptions.Builder<InstitutionGalleryData>().setQuery(query, snapshotParser).build();

    }

    @Override
    public InstitutionActivitiesData getInstitutionActivitiesData() {

        InstitutionActivitiesData activitiesData = new InstitutionActivitiesData();
        String[] categories = {"Name first", "Name second", "Name third"};
        ArrayList<Integer> firstImages = new ArrayList<>();
        ArrayList<Integer> secondImages = new ArrayList<>();
        ArrayList<Integer> thirdImages = new ArrayList<>();
        ArrayList<String> firstDesc = new ArrayList<>();
        ArrayList<String> secondDesc = new ArrayList<>();
        ArrayList<String> thirdDesc = new ArrayList<>();

        return activitiesData;
    }

    private String slogan;
    @Override
    public String getSlogan(String id) {

        mDatabase.getReference("clients").child(id).child("slogan").addValueEventListener(new ValueEventListener() {
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
    public FirebaseRecyclerOptions<InstitutionsListItemParent> getInstitutionLists(int category) {

        Query query = mDatabase.getReference().child("clients").orderByChild("category").equalTo(category);

        SnapshotParser<InstitutionsListItemParent> snapshotParser = new SnapshotParser<InstitutionsListItemParent>() {
            @NonNull
            @Override
            public InstitutionsListItemParent parseSnapshot(@NonNull DataSnapshot snapshot) {

                InstitutionsListItemParent collegeList = new InstitutionsListItemParent();

                collegeList.setId(snapshot.getKey());
                collegeList.setName(snapshot.child("name").getValue(String.class));
                collegeList.setIcon_image(snapshot.child("icon_image").getValue(String.class));
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

        return new FirebaseRecyclerOptions.Builder<InstitutionsListItemParent>().setQuery(query, snapshotParser).build();

    }

    @Override
    public DatabaseError sendContactUsMessage(String id, final InstitutionContactData contactData) {

        final DatabaseError[] error = new DatabaseError[1];
        mDatabase.getReference().child("clients").child(id).child("app_contact").push().setValue(contactData, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                error[0] = databaseError;
            }

        });

        return error[0];

    }

}
