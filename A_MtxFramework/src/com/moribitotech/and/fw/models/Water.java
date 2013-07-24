package com.moribitotech.and.fw.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.moribitotech.mtx.scene2d.AbstractActor;

public class Water extends AbstractActor {
	private Animation animWaterEnd;
	private Animation animWaterLoop;
	private Animation animWaterStart;
	private float extinguishTimer = 0.0F;
	private boolean isExtinguishing = false;
	private boolean isStartAnimationSet = false;

	public Water(float paramFloat1, float paramFloat2, boolean paramBoolean) {
		super(paramFloat1, paramFloat2, paramBoolean);
	}

	public void act(float paramFloat) {
		super.act(paramFloat);
		if (this.isExtinguishing) {
			if (this.extinguishTimer <= (float) getSecondsTime())
				setAnimationMomentary(this.animWaterEnd, true, null, true, true);
			this.isExtinguishing = false;
			this.isStartAnimationSet = false;
			if (!this.isStartAnimationSet) {
				setAnimationMomentary(this.animWaterStart, true,
						this.animWaterLoop, true, false);
				setAnimationLooping(true);
				this.isStartAnimationSet = true;
			}
		}
		return;
	}

	public void extinguishFire() {
		setKillAllAnimations(false);
		this.extinguishTimer = (3.0F + (float) getSecondsTime());
		this.isExtinguishing = true;
	}

	public void setUp(Animation paramAnimation1, Animation paramAnimation2,
			Animation paramAnimation3) {
		this.animWaterStart = paramAnimation1;
		this.animWaterLoop = paramAnimation2;
		this.animWaterEnd = paramAnimation3;
	}
}