
package com.guralnya.weatherforever.model.objects.dto.countries;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountriesDto {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

}
