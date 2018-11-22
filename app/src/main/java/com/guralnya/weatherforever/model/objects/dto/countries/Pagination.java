
package com.guralnya.weatherforever.model.objects.dto.countries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("totalPageCount")
    @Expose
    private Integer totalPageCount;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

}
