
package com.example.nasa.src.main.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class TechTransfer {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("page")
    private Long mPage;
    @SerializedName("perpage")
    private Long mPerpage;
    @SerializedName("results")
    private List<List<String>> mResults;
    @SerializedName("total")
    private Long mTotal;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public Long getPerpage() {
        return mPerpage;
    }

    public void setPerpage(Long perpage) {
        mPerpage = perpage;
    }

    public List<List<String>> getResults() {
        return mResults;
    }

    public void setResults(List<List<String>> results) {
        mResults = results;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }

}
