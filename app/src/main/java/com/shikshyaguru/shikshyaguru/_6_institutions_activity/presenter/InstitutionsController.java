
package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;

public class InstitutionsController {

    private InstitutionFakeDataSource dataSource;

    public InstitutionsController(InstitutionFakeDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getSlogan(String id) {
        return dataSource.getSlogan(id);
    }

}