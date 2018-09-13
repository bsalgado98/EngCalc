package gui;

import java.io.*;

import econ.*;


import java.util.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.Method; 
import java.lang.reflect.Field; 
import java.lang.reflect.Constructor; 

import gui.Main;;

public class EconManager {
	
	final String PERIODIC = "Periodic";
	final String CONTINUOUS = "Continuous";
	final String PRESENT = "Present";
	final String FUTURE = "Future";
	
	FlowPane eqBar = new FlowPane();
	//List<Button> eqButtonList = new ArrayList<Button>();
	HashMap<String, Button> eqButtonList = new HashMap<String, Button>();
	
	String currentEquation;
	
	HashMap<String, Double> eqInputFieldValues = new HashMap<String, Double>();
	ArrayList<String> options = new ArrayList<String>();
	
	Equation eq;
	
	Double result;
	Label resultLabel = new Label();
	
	VBox eqFormat = new VBox();
	Image eqImage;
	ImageView eqImageView = new ImageView();
	HashMap<String, TextField> eqInputFields = new HashMap<String, TextField>();
	VBox eqInput = new VBox();
	SplitPane equationInputArea = new SplitPane(eqFormat, eqInput);
	
	RadioButton option1 = new RadioButton();
	RadioButton option2 = new RadioButton();
	ToggleGroup optionButtons = new ToggleGroup();
	
	Button solveButton = new Button("Solve");
	
	public SplitPane econSplitPane = new SplitPane(eqBar, equationInputArea);
	
	SplitPane subjectSplitPane;
	
	EconManager econManager;
	
