package com.moribitotech.and.fw.screens.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.esotericsoftware.tablelayout.Cell;
import com.moribitotech.and.fw.data.GlobalData;
import com.moribitotech.and.fw.managers.EffectManager;
import com.moribitotech.and.fw.managers.GameManager;
import com.moribitotech.and.fw.managers.HintManager;
import com.moribitotech.and.fw.managers.NotificationManager;
import com.moribitotech.and.fw.screens.GameScreen;
import com.moribitotech.and.fw.screens.MainMenuScreen;
import com.moribitotech.mtx.android.IAndroidIntents;
import com.moribitotech.mtx.android.IAndroidObject;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.managers.MusicManager;
import com.moribitotech.mtx.managers.SettingsManager;
import com.moribitotech.mtx.scene2d.models.EmptyActorLight;
import com.moribitotech.mtx.scene2d.ui.ButtonLight;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.utils.UtilsOrigin;
import com.moribitotech.mtx.utils.UtilsOrigin.Origin;

public class GameScreenMenu
{
  private static final String ALREADY_USED = "You have already used this :(";
  private static final String HEADLINE_1 = "Sorry,";
  private static final String KEY_FOLLOWUSTWETTER = "followUsTwitter";
  private static final String KEY_LIKEUSFACEBOOK = "likeUsFacebook";
  private static final String KEY_TWEETABOUTUS = "tweetAboutUs";
  private ButtonLight btnFollowTwitter;
  private ButtonLight btnFreeAchievementHints;
  private ButtonLight btnHome;
  private ButtonLight btnLikeUsFacebook;
  private ButtonLight btnMusic;
  private ButtonLight btnRateUs;
  private ButtonLight btnSound;
  private ButtonLight btnSwarmAchievements;
  private ButtonLight btnSwarmLeaderBoard;
  private ButtonLight btnTweetAboutUs;
  private ButtonLight btnVibrate;
  private GameScreen gameScreen;
  private Group group;
  private EmptyActorLight labelFreeHints;
  private Table tableLeft;
  private Table tableRight;

  public GameScreenMenu(GameScreen paramGameScreen)
  {
    this.gameScreen = paramGameScreen;
    setUp();
  }

  private void earnHints(int paramInt)
  {
    this.gameScreen.getGameManager().getHintManager().increaseHint(paramInt);
  }

  private boolean isUsed(String paramString)
  {
    return SettingsManager.getBooleanPrefValue(paramString, false).booleanValue();
  }

  private void sendNotification(String paramString)
  {
    this.gameScreen.getGameManager().getNotificationManager().sendNotification(3.0F, "Sorry,", new String[] { paramString });
  }

  private void setFeatureUsed(String paramString)
  {
    SettingsManager.setBooleanPrefValue(paramString, true);
  }

  private void setUp()
  {
    this.group = new Group();
    this.group.setSize(AppSettings.SCREEN_W, AppSettings.SCREEN_H);
    this.group.setY(AppSettings.SCREEN_H);
    this.tableLeft = new Table();
    this.tableLeft.setFillParent(true);
    this.tableLeft.left();
    this.tableRight = new Table();
    this.tableRight.setFillParent(true);
    this.tableRight.right().bottom();
    setUpButtons();
    this.group.addActor(this.tableLeft);
    this.group.addActor(this.tableRight);
    this.gameScreen.getStage().addActor(this.group);
  }

