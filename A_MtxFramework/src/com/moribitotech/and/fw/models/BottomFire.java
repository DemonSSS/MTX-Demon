package com.moribitotech.and.fw.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.moribitotech.mtx.animation.AnimationCreator;
import com.moribitotech.mtx.utils.UtilsOrigin;
import com.moribitotech.mtx.utils.UtilsOrigin.Origin;
import com.moribitotech.mtx.utils.UtilsRandomizer;
import java.util.ArrayList;
import java.util.Random;

public class BottomFire extends Group {
	private ArrayList<BottomFireSingle> fires = new ArrayList();

	public void doGrow(float paramFloat1, float paramFloat2) {
		for (int i = 0;; i++) {
			if (i >= this.fires.size())
				return;
			BottomFireSingle localBottomFireSingle = (BottomFireSingle) this.fires
					.get(i);
			if (paramFloat1 + localBottomFireSingle.getScaleX() <= 3.2F) {
				localBottomFireSingle.clearActions();
				localBottomFireSingle.addAction(Actions.scaleBy(paramFloat1,
						paramFloat1, paramFloat2));
			}
		}
	}

	public void doShrunk(float paramFloat1, float paramFloat2) {
		for (int i = 0;; i++) {
			if (i >= this.fires.size())
				return;
			BottomFireSingle localBottomFireSingle = (BottomFireSingle) this.fires
					.get(i);
			if ((paramFloat1 + localBottomFireSingle.getScaleX() >= 1.0F)
					&& (paramFloat1 < 0.0F)) {
				localBottomFireSingle.clearActions();
				localBottomFireSingle.addAction(Actions.scaleBy(paramFloat1,
						paramFloat1, paramFloat2));
			}
		}
	}

	public ArrayList<BottomFireSingle> getFires() {
		return this.fires;
	}

	public float getGeneralScale() {
		if (this.fires.size() > 0)
			return ((BottomFireSingle) this.fires.get(0)).getScaleX();
		return 1.0F;
	}

	public void reset() {
		for (int i = 0;; i++) {
			if (i >= this.fires.size())
				return;
			BottomFireSingle localBottomFireSingle = (BottomFireSingle) this.fires
					.get(i);
			localBottomFireSingle.clearActions();
			localBottomFireSingle.addAction(Actions.scaleTo(1.0F, 1.0F, 1.5F));
		}
	}

	public void setUp(TextureAtlas paramTextureAtlas, String paramString,
			int paramInt1, int paramInt2, int paramInt3, float paramFloat,
			Random paramRandom, Touchable paramTouchable) {
		for (int i = 0;; i++) {
			if (i >= paramInt1)
				return;
			Animation localAnimation = AnimationCreator
					.getAnimationFromMultiTextures(paramTextureAtlas,
							paramString, 6, UtilsRandomizer.getRandomInclusive(
									paramRandom, 45, 70) / 1000.0F, false,
							false);
			float f = UtilsRandomizer.getRandomInclusive(paramRandom,
					paramInt2, paramInt3);
			BottomFireSingle localBottomFireSingle = new BottomFireSingle(f, f,
					true);
			localBottomFireSingle.setAnimation(localAnimation, true, true);
			localBottomFireSingle.setTouchable(paramTouchable);
			localBottomFireSingle.setPosition(i * (paramFloat / paramInt1)
					- localBottomFireSingle.getWidth() / 2.0F,
					0.0F - localBottomFireSingle.getHeight() / 1.8F);
			UtilsOrigin.setActorOrigin(localBottomFireSingle,
					UtilsOrigin.Origin.BOTTOM_CENTER);
			this.fires.add(localBottomFireSingle);
			addActor(localBottomFireSingle);
		}
	}
}