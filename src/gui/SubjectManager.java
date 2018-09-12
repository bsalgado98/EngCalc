package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SubjectManager {

	Object currentManager;
	StackPane root;
	
	public SubjectManager(StackPane root) {
		this.root = root;
	}
	
	public void setCurrentManager(String currentManager) {
		switch (currentManager) {
		case "Engineering Economics":
			//this.currentManager = new EconManager(this.root);
		}
	}
	
}
