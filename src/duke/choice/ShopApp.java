package duke.choice;

import java.util.Arrays;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * author: vichShir
 */
public class ShopApp 
{
	public static void main(String[] args) 
	{
		System.out.println("Welcome to Duke Choice Shop!");
		
		/* Clothing */
		Clothing item1 = new Clothing("Blue Jacket"		, 20.9	, "M");
		Clothing item2 = new Clothing("Orange T-Shirt"	, 10.5	, "S");
		Clothing item3 = new Clothing("Green Scarf"		, 5.0	, "S");
		Clothing item4 = new Clothing("Blue T-Shirt"	, 10.5	, "S");
		
		/* All clothing items */
		Clothing[] items = {item1, item2, item3, item4};
		
		/* Costumer */
		Costumer c1 = new Costumer("Pinky", 3);
		System.out.println("Hi " + c1.getName() + "! Your size is " + c1.getSize());
		
		c1.addItems(items);
		
		/* Average */
		double average = 0;
		int count = 0;
		
		for (Clothing item: c1.getItems()) 
		{
			if(item.getSize().equals("S")) 
			{
				count++;
				average += item.getPrice();
			}
		}
		
		try 
		{
			average = (count == 0) ? 0 : average/count;
		} 
		catch(ArithmeticException e) 
		{
			System.out.println("Nenhum item do mesmo tamanho.");
		}
		
		/* Print Total and Average */
		Arrays.sort(c1.getItems());
		System.out.println("Total: $" + c1.getTotalClothingCost());
		System.out.println("Average: $" + average);
		
		/* Helidon Server - Items List */
		try 
		{
			ItemList list = new ItemList(items);
			
			Routing routing = Routing.builder().get("/items", list).build();
			
			ServerConfiguration config = ServerConfiguration.builder()
					.bindAddress(InetAddress.getLocalHost())
					.port(8080).build();
			
			WebServer ws = WebServer.create(config, routing);
			
			ws.start();
		} 
		catch(UnknownHostException ex) 
		{
			ex.printStackTrace();
		}
	}
}
