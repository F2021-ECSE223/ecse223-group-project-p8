package ca.mcgill.ecse.climbsafe.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import static ca.mcgill.ecse.climbsafe.javafx.fxml.controllers.ViewUtils.successful;

import java.util.List;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet5Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.Utility;
import ca.mcgill.ecse.climbsafe.javafx.fxml.ClimbsafeFxmlView;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Tab;

import javafx.scene.control.TableView;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;

public class equipmentBundlePageController {

	// Storing Items
	List<Equipment> equipmentsCB;
	List<Equipment> equipmentsEB;
	List<String> equipmentNamesCB;
	List<String> equipmentNamesEB;
	List<Integer> equipmentQuantitiesCB;
	List<Integer> equipmentQuantitiesEB;

	@FXML
	private TextField bundleNameInput;
	@FXML
	private TableView<Equipment> bundleItemsTable;
	@FXML
	private TableColumn<Equipment, String> bundleEquipmentCB;
	@FXML
	private TableColumn<Equipment, Double> costCB;
	@FXML
	private TableColumn<Equipment, Double> weightCB;
	@FXML
	private TableColumn<Equipment, Integer> quantityCB;
	@FXML
	private ChoiceBox<String> equipmentMenuCB;
	@FXML
	private TextField equipmentQuantityInputCB;
	@FXML
	private Button addEquipmentToBundleButton;
	@FXML
	private Button createEquipmentBundleButton;
	@FXML
	private TextField guideDiscountCB;
	@FXML
	private Tab updateDiscountStatusMenu;
	@FXML
	private TextField newBundleNameInput;
	@FXML
	private TableColumn<Equipment, String> bundleEquipmentEB;
	@FXML
	private TableColumn<Equipment, Double> costEB;
	@FXML
	private TableColumn<Equipment, Double> weightEB;
	@FXML
	private TableColumn<Equipment, Integer> QuantityEB;
	@FXML
	private TextField equipmentQuantityInputEB;
	@FXML
	private Button updateEquipmentInBundleButton;
	@FXML
	private ChoiceBox<String> equipmentMenuEB;
	@FXML
	private ChoiceBox<String> selectBundleMenu;
	@FXML
	private Button updateEquipmentBundleButton;
	@FXML
	private TextField guideDiscountEB;

	@FXML
	public void initialize() {

		// initializeEquipmentMenuCB
		equipmentMenuCB.addEventHandler(ClimbsafeFxmlView.REFRESH_EVENT, e -> {
			equipmentMenuCB.setItems(ViewUtils.getEquipment());
			equipmentMenuCB.setValue(null);
		});

		// initializeSelectBundleMenu
		selectBundleMenu.addEventHandler(ClimbsafeFxmlView.REFRESH_EVENT, e -> {
			selectBundleMenu.setItems(ViewUtils.getBundles());
			selectBundleMenu.setValue(null);
		});

		// initializeEquipmentMenuEB
		equipmentMenuEB.addEventHandler(ClimbsafeFxmlView.REFRESH_EVENT, e -> {
			equipmentMenuEB.setItems(ViewUtils.getEquipmentFromBundle(selectBundleMenu.getValue()));
			equipmentMenuEB.setValue(null);
		});

		ClimbsafeFxmlView.getInstance().registerRefreshEvent(equipmentMenuCB, selectBundleMenu, equipmentMenuEB);

	}

	// Event Listener on Button[#addEquipmentToBundleButton].onAction
	@FXML
	public void addEquipmentToBundle(ActionEvent event) {
		String equipmentName = equipmentMenuCB.getValue();
		if (equipmentName == null) {
			ViewUtils.showError("Please select an equipment before adding");
		} else {
			try {
				int quantity = Integer.parseInt(this.equipmentQuantityInputCB.getText());
				if (quantity == 0) {
					ViewUtils.showError("Please input a valid quantity");
				} else {
					equipmentsCB.add(ViewUtils.getEquipmentFromName(equipmentName));
					equipmentNamesCB.add(equipmentName);
					equipmentQuantitiesCB.add(quantity);
					equipmentMenuCB.setValue(null);
					equipmentQuantityInputCB.setText("");
				}
			} catch (NumberFormatException e) {
				ViewUtils.showError("Please input a quantity");
			}
		}
	}

	// Event Listener on Button[#createEquipmentBundleButton].onAction
	@FXML
	public void createEquipmentBundle(ActionEvent event) {
		String bundleName = this.bundleNameInput.getText();
		String discountStr = this.guideDiscountCB.getText();
		if (bundleName == null || bundleName.trim().isEmpty()) {
			ViewUtils.showError("Please input a valid bundle name");
		} else if (discountStr == null || discountStr.trim().isEmpty()) {
			ViewUtils.showError("Please input a valid discount number");
		} else {
			try {
				int discount = Integer.parseInt(this.guideDiscountCB.getText());
				if (successful(() -> ClimbSafeFeatureSet5Controller.addEquipmentBundle(bundleName, discount,
						this.equipmentNamesCB, this.equipmentQuantitiesCB))) {
					this.bundleNameInput.setText("");
					this.guideDiscountCB.setText("");
					// update tables
				}
			} catch (NumberFormatException e) {
				ViewUtils.showError("Please input a valid discount number");
			}
		}
	}

	// Event Listener on Button[#updateEquipmentInBundleButton].onAction
	@FXML
	public void updateEquipmentInBundle(ActionEvent event) {
		String equipmentName = equipmentMenuEB.getValue();
		if (equipmentName == null) {
			ViewUtils.showError("Please select an equipment before updating it");
		} else {
			try {
				int quantity = Integer.parseInt(this.equipmentQuantityInputEB.getText());
				equipmentsEB.add(ViewUtils.getEquipmentFromName(equipmentName));
				equipmentNamesEB.add(equipmentName);
				equipmentQuantitiesEB.add(quantity);
				equipmentMenuEB.setValue(null);
				equipmentQuantityInputEB.setText("");
			} catch (NumberFormatException e) {
				ViewUtils.showError("Please input a quantity before updating");
			}
		}
	}

	// Event Listener on Button[#updateEquipmentBundleButton].onAction
	@FXML
	public void updateEquipmentBundle(ActionEvent event) {
		String equipmentOldName = selectBundleMenu.getValue();
		String bundleName = this.newBundleNameInput.getText();
		String discountStr = this.guideDiscountEB.getText();
		if (equipmentOldName == null) {
			ViewUtils.showError("Please select a bundle to edit");
		} else if (bundleName == null || bundleName.trim().isEmpty()) {
			ViewUtils.showError("Please input a valid bundle name");
		} else if (discountStr == null || discountStr.trim().isEmpty()) {
			ViewUtils.showError("Please input a valid discount number");
		} else {
			try {
				int discount = Integer.parseInt(this.guideDiscountCB.getText());
				if (successful(() -> ClimbSafeFeatureSet5Controller.updateEquipmentBundle(equipmentOldName, bundleName,
						discount, this.equipmentNamesEB, this.equipmentQuantitiesEB))) {
					this.selectBundleMenu.setValue(null);
					this.newBundleNameInput.setText("");
					this.guideDiscountEB.setText("");
					// update tables
				}
			} catch (NumberFormatException e) {
				ViewUtils.showError("Please input a valid discount number");
			}
		}
	}

}
