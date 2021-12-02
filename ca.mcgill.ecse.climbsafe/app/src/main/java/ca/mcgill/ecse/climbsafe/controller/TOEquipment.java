package ca.mcgill.ecse.climbsafe.controller;

public class TOEquipment {
	

	public String name;
	public int weight;
	public int pricePerWeek;
	
	public TOEquipment(String name, int weight, int pricePerWeek)
	  {
		this.name=name;
		this.weight=weight;
		this.pricePerWeek=pricePerWeek;
	  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPricePerWeek() {
		return pricePerWeek;
	}

	public void setPricePerWeek(int pricePerWeek) {
		this.pricePerWeek = pricePerWeek;
	}
	
	
}
