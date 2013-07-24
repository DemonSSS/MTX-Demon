package com.moribitotech.and.fw.managers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.esotericsoftware.tablelayout.Cell;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.utils.UtilsAssets;
import com.moribitotech.mtx.utils.UtilsAssets.Filter;

public class NotificationManager
{
  private BitmapFont font;
  private Table table;

  public NotificationManager(GameManager paramGameManager)
  {
    BitmapFont localBitmapFont = UtilsAssets.loadFont("data/font1", false, UtilsAssets.Filter.Linear_Linear);
    this.font = localBitmapFont;
    localBitmapFont.setScale(0.7F * AppSettings.getWorldSizeRatio());
    this.table = new Table();
    this.table.setSize(AppSettings.SCREEN_W, 120.0F * AppSettings.getWorldPositionYRatio());
    this.table.setPosition(0.0F, 0.0F - this.table.getHeight());
    this.table.center();
    this.table.padLeft(20.0F * AppSettings.getWorldPositionXRatio());
    paramGameManager.getStage().addActor(this.table);
  }

  public void sendNotification(float paramFloat, String paramString, String[] paramArrayOfString)
  {
    this.table.clear();
    Label.LabelStyle localLabelStyle = new Label.LabelStyle(this.font, null);
    Label localLabel1 = new Label(paramString, localLabelStyle);
    this.table.add(localLabel1).row().center();
    int i = paramArrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        EffectManager.runNewNotificationEffect(this.table, paramFloat);
        return;
      }
      Label localLabel2 = new Label(paramArrayOfString[j], localLabelStyle);
      this.table.add(localLabel2).row().center();
    }
  }
}