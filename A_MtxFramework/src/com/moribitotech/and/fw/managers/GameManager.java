package com.moribitotech.and.fw.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.tablelayout.Cell;
import com.moribitotech.and.fw.data.GameData;
import com.moribitotech.and.fw.data.PlayerData;
import com.moribitotech.and.fw.data.PuzzleLevel;
import com.moribitotech.and.fw.models.BottomFire;
import com.moribitotech.and.fw.models.InformationBox;
import com.moribitotech.and.fw.models.Letter;
import com.moribitotech.and.fw.models.Meter;
import com.moribitotech.and.fw.models.Word;
import com.moribitotech.and.fw.screens.GameScreen;
import com.moribitotech.and.fw.world.World;
import com.moribitotech.and.fw.world.WorldLayer1_Game;
import com.moribitotech.and.fw.world.WorldLayer2_FireSparkles;
import com.moribitotech.mtx.game.AbstractGameManager;
import com.moribitotech.mtx.game.GameState;
import com.moribitotech.mtx.interfaces.IGameManager;
import com.moribitotech.mtx.managers.AudioManager;
import com.moribitotech.mtx.managers.VibrationManager;
import com.moribitotech.mtx.screen.AbstractScreen;
import com.moribitotech.mtx.settings.AppSettings;
import com.moribitotech.mtx.settings.MtxLogger;
import com.moribitotech.mtx.utils.UtilsAssets;
import com.moribitotech.mtx.utils.UtilsAssets.Filter;

public class GameManager extends AbstractGameManager implements IGameManager {
	private AchievementManager achievementManager;
	private TextureAtlas atlas;
	private AudioManager audioManager;
	private TextureRegion bubbleTexture;
	private PuzzleLevel currentLevel;
	private GameData gameData;
	private GameInputManager gameInputManager;
	private GameScreen gameScreen;
	private HintManager hintManager;
	private NotificationManager notificationManager;
	private int numberOfLevels;
	private PlayerData playerData;
	private PunishmentManager punishmentManager;
	private BitmapFont puzzleFont;
	private TutorialManager tutorialManager;
	private VibrationManager vibrationManager;
	private World world;
	private WorldLayer1_Game worldLayer1_Game;
	private WorldLayer2_FireSparkles worldLayer2_FireSparkles;

	public GameManager(Stage paramStage, AbstractScreen paramAbstractScreen,
			GameScreen paramGameScreen) {
		super(paramStage, paramAbstractScreen);
		this.gameScreen = paramGameScreen;
		this.atlas = ((TextureAtlas) getScreen().getAssetManager().get(
				"data/pack1"));
		// 设置数据
		setUpData();
		// 获取字体和图片资源
		setUpCachedAssets();
		setUpPreManagers();
		setUpWorld();
		setUpPostManagers();
		this.numberOfLevels = 8000;
		startLevel(this.playerData.getStage());
		this.tutorialManager.showTutorialIntro();
	}

	// 获取图片及文字字体
	private void setUpCachedAssets() {
		this.puzzleFont = UtilsAssets.loadFont("data/font1", false,
				UtilsAssets.Filter.Linear_Linear);
		this.puzzleFont.setScale(this.puzzleFont.getScaleX()
				* AppSettings.getWorldSizeRatio());
		this.bubbleTexture = this.atlas.findRegion("img_obj_bubble");
		UtilsAssets.addFilter(this.bubbleTexture.getTexture(),
				UtilsAssets.Filter.Linear_Linear);
	}

	private void updateDataObjects() {
		this.gameData.setCurrentMeterBurn(this.worldLayer1_Game.getMeterBurn()
				.getCurrentMeter());
		this.gameData.setCurrentMeterWater(this.worldLayer1_Game
				.getMeterWater().getCurrentMeter());
		this.gameData.setBottomFireScale(this.worldLayer1_Game.getBottomFire()
				.getGeneralScale());
		this.gameData.setHintNumber(this.hintManager.getNumberOfHints());
	}

