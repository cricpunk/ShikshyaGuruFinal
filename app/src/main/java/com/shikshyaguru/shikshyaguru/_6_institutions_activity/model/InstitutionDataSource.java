package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesLevelInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerReviewInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class InstitutionDataSource implements InstitutionDataSourceInterface {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private String uId = NavigationDrawerFragment.currentUser.getUid();

    @SuppressLint("SimpleDateFormat")
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
    private String currentTime = dateFormat.format(Calendar.getInstance().getTime());

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
    public void getInstitutionProgrammesData(final ViewPagerProgrammesLevelInterface programmesInterface) {

        final List<String> levelList = new ArrayList<>();
        final HashMap<String, List<String>> levelFaculties = new HashMap<>();

        Query query = mDatabase.getReference().child("clients/A8RAbxdfC7Mcak1Ot9iIOSe2Oiq1/app_programmes");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot level : dataSnapshot.getChildren()) {
                    //+2, bachelors, masters
                    levelList.add(level.getKey());

                    List<String> facultyList = new ArrayList<>();
                    for (DataSnapshot faculties : level.getChildren()) {
                        //Programmes i.e, management. science etc.
                        facultyList.add(faculties.getKey());
                    }

                    levelFaculties.put(level.getKey(), facultyList);

                }

                InstitutionProgrammesData programmesData = new InstitutionProgrammesData();
                programmesData.setProgrammesLevelNameList(levelList);
                programmesData.setProgrammesCoursesNameList(levelFaculties);

                programmesInterface.setUpProgrammesLevel(programmesData);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
    public void getProgrammeCourses(final ViewPagerProgrammesCoursesInterface coursesFragmentInterface, String id, final String level, final String faculty) {

        final List<String> coursesList = new ArrayList<>();

        Query query = mDatabase.getReference().child("clients/A8RAbxdfC7Mcak1Ot9iIOSe2Oiq1/app_programmes/"+ level +"/"+ faculty);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot level : dataSnapshot.getChildren()) {
                    //BBA, MBA
                    coursesList.add(level.getKey());

                }

                InstitutionProgrammesData programmesData = new InstitutionProgrammesData();
                programmesData.setProgrammesCourses(coursesList);

                coursesFragmentInterface.setUpProgrammesCourses(programmesData);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void getCourseLoaderData(final ViewPagerProgrammesCoursesLoaderInterface coursesLoaderFragmentInterface, String level, String faculty, String programme) {

        System.out.println("========================================================");
        System.out.println(level+":"+faculty+":"+programme);

        final List<String> optionSemList = new ArrayList<>();
        final HashMap<String, List<HashMap<String, String>>> subjectCollection = new HashMap<>();
        final HashMap<String, List<HashMap<String, String>>> feeCollection = new HashMap<>();


        Query query = mDatabase.getReference().child("clients/A8RAbxdfC7Mcak1Ot9iIOSe2Oiq1/app_programmes/"+ level +"/"+ faculty +"/"+ programme);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println(dataSnapshot.getKey());

                for (DataSnapshot optSem : dataSnapshot.getChildren()) {

                    System.out.println(optSem.getKey());

                    //Option-1, SEM-1
                    optionSemList.add(optSem.getKey());

                    List<HashMap<String, String>> subjectList = new ArrayList<>();
                    List<HashMap<String, String>> feeList = new ArrayList<>();

                    for (DataSnapshot subjects : optSem.child("subjects").getChildren()) {
                        HashMap<String, String> subject = new HashMap<>();

                        subject.put("subject", subjects.child("subject_name").getValue(String.class));
                        subject.put("code", subjects.child("code").getValue(String.class));
                        subject.put("teacher", subjects.child("teacher").getValue(String.class));
                        subject.put("timing", subjects.child("timing").getValue(String.class));

                        subjectList.add(subject);
                    }

                    for (DataSnapshot fees : optSem.child("fees").getChildren()) {
                        HashMap<String, String> fee = new HashMap<>();

                        fee.put("type", fees.child("fee_type").getValue(String.class));
                        fee.put("amount", fees.child("amount").getValue(String.class));

                        feeList.add(fee);
                    }

                    subjectCollection.put(optSem.getKey(), subjectList);
                    feeCollection.put(optSem.getKey(), feeList);

                }

                InstitutionProgrammesData programmesData = new InstitutionProgrammesData();


                System.out.println(optionSemList);
                System.out.println(subjectCollection);
                System.out.println(feeCollection);

                programmesData.setOptionSemList(optionSemList);
                programmesData.setSubjectCollection(subjectCollection);
                programmesData.setFeeCollection(feeCollection);

                coursesLoaderFragmentInterface.setUpProgrammesCourses(programmesData);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

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
    public FirebaseRecyclerOptions<InstitutionsListItemParent> getInstitutionLists(int category, final ArrayList<String> favouriteInstitutions) {

        // If request is from favourite list then use different query
        if (category == NavigationDrawerFragment.FAVOURITE_CATEGORY ) {

            Query query = mDatabase.getReference().child("clients");

            SnapshotParser<InstitutionsListItemParent> snapshotParser = new SnapshotParser<InstitutionsListItemParent>() {

                @NonNull
                @Override
                public InstitutionsListItemParent parseSnapshot(@NonNull DataSnapshot snapshot) {

                    InstitutionsListItemParent favouriteInst = new InstitutionsListItemParent();

                    if (favouriteInstitutions.contains(snapshot.getKey())) {
                        favouriteInst.setId(snapshot.getKey());
                        favouriteInst.setName(snapshot.child("profile/name").getValue(String.class));
                        favouriteInst.setIcon_image(snapshot.child("profile/icon_image").getValue(String.class));
                        favouriteInst.setCity(snapshot.child("address/city").getValue(String.class));
                        Double rating = snapshot.child("app_reviews").child("overall_rating").getValue(Double.class);
                        if (rating != null) {
                            favouriteInst.setRating(String.valueOf(rating) + "*");
                        } else {
                            favouriteInst.setRating("n/a");
                        }
                    }

                    return favouriteInst;
                }
            };

            return new FirebaseRecyclerOptions.Builder<InstitutionsListItemParent>().setQuery(query, snapshotParser).build();

        } else {

            Query query = mDatabase.getReference().child("clients").orderByChild("profile/category").equalTo(category);

            SnapshotParser<InstitutionsListItemParent> snapshotParser = new SnapshotParser<InstitutionsListItemParent>() {
                @NonNull
                @Override
                public InstitutionsListItemParent parseSnapshot(@NonNull DataSnapshot snapshot) {

                    InstitutionsListItemParent collegeList = new InstitutionsListItemParent();

                    collegeList.setId(snapshot.getKey());
                    collegeList.setName(snapshot.child("profile/name").getValue(String.class));
                    collegeList.setIcon_image(snapshot.child("profile/icon_image").getValue(String.class));
                    collegeList.setCity(snapshot.child("address/city").getValue(String.class));
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

    @Override
    public void getInstitutionRatingsData(String id, final ViewPagerReviewInterface reviewInterface) {

        Query query = mDatabase.getReference().child("clients").child(id).child("app_reviews");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int totalReviewsCount = 0;

                int fiveStar = 0;
                int fourStar = 0;
                int threeStar = 0;
                int twoStar = 0;
                int oneStar = 0;

                int instRatingCount = 0;
                int eduRatingCount = 0;
                int infraRatingCount = 0;
                int mgmtRatingCount = 0;
                int techRatingCount = 0;

                int overallInstitutionRating = 0;
                int overallEducationRating = 0;
                int overallInfrastructureRating = 0;
                int overallManagementRating = 0;
                int overallTeachersRating = 0;

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Long institution = postSnapshot.child("institution").getValue(Long.class);
                    Long education = postSnapshot.child("education").getValue(Long.class);
                    Long infrastructure = postSnapshot.child("infrastructure").getValue(Long.class);
                    Long management = postSnapshot.child("management").getValue(Long.class);
                    Long teachers = postSnapshot.child("teachers").getValue(Long.class);

                    assert institution != null;
                    overallInstitutionRating += institution.intValue();
                    instRatingCount += 1;
                    switch (institution.intValue()) {
                        case 5: fiveStar++; break;
                        case 4: fourStar++; break;
                        case 3: threeStar++; break;
                        case 2: twoStar++; break;
                        case 1: oneStar++; break;
                        default: break;
                    }

                    assert education != null;
                    overallEducationRating += education.intValue();
                    eduRatingCount += 1;

                    assert infrastructure != null;
                    overallInfrastructureRating += infrastructure.intValue();
                    infraRatingCount += 1;

                    assert management != null;
                    overallManagementRating += management.intValue();
                    mgmtRatingCount += 1;

                    assert teachers != null;
                    overallTeachersRating += teachers.intValue();
                    techRatingCount += 1;

                    String review = postSnapshot.child("comment").getValue(String.class);

                    assert review != null;
                    if (!review.equals("")) {
                        totalReviewsCount ++;
                    }

                    System.out.println(institution + " : " + education + " : " + infrastructure  + " : " + management  + " : " + teachers);
                }

                System.out.println("Total rating :" + instRatingCount);
                System.out.println("Total reviews :" + totalReviewsCount);
                System.out.println(fiveStar+":"+fourStar+":"+threeStar+":"+twoStar+":"+oneStar);

                try {

                    double oaRating, oaEduRating, oaInfraRating, oaTechRating, oaMgmtRating;
                    oaRating = overallInstitutionRating / instRatingCount;
                    oaEduRating = overallEducationRating / eduRatingCount;
                    oaInfraRating = overallInfrastructureRating / infraRatingCount;
                    oaTechRating = overallTeachersRating / techRatingCount;
                    oaMgmtRating = overallManagementRating / mgmtRatingCount;

                    InstitutionRatingsData ratingsData = new InstitutionRatingsData();

                    ratingsData.setOverallRating(oaRating);
                    ratingsData.setEducationRating(oaEduRating);
                    ratingsData.setInfrastructureRating(oaInfraRating);
                    ratingsData.setTeachersRating(oaTechRating);
                    ratingsData.setManagementRating(oaMgmtRating);

                    ratingsData.setFiveStar(fiveStar);
                    ratingsData.setFourStar(fourStar);
                    ratingsData.setThreeStar(threeStar);
                    ratingsData.setTwoStar(twoStar);
                    ratingsData.setOneStar(oneStar);
                    ratingsData.setTotalRating(instRatingCount);
                    ratingsData.setTotalReviews(totalReviewsCount);

                    // setUpRatings method should be call from here otherwise data will not be display
                    // If we call this method normally view will be populated before firebase call back finish its task
                    // Which results empty data into views
                    // We cant call this method directly so we have to use interface to call this method
                    reviewInterface.setUpRatings(ratingsData);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // ...

            }
        });

    }

    @Override
    public FirebaseRecyclerOptions<InstitutionReviewsData> getInstitutionReviewData(String id) {

        Query query = mDatabase.getReference().child("clients").child(id).child("app_reviews");

        SnapshotParser<InstitutionReviewsData> snapshotParser = new SnapshotParser<InstitutionReviewsData>() {
            @NonNull
            @Override
            public InstitutionReviewsData parseSnapshot(@NonNull DataSnapshot snapshot) {

                InstitutionReviewsData reviewsData = new InstitutionReviewsData();

                Long rating = snapshot.child("institution").getValue(Long.class);
                Long like = snapshot.child("comment_like").getValue(Long.class);
                Long dislike = snapshot.child("comment_dislike").getValue(Long.class);

                reviewsData.setCommentId(snapshot.getKey());
                assert rating != null;
                reviewsData.setRating(rating.intValue());
                assert like != null;
                reviewsData.setComment_like(like.intValue());
                assert dislike != null;
                reviewsData.setComment_dislike(dislike.intValue());
                reviewsData.setHeading(snapshot.child("heading").getValue(String.class));
                reviewsData.setComment(snapshot.child("comment").getValue(String.class));
                reviewsData.setCommentedBy(getUserName(snapshot.getKey()));
                reviewsData.setPost_time(snapshot.child("post_time").getValue(String.class));

                return reviewsData;
            }
        };

        return new FirebaseRecyclerOptions.Builder<InstitutionReviewsData>().setQuery(query, snapshotParser).build();

    }

    @Override
    public void postUserReview(ViewPagerReviewInterface reviewInterface, String id, String uId, int instRating, int eduRating, int infraRating, int techRating, int mgmtRating, String comment) {

        HashMap<String, Object> reviewData = new HashMap<>();

        reviewData.put("comment", comment);
        reviewData.put("education", eduRating);
        reviewData.put("infrastructure", infraRating);
        reviewData.put("institution", instRating);
        reviewData.put("management", mgmtRating);
        reviewData.put("teachers", techRating);
        reviewData.put("comment_like", 0);
        reviewData.put("comment_dislike", 0);
        reviewData.put("post_time", currentTime);

        mDatabase.getReference().child("clients").child(id).child("app_reviews").child(uId).setValue(reviewData, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                //
            }
        });

    }

    private String getUserName(String uId) {

        final String name[] = new String[1];

        Query query = mDatabase.getReference().child("users").child(uId).child("profile");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name[0] = dataSnapshot.child("name").getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return name[0];
    }

    @Override
    public void validateAndProceedFavBtn(final InstitutionLoaderInterface loaderInterface, final String instId) {

        final DatabaseReference favRef = mDatabase.getReference().child("users").child(uId).child("favourites").child(instId);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    // Your item is in favourite list
                    loaderInterface.displaySnackbar("Institution is already in your favourite list.");
                } else {
                    // Add institution to favourite list
                    addToFavourite(loaderInterface, favRef);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                loaderInterface.displaySnackbar(databaseError.getMessage());
            }
        };
        favRef.addListenerForSingleValueEvent(eventListener);

    }

    private void addToFavourite(final InstitutionLoaderInterface loaderInterface, DatabaseReference favRef) {

        HashMap<String, String> favourite = new HashMap<>();
        favourite.put("added_on", currentTime);

        favRef.setValue(favourite, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError == null ) {
                    loaderInterface.displaySnackbar("Institution has been added to your favourite list.");

                } else {
                    loaderInterface.displaySnackbar(databaseError.getMessage());
                }

            }
        });

    }

    @Override
    public void validateAndProceedReportBtn(final InstitutionLoaderInterface loaderInterface, final String instId) {

        final DatabaseReference reportedInstRef = mDatabase.getReference().child("reported_institution").child(uId).child(instId);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    // Your item is in favourite list
                    loaderInterface.displaySnackbar("You have already reported this institution.");
                } else {
                    // Add institution to favourite list
                    reportInstitution(loaderInterface, reportedInstRef);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                loaderInterface.displaySnackbar(databaseError.getMessage());
            }

        };
        reportedInstRef.addListenerForSingleValueEvent(eventListener);

    }

    private void reportInstitution(final InstitutionLoaderInterface loaderInterface, DatabaseReference reportedInstitutionRef ) {

        HashMap<String, String> report = new HashMap<>();
        report.put("reported_on", currentTime);

        reportedInstitutionRef.setValue(report, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if (databaseError == null ) {
                    loaderInterface.displaySnackbar("Thank you for reporting this institution.");

                } else {
                    loaderInterface.displaySnackbar(databaseError.getMessage());
                }
            }

        });

    }


}
