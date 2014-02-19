package edu.uchc.connjur.nmrstar;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constants {
	
	public static final Set<Character> NEWLINE    = new HashSet<Character>(Arrays.asList('\n', '\r'));
	public static final Set<Character> WHITESPACE = new HashSet<Character>(Arrays.asList('\n', '\r', ' ', '\t'));
	public static final Set<Character> SPECIAL    = new HashSet<Character>(Arrays.asList('"', '#', '_', '\\'));

}
