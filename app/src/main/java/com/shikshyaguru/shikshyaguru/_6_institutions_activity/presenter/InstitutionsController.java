
package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.app.ActivityOptions;

import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionViewInterface;

public class InstitutionsController {

    private InstitutionViewInterface viewInterface;
    private InstitutionDataSourceInterface dataSource;

    public InstitutionsController(InstitutionDataSourceInterface dataSource) {
        this.dataSource = dataSource;
    }

    public InstitutionsController(InstitutionViewInterface viewInterface, InstitutionDataSourceInterface dataSource) {
        this.viewInterface = viewInterface;
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

}