  private void setUpButtons()
  {
    this.labelFreeHints = new EmptyActorLight(400.0F, 100.0F, true);
    this.labelFreeHints.setTextureRegion(this.gameScreen.getAtlas().findRegion("img_label_free_hints"), true);
    this.btnLikeUsFacebook = new ButtonLight(400.0F, 100.0F, this.gameScreen.getAtlas().findRegion("img_label_likeusfacebook"), true);
    this.btnFollowTwitter = new ButtonLight(400.0F, 100.0F, this.gameScreen.getAtlas().findRegion("img_label_followustweeter"), true);
    this.btnTweetAboutUs = new ButtonLight(400.0F, 100.0F, this.gameScreen.getAtlas().findRegion("img_label_tweetaboutus"), true);
    this.btnFreeAchievementHints = new ButtonLight(400.0F, 100.0F, this.gameScreen.getAtlas().findRegion("img_label_freeachievementhints"), true);
    this.btnSwarmLeaderBoard = new ButtonLight(250.0F, 250.0F, this.gameScreen.getAtlas().findRegion("img_btn_leaderboard"), true);
    this.btnSwarmAchievements = new ButtonLight(250.0F, 250.0F, this.gameScreen.getAtlas().findRegion("img_btn_achievements"), true);
    this.btnRateUs = new ButtonLight(500.0F, 125.0F, this.gameScreen.getAtlas().findRegion("img_btn_rateus"), true);
    this.btnHome = new ButtonLight(125.0F * 0.5F, 125.0F * 0.5F, this.gameScreen.getAtlas().findRegion("img_btn_home"), true);
    this.btnMusic = new ButtonLight(125.0F * 0.5F, 125.0F * 0.5F, this.gameScreen.getAtlas().findRegion("img_btn_music"), true);
    this.btnSound = new ButtonLight(125.0F * 0.5F, 125.0F * 0.5F, this.gameScreen.getAtlas().findRegion("img_btn_sound"), true);
    this.btnVibrate = new ButtonLight(125.0F * 0.5F, 125.0F * 0.5F, this.gameScreen.getAtlas().findRegion("img_btn_vibrate"), true);
    UtilsOrigin.Origin localOrigin = UtilsOrigin.Origin.CENTER;
    Actor[] arrayOfActor = new Actor[11];
    arrayOfActor[0] = this.btnLikeUsFacebook;
    arrayOfActor[1] = this.btnFollowTwitter;
    arrayOfActor[2] = this.btnTweetAboutUs;
    arrayOfActor[3] = this.btnFreeAchievementHints;
    arrayOfActor[4] = this.btnSwarmLeaderBoard;
    arrayOfActor[5] = this.btnSwarmAchievements;
    arrayOfActor[6] = this.btnRateUs;
    arrayOfActor[7] = this.btnHome;
    arrayOfActor[8] = this.btnMusic;
    arrayOfActor[9] = this.btnSound;
    arrayOfActor[10] = this.btnVibrate;
    UtilsOrigin.setActorsOrigin(localOrigin, arrayOfActor);
    this.btnMusic.setToggleActive(SettingsManager.isMusicOn());
    this.btnSound.setToggleActive(SettingsManager.isSoundOn());
    this.btnVibrate.setToggleActive(SettingsManager.isVibrationOn());
    EffectManager.runGameMenuToggleButtonEffect(this.btnMusic, SettingsManager.isMusicOn());
    EffectManager.runGameMenuToggleButtonEffect(this.btnSound, SettingsManager.isSoundOn());
    EffectManager.runGameMenuToggleButtonEffect(this.btnVibrate, SettingsManager.isVibrationOn());
    setUpTableRightButtonListeners();
    setUpTableLeftButtonListeners();
    this.tableLeft.add(this.labelFreeHints).left().row();
    this.tableLeft.add(this.btnLikeUsFacebook).left().row();
    this.tableLeft.add(this.btnFollowTwitter).left().row();
    this.tableLeft.add(this.btnTweetAboutUs).left().row();
    this.tableLeft.add(this.btnFreeAchievementHints).left().row();
    this.tableRight.add(this.btnHome).center();
    this.tableRight.add(this.btnMusic).center();
    this.tableRight.add(this.btnSound).center();
    this.tableRight.add(this.btnVibrate).center().row();
    this.tableRight.add(this.btnSwarmLeaderBoard).colspan(Integer.valueOf(2)).center();
    this.tableRight.add(this.btnSwarmAchievements).colspan(Integer.valueOf(2)).center().row();
    this.tableRight.add(this.btnRateUs).colspan(Integer.valueOf(4)).center();
  }

