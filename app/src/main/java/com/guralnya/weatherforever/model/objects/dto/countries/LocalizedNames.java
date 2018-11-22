
package com.guralnya.weatherforever.model.objects.dto.countries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalizedNames {

    @SerializedName("en")
    @Expose
    private String en;
    @SerializedName("nl")
    @Expose
    private String nl;
    @SerializedName("ru")
    @Expose
    private String ru;
    @SerializedName("uk")
    @Expose
    private String uk;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }

}
