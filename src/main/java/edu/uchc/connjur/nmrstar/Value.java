package edu.uchc.connjur.nmrstar;

/**
 * @author mattf
 * 
 * Four types of values:
 *  - unquoted -- `abc`
 *  - single-quoted -- `'abc'` and `'abc''`
 *  - double-quoted -- `"abc"` and `"abc""`
 *  - semicolon-quoted -- `\n;abc\n;`
 */
public class Value {
	
	public static enum ValueType {
		Unquoted,
		Singlequoted,
		Doublequoted,
		Semicolonquoted
		;
	}
	
	public final String value;
	public final ValueType type;
	
	// go through the static utility methods, don't touch the constructor
	private Value(String value, ValueType type) {
	    this.value = value;	
	    this.type = type;
	}
	
	/**
	 * disallowed characters: whitespace
	 * first character can not be one of " # _ \
	 */
	public static boolean isValidUnquoted(String value) {
		if ( value.length() == 0 ) {
			return false;
		}
		if ( Constants.SPECIAL.contains(value.charAt(0)) ) {
			return false;
		}
		for(char c : value.toCharArray()) {
			if ( Constants.WHITESPACE.contains(c) ) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * /^(data|save)_\S+/   <-- isn't a save or data opener
	 * /^(loop|stop|save)_\s/  <-- isn't a loop, stop, save keyword
	 */
	public static boolean isReservedWord(String value) {
		if ( value.length() < 5 ) {
			return false;
		}
		if ( value.equalsIgnoreCase("stop_") || 
			 value.equalsIgnoreCase("loop_") || 
			 value.equalsIgnoreCase("save_") ) {
			return true;
		}
		String prefix = value.substring(0,  5);
		if ( value.length() > 5 && 
			 (prefix.equalsIgnoreCase("save_") || prefix.equalsIgnoreCase("data_")) ) {
			return true;
		}
		return false;
	}

	public static Value unquoted(String value) {
		if ( !Value.isValidUnquoted(value) ) {
			throw new IllegalArgumentException("illegal character in unquoted value");
		}
		if ( Value.isReservedWord(value) ) {
			throw new IllegalArgumentException("unquoted value can not be reserved word");
		}
		return new Value(value, ValueType.Unquoted);
	}
	
	/**
	 * disallowed characters: newlines
	 */
	public static boolean isValidSingleQuoted(String value) {
		for (char c : value.toCharArray()) {
			if ( Constants.NEWLINE.contains(c) ) {
				return false;
			}
		}
		return true;
	}
	
	public static Value singleQuoted(String value) {
		if ( !Value.isValidSingleQuoted(value) ) {
			throw new IllegalArgumentException("illegal character in single-quoted value");
		}
		return new Value(value, ValueType.Singlequoted);
	}
	
	/**
	 * disallowed characters: newlines
	 */
	public static boolean isValidDoubleQuoted(String value) {
		return Value.isValidSingleQuoted(value);
	}
	
	public static Value doubleQuoted(String value) {
		if ( !Value.isValidDoubleQuoted(value) ) {
			throw new IllegalArgumentException("illegal character in double-quoted value");
		}
		return new Value(value, ValueType.Doublequoted);
	}
	
	/**
	 * disallowed character sequences: newline-semicolon
	 */
	public static boolean isValidSemicolonQuoted(String value) {
		for (int i = 0; i < (value.length() - 1); i++) {
			if ( value.charAt(i) == '\n' && value.charAt(i + 1) == ';' ) {
				return false;
			}
		}
		return true;
	}
	
	public static Value semicolonQuoted(String value) {
		if ( !Value.isValidSemicolonQuoted(value) ) {
			throw new IllegalArgumentException("illegal character in semicolon-quoted value");
		}
		return new Value(value, ValueType.Semicolonquoted);
	}
	
	

	
	@Override
	public int hashCode() {
		return this.value.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		return this.value.equals(other);
	}
	
}
