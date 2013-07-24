package com.moribitotech.and.fw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.moribitotech.and.fw.assets.Assets;
import com.moribitotech.and.fw.managers.GameIOManager;
import com.moribitotech.and.fw.screens.LoadingScreen;
import com.moribitotech.mtx.android.IAndroidObject;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.managers.SettingsManager;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.settings.MtxLogger;

public class MainStarter extends AbstractGame
{
  public MainStarter(IAndroidObject paramIAndroidObject)
  {
    setAndroidObject(paramIAndroidObject);
  }

  public void create()
  {
    super.create();
  }

  public void setUpAppSettings()
  {
    AppSettings.setUp(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 960.0F, 540.0F);
    MtxLogger.setLogs(true);
    if (!SettingsManager.isFirstLaunchDone()) 
    {
      GameIOManager.createFirstLaunchFiles();
      SettingsManager.setMusic(true);
      SettingsManager.setSound(true);
      SettingsManager.setVibration(true);
      SettingsManager.setFirstLaunchDone(true);
    }
  }

  public void setUpAssets()
  {
    Assets localAssets = new Assets();
    localAssets.loadAll();
    setAssets(localAssets);
  }

  public void setUpLoadingScreen()
  {
    setScreen(new LoadingScreen(this, "Loading Screen"));
  }
}