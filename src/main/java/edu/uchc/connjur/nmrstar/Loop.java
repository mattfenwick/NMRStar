package edu.uchc.connjur.nmrstar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author mattf
 * e.g.:
 * 
 * loop_
 *  _a _b
 *  1 2
 *  3 4
 * stop_
 */
public class Loop {

	public final List<Key> keys;
    public final List<List<Value>> rows;

    public Loop(List<Key> keys, List<List<Value>> rows) {
    	Set<Key> keySet = new HashSet<Key>(keys);
    	if ( keys.size() != keySet.size() ) {
    		throw new IllegalArgumentException("loop keys must be unique");
    	}
    	if ( keys.size() == 0 ) {
    		if ( rows.size() > 0 ) {
    			throw new IllegalArgumentException("loop with rows but no keys");
    		}
    	}
    	for (List<Value> row : rows) {
    		if ( keys.size() != row.size() ) {
    			throw new IllegalArgumentException("number of values in loop row must equal number of keys");
    		}
    	}
    	this.keys = keys;
        this.rows = rows;
    }
    
}
