package com.moribitotech.and.fw.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.moribitotech.and.fw.models.BottomFire;
import com.moribitotech.and.fw.screens.helpers.MainMenuScreenButtons;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.interfaces.IScreen;
import com.moribitotech.mtx.managers.MusicManager;
import com.moribitotech.mtx.scene2d.models.EmptyActorLight;
import com.moribitotech.mtx.scene2d.models.SmartActor;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.settings.AppSettings;
import java.util.Random;

public class MainMenuScreen extends AbstractScreen implements IScreen {
	private static final int NUMBER_OF_BOTTOM_FIRE = 40;
	private static final int NUMBER_OF_SPARKLES = 60;
	private TextureAtlas atlas;
	private BottomFire bottomFire;
	private MainMenuScreenButtons mainMenuScreenButtons;
	private float timer = 0.0F;

	public MainMenuScreen(AbstractGame paramAbstractGame, String paramString) {
		super(paramAbstractGame, paramString);
		setBackButtonActive(true);
		setUpScreenElements();
		setUpMenu();
		setUpSparkles(60);
		setUpBottomFire();
		addFPS(true);
	}

	private void setUpBottomFire() {
		Random localRandom = new Random();
		this.bottomFire = new BottomFire();
		this.bottomFire.setUp(this.atlas, "flame_a_", 40, 20, 250, getStage()
				.getWidth(), localRandom, Touchable.disabled);
		this.bottomFire.doGrow(0.1F, 3.0F);
		getStage().addActor(this.bottomFire);
	}

	private void setUpSparkles(int paramInt) {
		for (int i = 0; i < paramInt; i++) {
			Random localRandom = new Random();
			float f = 5 + localRandom.nextInt(30);
			SmartActor localSmartActor = new SmartActor(f, f, localRandom, true);
			localSmartActor.setTouchable(Touchable.disabled);
			localSmartActor.setTextureRegion(
					this.atlas.findRegion("img_obj_sparkle"), true);

			if (i % 2 == 0) {
				int[] arrayOfInt3 = new int[2];
				arrayOfInt3[1] = ((int) AppSettings.WORLD_WIDTH);
				int[] arrayOfInt4 = new int[2];
				arrayOfInt4[0] = (0 - (int) AppSettings.WORLD_WIDTH);
				localSmartActor.startActionMoveToDirection(arrayOfInt3,
						arrayOfInt4, 200 + (int) AppSettings.WORLD_HEIGHT,
						-200, 9, 3, false, true);
			}
			else
			{
				 int[] arrayOfInt1 = new int[2];
				 arrayOfInt1[0] = ((int) AppSettings.WORLD_WIDTH);
				 arrayOfInt1[1] = (2 * (int) AppSettings.WORLD_WIDTH);
				 int[] arrayOfInt2 = new int[2];
				 arrayOfInt2[1] = ((int) AppSettings.WORLD_WIDTH);
				 localSmartActor.startActionMoveToDirection(arrayOfInt1,
				 arrayOfInt2, 200 + (int) AppSettings.WORLD_HEIGHT, -200, 9,
				 3, false, true);
			}
				getStage().addActor(localSmartActor);
		}
	}

	public TextureAtlas getAtlas() {
		return this.atlas;
	}

	
	public MainMenuScreenButtons getMainMenuScreenButtons() {
		return this.mainMenuScreenButtons;
	}

	public void keyBackPressed() {
		super.keyBackPressed();
		if ((float) getSecondsTime() > 0.5F)
			Gdx.app.exit();
	}

	public void render(float paramFloat) {
		
		if ((this.mainMenuScreenButtons.getLogo() != null)
				&& ((float) getSecondsTime() > this.timer)) {
			if ((this.mainMenuScreenButtons.getLogo().getScaleX() <= 0.95F)
					|| (this.mainMenuScreenButtons.getLogo().getScaleX() >= 1.1F))
				this.mainMenuScreenButtons.getLogo().addAction(
						Actions.scaleTo(1.0F, 1.0F, 1.0F));
			else
				this.timer = (0.9F + (float) getSecondsTime());
			this.mainMenuScreenButtons.getLogo().addAction(
					Actions.scaleTo(0.93F, 0.93F, 1.0F));
		}
		super.render(paramFloat);
	}

	public void setUpMenu() {
		this.mainMenuScreenButtons = new MainMenuScreenButtons(this);
	}

	public void setUpScreenElements() {
		setOpenGLClearColor(0.1F, 0.1F, 0.1F, 1.0F);
		this.atlas = ((TextureAtlas) getAssetManager().get("data/pack1"));
		setBackgroundTexture(this.atlas.findRegion("img_bg"));
		if (getGame().getMusicManager().getCurrentMusic() == null) {
			getGame().getMusicManager().addMusicToShuffleList(
					(Music) getAssetManager().get("data/m_1.ogg", Music.class));
			getGame().getMusicManager().addMusicToShuffleList(
					(Music) getAssetManager().get("data/m_2.ogg", Music.class));
			getGame().getMusicManager().addMusicToShuffleList(
					(Music) getAssetManager().get("data/m_3.ogg", Music.class));
			getGame().getMusicManager().playShuffle(1.0F);
		}
	}
}