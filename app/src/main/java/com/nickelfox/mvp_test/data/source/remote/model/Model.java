
package com.nickelfox.mvp_test.data.source.remote.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Model {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;

    @SerializedName("articles")
    @Expose
    private List<SamachaarModel> samachaars = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<SamachaarModel> getSamachaars() {
        return samachaars;
    }

    public void setSamachaars(List<SamachaarModel> samachaars) {
        this.samachaars = samachaars;
    }

}
