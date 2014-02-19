package edu.uchc.connjur.nmrstar;

import java.util.List;
import java.util.Map;


/**
 * @author mattf
 * e.g.:
 * 
 * save_abc
 *   _xyz 123
 *   _def 456
 *   loop_
 *    _a _b
 *    1 2
 *    3 4
 *   stop_
 * save_
 */
public class Save {

	public final Map<Key, Value> keyVals;
	public final List<Loop> loops;

	public Save(Map<Key, Value> keyVals, List<Loop> loops) {
		this.keyVals = keyVals;
		this.loops = loops;
	}

}
