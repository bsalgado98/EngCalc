package gui;

import java.util.*;
import econ.*;

public class Tester {

	public static void main(String[] args) {
		
		//System.out.println(periodicInterestRateTest());
		//System.out.println(effectiveInterestRateTest());
		//System.out.println(futureWorthTest());
		System.out.println((1.782 * Math.pow(10, -10)) == (0.1782 * Math.pow(10, -9)));
		
	}
	
	public static double periodicInterestRateTest() {
		Exam1 calc = new Exam1("Periodic Interest Rate");
		HashMap<String, Double> values = new HashMap<String, Double>();
		values.put("r", 0.09);
		values.put("M", 4.0);
		calc.inputValues(values);
		return calc.solve();
	}
	
	public static double effectiveInterestRateTest() {
		Exam1 calc = new Exam1("Effective Interest Rate");
		HashMap<String, Double> values = new HashMap<String, Double>();
		values.put("r", 0.09);
		values.put("M", 4.0);
		ArrayList<String> options = new ArrayList<String>();
		options.add("periodically");
		calc.inputValues(values, options);
		return calc.solve();
	}
	
	public static double futureWorthTest() {
		Exam1 calc = new Exam1("Future Worth");
		HashMap<String, Double> values = new HashMap<String, Double>();
		values.put("P", 120000.0);
		values.put("i", 0.09308);
		values.put("N", 25.0);
		ArrayList<String> options = new ArrayList<String>();
		options.add("present");
		calc.inputValues(values, options);
		return calc.solve();
	}

}
