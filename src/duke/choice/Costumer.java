package duke.choice;

/*
 * author: vichShir
 */
public class Costumer 
{
	private String name;
	private String size;
	private Clothing[] items;
	
	public Costumer(String name, int measurement)
	{
		this.name = name;
		setSize(measurement);
	}
	
	public String getName() 
	{ 
		return name; 
	}
	
	public String getSize() 
	{
		return size;
	}
	
	public void setSize(String size)
	{
		this.size = size;
	}
	
	public void setSize(int measurement)
	{
		switch (measurement)
		{
			case 1: 
			case 2: 
			case 3:
				setSize("S");
				break;
			case 4: 
			case 5: 
			case 6:
				setSize("M");
				break;
			case 7: 
			case 8: 
			case 9:
				setSize("L");
				break;
			default:
				setSize("X");
		}
	}
	
	public void addItems(Clothing[] items)
	{
		this.items = items;
	}
	
	public Clothing[] getItems()
	{
		return this.items;
	}
	
	public double getTotalClothingCost()
	{
		double total = 0.0;
		
		for (Clothing item: items)
		{
			if(getSize().equals(item.getSize()))
			{
				total += item.getPrice();
				System.out.println("Item: " + item);
			}
		}
		
		return total;
	}
}
