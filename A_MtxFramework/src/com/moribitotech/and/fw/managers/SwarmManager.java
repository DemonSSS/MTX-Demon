//package com.moribitotech.and.fw.managers;
//
//import com.badlogic.gdx.Application;
//import com.badlogic.gdx.Gdx;
//import com.moribitotech.and.fw.models.Lock;
//import com.moribitotech.mtx.settings.MtxLogger;
//import com.swarmconnect.Swarm;
//import com.swarmconnect.SwarmAchievement;
//import com.swarmconnect.SwarmAchievement.AchievementUnlockedCB;
//import com.swarmconnect.SwarmLeaderboard;
//import java.util.Date;
//
//public class SwarmManager
//{
//  public static final int ACHIEVEMENT_10_ID = 13767;
//  public static final int ACHIEVEMENT_11_ID = 13769;
//  public static final int ACHIEVEMENT_12_ID = 13771;
//  public static final int ACHIEVEMENT_13_ID = 13773;
//  public static final int ACHIEVEMENT_14_ID = 13775;
//  public static final int ACHIEVEMENT_15_ID = 13779;
//  public static final int ACHIEVEMENT_1_ID = 13749;
//  public static final int ACHIEVEMENT_2_ID = 13751;
//  public static final int ACHIEVEMENT_3_ID = 13753;
//  public static final int ACHIEVEMENT_4_ID = 13755;
//  public static final int ACHIEVEMENT_5_ID = 13757;
//  public static final int ACHIEVEMENT_6_ID = 13759;
//  public static final int ACHIEVEMENT_7_ID = 13761;
//  public static final int ACHIEVEMENT_8_ID = 13763;
//  public static final int ACHIEVEMENT_9_ID = 13765;
//  private static final int LEADER_BOADRD_1_ID = 9085;
//  private static final int LEADER_BOADRD_2_ID = 9147;
//  public static boolean isActive = true;
//
//  public static void showAchievements()
//  {
//    if ((isActive) && (Gdx.app.getVersion() > 0))
//      Swarm.showAchievements();
//  }
//
//  public static void showDashboard()
//  {
//    if ((isActive) && (Gdx.app.getVersion() > 0))
//      Swarm.showDashboard();
//  }
//
//  public static void showLeaderBodards()
//  {
//    if ((isActive) && (Gdx.app.getVersion() > 0))
//      Swarm.showLeaderboards();
//  }
//
//  public static boolean unlockAchievement(int paramInt)
//  {
//    if ((isActive) && (Gdx.app.getVersion() > 0))
//    {
//      Lock localLock = new Lock();
//      SwarmAchievement.unlock(paramInt, new SwarmAchievement.AchievementUnlockedCB()
//      {
//        public void achievementUnlocked(boolean paramAnonymousBoolean, Date paramAnonymousDate)
//        {
//          SwarmManager.this.setLocked(paramAnonymousBoolean);
//        }
//      });
//      return localLock.isLocked();
//    }
//    MtxLogger.log(true, true, "MtxSwarmManager", "Swarm Manager Not Active / Desktop not support SWARM!");
//    return false;
//  }
//
//  public static void updateScore(int paramInt)
//  {
//    if ((isActive) && (Gdx.app.getVersion() > 0))
//      SwarmLeaderboard.submitScore(9085, paramInt);
//  }
//
//  public static void updateStage(int paramInt)
//  {
//    if ((isActive) && (Gdx.app.getVersion() > 0))
//      SwarmLeaderboard.submitScore(9147, paramInt);
//  }
//}