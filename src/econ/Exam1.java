package econ;

import java.util.*;

public class Exam1 extends Equation{

	//Constant collection of equation names; it is a HashSet because HashSets have the best lookup times
	public static final HashSet<String> EXAM1_EQLIST = new HashSet<>(Arrays.asList("Periodic Interest Rate", "Effective Annual Interest Rate", "Future Worth", "Present Worth", "Annual Worth"));
	
	public Exam1(String eqName) {
		super(eqName);
	}
	
	public Exam1() {
		
	}
	
	@Override
	public double solve() {
		switch (this.eqName) {
			case "Periodic Interest Rate":
				return periodicInterestRate();
			case "Effective Annual Interest Rate":
				return effectiveAnnualInterestRate();
			case "Future Worth":
				return futureWorth();
			case "Present Worth":
				return presentWorth();
			case "Annual Worth":
				return annualWorth();
			default:
				return -1001;
		}
		
	}
	
	//i_m = r/M
	//Assumes r is already a decimal
	public double periodicInterestRate() {
		double i_m;
		double r = this.values.get("r");
		double M = this.values.get("M");
		i_m = r/M;
		return i_m;
	}
	
	public double effectiveAnnualInterestRate() {
		double i_a = -999;
		double r = this.values.get("r");
		
		if(this.options.contains("Periodic")) {
			double M = this.values.get("M");
			i_a = Math.pow(1 + (r/M), M) - 1;
		}
		else if(this.options.contains("Continuous")) {
			i_a = Math.pow(Math.E, r) - 1;
		}
		//else {i_a = 999}
		return i_a;
	}
	
	//Compound amount factor:
	public double futureWorth() {
		double F = -999;
		double i = this.values.get("i");
		double N = this.values.get("N");
		
		if(this.options.contains("Present")) {
			double P = this.values.get("P");
			F = P * Math.pow(1 + i, N);
		}
		else if(this.options.contains("Future")) {
			double A = this.values.get("A");
			F = A * ((Math.pow(1 + i, N) - 1) / i);
		}
		//else {F = -999}
		return F;
	}
	
	public double presentWorth() {
		double P = -999;
		double i = this.values.get("i");
		double N = this.values.get("N");
		
		if(this.options.contains("Present")) {
			double F = this.values.get("F");
			P = F * (i / (Math.pow(1 + i, -N) - 1));
		}
		else if(this.options.contains("Future")) {
			double A = this.values.get("A");
			P = A * ((Math.pow(1 + i, N) - 1) / (i * Math.pow(1 + i, N)));
		}
		//else {P = -999}
		return P;
	}
	
	public double annualWorth() {
		double A = -999;
		double i = this.values.get("i");
		double N = this.values.get("N");
		
		if(this.options.contains("Present")) {
			double F = this.values.get("F");
			A = F * (i / (Math.pow(1 + i, N) - 1));
		}
		else if(this.options.contains("Future")) {
			double P = this.values.get("P");
			A = P * ((i * Math.pow(1 + i, N) / (Math.pow(1 + i, N) - 1)));
		}
		//else {A = -999}
		return A;
	}
	
}
