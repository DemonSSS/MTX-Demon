package com.moribitotech.and.fw.world;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.moribitotech.and.fw.managers.GameManager;
import com.moribitotech.mtx.scene2d.AbstractWorldScene2d;
import com.moribitotech.mtx.scene2d.models.SmartActor;
import com.moribitotech.mtx.settings.AppSettings;
import java.util.Random;

public class WorldLayer2_FireSparkles extends AbstractWorldScene2d {
	private static final int NUMBER_OF_SPARKLES = 60;
	public GameManager gameManager;

	public WorldLayer2_FireSparkles(GameManager paramGameManager,
			float paramFloat1, float paramFloat2, float paramFloat3,
			float paramFloat4) {
		super(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
		this.gameManager = paramGameManager;
		setTouchable(Touchable.disabled);
		setUpSparkles(60);
	}

	private void setUpSparkles(int paramInt) {
		for (int i = 0; i < paramInt; i++) {
			Random localRandom = new Random();
			float f = 5 + localRandom.nextInt(30);
			SmartActor localSmartActor = new SmartActor(f, f, localRandom, true);
			localSmartActor.setTouchable(Touchable.disabled);
			localSmartActor.setTextureRegion(this.gameManager.getAtlas()
					.findRegion("img_obj_sparkle"), true);

			if (i % 2 == 0) {
				int[] arrayOfInt3 = new int[2];
				arrayOfInt3[1] = ((int) AppSettings.WORLD_WIDTH);
				int[] arrayOfInt4 = new int[2];
				arrayOfInt4[0] = (0 - (int) AppSettings.WORLD_WIDTH);
				localSmartActor.startActionMoveToDirection(arrayOfInt3,
						arrayOfInt4, 200 + (int) AppSettings.WORLD_HEIGHT,
						-200, 9, 3, false, true);
			} else {
				int[] arrayOfInt1 = new int[2];
				arrayOfInt1[0] = ((int) AppSettings.WORLD_WIDTH);
				arrayOfInt1[1] = (2 * (int) AppSettings.WORLD_WIDTH);
				int[] arrayOfInt2 = new int[2];
				arrayOfInt2[1] = ((int) AppSettings.WORLD_WIDTH);
				localSmartActor.startActionMoveToDirection(arrayOfInt1,
						arrayOfInt2, 200 + (int) AppSettings.WORLD_HEIGHT,
						-200, 9, 3, false, true);
			}
			addActor(localSmartActor);
		}

	}
}