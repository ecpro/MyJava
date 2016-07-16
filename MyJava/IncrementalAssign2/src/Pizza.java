
public class Pizza{
	private String type;
	private String topping;
	private String name;
	private float timeForPrep;
	
	Pizza(String type,String topping,String name,float timeForPrep) throws InvalidDataEntry
	{
		if(type.equalsIgnoreCase("veg"))
			this.type="VEG";
		else if(type.equalsIgnoreCase("non-veg"))
			this.type="NON-VEG";
		else
			throw new InvalidDataEntry();
		
		this.timeForPrep=timeForPrep;
		this.topping=topping;
		this.name=name;
	}
	
	public String toString()
	{
		String s="Type: "+type+"\n"+"Topping: "+topping+"\n"+"Name: "+name+"\n"+"TimeForPrep: "+timeForPrep;
		return s;
	}
}

