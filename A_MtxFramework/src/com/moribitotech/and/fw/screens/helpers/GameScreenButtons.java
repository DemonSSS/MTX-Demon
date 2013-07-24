package com.moribitotech.and.fw.screens.helpers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.esotericsoftware.tablelayout.Cell;
import com.moribitotech.and.fw.buttons.GameHintButton;
import com.moribitotech.and.fw.data.PlayerData;
import com.moribitotech.and.fw.data.PuzzleLevel;
import com.moribitotech.and.fw.managers.EffectManager;
import com.moribitotech.and.fw.managers.GameManager;
import com.moribitotech.and.fw.managers.HintManager;
import com.moribitotech.and.fw.managers.TutorialManager;
import com.moribitotech.and.fw.models.BottomFire;
import com.moribitotech.and.fw.screens.GameScreen;
import com.moribitotech.and.fw.world.World;
import com.moribitotech.and.fw.world.WorldLayer1_Game;
import com.moribitotech.mtx.scene2d.ui.ButtonLight;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.utils.UtilsAssets;
import com.moribitotech.mtx.utils.UtilsAssets.Filter;
import com.moribitotech.mtx.utils.UtilsOrigin;
import com.moribitotech.mtx.utils.UtilsOrigin.Origin;

public class GameScreenButtons
{
  private GameHintButton btnHint;
  private ButtonLight btnMenu;
  private ButtonLight btnReset;
  private GameScreen gameScreen;
  private Table table;

  public GameScreenButtons(GameScreen paramGameScreen)
  {
    this.gameScreen = paramGameScreen;
    setUp();
  }

  private void setUp()
  {
    this.table = new Table();
    this.table.setFillParent(true);
    this.table.right().top();
    this.btnMenu = new ButtonLight(80.0F, 80.0F, this.gameScreen.getAtlas().findRegion("img_btn_menu"), true);
    this.btnReset = new ButtonLight(80.0F, 80.0F, this.gameScreen.getAtlas().findRegion("img_btn_refresh"), true);
    this.btnHint = new GameHintButton(80.0F, 80.0F, this.gameScreen.getAtlas().findRegion("img_btn_hint"), true);
    this.btnMenu.setToggleActive(false);
    UtilsOrigin.Origin localOrigin = UtilsOrigin.Origin.CENTER;
    Actor[] arrayOfActor = new Actor[3];
    arrayOfActor[0] = this.btnMenu;
    arrayOfActor[1] = this.btnReset;
    arrayOfActor[2] = this.btnHint;
    UtilsOrigin.setActorsOrigin(localOrigin, arrayOfActor);
    BitmapFont localBitmapFont = UtilsAssets.loadFont("data/font1", false, UtilsAssets.Filter.Linear_Linear);
    localBitmapFont.setScale(0.5F * AppSettings.getWorldSizeRatio());
    this.btnHint.setUp(localBitmapFont);
    this.btnHint.setText(this.gameScreen.getGameManager().getHintManager().getNumberOfHints()+"");
    setUpButtonListeners();
    this.table.add(this.btnReset);
    this.table.add(this.btnHint).padRight(25.0F * AppSettings.getWorldPositionXRatio());
    this.table.add(this.btnMenu);
    this.gameScreen.getStage().addActor(this.table);
  }

  private void setUpButtonListeners()
  {
    this.btnMenu.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        GameScreenButtons.this.btnMenu.setToggleSwitch();
        World localWorld = GameScreenButtons.this.getGameScreen().getGameManager().getWorld();
        if (GameScreenButtons.this.btnMenu.isToggleActive())
        {
          localWorld.setTouchable(Touchable.disabled);
          GameScreenButtons.this.btnReset.setTouchable(Touchable.disabled);
          GameScreenButtons.this.btnHint.setTouchable(Touchable.disabled);
          EffectManager.runGameMenuToggleButtonEffect(GameScreenButtons.this.btnMenu, false);
          EffectManager.runGameMenuSlideEffect(GameScreenButtons.this.gameScreen, true);
          GameScreenButtons.this.gameScreen.getGameManager().saveGame();
          GameScreenButtons.this.updateSwarm();
          return;
        }
        localWorld.setTouchable(Touchable.enabled);
        GameScreenButtons.this.btnReset.setTouchable(Touchable.enabled);
        GameScreenButtons.this.btnHint.setTouchable(Touchable.enabled);
        EffectManager.runGameMenuToggleButtonEffect(GameScreenButtons.this.btnMenu, true);
        EffectManager.runGameMenuSlideEffect(GameScreenButtons.this.gameScreen, false);
      }
    });
    this.btnReset.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        PuzzleLevel localPuzzleLevel = GameScreenButtons.this.gameScreen.getGameManager().getCurrentLevel();
        GameScreenButtons.this.gameScreen.getGameManager().startLevel(localPuzzleLevel.getPuzzleLevelNumber());
        GameScreenButtons.this.gameScreen.getGameManager().getWorldLayer1_Game().getBottomFire().doGrow(0.2F, 0.2F);
        GameScreenButtons.this.gameScreen.getGameManager().getTutorialManager().showTutorialResetButtonTouched();
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenButtons.this.btnReset);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
    this.btnHint.addListener(new ClickListener()
    {
      public void clicked(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        GameScreenButtons.this.gameScreen.getGameManager().getHintManager().showHint(GameScreenButtons.this.gameScreen.getGameManager().getCurrentLevel().getWord());
        GameScreenButtons.this.gameScreen.getGameManager().getTutorialManager().showTutorialHintButton();
      }

      public boolean touchDown(InputEvent paramAnonymousInputEvent, float paramAnonymousFloat1, float paramAnonymousFloat2, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        EffectManager.runGeneralButtonEffect(GameScreenButtons.this.btnHint);
        return super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1, paramAnonymousFloat2, paramAnonymousInt1, paramAnonymousInt2);
      }
    });
  }

  private void updateSwarm()
  {
//    SwarmManager.updateScore(this.gameScreen.getGameManager().getPlayer().getScore());
//    SwarmManager.updateStage(this.gameScreen.getGameManager().getPlayer().getStage());
  }

  public GameHintButton getBtnHint()
  {
    return this.btnHint;
  }

  public ButtonLight getBtnMenu()
  {
    return this.btnMenu;
  }

  public ButtonLight getBtnReset()
  {
    return this.btnReset;
  }

  public GameScreen getGameScreen()
  {
    return this.gameScreen;
  }

  public Table getMenuTableInScreen()
  {
    return this.table;
  }
}