	public void checkGameCondition() {
		if (PuzzleManager.isWordCorrect(this.currentLevel.getWord())) {
			this.playerData.increaseScore(this.currentLevel.getWord()
					.getLetters().length);
			this.worldLayer1_Game.getInformationBox().setScore(
					this.playerData.getScore());
			startLevel(1 + this.currentLevel.getPuzzleLevelNumber());
			if (!this.worldLayer1_Game.getMeterWater().isMeterMaximum())
				this.worldLayer1_Game.getMeterWater().increaseMeter(1);
			this.achievementManager.increaseCorrectGuessInARow();
			this.achievementManager.checkStageAchievements();
			this.achievementManager.checkCorrectGuessAchievements();
			this.tutorialManager.showTutorialCorrectGuess();
		}
		if (this.currentLevel.getWord().isWordReset()) {
			startLevel(this.currentLevel.getPuzzleLevelNumber());
			this.worldLayer1_Game.getBottomFire().doGrow(0.2F, 0.2F);
			Meter localMeter1 = this.worldLayer1_Game.getMeterWater();
			if (!localMeter1.isMeterMaximum())
				localMeter1.decreaseMeter(1);
			this.tutorialManager.showTutorialWrongGuess();
			this.achievementManager.resetCorrectGuessInARow();
		}

	}

	public AchievementManager getAchievementManager() {
		return this.achievementManager;
	}

	public TextureAtlas getAtlas() {
		return this.atlas;
	}

	public AudioManager getAudioManager() {
		return this.audioManager;
	}

	public PuzzleLevel getCurrentLevel() {
		return this.currentLevel;
	}

	public GameData getGameData() {
		return this.gameData;
	}

	public GameInputManager getGameInputManager() {
		return this.gameInputManager;
	}

	public GameScreen getGameScreen() {
		return this.gameScreen;
	}

	public HintManager getHintManager() {
		return this.hintManager;
	}

	public NotificationManager getNotificationManager() {
		return this.notificationManager;
	}

	public PlayerData getPlayer() {
		return this.playerData;
	}

	public PunishmentManager getPunishmentManager() {
		return this.punishmentManager;
	}

	public TutorialManager getTutorialManager() {
		return this.tutorialManager;
	}

	public VibrationManager getVibrationManager() {
		return this.vibrationManager;
	}

	public World getWorld() {
		return this.world;
	}

	public WorldLayer1_Game getWorldLayer1_Game() {
		return this.worldLayer1_Game;
	}

	public void saveGame() {
		updateDataObjects();
		GameIOManager.saveGame(this.playerData, this.gameData);
	}

	public void setCurrentLevel(PuzzleLevel paramPuzzleLevel) {
		this.currentLevel = paramPuzzleLevel;
	}

	public void setUpData() {
		this.playerData = new PlayerData();
		this.gameData = new GameData();
		// 从Manager中获取数据
		PlayerData localPlayerData = GameIOManager.getPlayerData();
		this.playerData.setStage(localPlayerData.getStage());
		this.playerData.setScore(localPlayerData.getScore());
		GameData localGameData = GameIOManager.getGameData();
		this.gameData.setCurrentMeterBurn(localGameData.getCurrentMeterBurn());
		this.gameData
				.setCurrentMeterWater(localGameData.getCurrentMeterWater());
		this.gameData.setBottomFireScale(localGameData.getBottomFireScale());
		this.gameData.setHintNumber(localGameData.getHintNumber());
	}

	public void setUpPostManagers() {
		this.notificationManager = new NotificationManager(this);
	}

	public void setUpPreManagers() {
		this.tutorialManager = new TutorialManager(this);
		this.hintManager = new HintManager(this);
		this.hintManager.setNumberOfHints(this.gameData.getHintNumber());
		this.gameInputManager = new GameInputManager(this);
		this.punishmentManager = new PunishmentManager(this);
		this.achievementManager = new AchievementManager(this);
		this.vibrationManager = new VibrationManager();
		this.audioManager = new AudioManager();
		// SwarmManager.isActive = true;
	}

