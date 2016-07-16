
public class MexicanPizza extends Pizza {

	MexicanPizza(String type, String topping, String name, float timeForPrep)
			throws InvalidDataEntry {
		super(type, topping, name, timeForPrep);
	}
	
	public String toString()
	{
		return "Mexican Pizza details are : Non-Veg Mexican Pizza with chicken toppings which takes 15 min for preparation";

	}
}
