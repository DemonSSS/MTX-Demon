package com.moribitotech.and.fw.managers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.moribitotech.and.fw.buttons.GameHintButton;
import com.moribitotech.and.fw.models.Letter;
import com.moribitotech.and.fw.screens.GameScreen;
import com.moribitotech.and.fw.screens.helpers.GameScreenMenu;
import com.moribitotech.and.fw.world.WorldLayer1_Game;
import com.moribitotech.mtx.scene2d.effects.EffectCreator;

public class EffectManager
{
  private static final float GAME_MENU_DELAY_DURATION = 0.3F;
  private static final float GAME_MENU_MOVE_DURATION = 0.3F;
  private static final float GENERAL_BUTTON_EFFECT_DURATION = 0.1F;
  private static final float GENERAL_BUTTON_SCALE = 1.1F;
  private static final float LETTER_TOUCH_DOWN_DURATION = 0.2F;
  private static final float NEW_LETTER_DELAY = 0.1F;
  private static final float NEW_LETTER_DELAY_INITIAL = 0.2F;
  private static final float NEW_LETTER_SCALE_DURATION = 0.1F;
  private static final float NOTIFICATION_MOVE_DURATION = 0.5F;

  public static void runGameMenuSlideEffect(GameScreen paramGameScreen, boolean paramBoolean)
  {
    WorldLayer1_Game localWorldLayer1_Game = paramGameScreen.getGameManager().getWorldLayer1_Game();
    Group localGroup = paramGameScreen.getGameScreenMenu().getGroup();
    localWorldLayer1_Game.clearActions();
    localGroup.clearActions();
    if (paramBoolean)
    {
      localWorldLayer1_Game.addAction(Actions.sequence(Actions.delay(0.0F), Actions.moveTo(localWorldLayer1_Game.getX(), 0.0F - localWorldLayer1_Game.getHeight(), 0.3F)));
      localGroup.addAction(Actions.sequence(Actions.delay(0.3F), Actions.moveTo(localGroup.getX(), 0.0F, 0.3F)));
      return;
    }
    localGroup.addAction(Actions.sequence(Actions.delay(0.0F), Actions.moveTo(localGroup.getX(), localGroup.getHeight(), 0.3F)));
    localWorldLayer1_Game.addAction(Actions.sequence(Actions.delay(0.3F), Actions.moveTo(localWorldLayer1_Game.getX(), 0.0F, 0.3F)));
  }

  public static void runGameMenuToggleButtonEffect(Actor paramActor, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramActor.addAction(Actions.rotateTo(0.0F, 0.5F));
      return;
    }
    paramActor.addAction(Actions.rotateTo(180.0F, 0.5F));
  }

  public static void runGeneralButtonEffect(Actor paramActor)
  {
    paramActor.clearActions();
    EffectCreator.create_SC_BTO(paramActor, 1.1F, 1.1F, 0.1F, null, false);
  }

  public static void runHintIncreasedEffect(GameHintButton paramGameHintButton)
  {
    paramGameHintButton.clearActions();
    paramGameHintButton.addAction(Actions.sequence(Actions.scaleTo(0.5F, 0.5F, 0.2F), Actions.rotateTo(360.0F, 0.25F), Actions.scaleTo(1.0F, 1.0F, 0.2F), Actions.rotateTo(0.0F)));
  }

  public static void runLetterTouchDownEffect(Actor paramActor)
  {
    EffectCreator.create_SC_FO(paramActor, 0.0F, 0.0F, 0.2F, 0.0F, null, false);
  }

  public static void runLoadingIntroEffect(Stage paramStage, Actor paramActor1, Actor paramActor2, float paramFloat)
  {
    paramActor1.setPosition(paramStage.getWidth() + paramActor1.getWidth(), paramActor1.getHeight() / 2.0F);
    paramActor2.setPosition(0.0F - paramActor2.getWidth(), paramActor2.getHeight() / 2.0F);
    float f1 = paramActor1.getY();
    float f2 = paramStage.getWidth() - paramActor1.getWidth() - paramActor1.getWidth() / 2.0F;
    EffectCreator.create_MT(paramActor1, paramFloat, 0.0F, f2, f1, null, false);
    EffectCreator.create_MT(paramActor2, paramFloat, 0.0F, f2 - paramActor2.getWidth(), f1, null, false);
  }

  public static void runNewNotificationEffect(Table paramTable, float paramFloat)
  {
    paramTable.addAction(Actions.sequence(Actions.moveTo(paramTable.getX(), 0.0F, 0.5F), Actions.delay(paramFloat), Actions.moveTo(paramTable.getX(), 0.0F - paramTable.getHeight(), 0.5F)));
  }

  public static void runNewWordIntroEffect(Letter[] paramArrayOfLetter)
  {
    float f = 0.2F;
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfLetter.length)
        return;
      paramArrayOfLetter[i].addAction(Actions.sequence(Actions.delay(f), Actions.scaleTo(1.18F, 1.18F, 0.1F), Actions.scaleTo(1.0F, 1.0F, 0.1F)));
      f += 0.1F;
    }
  }
}