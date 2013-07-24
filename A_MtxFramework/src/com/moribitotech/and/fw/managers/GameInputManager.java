package com.moribitotech.and.fw.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.moribitotech.and.fw.data.GameData;
import com.moribitotech.and.fw.data.GlobalData;
import com.moribitotech.and.fw.data.PuzzleLevel;
import com.moribitotech.and.fw.models.FireBurn;
import com.moribitotech.and.fw.models.GuessBox;
import com.moribitotech.and.fw.models.Letter;
import com.moribitotech.and.fw.models.Meter;
import com.moribitotech.and.fw.models.Word;
import com.moribitotech.and.fw.screens.GameScreen;
import com.moribitotech.and.fw.world.WorldLayer1_Game;
import com.moribitotech.mtx.managers.AudioManager;
import com.moribitotech.mtx.managers.VibrationManager;
import com.moribitotech.mtx.settings.MtxLogger;

public class GameInputManager
{
  private GameManager gameManager;
  private Sound soundBlow;
  private Sound soundBurn;

  public GameInputManager(GameManager paramGameManager)
  {
    this.gameManager = paramGameManager;
    this.soundBlow = ((Sound)paramGameManager.getGameScreen().getAssetManager().get("data/s_blow.wav", Sound.class));
    this.soundBurn = ((Sound)paramGameManager.getGameScreen().getAssetManager().get("data/s_burn.mp3", Sound.class));
  }

  public void touchBottomFire(float paramFloat1, float paramFloat2, FireBurn paramFireBurn)
  {
    this.gameManager.getVibrationManager().vibrate(GlobalData.TOUCH_BOTTOM_FIRE_VIBRATION);
    this.gameManager.getAudioManager().playSound(this.soundBurn, GlobalData.SOUND_BURN_VOLUME);
    paramFireBurn.setPosition(paramFloat1 - paramFireBurn.getWidth() / 2.0F, paramFloat2 - paramFireBurn.getHeight() / 2.0F);
    paramFireBurn.burn();
    Meter localMeter = this.gameManager.getWorldLayer1_Game().getMeterBurn();
    localMeter.increaseMeter(1);
    if (localMeter.isMeterMaximum())
    {
      this.gameManager.getPunishmentManager().punish();
      localMeter.reset();
    }
    this.gameManager.getGameData().increaseFireBurnNunmber();
    this.gameManager.getAchievementManager().checkFireTouchAchievements();
    this.gameManager.getTutorialManager().showTutorialBottomFireTouched();
  }

  public void touchLetter(Letter paramLetter)
  {
    this.gameManager.getAudioManager().playSound(this.soundBlow, GlobalData.SOUND_BLOW_VOLUME);
    EffectManager.runLetterTouchDownEffect(paramLetter);
    Word localWord = this.gameManager.getCurrentLevel().getWord();
    paramLetter.setIndexTouched(localWord.getNumberOfTouches());
    paramLetter.setTouched(true);
    localWord.setPuzzleStringBeingWritten(localWord.getPuzzleStringBeingWritten() + paramLetter.getPuzzleLetter());
    MtxLogger.log(true, true, "MtxGameInputManager", "Word being written: " + localWord.getPuzzleStringBeingWritten());
    this.gameManager.getWorldLayer1_Game().getGuessBox().setPuzzleWordBeingWritten(localWord.getPuzzleStringBeingWritten());
    localWord.increaseNumberOfTouches();
    if (localWord.getNumberOfTouches() == localWord.getLetters().length)
      localWord.reset();
    this.gameManager.checkGameCondition();
    if (this.gameManager.getWorldLayer1_Game().getMeterWater().isMeterMaximum())
      this.gameManager.getWorldLayer1_Game().activateBtnFireExtinguisher();
  }
}