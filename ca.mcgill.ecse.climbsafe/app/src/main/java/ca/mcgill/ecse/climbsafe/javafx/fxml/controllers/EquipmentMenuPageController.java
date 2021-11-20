package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.successful;
import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;


import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller; 
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller; 


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

public class EquipmentMenuPageController {
  @FXML private TextField EquipmentNameTextField;
  @FXML private TextField EquipmentWeightTextField;
  @FXML private TextField EquipmentPriceTextField;
  @FXML private Button addEquipmentButton;
  
  @FXML private TextField oldEquipmentNameTextField;
  @FXML private TextField newEquipmentWeightTextField;
  @FXML private TextField newEquipmentPriceTextField;
  @FXML private TextField newEquipmentNameTextField;
  @FXML private Button updateEquipmentButton;
  
  @FXML private TextField equipmentToDeleteTextField;
  @FXML private Button deleteEquipmentButton;

  
  // Event Listener on Button[#addDriverButton].onAction
  @FXML
  public void addEquipmentClicked(ActionEvent event) {
    
    
    try {
      String name = EquipmentNameTextField.getText();
      int weight = Integer.parseInt(EquipmentWeightTextField.getText());
      int price = Integer.parseInt(EquipmentPriceTextField.getText());
      if (name == null || name.trim().isEmpty()) {
        ViewUtils.showError("Please input a valid equipment name");
      }
        else {
    
        if (successful(() -> ClimbSafeFeatureSet4Controller.addEquipment(name, weight, price))) {
          EquipmentNameTextField.setText("");
          EquipmentWeightTextField.setText("");
          EquipmentPriceTextField.setText("");
          ClimbsafeFxmlView.getInstance().refresh();
        }
        }
      
    } catch (NumberFormatException e) {
      ViewUtils.showError("Please input a valid number");
    }
  }
public void updateEquipmentClicked(ActionEvent event) {
    
    
    try {
      String oldName = oldEquipmentNameTextField.getText();
      String newName = newEquipmentNameTextField.getText();
      int newWeight = Integer.parseInt(newEquipmentWeightTextField.getText());
      int newPrice = Integer.parseInt(newEquipmentPriceTextField.getText());
      if (oldName == null || oldName.trim().isEmpty()) {
        ViewUtils.showError("Please input a valid equipment name");
      }
      else if (newName == null || newName.trim().isEmpty()) {
        ViewUtils.showError("Please input a valid equipment name");
      }
        else {
    
        if (successful(() -> ClimbSafeFeatureSet4Controller.updateEquipment(oldName,newName, newWeight, newPrice))) {
          oldEquipmentNameTextField.setText("");
          newEquipmentNameTextField.setText("");
          newEquipmentWeightTextField.setText("");
          newEquipmentPriceTextField.setText("");
          ClimbsafeFxmlView.getInstance().refresh();
        }
        }
      
    } catch (NumberFormatException e) {
      ViewUtils.showError("Please input a valid number");
    }
  }

public void deleteEquipmentClicked(ActionEvent event) {
    String name = equipmentToDeleteTextField.getText();
    if (name == null || name.trim().isEmpty()) {
      ViewUtils.showError("Please input a valid equipment name");
    }
      else {
  
      if (successful(() -> ClimbSafeFeatureSet6Controller.deleteEquipment(name))) {
        equipmentToDeleteTextField.setText("");
        ClimbsafeFxmlView.getInstance().refresh();
      }
      }
}
}

  

