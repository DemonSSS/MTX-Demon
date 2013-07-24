package com.moribitotech.and.fw.models;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.moribitotech.mtx.scene2d.models.SmartGroup;
import com.moribitotech.mtx.utils.UtilsOrigin;
import com.moribitotech.mtx.utils.UtilsOrigin.Origin;
import java.util.Random;

public class Letter extends SmartGroup {
	private int indexCurrent = -2;
	private int indexOriginal = -1;
	private int indexTouched = 3;
	private boolean isHintActive;
	private boolean isTouched = false;
	private Label label;
	private Label labelHint;
	private char puzzleLetter;
	private Table table = new Table();

	public Letter(float paramFloat1, float paramFloat2, boolean paramBoolean,
			Random paramRandom, BitmapFont paramBitmapFont) {
		super(paramFloat1, paramFloat2, paramRandom, paramBoolean);
		this.table.setFillParent(true);
		addActor(this.table);
		Label.LabelStyle localLabelStyle = new Label.LabelStyle(
				paramBitmapFont, null);
		this.label = new Label("", localLabelStyle);
		this.table.add(this.label);
		this.isHintActive = false;
		this.labelHint = new Label("0", localLabelStyle);
		this.labelHint.setSize(getWidth(), getHeight());
		this.labelHint.setPosition(getX(), getHeight() / 2.0F);
		this.labelHint.setScale(0.0F);
		this.labelHint.setVisible(false);
		UtilsOrigin.setActorOrigin(this.labelHint, UtilsOrigin.Origin.CENTER);
		addActor(this.labelHint);
	}

	public void enableHint() {
		this.labelHint.setVisible(true);
		this.isHintActive = true;
	}

	public int getIndexCurrent() {
		return this.indexCurrent;
	}

	public int getIndexOriginal() {
		return this.indexOriginal;
	}

	public int getIndexTouched() {
		return this.indexTouched;
	}

	public Label getLabel() {
		return this.label;
	}

	public char getPuzzleLetter() {
		return this.puzzleLetter;
	}

	public boolean isHintActive() {
		return this.isHintActive;
	}

	public boolean isTouched() {
		return this.isTouched;
	}

	public void setHintActive(boolean paramBoolean) {
		this.isHintActive = paramBoolean;
	}

	public void setIndexCurrent(int paramInt) {
		this.indexCurrent = paramInt;
	}

	public void setIndexOriginal(int paramInt) {
		this.indexOriginal = paramInt;
		int count = paramInt + 1;
		this.labelHint.setText(count + "");
	}

	public void setIndexTouched(int paramInt) {
		this.indexTouched = paramInt;
	}

	public void setLabel(Label paramLabel) {
		this.label = paramLabel;
	}

	public void setPuzzleLetter(char paramChar) {
		this.puzzleLetter = paramChar;
	}

	public void setTouched(boolean paramBoolean) {
		this.isTouched = paramBoolean;
	}
}