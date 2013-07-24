package com.moribitotech.mtx;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.moribitotech.and.fw.MainStarter;
import com.moribitotech.mtx.android.IAdController;
import com.moribitotech.mtx.android.IAndroidIntents;
import com.moribitotech.mtx.android.IAndroidObject;
import com.moribitotech.mtx.android.IScoreLoop;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true;
        
        initialize(new MainStarter(null), cfg);
    }
}