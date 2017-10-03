package com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.presenter;

import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model.SearchDataInterface;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.views.SearchViewInterface;

/**
 * Created by ROOT on 9/24/2017.
 */

public class SearchController {

    private SearchViewInterface searchViewInterface;
    private SearchDataInterface searchDataInterface;

    public SearchController(SearchViewInterface searchViewInterface, SearchDataInterface searchDataInterface) {
        this.searchViewInterface = searchViewInterface;
        this.searchDataInterface = searchDataInterface;
    }

}
