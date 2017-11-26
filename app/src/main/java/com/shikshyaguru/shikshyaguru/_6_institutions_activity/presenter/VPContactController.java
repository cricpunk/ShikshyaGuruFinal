package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerContactInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPContactController {

    private ViewPagerContactInterface contactInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPContactController(ViewPagerContactInterface contactInterface, InstitutionDataSourceInterface dataSource) {
        this.contactInterface = contactInterface;
        this.dataSource = dataSource;
    }

}
