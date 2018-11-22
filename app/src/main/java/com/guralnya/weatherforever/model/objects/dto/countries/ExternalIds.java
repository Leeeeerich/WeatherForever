
package com.guralnya.weatherforever.model.objects.dto.countries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalIds {

    @SerializedName("geonames")
    @Expose
    private String geonames;

    public String getGeonames() {
        return geonames;
    }

    public void setGeonames(String geonames) {
        this.geonames = geonames;
    }

}
