package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

public class viewAssignmentPageController {
	@FXML
	private TableView<TOAssignment> overviewTable;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField codeTextField;
	@FXML
	private Button startButton;
	@FXML
	private Button finishButton;
	@FXML
	private Button CancelButton;
	@FXML
	private TextField weekTextField;
	@FXML
	private Button payButton;
	@FXML
	private Button initiateButton;

	@FXML
	public void initialize() {
	    // initialize the overview table by adding new columns
	    overviewTable.getColumns().add(createTableColumn("Route", "number"));
	    var busColumn = createTableColumn("Bus", "licencePlate");
	    overviewTable.getColumns().add(busColumn);
	    overviewTable.getColumns().add(createTableColumn("Shift", "shift"));

	    // Driver column needs to have customized string
	    var driverColumn = new TableColumn<TOAssignment, String>("Driver");
	    driverColumn.setCellValueFactory(data -> Bindings.createStringBinding(
	        () -> data.getValue().getDriverInfo()));
	    overviewTable.getColumns().add(driverColumn);

	    // change the color of the cells based on the value in the TODailyOverview
	    busColumn.setCellFactory(col -> new TableCell<>() {
	      @Override public void updateItem(String item, boolean empty) {
	        super.updateItem(item, empty);
	        var row = getTableRow();
	        setText(item);
	        setTextFill(Color.BLACK);
	        if (row.getItem() != null && row.getItem().getInRepairShop()) {
	          setText(item + " (in repair)");
	          setTextFill(Color.ORANGE);
	        }
	      }
	    });

	    driverColumn.setCellFactory(col -> new TableCell<>() {
	      @Override public void updateItem(String item, boolean empty) {
	        super.updateItem(item, empty);
	        var row = getTableRow();
	        setText(item);
	        setTextFill(Color.BLACK);
	        if (row.getItem() != null && row.getItem().getSick()) {
	          setText(item + " (sick)");
	          setTextFill(Color.RED);
	        }
	      }
	    });
	    
	}
	public static TableColumn<TOAssignment, String> createTableColumn(String header,
		      String propertyName) {
		    TableColumn<TOAssignment, String> column = new TableColumn<>(header);
		    column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
		    return column;
	}
	
	
	// Event Listener on Button[#startButton].onAction
	@FXML
	public void startClicked(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#finishButton].onAction
	@FXML
	public void finishClicked(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#CancelButton].onAction
	@FXML
	public void cancelClicked(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#payButton].onAction
	@FXML
	public void payClicked(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#initiateButton].onAction
	@FXML
	public void initiateClicked(ActionEvent event) {
		// TODO Autogenerated
	}
}
