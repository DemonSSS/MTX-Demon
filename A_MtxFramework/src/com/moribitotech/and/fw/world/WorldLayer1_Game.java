package com.moribitotech.and.fw.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.moribitotech.and.fw.data.GameData;
import com.moribitotech.and.fw.data.GlobalData;
import com.moribitotech.and.fw.data.PlayerData;
import com.moribitotech.and.fw.managers.EffectManager;
import com.moribitotech.and.fw.managers.GameInputManager;
import com.moribitotech.and.fw.managers.GameManager;
import com.moribitotech.and.fw.managers.TutorialManager;
import com.moribitotech.and.fw.models.BottomFire;
import com.moribitotech.and.fw.models.BottomFireSingle;
import com.moribitotech.and.fw.models.FireBurn;
import com.moribitotech.and.fw.models.GuessBox;
import com.moribitotech.and.fw.models.InformationBox;
import com.moribitotech.and.fw.models.Meter;
import com.moribitotech.and.fw.models.Water;
import com.moribitotech.and.fw.screens.GameScreen;
import com.moribitotech.mtx.animation.AnimationCreator;
import com.moribitotech.mtx.managers.AudioManager;
import com.moribitotech.mtx.scene2d.AbstractWorldScene2d;
import com.moribitotech.mtx.scene2d.ui.ButtonLight;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.utils.UtilsAssets;
import com.moribitotech.mtx.utils.UtilsAssets.Filter;
import com.moribitotech.mtx.utils.UtilsOrigin;
import com.moribitotech.mtx.utils.UtilsOrigin.Origin;
import com.moribitotech.mtx.utils.UtilsPositioner;
import com.moribitotech.mtx.utils.UtilsPositioner.Position;

public class WorldLayer1_Game extends AbstractWorldScene2d {
	private static final int NUMBER_OF_BOTTOM_FIRE = 40;
	private BottomFire bottomFire;
	private ButtonLight btnFireExtinguisher;
	private FireBurn fireBurn;
	public GameManager gameManager;
	private GuessBox guessBox;
	private InformationBox informationBox;
	private Meter meterBurn;
	private Meter meterWater;
	private Sound soundWater;
	private Water waterExtinguisherLeft;
	private Water waterExtinguisherRight;

