package me.lumpchen.xafp.sf.triplet;

import java.io.IOException;

import me.lumpchen.xafp.AFPInputStream;

public class X4DTriplet extends Triplet {

	public static final int ID = 0x4D;
	
	public X4DTriplet() {
		super();
		this.identifier = ID;
		this.name = "Area Definition";
	}
	
	@Override
	protected void readContents(AFPInputStream in) throws IOException {
		while (remain > 0) {
			in.readBytes(remain);
			remain = 0;
		}
	}

}
