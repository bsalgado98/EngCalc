package econ;

import java.util.*;

public class Equation {
	
	String eqName;
	ArrayList<String> options;
	HashMap<String, Double> values;
	
	public static final String ECON_EXAM1 = "Econ Exam 1";
	public static final String ECON_EXAM2 = "Econ Exam 2";
	public static final String ECON_EXAM3 = "Econ Exam 3";
	public static final String ECON_EXAM4 = "Econ Exam 4";
	public static final HashSet<String> ECON_EXAMS = new HashSet<String> (Arrays.asList(ECON_EXAM1, ECON_EXAM2, ECON_EXAM3, ECON_EXAM4));
	
	public Equation(String eqName) {
		
		this.eqName = eqName;
		
	}
	
	//ASSUMES values are inputed in correct format (handled by GUI)
	public void inputValues(HashMap<String, Double> values, ArrayList<String> options) {
		this.values = values;
		this.options = options;
	}
	
	public void inputValues(HashMap<String, Double> values) {
		this.values = values;
	}
	
	//Input the name of the equation set you want ("Econ Exam 1", "Econ Exam 2", etc...) and get back the list of equation names from that set
	public static HashSet<String> getEquationNameList(String equationSet) {
		
		switch (equationSet) {
		case ECON_EXAM1:
			return Exam1.EXAM1_EQLIST;
		default:
			return new HashSet<String>(Arrays.asList("Yeah chief, that equation set don't exist..."));
		//TODO ADD REST OF EXAMS
		}
		
	}
	
}
