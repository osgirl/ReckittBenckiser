package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class DeepFreezerGetterSetter {
	
	String deepfreeztable;
	
	ArrayList<String> fid = new ArrayList<String>();
	ArrayList<String> deep_frrezer = new ArrayList<String>();
	ArrayList<String> type = new ArrayList<String>();
	ArrayList<String> sequence = new ArrayList<String>();

	public String getDeepfreeztable() {
		return deepfreeztable;
	}

	public void setDeepfreeztable(String deepfreeztable) {
		this.deepfreeztable = deepfreeztable;
	}
	
	public ArrayList<String> getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid.add(fid);
	}
	
	public ArrayList<String> getDeep_frrezer() {
		return deep_frrezer;
	}

	public void setDeep_frrezer(String deep_frrezer) {
		this.deep_frrezer.add(deep_frrezer);
	}
	
	public ArrayList<String> getType() {
		return type;
	}

	public void setType(String type) {
		this.type.add(type);
	}
	
	public ArrayList<String> getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence.add(sequence);
	}

}
