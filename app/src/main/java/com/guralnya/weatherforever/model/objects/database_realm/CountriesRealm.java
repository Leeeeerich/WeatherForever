package com.guralnya.weatherforever.model.objects.database_realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CountriesRealm extends RealmObject {

    private String mCountryName;
    private String mCountryIso;

    @PrimaryKey
    private String mCountryIsoNumeric;

    public CountriesRealm() {
    }

    public CountriesRealm(String countryName, String countryIso, String mCountryIsoNumeric) {
        this.mCountryName = countryName;
        this.mCountryIso = countryIso;
        this.mCountryIsoNumeric = mCountryIsoNumeric;
    }

    public String getCountryName() {
        return mCountryName;
    }

    public void setCountryName(String countryName) {
        this.mCountryName = countryName;
    }

    public String getCountryIso() {
        return mCountryIso;
    }

    public void setCountryIso(String countryIso) {
        this.mCountryIso = countryIso;
    }

    public String getCountryIsoNumeric() {
        return mCountryIsoNumeric;
    }

    public void setCountryIsoNumeric(String countryIsoNumeric) {
        this.mCountryIsoNumeric = countryIsoNumeric;
    }
}