	public EconManager(SplitPane subjectSplitPane) {
		
		this.subjectSplitPane = subjectSplitPane;
		econSplitPane.setOrientation(Orientation.VERTICAL);
		equationInputArea.setOrientation(Orientation.VERTICAL);
		
		optionButtons.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
		    public void changed(ObservableValue<? extends Toggle> ov,
		        Toggle old_toggle, Toggle new_toggle) {
		            if (optionButtons.getSelectedToggle().equals(option1)) {
		                switch (currentEquation) {
		                case "Periodic Interest Rate":
		                case "Effective Annual Interest Rate":
		                	try {
								effectiveAnnualInterestRate(PERIODIC);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                case "Present Worth":
		                	try {
								presentWorth(PRESENT);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                case "Future Worth":
		                	try {
								futureWorth(PRESENT);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                case "Annual Worth":
		                	try {
								annualWorth(PRESENT);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                }
		            }
		            else if (optionButtons.getSelectedToggle().equals(option2)) {
		            	switch (currentEquation) {
		                case "Periodic Interest Rate":
		                case "Effective Annual Interest Rate":
		                	try {
								effectiveAnnualInterestRate(CONTINUOUS);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                case "Present Worth":
		                	try {
								presentWorth(FUTURE);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                case "Future Worth":
		                	try {
								futureWorth(FUTURE);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                case "Annual Worth":
		                	try {
								annualWorth(FUTURE);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		                }
		            }
		        }
		});
			
		try {
			periodicInterestRate();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		setEquationSet(econ.Equation.ECON_EXAM1);
		
		for(String button : eqButtonList.keySet()) {
			eqButtonList.get(button).setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	switch (eqButtonList.get(button).getText()) {
					case "Periodic Interest Rate":
						try {
							periodicInterestRate();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					case "Effective Annual Interest Rate":
						try {
							effectiveAnnualInterestRate(PERIODIC);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						option1.setText(PERIODIC);
						option1.setUserData(PERIODIC);
						option1.setToggleGroup(optionButtons);
						option1.setSelected(true);
						option2.setText(CONTINUOUS);
						option2.setUserData(CONTINUOUS);
						option2.setToggleGroup(optionButtons);
						break;
					case "Present Worth":
						try {
							presentWorth(PRESENT);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						option1.setText(PRESENT);
						option1.setUserData(PRESENT);
						option1.setToggleGroup(optionButtons);
						option1.setSelected(true);
						option2.setText(FUTURE);
						option2.setUserData(FUTURE);
						option2.setToggleGroup(optionButtons);
						break;
					case "Future Worth":
						try {
							futureWorth(PRESENT);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						option1.setText(PRESENT);
						option1.setUserData(PRESENT);
						option1.setToggleGroup(optionButtons);
						option1.setSelected(true);
						option2.setText(FUTURE);
						option2.setUserData(FUTURE);
						option2.setToggleGroup(optionButtons);
						break;
					case "Annual Worth":
						try {
							annualWorth(PRESENT);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						option1.setText(PRESENT);
						option1.setUserData(PRESENT);
						option1.setToggleGroup(optionButtons);
						option1.setSelected(true);
						option2.setText(FUTURE);
						option2.setUserData(FUTURE);
						option2.setToggleGroup(optionButtons);
						break;
			    }		
			    
			    }
			});
		}
		//eqInput.getChildren().add(solveButton);
		solveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				solve();
			}
		});
	}
	
	public void setEquationSet(String equationSetName) {
		eqButtonList.clear();
		HashSet<String> equationSet = econ.Equation.getEquationNameList(equationSetName);
		for(String eqName : equationSet) {
			eqButtonList.put(eqName, new Button(eqName));
			eqBar.getChildren().add(eqButtonList.get(eqName));
		}

		switch (equationSetName) {
		case econ.Equation.ECON_EXAM1:
			eq = new Exam1(equationSetName);
		}
	}
	
	public void solve() {
		eq.setEquation(currentEquation);
		System.out.println("Equation: " + eq.getEquation());
		for(String eqName : eqInputFields.keySet()) {
			eqInputFieldValues.put(eqName, Double.parseDouble(eqInputFields.get(eqName).getText()));
		}
		options.add(optionButtons.getSelectedToggle().getUserData().toString());
		eq.inputValues(eqInputFieldValues, options);
		result = eq.solve();
		resultLabel.setText(result.toString());
	}

	public void periodicInterestRate() throws FileNotFoundException {
		currentEquation = "Periodic Interest Rate";
		
		eqInputFields.clear();
		
		eqImage = new Image(new FileInputStream("C:\\Users\\bruno\\eclipse-workspace\\EngCalc\\src\\gui\\periodic-interest-rate.PNG"));
		eqImageView.setImage(eqImage);
		eqFormat.getChildren().clear();
		eqFormat.getChildren().add(eqImageView);
		
		eqInput.getChildren().clear();
		Label r_label = new Label("r : ");
		TextField r = new TextField();
		eqInputFields.put("r", r);
		Label M_label = new Label("M : ");
		TextField M = new TextField();
		eqInputFields.put("M", M);
		eqInput.getChildren().addAll(r_label, r, M_label, M, solveButton, resultLabel);
	}
	
	public void effectiveAnnualInterestRate(String option) throws FileNotFoundException {
		currentEquation = "Effective Annual Interest Rate";
		
		eqInputFields.clear();
		
		eqInput.getChildren().clear();
		Label r_label;
		TextField r;
		Label M_label;
		TextField M;
		
		switch (option) {
		case PERIODIC :
			eqImage = new Image(new FileInputStream("C:\\Users\\bruno\\eclipse-workspace\\EngCalc\\src\\gui\\effective-annual-interest-rate_periodic.PNG"));
			r_label = new Label("r : ");
			r = new TextField();
			eqInputFields.put("r", r);
			M_label = new Label("M : ");
			M = new TextField();
			eqInputFields.put("M", M);
			eqInput.getChildren().addAll(r_label, r, M_label, M, solveButton, resultLabel);
			break;
		case CONTINUOUS :
			eqImage = new Image(new FileInputStream("C:\\Users\\bruno\\eclipse-workspace\\EngCalc\\src\\gui\\effective-annual-interest-rate_continuous.PNG"));
			r_label = new Label("r : ");
			r = new TextField();
			eqInputFields.put("r", r);
			eqInput.getChildren().addAll(r_label, r, solveButton, resultLabel);
			break;
		}
		
		eqImageView.setImage(eqImage);
		eqFormat.getChildren().clear();
		eqFormat.getChildren().addAll(eqImageView, option1, option2);
		
	}
	
	public void presentWorth(String option) throws FileNotFoundException {
		currentEquation = "Present Worth";
		
		eqInputFields.clear();
		
		eqInput.getChildren().clear();
		Label F_label;
		TextField F;
		Label A_label;
		TextField A;
		Label i_label;
		TextField i;
		Label N_label;
		TextField N;
		
		switch (option) {
		case PRESENT :
			eqImage = new Image(new FileInputStream("C:\\Users\\bruno\\eclipse-workspace\\EngCalc\\src\\gui\\present-worth_present.PNG"));
			F_label = new Label("F : ");
			F = new TextField();
			eqInputFields.put("F", F);
			i_label = new Label("i : ");
			i = new TextField();
			eqInputFields.put("i", i);
			N_label = new Label("N : ");
			N = new TextField();
			eqInputFields.put("N", N);
			eqInput.getChildren().addAll(F_label, F, i_label, i, N_label, N, solveButton, resultLabel);
			break;
		case FUTURE :
			eqImage = new Image(new FileInputStream("C:\\Users\\bruno\\eclipse-workspace\\EngCalc\\src\\gui\\present-worth_future.PNG"));
			A_label = new Label("A : ");
			A = new TextField();
			eqInputFields.put("A", A);
			i_label = new Label("i : ");
			i = new TextField();
			eqInputFields.put("i", i);
			N_label = new Label("N : ");
			N = new TextField();
			eqInputFields.put("N", N);
			eqInput.getChildren().addAll(A_label, A, i_label, i, N_label, N, solveButton, resultLabel);
			break;
		}
		eqImageView.setImage(eqImage);
		eqFormat.getChildren().clear();
		eqFormat.getChildren().addAll(eqImageView, option1, option2);
	}
	
	public void futureWorth(String option) throws FileNotFoundException {
		currentEquation = "Future Worth";
		
		eqInputFields.clear();
		
		eqInput.getChildren().clear();
		Label P_label;
		TextField P;
		Label A_label;
		TextField A;
		Label i_label;
		TextField i;
		Label N_label;
		TextField N;
		
		switch (option) {
		case PRESENT :
			eqImage = new Image(new FileInputStream("C:\\Users\\bruno\\eclipse-workspace\\EngCalc\\src\\gui\\future-worth_present.PNG"));
			P_label = new Label("P : ");
			P = new TextField();
			eqInputFields.put("P", P);
			i_label = new Label("i : ");
			i = new TextField();
			eqInputFields.put("i", i);
			N_label = new Label("N : ");
			N = new TextField();
			eqInputFields.put("N", N);
			eqInput.getChildren().addAll(P_label, P, i_label, i, N_label, N, solveButton, resultLabel);
			break;
		case FUTURE :
			eqImage = new Image(new FileInputStream("C:\\Users\\bruno\\eclipse-workspace\\EngCalc\\src\\gui\\future-worth_future.PNG"));
			A_label = new Label("A : ");
			A = new TextField();
			eqInputFields.put("A", A);
			i_label = new Label("i : ");
			i = new TextField();
			eqInputFields.put("i", i);
			N_label = new Label("N : ");
			N = new TextField();
			eqInputFields.put("N", N);
			eqInput.getChildren().addAll(A_label, A, i_label, i, N_label, N, solveButton, resultLabel);
			break;
		}
		eqImageView.setImage(eqImage);
		eqFormat.getChildren().clear();
		eqFormat.getChildren().addAll(eqImageView, option1, option2);
	}
	
	public void annualWorth(String option) throws FileNotFoundException {
		currentEquation = "Annual Worth";
		
		eqInputFields.clear();
		
		eqInput.getChildren().clear();
		Label F_label;
		TextField F;
		Label P_label;
		TextField P;
		Label i_label;
		TextField i;
		Label N_label;
		TextField N;
		
		switch (option) {
		case PRESENT :
			eqImage = new Image(new FileInputStream("C:\\Users\\bruno\\eclipse-workspace\\EngCalc\\src\\gui\\annual-worth_present.PNG"));
			F_label = new Label("F : ");
			F = new TextField();
			eqInputFields.put("F", F);
			i_label = new Label("i : ");
			i = new TextField();
			eqInputFields.put("i", i);
			N_label = new Label("N : ");
			N = new TextField();
			eqInputFields.put("N", N);
			eqInput.getChildren().addAll(F_label, F, i_label, i, N_label, N, solveButton, resultLabel);
			break;
		case FUTURE :
			eqImage = new Image(new FileInputStream("C:\\Users\\bruno\\eclipse-workspace\\EngCalc\\src\\gui\\annual-worth_future.PNG"));
			P_label = new Label("P : ");
			P = new TextField();
			eqInputFields.put("P", P);
			i_label = new Label("i : ");
			i = new TextField();
			eqInputFields.put("i", i);
			N_label = new Label("N : ");
			N = new TextField();
			eqInputFields.put("N", N);
			eqInput.getChildren().addAll(P_label, P, i_label, i, N_label, N, solveButton, resultLabel);
			break;
		}
		eqImageView.setImage(eqImage);
		eqFormat.getChildren().clear();
		eqFormat.getChildren().addAll(eqImageView, option1, option2);
	}

}
	
//TODO
//Equation eqName is being set to the proper equation name after the click.......
//
	

