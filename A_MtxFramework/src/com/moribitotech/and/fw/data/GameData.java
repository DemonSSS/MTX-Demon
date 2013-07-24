package com.moribitotech.and.fw.data;

public class GameData
{
  private float bottomFireScale = 1.0F;
  private int currentMeterBurn = 0;
  private int currentMeterWater = 0;
  private int fireBurnNumber = 0;
  private int hintNumber = 10;

  public float getBottomFireScale()
  {
    return this.bottomFireScale;
  }

  public int getCurrentMeterBurn()
  {
    return this.currentMeterBurn;
  }

  public int getCurrentMeterWater()
  {
    return this.currentMeterWater;
  }

  public int getFireBurnNumber()
  {
    return this.fireBurnNumber;
  }

  public int getHintNumber()
  {
    return this.hintNumber;
  }

  public void increaseFireBurnNunmber()
  {
    this.fireBurnNumber = (1 + this.fireBurnNumber);
  }

  public void setBottomFireScale(float paramFloat)
  {
    this.bottomFireScale = paramFloat;
  }

  public void setCurrentMeterBurn(int paramInt)
  {
    this.currentMeterBurn = paramInt;
  }

  public void setCurrentMeterWater(int paramInt)
  {
    this.currentMeterWater = paramInt;
  }

  public void setFireBurnNumber(int paramInt)
  {
    this.fireBurnNumber = paramInt;
  }

  public void setHintNumber(int paramInt)
  {
    this.hintNumber = paramInt;
  }
}