  private void setUpTableLeftButtonListeners()
  {
    this.btnLikeUsFacebook.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Gdx.net.openURI("https://www.facebook.com/MoribitoTech");
        if (!GameScreenMenu.this.isUsed("likeUsFacebook"))
        {
          GameScreenMenu.this.earnHints(10);
          GameScreenMenu.this.setFeatureUsed("likeUsFacebook");
          return;
        }
        GameScreenMenu.this.sendNotification("You have already used this :(");
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenMenu.this.btnLikeUsFacebook);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnFollowTwitter.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Gdx.net.openURI("https://twitter.com/MoribitoTech");
        if (!GameScreenMenu.this.isUsed("followUsTwitter"))
        {
          GameScreenMenu.this.earnHints(10);
          GameScreenMenu.this.setFeatureUsed("followUsTwitter");
          return;
        }
        GameScreenMenu.this.sendNotification("You have already used this :(");
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenMenu.this.btnFollowTwitter);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnTweetAboutUs.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        GameScreenMenu.this.gameScreen.getGame().getAndroidObject().getAndroidIntents().startShareIntent("Share Flame Words;", "Superp Word Game!", "Check this awsome word game + link...");
        if (!GameScreenMenu.this.isUsed("tweetAboutUs"))
        {
          GameScreenMenu.this.earnHints(5);
          GameScreenMenu.this.setFeatureUsed("tweetAboutUs");
          return;
        }
        GameScreenMenu.this.sendNotification("You have already used this :(");
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenMenu.this.btnTweetAboutUs);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnFreeAchievementHints.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
//        SwarmManager.showAchievements();
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenMenu.this.btnFreeAchievementHints);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
  }

  private void setUpTableRightButtonListeners()
  {
    this.btnSwarmLeaderBoard.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
//        SwarmManager.showLeaderBodards();
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenMenu.this.btnSwarmLeaderBoard);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnSwarmAchievements.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
//        SwarmManager.showAchievements();
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenMenu.this.btnSwarmAchievements);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnRateUs.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.moribitotech.android.flamewords");
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenMenu.this.btnRateUs);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnHome.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        MainMenuScreen localMainMenuScreen = new MainMenuScreen(GameScreenMenu.this.gameScreen.getGame(), "Main Menu Screen");
        localMainMenuScreen.getStage().addAction(Actions.fadeOut(0.0F));
        AlphaAction localAlphaAction1 = Actions.fadeOut(0.5F);
        AlphaAction localAlphaAction2 = Actions.fadeIn(0.5F);
        GameScreenMenu.this.gameScreen.getGame().setScreenWithTransition(GameScreenMenu.this.gameScreen, localAlphaAction1, localMainMenuScreen, localAlphaAction2, true);
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenMenu.this.btnHome);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnMusic.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        GameScreenMenu.this.btnMusic.setToggleSwitch();
        if (GameScreenMenu.this.btnMusic.isToggleActive())
        {
          SettingsManager.setMusic(true);
          EffectManager.runGameMenuToggleButtonEffect(GameScreenMenu.this.btnMusic, true);
          GameScreenMenu.this.gameScreen.getGame().getMusicManager().playShuffle(GlobalData.MUSIC_BACKGROUND_VOLUME);
          return;
        }
        SettingsManager.setMusic(false);
        EffectManager.runGameMenuToggleButtonEffect(GameScreenMenu.this.btnMusic, false);
        GameScreenMenu.this.gameScreen.getGame().getMusicManager().stopMusic();
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnSound.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        GameScreenMenu.this.btnSound.setToggleSwitch();
        if (GameScreenMenu.this.btnSound.isToggleActive())
        {
          SettingsManager.setSound(true);
          EffectManager.runGameMenuToggleButtonEffect(GameScreenMenu.this.btnSound, true);
          return;
        }
        SettingsManager.setSound(false);
        EffectManager.runGameMenuToggleButtonEffect(GameScreenMenu.this.btnSound, false);
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnVibrate.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        GameScreenMenu.this.btnVibrate.setToggleSwitch();
        if (GameScreenMenu.this.btnVibrate.isToggleActive())
        {
          SettingsManager.setVibration(true);
          EffectManager.runGameMenuToggleButtonEffect(GameScreenMenu.this.btnVibrate, true);
          return;
        }
        SettingsManager.setVibration(false);
        EffectManager.runGameMenuToggleButtonEffect(GameScreenMenu.this.btnVibrate, false);
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
  }

  public Group getGroup()
  {
    return this.group;
  }
}