package com.moribitotech.and.fw.managers;

import com.moribitotech.and.fw.models.Letter;
import com.moribitotech.and.fw.models.Word;
import com.moribitotech.and.fw.screens.GameScreen;
import com.moribitotech.and.fw.screens.helpers.GameScreenButtons;

public class HintManager
{
  private static final String HINT_HEADLINE = "Hints";
  private static final String HINT_NONE_LEFT = "No hint left, Try to earn through achievements";
  private static final float HINT_NOTIFICATION_DURATION = 4.0F;
  private static final int MAXIMUM_HINT = 999;
//  private static final int MINUMUM_HINT;
  private GameManager gameManager;
  private int numberOfHints;

  public HintManager(GameManager paramGameManager)
  {
    this.gameManager = paramGameManager;
    this.numberOfHints = 0;
  }

  public void decreaseHint(int paramInt)
  {
    if (this.numberOfHints - paramInt >= 0)
    {
      this.numberOfHints -= paramInt;
      return;
    }
  }

  public int getNumberOfHints()
  {
    return this.numberOfHints;
  }

  public void increaseHint(int paramInt)
  {
    if (paramInt + this.numberOfHints <= 999)
    {
      this.numberOfHints = (paramInt + this.numberOfHints);
      EffectManager.runHintIncreasedEffect(this.gameManager.getGameScreen().getGameScreenButtons().getBtnHint());
      return;
    }
    this.numberOfHints = 999;
  }

  public void setNumberOfHints(int paramInt)
  {
    if (paramInt <= 999)
    {
      this.numberOfHints = paramInt;
      return;
    }
  }

  public void showHint(Word paramWord)
  {
    if (this.numberOfHints > 0)
      for (int i = 0; ; i++)
      {
        if (i >= paramWord.getLetters().length)
          return;
        Letter localLetter = paramWord.getLetters()[i];
        if ((!localLetter.isTouched()) && (!localLetter.isHintActive()))
        {
          localLetter.enableHint();
          decreaseHint(1);
          return;
        }
      }
    this.gameManager.getNotificationManager().sendNotification(4.0F, "Hints", new String[] { "No hint left, Try to earn through achievements" });
  }
}