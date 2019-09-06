package me.lumpchen.xdiff.document.compare;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareSetting {

	public boolean enableCompareImage = true;
	public boolean enableComparePath = true;
	public boolean enableMergePath = true;
	public boolean enableCompareAnnots = true;
	public boolean enableTextPositionCompare = true;
	
	public boolean enableImageAppearanceCompare = false;
	public boolean enableAnnotAppearanceCompare = true;
	
	public boolean disableLineBreaking = false;
	
	public float toleranceOfHorPosition = 1f;
	public float toleranceOfVerPosition = 1f;
	
	public float toleranceOfRectWidth = 1f;
	public float toleranceOfRectHeight = 1f;
	
	public boolean ignoreZeroSizeArea = true;
	
	public float scaleOfSpaceingBetweenWords = 0.5f;
	
	public Map<String, List<Rectangle>> acceptanceDifferenceArea = new HashMap<String, List<Rectangle>>(0);
	
	public boolean ignoreInvisibleGraphics = true;
	
	public boolean ignoreInvisibleText = false;
	
	public float globalSearchDistance = -1;
	
	public List<String[]> acceptanceDifferenceFontNameMap = new ArrayList<String[]>(0);
	
	public List<Character[]> acceptanceDifferenceCharMap = new ArrayList<Character[]>(0);
	
	public boolean enablePathPixelCompare = false;
	
	public List<String> symbolFontList = new ArrayList<String>(0);
	
	public CompareSetting() {
	}
	
	public List<Rectangle> getAcceptanceDifferenceArea(int pageNo, boolean isBase) {
		if (this.acceptanceDifferenceArea == null) {
			return new ArrayList<Rectangle>(0);
		}
		String key = pageNo + " " + (isBase ? "control" : "test");
		if (this.acceptanceDifferenceArea.containsKey(key)) {
			return this.acceptanceDifferenceArea.get(key);
		} else {
			key = "n" + " " + (isBase ? "control" : "test");
			if (this.acceptanceDifferenceArea.containsKey(key)) {
				return this.acceptanceDifferenceArea.get(key);
			}
		}
		return new ArrayList<Rectangle>(0);
	}
	
}
