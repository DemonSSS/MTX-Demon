package com.moribitotech.and.fw.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.models.EmptyActorLight;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.utils.UtilsPositioner;
import com.moribitotech.mtx.utils.UtilsPositioner.Position;

public class CreditsScreen extends AbstractScreen
{
  private boolean isNextScreenSet = false;

  public CreditsScreen(AbstractGame paramAbstractGame, String paramString)
  {
    super(paramAbstractGame, paramString);
    setOpenGLClearColor(0.1F, 0.1F, 0.1F, 1.0F);
    setBackButtonActive(true);
    TextureAtlas localTextureAtlas = (TextureAtlas)getAssetManager().get("data/pack1");
    setBackgroundTexture(localTextureAtlas.findRegion("img_bg"));
    EmptyActorLight localEmptyActorLight = new EmptyActorLight(960.0F, 540.0F, true);
    localEmptyActorLight.setTextureRegion(localTextureAtlas.findRegion("img_credits"), true);
    UtilsPositioner.setActorPoisiton(localEmptyActorLight, 0.0F, 0.0F, AppSettings.SCREEN_W, AppSettings.SCREEN_H, UtilsPositioner.Position.CENTER);
    getStage().addActor(localEmptyActorLight);
  }

  public void keyBackPressed()
  {
    super.keyBackPressed();
    if (!this.isNextScreenSet)
    {
      MainMenuScreen localMainMenuScreen = new MainMenuScreen(getGame(), "Main Menu Screen");
      localMainMenuScreen.getStage().addAction(Actions.fadeOut(0.0F));
      AlphaAction localAlphaAction1 = Actions.fadeOut(0.5F);
      AlphaAction localAlphaAction2 = Actions.fadeIn(0.5F);
      getGame().setScreenWithTransition(this, localAlphaAction1, localMainMenuScreen, localAlphaAction2, true);
      this.isNextScreenSet = true;
    }
  }
}