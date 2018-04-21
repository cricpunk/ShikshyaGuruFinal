package com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.model;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

public class InstitutionsSuggestion implements SearchSuggestion {

    private String instId;
    private String institutionName;
    private boolean mIsHistory = false;


    public InstitutionsSuggestion(String instId, String institutionName) {
        this.instId = instId;
        this.institutionName = institutionName;
    }

    public InstitutionsSuggestion(String suggestion) {
        this.institutionName = suggestion.toLowerCase();
    }

    private InstitutionsSuggestion(Parcel source) {
        this.institutionName = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return institutionName;
    }

    public String getInstId() {
        return instId;
    }

    public static final Creator<InstitutionsSuggestion> CREATOR = new Creator<InstitutionsSuggestion>() {

        @Override
        public InstitutionsSuggestion createFromParcel(Parcel in) {
            return new InstitutionsSuggestion(in);
        }

        @Override
        public InstitutionsSuggestion[] newArray(int size) {
            return new InstitutionsSuggestion[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(institutionName);
        dest.writeInt(mIsHistory ? 1 : 0);
    }

}