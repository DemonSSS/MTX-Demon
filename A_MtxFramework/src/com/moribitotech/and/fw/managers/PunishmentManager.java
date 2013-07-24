package com.moribitotech.and.fw.managers;

import com.moribitotech.and.fw.data.PlayerData;
import com.moribitotech.and.fw.data.PuzzleLevel;
import com.moribitotech.and.fw.models.BottomFire;
import com.moribitotech.and.fw.models.InformationBox;
import com.moribitotech.and.fw.models.Letter;
import com.moribitotech.and.fw.models.Meter;
import com.moribitotech.and.fw.models.Word;
import com.moribitotech.and.fw.world.WorldLayer1_Game;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.utils.UtilsRandomizer;
import java.util.Random;

public class PunishmentManager
{
  private static final int DECREASE_LEVEL_RANGE = 10;
  private static final int DECREASE_SCORE_RANGE = 20;
  private static final String HEADLINE_DOUBLE = "DOUBLE PUNISHMENT";
  private static final String HEADLINE_SINGLE = "PUNISHMENT";
  private static final int MAX_RANDOM = 100;
  private static final int MIN_RANDOM = 0;
  private static final float NOTIFICATION_DURATION = 4.0F;
  private static final String P1 = "Go couple of stages back";
  private static final String P2 = "I got all water of yours";
  private static final String P3 = "Floating letters";
  private static final String P4 = "Cutting some score";
  private static final String P5 = "Extreme fire";
  private GameManager gameManager;
  private Random rnd = new Random();

  public PunishmentManager(GameManager paramGameManager)
  {
    this.gameManager = paramGameManager;
  }

  private void punishDecraaseScore()
  {
    int i = UtilsRandomizer.getRandomInclusive(this.rnd, 1, 20);
    this.gameManager.getPlayer().decreaseScore(i);
    this.gameManager.getWorldLayer1_Game().getInformationBox().setScore(this.gameManager.getPlayer().getScore());
  }

  private void punishDecreaseLevel()
  {
    int i = this.gameManager.getCurrentLevel().getPuzzleLevelNumber();
    if (i > 10)
    {
      int j = UtilsRandomizer.getRandomInclusive(this.rnd, 1, 10);
      this.gameManager.startLevel(i - j);
      return;
    }
    this.gameManager.startLevel(1);
  }

  private void punishFloatingLetters()
  {
    Word localWord = this.gameManager.getCurrentLevel().getWord();
    for (int i = 0; ; i++)
    {
      if (i >= localWord.getLetters().length)
        return;
      Letter localLetter = localWord.getLetters()[i];
      localLetter.startActionMoveFreely(4, (int)AppSettings.WORLD_WIDTH - (int)localLetter.getWidth(), (int)AppSettings.WORLD_HEIGHT - (int)localLetter.getHeight(), 3.0F);
    }
  }

  private void punishIncreaseFireLevel()
  {
    float f = this.gameManager.getWorldLayer1_Game().getBottomFire().getGeneralScale();
    this.gameManager.getWorldLayer1_Game().getBottomFire().doGrow(f / 2.0F, 0.2F);
  }

  private void punishRemoveExtinghuisher()
  {
    this.gameManager.getWorldLayer1_Game().getMeterWater().reset();
    this.gameManager.getWorldLayer1_Game().deactivateBtnFireExtinguisher();
  }

  public void punish()
  {
    int i = UtilsRandomizer.getRandomInclusive(this.rnd, 0, 100);
    if (i < 10)
    {
      punishDecreaseLevel();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "PUNISHMENT", new String[] { "Go couple of stages back" });
      return;
    }
    if (i < 20)
    {
      punishRemoveExtinghuisher();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "PUNISHMENT", new String[] { "I got all water of yours" });
      return;
    }
    if (i < 30)
    {
      punishDecraaseScore();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "PUNISHMENT", new String[] { "Cutting some score" });
      return;
    }
    if (i < 80)
    {
      punishFloatingLetters();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "PUNISHMENT", new String[] { "Floating letters" });
      return;
    }
    if (i < 90)
    {
      punishIncreaseFireLevel();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "PUNISHMENT", new String[] { "Extreme fire" });
      return;
    }
    if (i < 92)
    {
      punishIncreaseFireLevel();
      punishDecraaseScore();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "DOUBLE PUNISHMENT", new String[] { "Go couple of stages back", "Cutting some score" });
      return;
    }
    if (i < 94)
    {
      punishRemoveExtinghuisher();
      punishIncreaseFireLevel();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "DOUBLE PUNISHMENT", new String[] { "I got all water of yours", "Extreme fire" });
      return;
    }
    if (i < 96)
    {
      punishFloatingLetters();
      punishIncreaseFireLevel();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "DOUBLE PUNISHMENT", new String[] { "Floating letters", "Extreme fire" });
      return;
    }
    if (i < 98)
    {
      punishDecraaseScore();
      punishRemoveExtinghuisher();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "DOUBLE PUNISHMENT", new String[] { "Cutting some score", "I got all water of yours" });
      return;
    }
    if (i <= 100)
    {
      punishDecraaseScore();
      punishIncreaseFireLevel();
      this.gameManager.getNotificationManager().sendNotification(4.0F, "DOUBLE PUNISHMENT", new String[] { "Extreme fire", "Cutting some score" });
      return;
    }
    punishFloatingLetters();
    this.gameManager.getNotificationManager().sendNotification(4.0F, "PUNISHMENT", new String[] { "Floating letters" });
  }
}