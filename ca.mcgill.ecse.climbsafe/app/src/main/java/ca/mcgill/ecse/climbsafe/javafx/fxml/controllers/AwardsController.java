package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

	

public class AwardsController {

    @FXML
    private Button apply;

    @FXML
    private Text memberName;

    @FXML
    private TextField memberTextField;

    @FXML
    private Button searchButton;

    @FXML
    private Button spinButton;

    @FXML
    private Text spinNum;
	
    int num = 0;
    int repeat = 0;
    private int increment = 0;
    @FXML
	public void initialize() {
    	spinNum.setText("*");
    
    }
    @FXML
    void applyClick(ActionEvent event) {

    }

    @FXML
    void clickSpin(ActionEvent event) {
    	increment = 0;
    	repeat = 0;
    	animator();
    }

    @FXML
    void searchButton(ActionEvent event) {
    	
    	String email=memberTextField.getText();
		if (email == null || email.trim().isEmpty()) {
		      ViewUtils.showError("Please input a valid email");
		    }
		else {
			if(ViewUtils.findMemberEmail(email)!=null) {
				memberName.setText(ViewUtils.getMemberName(email));
	
				
				ClimbsafeFxmlView.getInstance().refresh();
			}
			else {
				ViewUtils.showError("Member does not exist in system");
			}
	      }
    }
    
    void animator() {
    
    	double delay = 0.05;
     
        for (int i= 0; i< 30; i++) {
        	PauseTransition pause = new PauseTransition(Duration.seconds(delay));
        	pause.setOnFinished(event ->{
        		FadeTransition ft = new FadeTransition(Duration.millis(100), spinNum);
        		if (increment > 31) {
        			spinNum.setFill(Color.rgb(0,0,0,1));

            	} else {
            		Random rng = new Random();
            	    int c = rng.nextInt();
            	    int r = c & 250;
            	    int g = (c >>> 8) & 250;
            	    int b = (c >>> 16) & 250;
       
            	    spinNum.setFill(Color.rgb(r, g, b, 1));
	            	spinNum.setText(String.valueOf(ThreadLocalRandom.current().nextInt(10, 30 + 1)));
	            	ft.setFromValue(0.5);
	            	ft.setToValue(1);
	            	ft.play();
            	}
            	if (repeat<=2) {
                    pause.play();
                } repeat++;
                increment++;
                
                ft.stop();
                
                
            });
        	delay = delay*1.15;
        	pause.play();
        	
        	
        }
 
    }

}
