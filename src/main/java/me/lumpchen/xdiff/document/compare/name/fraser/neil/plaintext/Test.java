package me.lumpchen.xdiff.document.compare.name.fraser.neil.plaintext;

import java.util.LinkedList;

import me.lumpchen.xdiff.document.compare.TextDiffUtil;
import me.lumpchen.xdiff.document.compare.name.fraser.neil.plaintext.diff_match_patch.Diff;

public class Test {

	public static void main(String[] args) {
/*		String s1 = "The diffwarrence algorithm in this library operates in character mode. "
				+ "This produces the most detailed diff possible.  However, "
				+ "for some  one may wish to take a coarser approach.";
		
		String s2 = "applications The diffeedrence algorithm in this library operates in character mode. "
				+ "This produces the most detailed diff possible. However, "
				+ "for some applications one may wish to take a coarser approach.";*/
		
/*		String s1 = "我我-我";
		String s2 = "我你我=他我";
		diff_match_patch dmp = new diff_match_patch();
		dmp.Diff_Timeout = 0;
		LinkedList<Diff> diffs = dmp.diff_main(s1, s2);
		for (Diff diff : diffs) {
			System.out.println(diff);
		}*/

		System.out.println(TextDiffUtil.similarity("abc", "1abcd"));
	
	}
}
