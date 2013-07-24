package com.moribitotech.and.fw.managers;

import com.moribitotech.and.fw.data.GameData;
import com.moribitotech.and.fw.data.PlayerData;
import com.moribitotech.mtx.managers.SettingsManager;

public class AchievementManager
{
  private static final int A_10_STAGE = 7500;
  private static final int A_11_STAGE = 8000;
  private static final int A_12_BURN = 50;
  private static final int A_13_BURN = 100;
  private static final int A_14_BURN = 1000;
  private static final int A_15_CORRECT_GUESS = 50;
  private static final int A_1_STAGE = 25;
  private static final int A_2_STAGE = 50;
  private static final int A_3_STAGE = 100;
  private static final int A_4_STAGE = 250;
  private static final int A_5_STAGE = 500;
  private static final int A_6_STAGE = 750;
  private static final int A_7_STAGE = 1000;
  private static final int A_8_STAGE = 2500;
  private static final int A_9_STAGE = 5000;
  private static final int R_10_HINT = 75;
  private static final int R_11_HINT = 0;
  private static final int R_12_HINT = 3;
  private static final int R_13_HINT = 6;
  private static final int R_14_HINT = 10;
  private static final int R_15_HINT = 10;
  private static final int R_1_HINT = 2;
  private static final int R_2_HINT = 3;
  private static final int R_3_HINT = 5;
  private static final int R_4_HINT = 7;
  private static final int R_5_HINT = 10;
  private static final int R_6_HINT = 15;
  private static final int R_7_HINT = 25;
  private static final int R_8_HINT = 35;
  private static final int R_9_HINT = 50;
  private boolean a_10_done = false;
  private boolean a_11_done = false;
  private boolean a_12_done = false;
  private boolean a_13_done = false;
  private boolean a_14_done = false;
  private boolean a_15_done = false;
  private boolean a_1_done = false;
  private boolean a_2_done = false;
  private boolean a_3_done = false;
  private boolean a_4_done = false;
  private boolean a_5_done = false;
  private boolean a_6_done = false;
  private boolean a_7_done = false;
  private boolean a_8_done = false;
  private boolean a_9_done = false;
  private int correctGuessInARow = 0;
  private GameManager gameManager;

  public AchievementManager(GameManager paramGameManager)
  {
    this.gameManager = paramGameManager;
    cacheAchivementsFromAndroidPrefs();
    this.correctGuessInARow = 0;
  }

  private void cacheAchivementsFromAndroidPrefs()
  {
    this.a_1_done = isAchievementDone("13749");
    this.a_2_done = isAchievementDone("13751");
    this.a_3_done = isAchievementDone("13753");
    this.a_4_done = isAchievementDone("13755");
    this.a_5_done = isAchievementDone("13757");
    this.a_6_done = isAchievementDone("13759");
    this.a_7_done = isAchievementDone("13761");
    this.a_8_done = isAchievementDone("13763");
    this.a_9_done = isAchievementDone("13765");
    this.a_10_done = isAchievementDone("13767");
    this.a_11_done = isAchievementDone("13769");
    this.a_12_done = isAchievementDone("13771");
    this.a_13_done = isAchievementDone("13773");
    this.a_14_done = isAchievementDone("13775");
    this.a_15_done = isAchievementDone("13779");
  }

  private boolean isAchievementDone(String paramString)
  {
    return SettingsManager.getBooleanPrefValue(paramString, false).booleanValue();
  }

  private void setAchievementDone(String paramString)
  {
    SettingsManager.setBooleanPrefValue(paramString, true);
  }

  public void checkCorrectGuessAchievements()
  {
    if ((!this.a_15_done) && (this.correctGuessInARow >= 50) )
    {
      this.gameManager.getHintManager().increaseHint(10);
      setAchievementDone("13779");
      this.a_15_done = true;
    }
  }

  public void checkFireTouchAchievements()
  {
    if ((!this.a_12_done) && (this.gameManager.getGameData().getFireBurnNumber() >= 50))
    {
      this.gameManager.getHintManager().increaseHint(3);
      setAchievementDone("13771");
      this.a_12_done = true;
    }
    if ((!this.a_13_done) && (this.gameManager.getGameData().getFireBurnNumber() >= 100))
    {
      this.gameManager.getHintManager().increaseHint(6);
      setAchievementDone("13773");
      this.a_13_done = true;
    }
    if ((!this.a_14_done) && (this.gameManager.getGameData().getFireBurnNumber() >= 1000))
    {
      this.gameManager.getHintManager().increaseHint(10);
      setAchievementDone("13775");
      this.a_14_done = true;
    }
  }

  public void checkStageAchievements()
  {
    if ((!this.a_1_done) && (this.gameManager.getPlayer().getStage() >= 25) )
    {
      this.gameManager.getHintManager().increaseHint(2);
      setAchievementDone("13749");
      this.a_1_done = true;
    }
    if ((!this.a_2_done) && (this.gameManager.getPlayer().getStage() >= 50) )
    {
      this.gameManager.getHintManager().increaseHint(3);
      setAchievementDone("13751");
      this.a_2_done = true;
    }
    if ((!this.a_3_done) && (this.gameManager.getPlayer().getStage() >= 100) )
    {
      this.gameManager.getHintManager().increaseHint(5);
      setAchievementDone("13753");
      this.a_3_done = true;
    }
    if ((!this.a_4_done) && (this.gameManager.getPlayer().getStage() >= 250) )
    {
      this.gameManager.getHintManager().increaseHint(7);
      setAchievementDone("13755");
      this.a_4_done = true;
    }
    if ((!this.a_5_done) && (this.gameManager.getPlayer().getStage() >= 500))
    {
      this.gameManager.getHintManager().increaseHint(10);
      setAchievementDone("13757");
      this.a_5_done = true;
    }
    if ((!this.a_6_done) && (this.gameManager.getPlayer().getStage() >= 750) )
    {
      this.gameManager.getHintManager().increaseHint(15);
      setAchievementDone("13759");
      this.a_6_done = true;
    }
    if ((!this.a_7_done) && (this.gameManager.getPlayer().getStage() >= 1000) )
    {
      this.gameManager.getHintManager().increaseHint(25);
      setAchievementDone("13761");
      this.a_7_done = true;
    }
    if ((!this.a_8_done) && (this.gameManager.getPlayer().getStage() >= 2500))
    {
      this.gameManager.getHintManager().increaseHint(35);
      setAchievementDone("13763");
      this.a_8_done = true;
    }
    if ((!this.a_9_done) && (this.gameManager.getPlayer().getStage() >= 5000) )
    {
      this.gameManager.getHintManager().increaseHint(50);
      setAchievementDone("13765");
      this.a_9_done = true;
    }
    if ((!this.a_10_done) && (this.gameManager.getPlayer().getStage() >= 7500))
    {
      this.gameManager.getHintManager().increaseHint(75);
      setAchievementDone("13767");
      this.a_10_done = true;
    }
    if ((!this.a_11_done) && (this.gameManager.getPlayer().getStage() >= 8000))
    {
      this.gameManager.getHintManager().increaseHint(0);
      setAchievementDone("13769");
      this.a_11_done = true;
    }
  }

  public int getCorrectGuessInARow()
  {
    return this.correctGuessInARow;
  }

  public void increaseCorrectGuessInARow()
  {
    this.correctGuessInARow = (1 + this.correctGuessInARow);
  }

  public void resetCorrectGuessInARow()
  {
    this.correctGuessInARow = 0;
  }
}