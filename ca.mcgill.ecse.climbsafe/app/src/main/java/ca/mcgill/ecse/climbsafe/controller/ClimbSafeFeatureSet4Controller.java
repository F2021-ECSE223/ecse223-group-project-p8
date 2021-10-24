package ca.mcgill.ecse.climbsafe.controller;
import ca.mcgill.ecse.climbsafe.model.Equipment;
/**Methods to add equipment to the ClimbSafe system, and update existing ones
 * 
 * @author MayaAjji
 *
 */
public class ClimbSafeFeatureSet4Controller {
/**Adds a piece of equipment to the system
 * 
 * @param name name of the equipment to be added (it cannot already exist in the system)
 * @param weight weight of equipment to be added
 * @param pricePerWeek price of each piece of equipment, per week
 * @throws InvalidInputException
 */
  public static void addEquipment(String name, int weight, int pricePerWeek) 
      throws InvalidInputException {
    var error= ""; 
    Equipment equipmentEntered;
    if (name.equals("")) {
      error= "Name cannot be empty.";
      throw new InvalidInputException(error);
    }
    if (weight<=0) {
      error = "Weight must be greater than 0.";
      throw new InvalidInputException(error);
      
    }
    if (pricePerWeek<0) {
      error = "Price must be greater than or equal to 0. ";
      throw new InvalidInputException(error);
    }
    
    if (Utility.climbSafe.hasEquipment() && Utility.equipmentExists(name, Utility.climbSafe)) {
      //a piece of equipment with the same name cannot already exist in the system
      error = "A piece of equipment with the name "+ name +" already exists in system";
      throw new InvalidInputException(error);
      }
    
    if (Utility.climbSafe.hasBundles() && Utility.bundleExistsInSystem(Utility.climbSafe, name)) {
      //a bundle with the same name cannot already exist in the system
      error = "An equipment bundle with the name "+ name +" already exists in system.";
    }
  
    try {
      equipmentEntered = Utility.climbSafe.addEquipment(name,weight,pricePerWeek);
    }
    catch (RuntimeException e){
      error = e.getMessage();
      throw new InvalidInputException(error);
    }
  }


/**Updates a piece of equipment in the system
 * 
 * @param oldName 
 * @param newName this cannot be an already existing name
 * @param newWeight 
 * @param newPricePerWeek
 * @throws InvalidInputException
 */
  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {
    var error="";
    if (newName.equals("")) {
      error= "New name cannot be empty.";
      throw new InvalidInputException(error);
    }
    if (newWeight<=0) {
      error = "New weight must be greater than 0.";
      throw new InvalidInputException(error);
    }
    if (newPricePerWeek<0) {
      error = "New price must be greater than or equal to 0.";
      throw new InvalidInputException(error);
    }
    if (!Utility.equipmentExists(oldName, Utility.climbSafe)) {
      error = "This piece of equipment does not exist.";
      throw new InvalidInputException(error);
    }
    
    if (Utility.equipmentExists(newName, Utility.climbSafe)) {
      error= "A piece of equipment with the name "+ newName + " already exists in the system.";
      throw new InvalidInputException(error);
    }
    
    if (Utility.bundleExistsInSystem(Utility.climbSafe, newName)) {
      error = "An equipment Bundle with the same name already exists in the system.";
      throw new InvalidInputException(error);
    }
    
    Equipment eq_to_be_updated = Utility.findEquipment(oldName);
    eq_to_be_updated.setName(newName);
    eq_to_be_updated.setWeight(newWeight);
    eq_to_be_updated.setPricePerWeek(newPricePerWeek);
  }

}
