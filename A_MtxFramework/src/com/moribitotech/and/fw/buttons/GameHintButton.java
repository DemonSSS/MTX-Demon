package com.moribitotech.and.fw.buttons;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.esotericsoftware.tablelayout.Cell;
import com.moribitotech.mtx.scene2d.ui.ButtonLight;

public class GameHintButton extends ButtonLight
{
  private Label label;
  private Table table;

  public GameHintButton(float paramFloat1, float paramFloat2, TextureRegion paramTextureRegion, boolean paramBoolean)
  {
    super(paramFloat1, paramFloat2, paramTextureRegion, paramBoolean);
  }

  public void setText(String paramString)
  {
    this.label.setText(paramString);
  }

  public void setUp(BitmapFont paramBitmapFont)
  {
    this.table = new Table();
    this.table.setFillParent(true);
    this.table.center();
    this.label = new Label("", new Label.LabelStyle(paramBitmapFont, null));
    this.table.add(this.label).center();
    addActor(this.table);
  }
}