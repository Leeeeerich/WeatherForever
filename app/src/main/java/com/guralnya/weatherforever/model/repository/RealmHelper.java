package com.guralnya.weatherforever.model.repository;

public class RealmHelper {

    private RealmHelper(){}
    private static RealmHelper mRealmHelperInstance;

    public static RealmHelper getRealmHelperInstance() {
        if (mRealmHelperInstance == null){
            mRealmHelperInstance = new RealmHelper();
        }
        return mRealmHelperInstance;
    }


}
