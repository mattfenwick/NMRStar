package edu.uchc.connjur.nmrstar;


/**
 * @author mattf
 *
 * e.g. `_abc`
 */
public class Key {

	public final String key;
	
	public Key(String key) {
		if ( !Key.isValidKey(key) ) {
			throw new IllegalArgumentException("illegal character in key");
		}
		this.key = key;
	}
	
	/**
	 * no whitespace allowed
	 */
	public static boolean isValidKey(String key) {
		for (char c : key.toCharArray()) {
			if ( Constants.WHITESPACE.contains(c) ) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return this.key.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		return this.key.equals(other);
	}

}
