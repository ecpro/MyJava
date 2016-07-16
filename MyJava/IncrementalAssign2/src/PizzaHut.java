import java.util.*;
public class PizzaHut {

	@SuppressWarnings("resource")
	public static void main(String[] args)throws Exception {
		try{
			String loop="y";
			while(loop=="y"||loop=="Y")
			{
				System.out.println("Choose from the following:"+"\n"+" 1 for Mexican Pizza"+"\n"+"2 for Italian Pizza");
				Scanner sc=new Scanner(System.in);
				int choice=sc.nextInt();
				if(choice==1)
				{
					MexicanPizza mp=new MexicanPizza("veg","sdf","fadag",15);
					System.out.println(mp);
				}
				if(choice==2)
				{
					ItalianPizza ip=new ItalianPizza("non-veg", "gasd", "agg", 12);
					System.out.println(ip);
				}
		
			System.out.println("do you want more orders");	
//			if(sc.nextLine())
//			{
//				loop=sc.next();
//			}
			
			loop=sc.nextLine();
					
			
		}}
		catch(InvalidDataEntry e)
		{
			System.out.println(e);
		}
	}

}
