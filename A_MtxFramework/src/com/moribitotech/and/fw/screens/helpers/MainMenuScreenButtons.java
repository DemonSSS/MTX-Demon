package com.moribitotech.and.fw.screens.helpers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.esotericsoftware.tablelayout.Cell;
import com.moribitotech.and.fw.managers.EffectManager;
import com.moribitotech.and.fw.screens.CreditsScreen;
import com.moribitotech.and.fw.screens.GameScreen;
import com.moribitotech.and.fw.screens.MainMenuScreen;
import com.moribitotech.mtx.android.IAdController;
import com.moribitotech.mtx.android.IAdControllerAppBrain;
import com.moribitotech.mtx.android.IAndroidObject;
import com.moribitotech.mtx.game.AbstractGame;
import com.moribitotech.mtx.scene2d.models.EmptyActorLight;
import com.moribitotech.mtx.scene2d.ui.ButtonLight;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.utils.UtilsOrigin;
import com.moribitotech.mtx.utils.UtilsOrigin.Origin;

public class MainMenuScreenButtons {
	private ButtonLight btnCredits;
	private ButtonLight btnGames;
	private ButtonLight btnPlay;
	private EmptyActorLight logo;
	private MainMenuScreen mainMenuScreen;
	private Table tableButtons;
	private Table tableMain;

	public MainMenuScreenButtons(MainMenuScreen paramMainMenuScreen) {
		this.mainMenuScreen = paramMainMenuScreen;
		setUp();
	}
	//给memu按钮添加事件 点下时的效果
	private void setUpButtonListeners() {
		this.btnPlay.addListener(new ClickListener() {
			public void clicked(InputEvent paramAnonymousInputEvent,
					float paramAnonymousFloat1, float paramAnonymousFloat2) {
				GameScreen localGameScreen = new GameScreen(mainMenuScreen
						.getGame(), "Game Screen");
				localGameScreen.getStage().addAction(Actions.fadeOut(0.0F));
				MainMenuScreenButtons.this.mainMenuScreen.getGame()
						.setScreenWithTransition(
								MainMenuScreenButtons.this.mainMenuScreen,
								Actions.fadeOut(0.5F), localGameScreen,
								Actions.fadeIn(0.5F), true);
			}

			public boolean touchDown(InputEvent paramAnonymousInputEvent,
					float paramAnonymousFloat1, float paramAnonymousFloat2,
					int paramAnonymousInt1, int paramAnonymousInt2) {
				EffectManager.runGeneralButtonEffect(btnPlay);
				return super.touchDown(paramAnonymousInputEvent,
						paramAnonymousFloat1, paramAnonymousFloat2,
						paramAnonymousInt1, paramAnonymousInt2);
			}
		});
		this.btnGames.addListener(new ClickListener() {
			public void clicked(InputEvent paramAnonymousInputEvent,
					float paramAnonymousFloat1, float paramAnonymousFloat2) {
				mainMenuScreen.getGame().getAndroidObject().getAdController()
						.getAdControllerAppBrain().showDirectInterstitial();
			}

			public boolean touchDown(InputEvent paramAnonymousInputEvent,
					float paramAnonymousFloat1, float paramAnonymousFloat2,
					int paramAnonymousInt1, int paramAnonymousInt2) {
				EffectManager.runGeneralButtonEffect(btnGames);
				return super.touchDown(paramAnonymousInputEvent,
						paramAnonymousFloat1, paramAnonymousFloat2,
						paramAnonymousInt1, paramAnonymousInt2);
			}
		});
		this.btnCredits.addListener(new ClickListener() {
			public void clicked(InputEvent paramAnonymousInputEvent,
					float paramAnonymousFloat1, float paramAnonymousFloat2) {
				CreditsScreen localCreditsScreen = new CreditsScreen(
						mainMenuScreen.getGame(), "Credits Screen");
				localCreditsScreen.getStage().addAction(Actions.fadeOut(0.0F));
				mainMenuScreen.getGame().setScreenWithTransition(
						MainMenuScreenButtons.this.mainMenuScreen,
						Actions.fadeOut(0.5F), localCreditsScreen,
						Actions.fadeIn(0.5F), true);
			}

			public boolean touchDown(InputEvent paramAnonymousInputEvent,
					float paramAnonymousFloat1, float paramAnonymousFloat2,
					int paramAnonymousInt1, int paramAnonymousInt2) {
				EffectManager
						.runGeneralButtonEffect(MainMenuScreenButtons.this.btnCredits);
				return super.touchDown(paramAnonymousInputEvent,
						paramAnonymousFloat1, paramAnonymousFloat2,
						paramAnonymousInt1, paramAnonymousInt2);
			}
		});
	}
	//logo设置
	private void setUpLogo() {
		this.logo = new EmptyActorLight(500.0F, 350.0F, true);
		//设置不能点击
		this.logo.setTouchable(Touchable.disabled);
		this.logo.setTextureRegion(
				this.mainMenuScreen.getAtlas().findRegion("img_logo"), true);
		this.tableMain.add(this.logo);
		UtilsOrigin.setActorOrigin(this.logo, UtilsOrigin.Origin.CENTER);
		this.mainMenuScreen.getStage().addActor(this.logo);
	}

	public void debug(Stage paramStage) {
		Table.drawDebug(paramStage);
	}

	public EmptyActorLight getLogo() {
		return this.logo;
	}

	public void setUp() {
		this.tableMain = new Table();
		this.tableMain.setFillParent(true);
		this.tableButtons = new Table();
		setUpLogo();
		this.btnPlay = new ButtonLight(310.0F, 130.0F, this.mainMenuScreen
				.getAtlas().findRegion("img_btn_play"), true);
		this.btnGames = new ButtonLight(310.0F, 130.0F, this.mainMenuScreen
				.getAtlas().findRegion("img_btn_games"), true);
		this.btnCredits = new ButtonLight(310.0F, 130.0F, this.mainMenuScreen
				.getAtlas().findRegion("img_btn_credits"), true);
		UtilsOrigin.Origin localOrigin = UtilsOrigin.Origin.CENTER;
		Actor[] arrayOfActor = new Actor[3];
		arrayOfActor[0] = this.btnPlay;
		arrayOfActor[1] = this.btnGames;
		arrayOfActor[2] = this.btnCredits;
		UtilsOrigin.setActorsOrigin(localOrigin, arrayOfActor);
		setUpButtonListeners();
		this.tableButtons.add(this.btnPlay).row();
		this.tableButtons.add(this.btnGames).row();
		this.tableButtons.add(this.btnCredits).row();
//		this.tableButtons.center().padRight(
//				50.0F * AppSettings.getWorldPositionXRatio());
		this.tableMain.add(this.tableButtons).center();
		this.mainMenuScreen.getStage().addActor(this.tableMain);
	}
}