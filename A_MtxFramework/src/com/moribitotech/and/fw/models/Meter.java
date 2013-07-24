package com.moribitotech.and.fw.models;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.moribitotech.mtx.scene2d.AbstractActorLight;
import com.moribitotech.mtx.settings.MtxLogger;

public class Meter extends AbstractActorLight
{
  private int currentMeter;
  private boolean isMeterActive = true;
  private int maximumMeter;
  private int minumumMeter;

  public Meter(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    super(paramFloat1, paramFloat2, paramBoolean);
  }

  private void scaleMeterView(float paramFloat)
  {
    float f = this.currentMeter / this.maximumMeter;
    addAction(Actions.scaleTo(getScaleX(), f, paramFloat));
  }

  public void decreaseMeter(int paramInt)
  {
    if ((this.isMeterActive) && (this.currentMeter - paramInt >= this.minumumMeter))
    {
      this.currentMeter -= paramInt;
      scaleMeterView(0.3F);
    }
  }

  public int getCurrentMeter()
  {
    return this.currentMeter;
  }

  public int getMaximumMeter()
  {
    return this.maximumMeter;
  }

  public int getMinumumMeter()
  {
    return this.minumumMeter;
  }

  public void increaseMeter(int paramInt)
  {
    if ((this.isMeterActive) && (paramInt + this.currentMeter <= this.maximumMeter))
    {
      this.currentMeter = (paramInt + this.currentMeter);
      scaleMeterView(0.3F);
//      MtxLogger.log(true, true, "Mtxxx", this.currentMeter);
    }
  }

  public boolean isMeterActive()
  {
    return this.isMeterActive;
  }

  public boolean isMeterMaximum()
  {
    return this.currentMeter == this.maximumMeter;
  }

  public void reset()
  {
    float f = this.currentMeter / this.maximumMeter;
    addAction(Actions.sequence(Actions.scaleTo(getScaleX(), f, 0.3F), Actions.scaleTo(getScaleX(), 0.0F, 0.3F)));
    this.currentMeter = this.minumumMeter;
  }

  public void setMeterActive(boolean paramBoolean)
  {
    this.isMeterActive = paramBoolean;
  }

  public void setUp(int paramInt1, int paramInt2, int paramInt3)
  {
    this.minumumMeter = paramInt1;
    this.maximumMeter = paramInt2;
    this.currentMeter = paramInt3;
    scaleMeterView(0.3F);
  }
}