package com.mio.jrdv.kidstimergenerator;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by joseramondelgado on 02/11/16.
 *
 * http://stackoverflow.com/questions/13558550/can-i-get-data-from-shared-preferences-inside-a-service
 *
 * For example, if you need access to your preferences somewhere else, you may call this to read preferences:

 String str = MyApplication.preferences.getString( KEY, DEFAULT );


 Or you may call this to save something to the preferences:

 MyApplication.preferences.edit().putString( KEY, VALUE ).commit();


 (don't forget to call commit() after adding or changing preferences!)
 */

public class Myapplication extends Application {
    public static SharedPreferences preferences;
    public static  final String PREF_FOTO_PATH_KID1="PrefFotoKid1";
    public static  final String PREF_FOTO_PATH_KID2="PrefFotoKid2";
    public static  final String PREF_FOTO_PATH_KID3="PrefFotoKid3";

    public static  final String PREF_NAME_KID1="PrefNameKid1";
    public static  final String PREF_NAME_KID2="PrefNameKid2";
    public static  final String PREF_NAME_KID3="PrefNameKid3";



    @Override
    public void onCreate() {
        super.onCreate();

        preferences = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
    }
}

