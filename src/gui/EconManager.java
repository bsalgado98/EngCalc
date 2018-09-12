package gui;

import econ.*;

import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.lang.reflect.Method; 
import java.lang.reflect.Field; 
import java.lang.reflect.Constructor; 

import gui.Main;;

public class EconManager  {
	
	//Econ Split Pane -> Subject Split Pane
	FlowPane eqBar = new FlowPane();
	//List<Button> eqButtonList = new ArrayList<Button>();
	HashMap<String, Button> eqButtonList = new HashMap<String, Button>();
	
	HBox equationInputArea = new HBox();
	
	public SplitPane econSplitPane = new SplitPane(eqBar, equationInputArea);
	
	SplitPane subjectSplitPane;
	
	EconManager econManager;
	
	public EconManager(SplitPane subjectSplitPane) {

		this.subjectSplitPane = subjectSplitPane;
		econSplitPane.setOrientation(Orientation.VERTICAL);
		
		//econManager = Main.econManager;
		
		setEquationSet(econ.Equation.ECON_EXAM1);
		
		for(String button : eqButtonList.keySet()) {
			eqButtonList.get(button).setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	switch (eqButtonList.get(button).getText()) {
					case "Periodic Interest Rate":
						periodicInterestRate();
					case "Effective Interest Rate":
						effectiveInterestRate();
					case "Present Worth":
						presentWorth();
					case "Future Worth":
						futureWorth();
					case "Annual Worth":
						annualWorth();
			    }		
			    
			    }
			});
		}
	}
	
	public void setEquationSet(String equationSetName) {
		eqButtonList.clear();
		HashSet<String> equationSet = econ.Equation.getEquationNameList(equationSetName);
		for(String eqName : equationSet) {
			eqButtonList.put(eqName, new Button(eqName));
			eqBar.getChildren()
		}
		//eqBar.getChildren().addAll(eqButtonList);
	}

	public void periodicInterestRate() {
		System.out.println("periodic interset rate");
	}
	
	public void effectiveInterestRate() {
		System.out.println("Effective interest rate");
	}
	
	public void presentWorth() {
		System.out.println("present worth");
	}
	
	public void futureWorth() {
		System.out.println("future worth");
	}
	
	public void annualWorth() {
		System.out.println("annual worth");
	}

}
	

	

