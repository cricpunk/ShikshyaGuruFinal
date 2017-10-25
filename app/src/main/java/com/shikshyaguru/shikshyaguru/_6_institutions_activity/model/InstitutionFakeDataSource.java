package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InstitutionFakeDataSource implements InstitutionDataSourceInterface {

    private final int SIZE = 10;
    private Random random = new Random();
    private int randOne;
    private int randTwo;
    private int randThree;

    final String[] NEWS_AND_EVENTS = {
            "News and events from college 1", "News and events from college 1",
            "News and events from college 1", "News and events from college 1",
            "News and events from college 1", "News and events from college 1"
    };
    final String[] INTRO_HEADING = {
            "Introduction",
            "CEO Message",
            "For Students"
    };
    final String[] INTRO = {
            String.valueOf(R.string.demo_introduction),
            String.valueOf(R.string.demo_introduction),
            String.valueOf(R.string.demo_introduction)
    };
    final int[] INTRO_IMAGE = {
            R.drawable.slider1, R.drawable.slider1, R.drawable.slider1
    };
    final String[] RATING = {"4.2", "3.5", "4.8"};
    final String[] REVIEW_HEADING = {"Very nice", "Worthy joining", "Useless college"};
    final String[] REVIEW = {String.valueOf(R.string.review), String.valueOf(R.string.review), String.valueOf(R.string.review)};
    final String[] USER_NAME = {"Pankaj Koirala", "Bidhya Sapkota", "Neymar Jr."};
    final String[] REVIEW_DATE = {"7 Aug, 2016", "17 Aug, 2016", "24 Jan, 2017"};
    final int[] LIKE_COUNT = {125, 569, 920};
    final int[] DISLIKE_COUNT = {25, 69, 20};

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
    public List<TeachersData> getTeachersData() {
        return null;
    }

    @Override
    public List<StaffData> getStaffData() {
        return null;
    }

}
