


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This panel is the basic panel, inside which other panels are placed.  
 * Before beginning to implement, design the structure of your GUI in order to 
 * understand what panels go inside which ones, and what buttons or other components
 * go in which panels.  
 * @author ralexander
 *
 */
//make the main panel's layout be a VBox
public class FXMainPane extends VBox {

	//student Task #2:
	//  declare five buttons, a label, and a textfield
	Button b1 = new Button();
	Button b2 = new Button();
	Button b3 = new Button();
	Button b4 = new Button();
	Button b5 = new Button();
	
	Label l1 = new Label();
	
	TextField tf;
	//  declare two HBoxes
	
	HBox hbox;
	HBox hbox2;
	
	
	//student Task #4:
	//  declare an instance of DataManager
	DataManager dm;
	/**
	 * The MainPanel constructor sets up the entire GUI in this approach.  Remember to
	 * wait to add a component to its containing component until the container has
	 * been created.  This is the only constraint on the order in which the following 
	 * statements appear.
	 */
	FXMainPane() {
		//student Task #2:
		//  instantiate the buttons, label, and textfield
		b1.setText("Hello");
		b2.setText("Howdy");
		b3.setText("Chinese");
		b4.setText("Clear");
		b5.setText("Exit");
		
		l1.setText("Feedback:");
		
		tf = new TextField();
		
		//  instantiate the HBoxes
		hbox = new HBox();
		hbox2 = new HBox();
		
		
		//student Task #4:
		//  instantiate the DataManager instance
		dm = new DataManager();
		// button to work
		b1.setOnAction(new ButtonHandler());
		b2.setOnAction(new ButtonHandler());
		b3.setOnAction(new ButtonHandler());	
		b4.setOnAction(new ButtonHandler());
		b5.setOnAction(new ButtonHandler());
		
		//  set margins and set alignment of the components
		this.setPadding(new Insets(20));
		hbox.setSpacing(10);
		hbox2.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		setSpacing(10);
		setAlignment(Pos.CENTER);
		
		
		
		
		//student Task #3:
		//  add the label and textfield to one of the HBoxes
		hbox.getChildren().addAll(l1, tf);
		//  add the buttons to the other HBox
		hbox2.getChildren().addAll(b1,b2,b3,b4,b5);
		//  add the HBoxes to this FXMainPanel (a VBox)
		getChildren().addAll(hbox, hbox2);
	}
	
	//Task #4:
	//  create a private inner class to handle the button clicks
	//inner class named BUttonHandler that implements EventHandler<ActionEvent>
	private class ButtonHandler implements EventHandler<ActionEvent	>{
		//method handle(ActionEvent event)
		public void handle(ActionEvent event) {
			//event.getTarget()
			Object click =event.getTarget();
			//if else is structure
			if(click == b1) {
				tf.setText(dm.getHello());
			}else if(click == b2) {
				tf.setText(dm.getHowdy());
			}else if (click ==b3) {
				tf.setText(dm.getChinese());
			}else if (click ==b4) {
				tf.setText("");
			}else if(click == b5) {
				Platform.exit();
				System.exit(0);
			}
			
			
		}
		
		
	}
	
	
}
	
