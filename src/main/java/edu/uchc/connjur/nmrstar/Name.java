package edu.uchc.connjur.nmrstar;


/**
 * @author mattf
 *
 * as in:  `data_123456` or `save_abc_chemical_shift_list_1`
 * disallowed characters:  whitespace
 * unsure: non-ascii and non-printable characters
 * 
 */
public class Name {

	public final String value;
	
	public Name(String value) {
		if ( !Name.isValidName(value) ) {
			throw new IllegalArgumentException("illegal character in Name");
		}
		this.value = value;
	}
	
	public static boolean isValidName(String value) {
		for (char c : value.toCharArray()) {
			if ( Constants.WHITESPACE.contains(c) ) {
				return false;
			}
		}
		return true;
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
