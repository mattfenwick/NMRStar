package edu.uchc.connjur.main;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uchc.connjur.nmrstar.Data;
import edu.uchc.connjur.nmrstar.Key;
import edu.uchc.connjur.nmrstar.Loop;
import edu.uchc.connjur.nmrstar.Name;
import edu.uchc.connjur.nmrstar.Save;
import edu.uchc.connjur.nmrstar.Value;

public class Example {

	public static void main(String [] args) {
		Key k1 = new Key("key1");
		Key k2 = new Key("key2");
		Name n1 = new Name("d1");
		Name n2 = new Name("s1");
		Value v1 = Value.semicolonQuoted("ab\ncd;e\nq");
		Value v2 = Value.unquoted("abc");
		Value v3 = Value.singleQuoted("123'");
		Value v4 = Value.doubleQuoted("qr st");
		Loop l1 = new Loop(Arrays.asList(k1, k2), 
				 		   Arrays.asList(Arrays.asList(v3, v2), 
				 				   		 Arrays.asList(v2, v4)));
		Loop l2 = new Loop(Arrays.asList(k2), Arrays.<List<Value>>asList());
		Map<Key, Value> keyVals1 = new HashMap<Key, Value>();
		keyVals1.put(k1, v4);
		keyVals1.put(k2, v1);
		Save s1 = new Save(keyVals1, Arrays.asList(l1, l2));
		Map<Key, Value> keyVals2 = new HashMap<Key, Value>();
		keyVals2.put(k2, v3);
		Save s2 = new Save(keyVals2, Arrays.asList(l2));
		Map<Name, Save> saveFrames = new HashMap<Name, Save>();
		saveFrames.put(n1, s1);
		saveFrames.put(n2, s2);
		Data d = new Data(new Name("samp3"), saveFrames);
		
		Serializer ser = new Serializer();
		System.out.print(ser.nmrStar(d).toString());
	}
}
