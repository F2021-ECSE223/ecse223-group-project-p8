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
import ca.mcgill.ecse.climbsafe.controller.Utility;

	

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
    String email;
    int num = 0;
    int repeat = 0;
    private int discount = 0;
    private int increment = 0;
    private boolean foundmember = false;
    @FXML
	public void initialize() {
    	spinNum.setText("*");
    
    }
    @FXML
    void applyClick(ActionEvent event) {
    		if (email == null) {
    			ViewUtils.showError("Please select a member first!");
    		} 

    		else if (Utility.findMemberEmail(email).getPrizeDiscount() != 0){
    			ViewUtils.showError("Already spun for this member!");
    			
    			
    		} else if (discount == 0) {
    			ViewUtils.showError("Please spin first!");
    		}else {
    			
    			Utility.findMemberEmail(email).setPrizeDiscount(Integer.parseInt(spinNum.getText()));
    			foundmember = false;
    			ViewUtils.makePopupWindow("","Prize Discount has been applied");
    		}
    }

    @FXML
    void clickSpin(ActionEvent event) {
    	if (foundmember) {
	    	increment = 0;
	    	repeat = 0;
	    	animator();
    	}
	    else {
			ViewUtils.showError("Please select a member first!");
		}
    }

    @FXML
    void searchButton(ActionEvent event) {
    	
    	email=memberTextField.getText();
		if (email == null || email.trim().isEmpty()) {
				foundmember = false;
		      ViewUtils.showError("Please input a valid email");
		}
		else {
			if(Utility.findMemberEmail(email)!=null) {
				if (Utility.findMemberEmail(email).getPrizeDiscount() == 0) {
					foundmember = true;
					memberName.setText(Utility.getMemberName(email));
					ClimbsafeFxmlView.getInstance().refresh();
				} else {
					memberName.setText("Already spun!");	
				}
	
				
			}
			else {
				foundmember = false;
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
        			discount = Integer.parseInt(spinNum.getText());
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
                }
            	repeat++;
                increment++;
                
                ft.stop();
                
                
            });
        	delay = delay*1.15;
        	pause.play();
        	
        	
        }
 
    }

}