	public WorldLayer1_Game(GameManager paramGameManager, float paramFloat1,
			float paramFloat2, float paramFloat3, float paramFloat4) {
		super(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
		this.gameManager = paramGameManager;
		setUpBottomFire();
		setUpFireBurn();
		setUpMeters();
		setUpGuessBox();
		setUpInformationBox();
		setUpWaterExtingusihers();
		setUpFireExtinguisherButton();
		setUpListeners();
	}

	private void setUpBottomFire() {
		this.bottomFire = new BottomFire();
		this.bottomFire.setUp(this.gameManager.getAtlas(), "flame_a_", 40, 20,
				250, AppSettings.WORLD_WIDTH, this.gameManager.getRandom(),
				Touchable.enabled);
		this.bottomFire.doGrow(this.gameManager.getGameData()
				.getBottomFireScale() - 1.0F, 3.0F);
		addActor(this.bottomFire);
	}

	private void setUpFireBurn() {
		Animation localAnimation = AnimationCreator
				.getAnimationFromMultiTextures(this.gameManager.getAtlas(),
						"blood_d_", 6, 0.065F, false, false);
		this.fireBurn = new FireBurn(150.0F, 150.0F, true);
		this.fireBurn.setTouchable(Touchable.disabled);
		this.fireBurn.setUp(localAnimation);
		int i = this.bottomFire.getChildren().size;
		this.fireBurn.setZIndex(i + 1);
		addActor(this.fireBurn);
	}

	private void setUpFireExtinguisherButton() {
		this.soundWater = ((Sound) this.gameManager.getGameScreen()
				.getAssetManager().get("data/s_water.mp3", Sound.class));
		this.btnFireExtinguisher = new ButtonLight(150.0F, 150.0F,
				this.gameManager.getAtlas().findRegion(
						"img_btn_fireextingguisher"), true);
		if (this.gameManager.getGameData().getCurrentMeterWater() >= this.meterWater
				.getMaximumMeter())
			activateBtnFireExtinguisher();
		else
			deactivateBtnFireExtinguisher();
		UtilsPositioner.setActorPoisiton(this.btnFireExtinguisher, this,
				UtilsPositioner.Position.BOTTOM_RIGHT);
		UtilsOrigin.Origin localOrigin = UtilsOrigin.Origin.CENTER;
		Actor[] arrayOfActor = new Actor[1];
		arrayOfActor[0] = this.btnFireExtinguisher;
		UtilsOrigin.setActorsOrigin(localOrigin, arrayOfActor);
		this.btnFireExtinguisher.addListener(new ClickListener() {
			public void clicked(InputEvent paramAnonymousInputEvent,
					float paramAnonymousFloat1, float paramAnonymousFloat2) {
				WorldLayer1_Game.this.gameManager.getAudioManager().playSound(
						WorldLayer1_Game.this.soundWater,
						GlobalData.SOUND_WATER_VOLUME);
				WorldLayer1_Game.this.waterExtinguisherLeft.extinguishFire();
				WorldLayer1_Game.this.waterExtinguisherRight.extinguishFire();
				WorldLayer1_Game.this.bottomFire.reset();
				WorldLayer1_Game.this.meterWater.reset();
				WorldLayer1_Game.this.deactivateBtnFireExtinguisher();
				WorldLayer1_Game.this.gameManager.getTutorialManager()
						.showTutorialExtinguisher();
			}

			public boolean touchDown(InputEvent paramAnonymousInputEvent,
					float paramAnonymousFloat1, float paramAnonymousFloat2,
					int paramAnonymousInt1, int paramAnonymousInt2) {
				EffectManager
						.runGeneralButtonEffect(WorldLayer1_Game.this.btnFireExtinguisher);
				return super.touchDown(paramAnonymousInputEvent,
						paramAnonymousFloat1, paramAnonymousFloat2,
						paramAnonymousInt1, paramAnonymousInt2);
			}
		});
		addActor(this.btnFireExtinguisher);
	}

	private void setUpGuessBox() {
		this.guessBox = new GuessBox(null, 450.0F / 1.6F, 90.0F / 1.6F, true);
		UtilsPositioner.setActorPoisiton(this.guessBox, this,
				UtilsPositioner.Position.TOP_CENTER);
		UtilsOrigin.setActorOrigin(this.guessBox, UtilsOrigin.Origin.CENTER);
		BitmapFont localBitmapFont = UtilsAssets.loadFont("data/font1", false,
				UtilsAssets.Filter.Linear_Linear);
		this.guessBox.setUp(localBitmapFont);
		addActor(this.guessBox);
	}

	private void setUpInformationBox() {
		this.informationBox = new InformationBox(null, 500.0F, 60.0F, true);
		UtilsPositioner.setActorPoisiton(this.informationBox, this,
				UtilsPositioner.Position.TOP_LEFT);
		BitmapFont localBitmapFont = UtilsAssets.loadFont("data/font1", false,
				UtilsAssets.Filter.Linear_Linear);
		this.informationBox.setUp(localBitmapFont);
		this.informationBox.setScore(this.gameManager.getPlayer().getScore());
		this.informationBox.align(8);
		this.informationBox.padLeft(20.0F * AppSettings
				.getWorldPositionXRatio());
		addActor(this.informationBox);
	}

	private void setUpListeners() {
		addListener(new ActorGestureListener() {
			public void touchDown(InputEvent paramAnonymousInputEvent,
					float paramAnonymousFloat1, float paramAnonymousFloat2,
					int paramAnonymousInt1, int paramAnonymousInt2) {
				super.touchDown(paramAnonymousInputEvent, paramAnonymousFloat1,
						paramAnonymousFloat2, paramAnonymousInt1,
						paramAnonymousInt2);
				if ((getTouchDownTarget() instanceof BottomFireSingle))
					WorldLayer1_Game.this.gameManager.getGameInputManager()
							.touchBottomFire(paramAnonymousFloat1,
									paramAnonymousFloat2,
									WorldLayer1_Game.this.fireBurn);
			}
		});
	}

	private void setUpMeters() {
		this.meterWater = new Meter(
				20.0F * AppSettings.getWorldPositionXRatio(),
				AppSettings.SCREEN_H, false);
		this.meterWater.setTextureRegion(this.gameManager.getAtlas()
				.findRegion("img_obj_meter_water"), true);
		this.meterWater.setUp(0, 5, this.gameManager.getGameData()
				.getCurrentMeterWater());
		UtilsPositioner.setActorPoisiton(this.meterWater, this,
				UtilsPositioner.Position.BOTTOM_RIGHT);
		UtilsOrigin.setActorOrigin(this.meterWater,
				UtilsOrigin.Origin.BOTTOM_CENTER);
		this.meterBurn = new Meter(
				20.0F * AppSettings.getWorldPositionXRatio(),
				AppSettings.SCREEN_H, false);
		this.meterBurn.setTextureRegion(
				this.gameManager.getAtlas().findRegion("img_obj_meter_burn"),
				true);
		this.meterBurn.setUp(0, 2, this.gameManager.getGameData()
				.getCurrentMeterBurn());
		UtilsPositioner.setActorPoisiton(this.meterBurn, this,
				UtilsPositioner.Position.BOTTOM_LEFT);
		UtilsOrigin.setActorOrigin(this.meterBurn,
				UtilsOrigin.Origin.BOTTOM_CENTER);
		this.meterWater.setTouchable(Touchable.disabled);
		this.meterBurn.setTouchable(Touchable.disabled);
		addActor(this.meterBurn);
		addActor(this.meterWater);
	}

	private void setUpWaterExtingusihers() {
		Animation localAnimation1 = AnimationCreator
				.getAnimationFromMultiTextures(this.gameManager.getAtlas(),
						"waterstart_", 5, 0.05F, false, false);
		Animation localAnimation2 = AnimationCreator
				.getAnimationFromMultiTextures(this.gameManager.getAtlas(),
						"waterloop_", 9, 0.05F, false, false);
		Animation localAnimation3 = AnimationCreator
				.getAnimationFromMultiTextures(this.gameManager.getAtlas(),
						"waterend_", 9, 0.05F, false, false);
		this.waterExtinguisherLeft = new Water(192.0F, 192.0F, true);
		this.waterExtinguisherLeft.setUp(localAnimation1, localAnimation2,
				localAnimation3);
		this.waterExtinguisherLeft.rotate(10.0F);
		UtilsPositioner.setActorPoisiton(this.waterExtinguisherLeft, this,
				UtilsPositioner.Position.BOTTOM_LEFT);
		UtilsOrigin.setActorOrigin(this.waterExtinguisherLeft,
				UtilsOrigin.Origin.CENTER);
		this.waterExtinguisherLeft.setY(0.0F - this.waterExtinguisherLeft
				.getHeight() / 3.0F);
		Animation localAnimation4 = AnimationCreator
				.getAnimationFromMultiTextures(this.gameManager.getAtlas(),
						"waterstart_", 5, 0.05F, true, false);
		Animation localAnimation5 = AnimationCreator
				.getAnimationFromMultiTextures(this.gameManager.getAtlas(),
						"waterloop_", 9, 0.05F, true, false);
		Animation localAnimation6 = AnimationCreator
				.getAnimationFromMultiTextures(this.gameManager.getAtlas(),
						"waterend_", 9, 0.05F, true, false);
		this.waterExtinguisherRight = new Water(192.0F, 192.0F, true);
		this.waterExtinguisherRight.setUp(localAnimation4, localAnimation5,
				localAnimation6);
		this.waterExtinguisherRight.rotate(-10.0F);
		UtilsPositioner.setActorPoisiton(this.waterExtinguisherRight, this,
				UtilsPositioner.Position.BOTTOM_RIGHT);
		UtilsOrigin.setActorOrigin(this.waterExtinguisherRight,
				UtilsOrigin.Origin.CENTER);
		this.waterExtinguisherRight.setY(0.0F - this.waterExtinguisherRight
				.getHeight() / 3.0F);
		this.waterExtinguisherLeft.setTouchable(Touchable.disabled);
		this.waterExtinguisherRight.setTouchable(Touchable.disabled);
		addActor(this.waterExtinguisherLeft);
		addActor(this.waterExtinguisherRight);
	}

	public void activateBtnFireExtinguisher() {
		this.btnFireExtinguisher.addAction(Actions.scaleTo(1.0F, 1.0F, 0.5F));
		this.btnFireExtinguisher.setTouchable(Touchable.enabled);
	}

	public void deactivateBtnFireExtinguisher() {
		this.btnFireExtinguisher.addAction(Actions.scaleTo(0.0F, 0.0F, 0.5F));
		this.btnFireExtinguisher.setTouchable(Touchable.disabled);
	}

	public BottomFire getBottomFire() {
		return this.bottomFire;
	}

	public ButtonLight getBtnFireExtinguisher() {
		return this.btnFireExtinguisher;
	}

	public GuessBox getGuessBox() {
		return this.guessBox;
	}

	public InformationBox getInformationBox() {
		return this.informationBox;
	}

	public Meter getMeterBurn() {
		return this.meterBurn;
	}

	public Meter getMeterWater() {
		return this.meterWater;
	}

	public Water getWaterExtinguisherLeft() {
		return this.waterExtinguisherLeft;
	}

	public Water getWaterExtinguisherRight() {
		return this.waterExtinguisherRight;
	}

	public void setInformationBox(InformationBox paramInformationBox) {
		this.informationBox = paramInformationBox;
	}
}