package com.moribitotech.and.fw.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.moribitotech.and.fw.managers.EffectManager;
import com.moribitotech.mtx.asset.AbstractAssets;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.models.EmptyActorLight;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.screen.AbstractScreenLoading;
import com.moribitotech.mtx.utils.UtilsAssets;
import com.moribitotech.mtx.utils.UtilsAssets.Filter;
import com.moribitotech.mtx.utils.UtilsOrigin;
import com.moribitotech.mtx.utils.UtilsOrigin.Origin;

public class LoadingScreen extends AbstractScreenLoading
{
  private static final float MOVE_SPEED = 0.9F;
  private boolean isNextScreenSet = false;
  private EmptyActorLight loadingGear;
  private EmptyActorLight loadingText;

  public LoadingScreen(AbstractGame paramAbstractGame, String paramString)
  {
    super(paramAbstractGame, paramString);
  }

  public void render(float paramFloat)
  {
    super.render(paramFloat);
    if ((getGame().getAssets().getAssetManager().update()) && ((float)getSecondsTime() > getLoadingTime()) && (!this.isNextScreenSet))
    {
      MainMenuScreen localMainMenuScreen = new MainMenuScreen(getGame(), "Main Menu");
      localMainMenuScreen.getStage().addAction(Actions.fadeOut(0.0F));
      AlphaAction localAlphaAction1 = Actions.fadeOut(1.0F);
      AlphaAction localAlphaAction2 = Actions.fadeIn(0.5F);
      getGame().setScreenWithTransition(this, localAlphaAction1, localMainMenuScreen, localAlphaAction2, true);
      this.isNextScreenSet = true;
    }
    this.loadingGear.rotate(-80.0F);
  }

  public void setUpLoading()
  {
    setOpenGLClearColor(0.1F, 0.1F, 0.1F, 1.0F);
    Texture localTexture1 = UtilsAssets.loadTexture("data/img_loading_text.png", UtilsAssets.Filter.Linear_Linear);
    Texture localTexture2 = UtilsAssets.loadTexture("data/img_loading_gear.png", UtilsAssets.Filter.Linear_Linear);
    TextureRegion localTextureRegion1 = new TextureRegion(localTexture1);
    TextureRegion localTextureRegion2 = new TextureRegion(localTexture2);
    this.loadingText = new EmptyActorLight(210.0F, 50.0F, true);
    this.loadingText.setTextureRegion(localTextureRegion1, true);
    this.loadingGear = new EmptyActorLight(50.0F, 50.0F, true);
    this.loadingGear.setTextureRegion(localTextureRegion2, true);
    UtilsOrigin.setActorOrigin(this.loadingGear, UtilsOrigin.Origin.CENTER);
    getStage().addActor(this.loadingText);
    getStage().addActor(this.loadingGear);
    EffectManager.runLoadingIntroEffect(getStage(), this.loadingText, this.loadingGear, 0.9F);
    setLoadingTime(1.0F);
  }
}