package gui;

import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import econ.*;

public class Main extends Application {

	//Menu Toolbar
	public Menu file = new Menu("File");
	public MenuBar toolbar = new MenuBar(file);
	
	//Sidebar
	public TitledPane econPane = new TitledPane();
	public VBox econSubjectBox = new VBox();
	public ArrayList<Button> equationSetButtons = new ArrayList<Button>();
	public TitledPane matPane = new TitledPane();
	public VBox matSubjectBox = new VBox();
	public Button matBtn = new Button("Materials");
	public Accordion sidebar = new Accordion();
	
	//Subject Split Pane -> Main Border Pane
	public SplitPane subjectSplitPane;
	
	//Main Border Pane (Menu buttons)
	public BorderPane borderPane = new BorderPane(subjectSplitPane);

	public StackPane root = new StackPane();
	public Scene main = new Scene(borderPane, 800, 600);
	
	//Starting subject on Subject Split Pane: Engineering Economics
	public EconManager econManager = new EconManager(subjectSplitPane);

	public final double SIDEBAR_PADDING = 30.0;

	@Override
	public void start(Stage primaryStage) {

		SubjectManager subjectManager = new SubjectManager(root);
		//AnchorPane anchorPane = new AnchorPane();
		
		//Sidebar: Engineering Economics
		econPane.setText("Engineering Economics");
		for(String econExamSet : econ.Equation.ECON_EXAMS);
		econPane.setContent(econSubjectBox);
		
		//Sidebar: Materials
		matPane.setText("Materials Processing");
		matSubjectBox.getChildren().add(matBtn);
		matPane.setContent(matSubjectBox);
		
		subjectSplitPane = new SplitPane(sidebar, econManager.econSplitPane);
		
		subjectSplitPane.setOrientation(Orientation.HORIZONTAL);
		
		borderPane.setTop(toolbar);
		borderPane.setCenter(subjectSplitPane);
		
		//AnchorPane.setTopAnchor(econBtn, SIDEBAR_PADDING);
		//AnchorPane.setLeftAnchor(econBtn, SIDEBAR_PADDING);
//		econBtn.setOnAction(new EventHandler<ActionEvent>() {

//			@Override
//			public void handle(ActionEvent event) {
//				subjectManager.setCurrentManager("Engineering Economics");
//				econ = new Scene(econRoot, 800, 600);
//				primaryStage.setScene(econ);
//				System.out.println("Aye my mans");
//			}
//		});

		//anchorPane.getChildren().addAll(econBtn);
		//root.getChildren().addAll(sidebar, borderPane);
		
		sidebar.getPanes().addAll(econPane, matPane);
		
		main.getStylesheets().add("gui/EngCalcStyle.css");
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(main);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
