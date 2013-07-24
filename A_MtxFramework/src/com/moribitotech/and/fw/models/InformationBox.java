package com.moribitotech.and.fw.models;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.moribitotech.mtx.scene2d.ui.TableModel;
import com.moribitotech.mtx.settings.AppSettings;

public class InformationBox extends TableModel
{
  private static final String PREFFIX_SCORE = "SCORE: ";
  private static final String PREFFIX_SPACE = "    ";
  private static final String PREFFIX_STAGE = "STAGE: ";
  private Label labelScore;
  private Label labelStage;

  public InformationBox(TextureRegion paramTextureRegion, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    super(paramTextureRegion, paramFloat1, paramFloat2, paramBoolean);
  }

  public void setScore(int paramInt)
  {
    this.labelScore.setText("SCORE: " + paramInt);
  }

  public void setStage(int paramInt)
  {
    this.labelStage.setText("STAGE: " + paramInt + "    ");
  }

  public void setUp(BitmapFont paramBitmapFont)
  {
    Label.LabelStyle localLabelStyle = new Label.LabelStyle(paramBitmapFont, null);
    this.labelStage = new Label("", localLabelStyle);
    this.labelScore = new Label("", localLabelStyle);
    this.labelStage.setFontScale(0.5F * AppSettings.getWorldSizeRatio());
    this.labelScore.setFontScale(0.5F * AppSettings.getWorldSizeRatio());
    add(this.labelStage);
    add(this.labelScore);
  }
}