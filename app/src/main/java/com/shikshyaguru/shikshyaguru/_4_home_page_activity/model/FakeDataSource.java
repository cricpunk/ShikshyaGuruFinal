package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;


import com.shikshyaguru.shikshyaguru.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class FakeDataSource implements DataSourceInterface {

    //private static final int SIZE_OF_NEWS_COLLECTION = 10;
    private static final int SIZE_OF_INSTITUTIONS_COLLECTION = 20;
    private Random random = new Random();

    private final int[] DRAWER_MAIN_HEADER_ICONS = {
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black,
            R.drawable.ic_mail_black
    };

    private final String[] DRAWER_MAIN_HEADER = {
            "Heading 1",
            "Heading 2",
            "Heading 3",
            "Heading 4",
            "Heading 5",
            "Heading 6",
            "Heading 1",
            "Heading 2",
            "Heading 3",
            "Heading 4",
            "Heading 5",
            "Heading 6"
    };

    private final int[] HP_SLIDER_IMAGE = {
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example,
            R.drawable.example
    };

    private final String[] SLIDER_CANDIDATES_NAME = {
            "Candidate 1",
            "Candidate 2",
            "Candidate 3",
            "Candidate 4",
            "Candidate 5",
            "Candidate 6",
            "Candidate 7",
            "Candidate 8",
            "Candidate 9",
            "Candidate 10"
    };

    private final String[] OPTIONS = {
            "Top Categories",
            "Nepal",
            "User Choice",
            "Country",
            "Editor Choice"
    };

    private final String[] NEWS_HEADLINES = {
            "Islington college introduced new courses for the computing student",
            "Herald college introduced new courses for the computing student",
            "Prime college introduced new courses for the computing student",
            "ABC college introduced new courses for the computing student",
            "XYZ college introduced new courses for the computing student",
            "Herald college introduced new courses for the computing student",
            "Herald college introduced new courses for the computing student",
            "Prime college introduced new courses for the computing student",
            "ABC college introduced new courses for the computing student",
            "XYZ college introduced new courses for the computing student",
            "Herald college introduced new courses for the computing student",
            "Prime college introduced new courses for the computing student"
    };

    private final int[] INSTITUTIONS_ICON = {
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius
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

    private final int[] COLLEGES_ICON = {
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius,
            R.drawable.bg_radius
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
    public List<HomePageSliderListItem> getListOfSliderCandidates() {
        ArrayList<HomePageSliderListItem> listOfData = new ArrayList<>();
        for (int i = 0; i < SLIDER_CANDIDATES_NAME.length; i++) {
            int randOne = random.nextInt(4);
            HomePageSliderListItem homePageSliderListItem = new HomePageSliderListItem(
                    HP_SLIDER_IMAGE[i],
                    SLIDER_CANDIDATES_NAME[i],
                    INSTITUTIONS_CITY_NAME[randOne]
            );
            listOfData.add(homePageSliderListItem);
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
    public List<NewsListItem> getListOfNewsData() {

        ArrayList<NewsListItem> listOfNewsData = new ArrayList<>();

        for (String NEWS_HEADLINE : NEWS_HEADLINES) {
            NewsListItem newsListItem = new NewsListItem(
                    NEWS_HEADLINE
            );

            listOfNewsData.add(newsListItem);
        }

        return listOfNewsData;
    }

    @Override
    public List<CollegeListItem> getListOfCollegesData() {

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
    public List<ListOfInstitutionsHeading> getTotalInstitutionsHeading() {

        ArrayList<ListOfInstitutionsHeading> listOfInstitutionsHeadings = new ArrayList<>();

        for (int i = 0; i < INSTITUTIONS_HEADING.length; i++) {

            ListOfInstitutionsHeading listOfInstitutionsHeading = new ListOfInstitutionsHeading(
                    INSTITUTIONS_HEADING[i],
                    INSTITUTIONS_HEADING_ID[i],
                    RELATED_INSTITUTION_DATA[i]
            );

            listOfInstitutionsHeadings.add(listOfInstitutionsHeading);
        }

        return listOfInstitutionsHeadings;
    }


}
