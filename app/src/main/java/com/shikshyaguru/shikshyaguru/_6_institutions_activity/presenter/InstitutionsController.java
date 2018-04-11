
package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.app.ActivityOptions;

import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionMainInterface;

public class InstitutionsController {

    private InstitutionMainInterface viewInterface;
    private InstitutionLoaderInterface loaderInterface;
    private InstitutionDataSourceInterface dataSource;

    public InstitutionsController(InstitutionMainInterface viewInterface, InstitutionDataSourceInterface dataSource) {
        this.viewInterface = viewInterface;
        this.dataSource = dataSource;
    }

    public InstitutionsController(InstitutionLoaderInterface loaderInterface, InstitutionDataSourceInterface dataSource) {
        this.loaderInterface = loaderInterface;
        this.dataSource = dataSource;
    }

    public void fetchInstitutionList(int category) {
        viewInterface.setUpInstitutionAdapter(dataSource.getInstitutionLists(category));
    }

    public String getSlogan(String id) {
        return dataSource.getSlogan(id);
    }

    public void onInstitutionItemClick(InstitutionsListItemParent instDetails, ActivityOptions options) {
        viewInterface.openInstitutionDetails(instDetails, options);
    }

    public void validateAndProceedFavBtn(String id) {
        dataSource.validateAndProceedFavBtn(loaderInterface, id);
    }

    public void fabNavigateBtnClick() {
        loaderInterface.navigateDirection();
    }

    public void fabReviewBtnClick() {
        loaderInterface.reviewInstitution();
    }

    public void fabSuggestBtnClick() {
        loaderInterface.suggestFriends();
    }

    public void validateAndProceedReportBtn(String id) {
        dataSource.validateAndProceedReportBtn(loaderInterface, id);
    }



}