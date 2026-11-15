package Ejemplo1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
	
public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Lookbehind positivo
//		Matcher m = Pattern.compile("(?<=\\$)\\d+").matcher("Total : $250");
//		if (m.find()) System.out.println(m.group());
		
		//Lookahead positivo
//		Matcher m = Pattern.compile("\\d+(?=\\$)").matcher("Total : 45#, 30$");
//		if (m.find()) System.out.println(m.group());
		
		//Lookahead negativo
//		Matcher m = Pattern.compile("\\b[a-zA-Z]+\\b(?!script)").matcher("java y javascript");
//		if (m.find()) System.out.println(m.group());
		
		//Lookbehind negativo
//		Matcher m = Pattern.compile("(?<!-)\b\\d+").matcher("5 -3 8");
//		if (m.find()) System.out.println(m.group());
		
		//Práctica
		Matcher Clave = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%&!])(.{8,})($)").matcher("Java2026!");
		if (Clave.find()) System.out.println(Clave.group());
		
		Clave = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%&!])(.{8,})($)").matcher("java2026");
		if (Clave.find()) System.out.println(Clave.group());
		
	}

}
