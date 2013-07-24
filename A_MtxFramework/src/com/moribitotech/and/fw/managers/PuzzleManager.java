package com.moribitotech.and.fw.managers;

import com.moribitotech.and.fw.models.Letter;
import com.moribitotech.and.fw.models.Word;
import com.moribitotech.mtx.settings.MtxLogger;
import com.moribitotech.mtx.utils.UtilsNumbers;

public class PuzzleManager {
	public static boolean isWordCorrect(Word paramWord) {
		if (paramWord.getPuzzleString().equals(
				paramWord.getPuzzleStringBeingWritten())) {
			MtxLogger.log(true, true, "MtxPuzzleManager",
					"Word is correctly guessed");
			return true;
		}
		return false;
	}

	public static void shuffleWordLetters(Word paramWord) {
		if (paramWord.getLetters().length > 0) {
			int[] arrayOfInt = new int[paramWord.getLetters().length];
			UtilsNumbers.shuffleArray(arrayOfInt);
			for (int i = 0; i < arrayOfInt.length; i++) {
				int j = arrayOfInt[i];
				paramWord.getLetters()[j].setIndexCurrent(arrayOfInt[j]);
			}
		}
	}
}