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

public class CollegesHomeFakeDataSource implements CollegesHomeDataSource {

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

    @Override
    public List<CollegesHomeNewsAndEventsData> getCollegesHomeNewsAndEventData() {
        ArrayList<CollegesHomeNewsAndEventsData> listOfData = new ArrayList<>();

        for (String newsAndEvents : NEWS_AND_EVENTS) {
            CollegesHomeNewsAndEventsData newsAndEventsData = new
                    CollegesHomeNewsAndEventsData(newsAndEvents);
            listOfData.add(newsAndEventsData);
        }

        return listOfData;
    }

    @Override
    public List<CollegeHomeIntroData> getCollegeHomeIntroData() {
        ArrayList<CollegeHomeIntroData> listOfData = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {

            randOne = random.nextInt(3);
            randTwo = random.nextInt(3);
            randThree = random.nextInt(3);

            CollegeHomeIntroData collegeHomeIntroData = new CollegeHomeIntroData(
                    INTRO_IMAGE[randOne],
                    INTRO_HEADING[randTwo],
                    INTRO[randThree]
            );

            listOfData.add(collegeHomeIntroData);
        }

        return listOfData;
    }

}
