package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.MenuItem;

import javafx.scene.control.Tab;

import javafx.scene.input.MouseEvent;

import javafx.scene.control.MenuButton;

import javafx.scene.input.InputMethodEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;

public class equipmentBundlePageController {
	@FXML
	private TextField bundleNameInput;
	@FXML
	private MenuButton discountStatusMenu;
	@FXML
	private MenuItem discountStatusTrue;
	@FXML
	private MenuItem discountStatusFalse;
	@FXML
	private TableView bundleItemsTable;
	@FXML
	private TableColumn bundleEquipmentCB;
	@FXML
	private TableColumn costCB;
	@FXML
	private TableColumn weightCB;
	@FXML
	private TableColumn quantityCB;
	@FXML
	private ChoiceBox equipmentMenuCB;
	@FXML
	private TextField equipmentQuanityInputCB;
	@FXML
	private Button addEquipmentToBundleButton;
	@FXML
	private Button createEquipmentBundleButton;
	@FXML
	private Tab updateDiscountStatusMenu;
	@FXML
	private TextField newBundleNameInput;
	@FXML
	private TableColumn bundleEquipmentEB;
	@FXML
	private TableColumn costEB;
	@FXML
	private TableColumn weightEB;
	@FXML
	private TableColumn quanityEB;
	@FXML
	private TextField equipmentQuanityInputEB;
	@FXML
	private Button updateEquipmentInBundleButton;
	@FXML
	private Button updateBundleNameButton;
	@FXML
	private MenuButton newDiscountStatusMenu;
	@FXML
	private MenuItem newDiscountStatusTrue;
	@FXML
	private MenuItem newDiscountStatusFalse;
	@FXML
	private Button updateDiscountStatusButton;
	@FXML
	private ChoiceBox equipmentMenuEB;
	@FXML
	private ChoiceBox selectBundleMenu;

	// Event Listener on TextField[#bundleNameInput].onInputMethodTextChanged
	@FXML
	public void getBundleName(InputMethodEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuButton[#discountStatusMenu].onMouseClicked
	@FXML
	public void getDiscountStatusMenuClick(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#discountStatusTrue].onAction
	@FXML
	public void setDiscountStatusTrue(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#discountStatusFalse].onAction
	@FXML
	public void setDiscountStatusFalse(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#equipmentQuanityInputCB].onInputMethodTextChanged
	@FXML
	public void getEquipmentQuanityCB(InputMethodEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#addEquipmentToBundleButton].onMouseClicked
	@FXML
	public void addEquipmentToBundle(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#createEquipmentBundleButton].onMouseClicked
	@FXML
	public void createEquipmentBundle(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#newBundleNameInput].onInputMethodTextChanged
	@FXML
	public void getNewBundleName(InputMethodEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TextField[#equipmentQuanityInputEB].onInputMethodTextChanged
	@FXML
	public void getEquipmentQuanityEB(InputMethodEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#updateEquipmentInBundleButton].onMouseClicked
	@FXML
	public void updateEquipmentInBundle(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#updateBundleNameButton].onMouseClicked
	@FXML
	public void updateBundleName(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#newDiscountStatusTrue].onAction
	@FXML
	public void setNewDiscountStatusTrue(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem[#newDiscountStatusFalse].onAction
	@FXML
	public void setNewDiscountStatusFalse(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#updateDiscountStatusButton].onMouseClicked
	@FXML
	public void updateDiscountStatus(MouseEvent event) {
		// TODO Autogenerated
	}
}
