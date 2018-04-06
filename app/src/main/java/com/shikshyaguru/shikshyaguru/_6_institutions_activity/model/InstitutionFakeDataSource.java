package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shikshyaguru.shikshyaguru.R;

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

    private final String[] NEWS_AND_EVENTS = {
            "News and events from college 1", "News and events from college 1",
            "News and events from college 1", "News and events from college 1",
            "News and events from college 1", "News and events from college 1"
    };
    private final String[] INTRO_HEADING = {
            "Introduction",
            "CEO Message",
            "For Students"
    };
    private final String[] INTRO = {
            String.valueOf(R.string.demo_introduction),
            String.valueOf(R.string.demo_introduction),
            String.valueOf(R.string.demo_introduction)
    };
    private final int[] INTRO_IMAGE = {
            R.drawable.slider1, R.drawable.slider1, R.drawable.slider1
    };
    private final String[] NAME = {
            "Mr.Sulav Budhathoki","Dr. Benson Soong","Mr. Kelvin Ng"
    };
    private final String[] ACADEMIC_QUALIFICATION = {
            "PhD","MBA","MBA"
    };
    private final String[] INSTITUTIONS = {
            "Cambridge","Cambridge","LMU"
    };
    private final String[] POST = {
            "Executive Chairman","Chief Academic Officer","Chief Operating Officer"
    };
    private final int[] MY_IMAGE = {
            R.drawable.me, R.drawable.me, R.drawable.me
    };

    private final int[] IMAGES = {
            R.drawable.model1, R.drawable.model2, R.drawable.model3,
            R.drawable.model4, R.drawable.model5, R.drawable.me
    };

    private final String[] RATING = {"4.2", "3.5", "4.8"};
    private final String[] REVIEW_HEADING = {"Very nice", "Worthy joining", "Useless college"};
    private final String[] REVIEW = {String.valueOf(R.string.review), String.valueOf(R.string.review), String.valueOf(R.string.review)};
    private final String[] USER_NAME = {"Pankaj Koirala", "Bidhya Sapkota", "Neymar Jr."};
    private final String[] REVIEW_DATE = {"7 Aug, 2016", "17 Aug, 2016", "24 Jan, 2017"};
    private final int[] LIKE_COUNT = {125, 569, 920};
    private final int[] DISLIKE_COUNT = {25, 69, 20};

    @Override
    public List<InstitutionHomeNewsAndEventsData> getInstitutionHomeNewsAndEventData() {
        ArrayList<InstitutionHomeNewsAndEventsData> listOfData = new ArrayList<>();

        for (String newsAndEvents : NEWS_AND_EVENTS) {
            InstitutionHomeNewsAndEventsData newsAndEventsData = new
                    InstitutionHomeNewsAndEventsData(newsAndEvents);
            listOfData.add(newsAndEventsData);
        }

        return listOfData;
    }

    @Override
    public List<InstitutionHomeIntroData> getInstitutionHomeIntroData() {
        ArrayList<InstitutionHomeIntroData> listOfData = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {

            randOne = random.nextInt(3);
            randTwo = random.nextInt(3);
            randThree = random.nextInt(3);

            InstitutionHomeIntroData institutionHomeIntroData = new InstitutionHomeIntroData(
                    INTRO_IMAGE[randOne],
                    INTRO_HEADING[randTwo],
                    INTRO[randThree]
            );

            listOfData.add(institutionHomeIntroData);
        }

        return listOfData;
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
    public List<InstitutionTeachersData> getTeachersData() {
        return null;
    }

    @Override
    public List<InstitutionStaffData> getStaffData() {
        return null;
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
    public List<InstitutionManagementData> getInstitutionManagementData() {
        List<InstitutionManagementData> listOfData = new ArrayList<>();

        for (int i = 0; i < SIZE ; i++) {

            randOne = random.nextInt(3);
            randTwo = random.nextInt(3);
            randThree = random.nextInt(3);
            int randFour = random.nextInt(3);
            int randFive = random.nextInt(3);
            int randSix = random.nextInt(3);

            InstitutionManagementData managementData = new InstitutionManagementData(
                    NAME[randOne],POST[randTwo],ACADEMIC_QUALIFICATION[randThree],
                    INSTITUTIONS[randFour],INTRO[randFive],MY_IMAGE[randSix]
            );

            listOfData.add(managementData);

        }
        return listOfData;
    }

    @Override
    public List<InstitutionStudentAlumniData> getListOfStudentAlumniData() {
        return null;
    }

    @Override
    public InstitutionGalleryData getInstitutionGalleryData() {
        InstitutionGalleryData galleryData = new InstitutionGalleryData();

        ArrayList<Integer> category1 = new ArrayList<>();
        ArrayList<Integer> category2 = new ArrayList<>();
        ArrayList<Integer> category3 = new ArrayList<>();
        ArrayList<Integer> category4 = new ArrayList<>();
        ArrayList<Integer> category5 = new ArrayList<>();
        ArrayList<Integer> category6 = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            randOne = random.nextInt(6);
            category1.add(IMAGES[randOne]);
        }

        for (int i = 0; i < 47; i++) {
            randOne = random.nextInt(6);
            category2.add(IMAGES[randOne]);
        }

        for (int i = 0; i < 40; i++) {
            randOne = random.nextInt(6);
            category3.add(IMAGES[randOne]);
        }

        for (int i = 0; i < 22; i++) {
            randOne = random.nextInt(6);
            category4.add(IMAGES[randOne]);
        }

        for (int i = 0; i < 37; i++) {
            randOne = random.nextInt(6);
            category5.add(IMAGES[randOne]);
        }

        for (int i = 0; i < 15; i++) {
            randOne = random.nextInt(6);
            category6.add(IMAGES[randOne]);
        }

        HashMap<String, ArrayList<Integer>> categoriesWithImages = new HashMap<>();

        categoriesWithImages.put("Student life", category1);
        categoriesWithImages.put("College", category2);
        categoriesWithImages.put("Activities", category3);
        categoriesWithImages.put("Events", category4);
        categoriesWithImages.put("Entertainment", category5);
        categoriesWithImages.put("Extra", category6);

        galleryData.setCategoryWithImages(categoriesWithImages);

        return galleryData;
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

}
