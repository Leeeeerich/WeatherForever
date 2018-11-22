
package com.guralnya.weatherforever.model.objects.dto.countries;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("iso")
    @Expose
    private String iso;
    @SerializedName("iso3")
    @Expose
    private String iso3;
    @SerializedName("isoNumeric")
    @Expose
    private String isoNumeric;
    @SerializedName("fips")
    @Expose
    private String fips;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("phonePrefix")
    @Expose
    private List<String> phonePrefix = null;
    @SerializedName("postalCodeFormat")
    @Expose
    private String postalCodeFormat;
    @SerializedName("postalCodeRegex")
    @Expose
    private String postalCodeRegex;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("externalIds")
    @Expose
    private ExternalIds externalIds;
    @SerializedName("localizedNames")
    @Expose
    private LocalizedNames localizedNames;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public List<String> getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(List<String> phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    public String getPostalCodeFormat() {
        return postalCodeFormat;
    }

    public void setPostalCodeFormat(String postalCodeFormat) {
        this.postalCodeFormat = postalCodeFormat;
    }

    public String getPostalCodeRegex() {
        return postalCodeRegex;
    }

    public void setPostalCodeRegex(String postalCodeRegex) {
        this.postalCodeRegex = postalCodeRegex;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public ExternalIds getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIds externalIds) {
        this.externalIds = externalIds;
    }

    public LocalizedNames getLocalizedNames() {
        return localizedNames;
    }

    public void setLocalizedNames(LocalizedNames localizedNames) {
        this.localizedNames = localizedNames;
    }

}
