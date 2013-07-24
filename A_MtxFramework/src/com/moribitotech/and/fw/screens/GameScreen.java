package com.moribitotech.and.fw.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.moribitotech.and.fw.buttons.GameHintButton;
import com.moribitotech.and.fw.managers.GameManager;
import com.moribitotech.and.fw.managers.HintManager;
import com.moribitotech.and.fw.screens.helpers.GameScreenButtons;
import com.moribitotech.and.fw.screens.helpers.GameScreenMenu;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.game.GameState;
import com.moribitotech.mtx.interfaces.IGameScreen;
import com.moribitotech.mtx.managers.BenchmarkManager;
import com.moribitotech.mtx.managers.BenchmarkManager.BenchmarkPosition;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.utils.UtilsAssets;

public class GameScreen extends AbstractScreen
  implements IGameScreen
{
  private TextureAtlas atlas = (TextureAtlas)getAssetManager().get("data/pack1");
  private BenchmarkManager benchmarkManager;
  private GameManager gameManager;
  private GameScreenButtons gameScreenButtons;
  private GameScreenMenu gameScreenMenu;
  private boolean isNextScreenSet = false;

  public GameScreen(AbstractGame paramAbstractGame, String paramString)
  {
    super(paramAbstractGame, paramString);
    setOpenGLClearColor(0.1F, 0.1F, 0.1F, 1.0F);
    setBackButtonActive(true);
    setUpGameManager();
    setUpGameMenu();
    setUpBenchmarkManager();
  }

  private void setUpBenchmarkManager()
  {
    BitmapFont localBitmapFont = UtilsAssets.loadFont("data/default", false, null);
    this.benchmarkManager = new BenchmarkManager(getStage(), localBitmapFont, BenchmarkManager.BenchmarkPosition.TOP_LEFT);
    this.benchmarkManager.setBenchmarkManagerActive(false);
  }

  public TextureAtlas getAtlas()
  {
    return this.atlas;
  }

  public BenchmarkManager getBenchmarkManager()
  {
    return this.benchmarkManager;
  }

  public GameManager getGameManager()
  {
    return this.gameManager;
  }

  public GameScreenButtons getGameScreenButtons()
  {
    return this.gameScreenButtons;
  }

  public GameScreenMenu getGameScreenMenu()
  {
    return this.gameScreenMenu;
  }

  public void hide()
  {
    super.hide();
    this.gameManager.saveGame();
  }

  public void keyBackPressed()
  {
    super.keyBackPressed();
    if (!this.isNextScreenSet)
    {
      getStage().getRoot().setTouchable(Touchable.disabled);
      MainMenuScreen localMainMenuScreen = new MainMenuScreen(getGame(), "Main Menu Screen");
      localMainMenuScreen.getStage().addAction(Actions.fadeOut(0.0F));
      AlphaAction localAlphaAction1 = Actions.fadeOut(0.5F);
      AlphaAction localAlphaAction2 = Actions.fadeIn(0.5F);
      getGame().setScreenWithTransition(this, localAlphaAction1, localMainMenuScreen, localAlphaAction2, true);
      this.isNextScreenSet = true;
    }
  }

  public void pause()
  {
    super.pause();
    this.gameManager.saveGame();
  }

  public void render(float paramFloat)
  {
    super.render(paramFloat);
    this.benchmarkManager.update();
    this.gameManager.update(paramFloat);
    this.gameScreenButtons.getBtnHint().setText(this.gameManager.getHintManager().getNumberOfHints()+"");
  }

  public void setUpGameManager()
  {
    this.gameManager = new GameManager(getStage(), this, this);
    this.gameManager.setGameState(GameState.GAME_RUNNING);
  }

  public void setUpGameMenu()
  {
    this.gameScreenMenu = new GameScreenMenu(this);
    this.gameScreenButtons = new GameScreenButtons(this);
  }
}