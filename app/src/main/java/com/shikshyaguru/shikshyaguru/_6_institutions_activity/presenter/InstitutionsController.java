package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.CollegesHomeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionViewInterface;

public class InstitutionsController {

    private InstitutionViewInterface viewInterface;
    private CollegesHomeDataSource dataSource;

    public InstitutionsController(InstitutionViewInterface viewInterface, CollegesHomeDataSource dataSource) {
        this.viewInterface = viewInterface;
        this.dataSource = dataSource;

        setUpNewsAndEvents();
        setUpHomeIntro();
    }

    private void setUpNewsAndEvents(){
        viewInterface.setUpNewsAdapterAndView(dataSource.getCollegesHomeNewsAndEventData());
    }

    private void setUpHomeIntro() {
        viewInterface.setUpHomeIntroAdapterAndView(dataSource.getCollegeHomeIntroData());
    }
}
