
public class ItalianPizza extends Pizza {
	
	ItalianPizza(String type, String topping, String name, float timeForPrep)
			throws InvalidDataEntry {
		super(type, topping, name, timeForPrep);
	}

	public String toString()
	{
		return "Italian Pizza details are: Veg Margherita Pizza with Mushroom toppings which take 10 min for preparation";

	}
}
