
package com.hncgc1990.refreshloadmore.data;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class PostData <T> {

    @SerializedName("error")
    private Boolean mError;
    @SerializedName("results")
    private  T mResults;

    public Boolean getError() {
        return mError;
    }

    public void setError(Boolean error) {
        mError = error;
    }

    public T getResults() {
        return mResults;
    }

    public void setResults(T results) {
        mResults = results;
    }


    @Override
    public String toString() {
        return "PostData{" +
                "mError=" + mError +
                ", mResults=" + mResults +
                '}';
    }
}
