package com.moribitotech.and.fw.managers;

import com.moribitotech.mtx.managers.SettingsManager;

public class TutorialManager
{
  private static final String HEADLINE = "TUTORIAL";
  private static final String KEY_BOTTOM_FIRE = "firstTimeBottomFire";
  private static final String KEY_CORRECT_GUESS = "firstTimeCorrectGuess";
  private static final String KEY_EXTINGUISHER = "firstHintButton";
  private static final String KEY_HINT_BUTTON = "firstHintButton";
  private static final String KEY_INTRO = "firstTimeIntro";
  private static final String KEY_RESET_BUTTON = "firstTimeResetButton";
  private static final String KEY_WRONG_GUESS = "firstTimeWrongGuess";
  private static final String MSG_BOTTOM_FIRE_1 = "Red bar grows when you touch bottom fire";
  private static final String MSG_BOTTOM_FIRE_2 = "You will be punished if touch a lot...";
  private static final String MSG_CORRECT_GUESS_1 = "Well done, try the new word";
  private static final String MSG_CORRECT_GUESS_2 = "Also each time you guess correct blue water bar will grow";
  private static final String MSG_EXTINGUISHER = "Extinguisher will exinguish bottom fire, so you touch less";
  private static final String MSG_HINT_BUTTON = "Hints will show you letter index starting from 1";
  private static final String MSG_INTRO = "Letters are shuffled, try to guess the word";
  private static final String MSG_RESET_BUTTON = "Each time you reset, bottom fire will grow";
  private static final String MSG_WRONG_GUESS = "Each time you guess wrong, bottom fire will grow";
  private static final float NOTIFICATIN_DURATION = 10.0F;
  private GameManager gameManager;
  private boolean isBottomFireDone = false;
  private boolean isCorrectGuessDone = false;
  private boolean isExtinghuisherDone = false;
  private boolean isHintButtonDone = false;
  private boolean isIntroDone = false;
  private boolean isResetButtonDone = false;
  private boolean isWrongGuessDone = false;
  /**
   * 帮助界面
   * @param paramGameManager
   */
  public TutorialManager(GameManager paramGameManager)
  {
    this.gameManager = paramGameManager;
    cacheFromAndroidPrefs();
  }

  private void cacheFromAndroidPrefs()
  {
    this.isIntroDone = isFirstTime("firstTimeIntro");
    this.isCorrectGuessDone = isFirstTime("firstTimeCorrectGuess");
    this.isWrongGuessDone = isFirstTime("firstTimeWrongGuess");
    this.isResetButtonDone = isFirstTime("firstTimeResetButton");
    this.isBottomFireDone = isFirstTime("firstTimeBottomFire");
    this.isHintButtonDone = isFirstTime("firstHintButton");
    this.isExtinghuisherDone = isFirstTime("firstHintButton");
  }

  private boolean isFirstTime(String paramString)
  {
    return SettingsManager.getBooleanPrefValue(paramString, false).booleanValue();
  }

  private void sendNotification(String paramString)
  {
    this.gameManager.getNotificationManager().sendNotification(10.0F, "TUTORIAL", new String[] { paramString });
  }

  private void setFirstTimeDone(String paramString)
  {
    SettingsManager.setBooleanPrefValue(paramString, true);
  }

  public void showTutorialBottomFireTouched()
  {
    if (!this.isBottomFireDone)
    {
      this.gameManager.getNotificationManager().sendNotification(10.0F, "TUTORIAL", new String[] { "Red bar grows when you touch bottom fire", "You will be punished if touch a lot..." });
      setFirstTimeDone("firstTimeBottomFire");
      this.isBottomFireDone = true;
    }
  }

  public void showTutorialCorrectGuess()
  {
    if (!this.isCorrectGuessDone)
    {
      this.gameManager.getNotificationManager().sendNotification(10.0F, "TUTORIAL", new String[] { "Well done, try the new word", "Also each time you guess correct blue water bar will grow" });
      setFirstTimeDone("firstTimeCorrectGuess");
      this.isCorrectGuessDone = true;
    }
  }

  public void showTutorialExtinguisher()
  {
    if (!this.isExtinghuisherDone)
    {
      sendNotification("Extinguisher will exinguish bottom fire, so you touch less");
      setFirstTimeDone("firstHintButton");
      this.isExtinghuisherDone = true;
    }
  }

  public void showTutorialHintButton()
  {
    if (!this.isHintButtonDone)
    {
      sendNotification("Hints will show you letter index starting from 1");
      setFirstTimeDone("firstHintButton");
      this.isHintButtonDone = true;
    }
  }

  public void showTutorialIntro()
  {
    if (!this.isIntroDone)
    {
      sendNotification("Letters are shuffled, try to guess the word");
      setFirstTimeDone("firstTimeIntro");
      this.isIntroDone = true;
    }
  }

  public void showTutorialResetButtonTouched()
  {
    if (!this.isResetButtonDone)
    {
      sendNotification("Each time you reset, bottom fire will grow");
      setFirstTimeDone("firstTimeResetButton");
      this.isResetButtonDone = true;
    }
  }

  public void showTutorialWrongGuess()
  {
    if (!this.isWrongGuessDone)
    {
      sendNotification("Each time you guess wrong, bottom fire will grow");
      setFirstTimeDone("firstTimeWrongGuess");
      this.isWrongGuessDone = true;
    }
  }
}