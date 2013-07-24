package com.moribitotech.and.fw.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.moribitotech.mtx.scene2d.AbstractActor;

public class FireBurn extends AbstractActor
{
  private Animation animFireBurn;

  public FireBurn(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    super(paramFloat1, paramFloat2, paramBoolean);
  }

  public void burn()
  {
    setAnimationMomentary(this.animFireBurn, true, null, true, false);
  }

  public void setUp(Animation paramAnimation)
  {
    this.animFireBurn = paramAnimation;
  }
}