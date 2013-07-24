package com.moribitotech.and.fw.models;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.moribitotech.mtx.scene2d.ui.TableModel;
import com.moribitotech.mtx.settings.AppSettings;

public class GuessBox extends TableModel
{
  private Label lblPuzzleWord;

  public GuessBox(TextureRegion paramTextureRegion, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    super(paramTextureRegion, paramFloat1, paramFloat2, paramBoolean);
  }

  public Label getLblPuzzleWord()
  {
    return this.lblPuzzleWord;
  }

  public void setPuzzleWordBeingWritten(String paramString)
  {
    this.lblPuzzleWord.setText(paramString);
  }

  public void setUp(BitmapFont paramBitmapFont)
  {
    this.lblPuzzleWord = new Label("", new Label.LabelStyle(paramBitmapFont, null));
    this.lblPuzzleWord.setFontScale(0.8F * AppSettings.getWorldSizeRatio());
    add(this.lblPuzzleWord);
  }
}