	public void setUpWorld() {
		this.world = new World(this, 0.0F, 0.0F, AppSettings.WORLD_WIDTH,
				AppSettings.WORLD_HEIGHT);
		this.worldLayer1_Game = new WorldLayer1_Game(this, 0.0F, 0.0F,
				AppSettings.WORLD_WIDTH, AppSettings.WORLD_HEIGHT);
		this.worldLayer2_FireSparkles = new WorldLayer2_FireSparkles(this,
				0.0F, 0.0F, AppSettings.WORLD_WIDTH, AppSettings.WORLD_HEIGHT);
		this.world.addActor(this.worldLayer2_FireSparkles);
		this.world.addActor(this.worldLayer1_Game);
		getStage().addActor(this.world);
	}

	public void startLevel(int paramInt) {
		if (paramInt <= this.numberOfLevels) {
			int k = 0;
			Word localWord = null;
			Letter[] arrayOfLetter = null;
			if (this.currentLevel != null) {
				k = 0;
				if (k >= this.currentLevel.getWord().getLetters().length) {
					if (this.worldLayer1_Game.getChildren().contains(
							this.currentLevel.getWord(), true))
						this.worldLayer1_Game.removeActor(this.currentLevel
								.getWord());
					this.currentLevel = null;
				}
			} else {
				this.currentLevel = GameIOManager.getPuzzleLevel(paramInt,
						this.puzzleFont);
				PuzzleManager.shuffleWordLetters(this.currentLevel.getWord());
				localWord = this.currentLevel.getWord();
				localWord.setFillParent(true);
				localWord.center();
				this.worldLayer1_Game.addActor(localWord);
				arrayOfLetter = new Letter[localWord.getLetters().length];
			}
			int j;
			for (int i = 0;; i++) {
				if (i >= localWord.getLetters().length) {
					j = 0;
					if (j > arrayOfLetter.length) {
						EffectManager.runNewWordIntroEffect(arrayOfLetter);
						this.playerData.setStage(this.currentLevel
								.getPuzzleLevelNumber());
						this.worldLayer1_Game.getInformationBox().setStage(
								this.playerData.getStage());
						setGameState(GameState.GAME_RUNNING);
						MtxLogger.log(
								logActive,
								true,
								"MtxGameManagerLog",
								"New level started: "
										+ this.currentLevel
												.getPuzzleLevelNumber());
						return;
					} else {
						Letter localLetter2 = arrayOfLetter[j];
						localLetter2.setScale(0.0F);
						localLetter2.setZIndex(1);
						if (j % 7 == 0) {
							localWord.row();
							localWord.add(localLetter2).center();
						} else {
							j++;
							localWord.add(localLetter2).center();
						}
					}
					Letter localLetter3 = this.currentLevel.getWord()
							.getLetters()[k];
					MtxLogger.log(logActive, true, "MtxGameManagerLog",
							"Disposed: " + localLetter3.getPuzzleLetter());
					localLetter3.remove();
					k++;
					break;
				}
				final Letter localLetter1 = localWord.getLetters()[i];
				localLetter1.setTextureRegion(this.bubbleTexture, true);
				arrayOfLetter[localLetter1.getIndexCurrent()] = localLetter1;
				localLetter1.addListener(new ClickListener() {
					public boolean touchDown(
							InputEvent paramAnonymousInputEvent,
							float paramAnonymousFloat1,
							float paramAnonymousFloat2, int paramAnonymousInt1,
							int paramAnonymousInt2) {
						GameManager.this.gameInputManager
								.touchLetter(localLetter1);
						return super.touchDown(paramAnonymousInputEvent,
								paramAnonymousFloat1, paramAnonymousFloat2,
								paramAnonymousInt1, paramAnonymousInt2);
					}
				});
			}

		}
		this.notificationManager
				.sendNotification(
						10.0F,
						"Congratulations",
						new String[] { "You have cleared all stages, New stages are coming soon..." });
	}

	public void update(float paramFloat) {
	}
}