package com.moribitotech.and.fw.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.moribitotech.mtx.asset.AbstractAssets;
import com.moribitotech.mtx.interfaces.IAssets;

public class Assets extends AbstractAssets implements IAssets {
	public static final String TA_PATH = "data/pack1";
	public static final String anim_blood = "blood_d_";
	public static final String anim_fire = "flame_a_";
	public static final String anim_water_end = "waterend_";
	public static final String anim_water_loop = "waterloop_";
	public static final String anim_water_start = "waterstart_";
	public static final String img_bg = "img_bg";
	public static final String img_btn_achievements = "img_btn_achievements";
	public static final String img_btn_credits = "img_btn_credits";
	public static final String img_btn_fireextingguisher = "img_btn_fireextingguisher";
	public static final String img_btn_games = "img_btn_games";
	public static final String img_btn_hint = "img_btn_hint";
	public static final String img_btn_home = "img_btn_home";
	public static final String img_btn_leaderboard = "img_btn_leaderboard";
	public static final String img_btn_menu = "img_btn_menu";
	public static final String img_btn_music = "img_btn_music";
	public static final String img_btn_play = "img_btn_play";
	public static final String img_btn_rateus = "img_btn_rateus";
	public static final String img_btn_refresh = "img_btn_refresh";
	public static final String img_btn_reset = "img_btn_reset";
	public static final String img_btn_sound = "img_btn_sound";
	public static final String img_btn_vibrate = "img_btn_vibrate";
	public static final String img_credits = "img_credits";
	public static final String img_label_followustweeter = "img_label_followustweeter";
	public static final String img_label_free_hints = "img_label_free_hints";
	public static final String img_label_freeachievementhints = "img_label_freeachievementhints";
	public static final String img_label_likeusfacebook = "img_label_likeusfacebook";
	public static final String img_label_tweetaboutus = "img_label_tweetaboutus";
	public static final String img_loading_gear = "img_loading_gear";
	public static final String img_loading_text = "img_loading_text";
	public static final String img_logo = "img_logo";
	public static final String img_obj_bubble = "img_obj_bubble";
	public static final String img_obj_meter_burn = "img_obj_meter_burn";
	public static final String img_obj_meter_water = "img_obj_meter_water";
	public static final String img_obj_sparkle = "img_obj_sparkle";
	public static final String m_1 = "data/m_1.ogg";
	public static final String m_2 = "data/m_2.ogg";
	public static final String m_3 = "data/m_3.ogg";
	public static final String s_blow = "data/s_blow.wav";
	public static final String s_burn = "data/s_burn.mp3";
	public static final String s_water = "data/s_water.mp3";

	public void loadAll() {
		loadSkin();
		loadTextureAtlas();
		loadImages();
		loadFonts();
		loadAnimations();
		loadSoundsAndMusics();
	}

	public void loadAnimations() {
	}

	public void loadFonts() {
	}

	public void loadImages() {
	}

	public void loadSkin() {
	}

	public void loadSoundsAndMusics() {
		getAssetManager().load("data/m_1.ogg", Music.class);
		getAssetManager().load("data/m_2.ogg", Music.class);
		getAssetManager().load("data/m_3.ogg", Music.class);
		getAssetManager().load("data/s_blow.wav", Sound.class);
		getAssetManager().load("data/s_water.mp3", Sound.class);
		getAssetManager().load("data/s_burn.mp3", Sound.class);
	}

	public void loadTextureAtlas() {
		getAssetManager().load("data/pack1", TextureAtlas.class);
	}
}