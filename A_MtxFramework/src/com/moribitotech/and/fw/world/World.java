package com.moribitotech.and.fw.world;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Scaling;
import com.moribitotech.and.fw.managers.GameManager;
import com.moribitotech.mtx.game.GameState;
import com.moribitotech.mtx.scene2d.AbstractWorldScene2d;

public class World extends AbstractWorldScene2d
{
  public GameManager gameManager;

  public World(GameManager paramGameManager, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    super(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    this.gameManager = paramGameManager;
    setBackgroundTexture(paramGameManager.getAtlas().findRegion("img_bg"), Scaling.stretch, true, false);
  }

  public void act(float paramFloat)
  {
    if (this.gameManager.getGameState() == GameState.GAME_RUNNING)
      super.act(paramFloat);
  }
}