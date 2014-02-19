package edu.uchc.connjur.nmrstar;

import java.util.Map;

public class Data {

	public final Name name;
	public final Map<Name, Save> saveFrames;
	
	public Data(Name name, Map<Name, Save> saveFrames) {
		this.name = name;
		this.saveFrames = saveFrames;
	}

}
