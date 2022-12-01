package laundro;
import java.util.*;



import java.io.*;
import java.text.SimpleDateFormat;

public class main_sc {
	
	
	static ArrayList <User> users = new ArrayList<>();
	static ArrayList<Washplan> washplans = new ArrayList<>();
	static ArrayList<Hostel> hostels = new ArrayList<>();
	static boolean adminlogged=false;
	static
	{washplans.add(new Washplan(100,10,"A",6));
	washplans.add(new Washplan(200,25,"B",4));
	hostels.add(new Hostel("Budh"));
	hostels.add(new Hostel("Vyas"));}
 	static User login(int n) 
	{Scanner sc = new Scanner(System.in);
		System.out.println("Enter ID number");
			String idno= sc.nextLine();
			
			int flag=0;
	
			// to search whether user exists
			for( User u : users){
				
				if(u.idnumber.equals(idno))
				{flag=1;
				//System.out.println("Logged In!!");
				return u;
				}
				}
			
			if(n==0)
			{
			if(flag==0)
			{
				System.out.println("Enter Name ");

				String name = sc.nextLine();
				System.out.println("Hostels:");
				for(Hostel h : hostels)
				{
					System.out.println(h);
				}
				Hostel hos = hostels.get(0);
				int f=0;
				while(f==0)
				{System.out.println("Select Hostel ");
					String ch=sc.nextLine();
					for(Hostel h : hostels)
					{
						if(h.name.equalsIgnoreCase(ch))
							{f=1;
							hos=h;
							break;
							
							}
					}
				}
				System.out.println("Select washplan");
				for(Washplan w: washplans)
				{
					System.out.println(w);
				}
				
				f=0;
				while(f==0)
				{System.out.println("enter wash code");
					String ch=sc.nextLine();
					for(Washplan w: washplans)
					{
						if(w.code.equalsIgnoreCase(ch))
							{f=1;
							users.add(new User( name, idno, w,hos));
							System.out.println("Registration complete!!");
							hos.displayDrop();
							int s = users.size()-1;
							return users.get(s);
							}
					}
				}
			
			}
			return users.get(0);}
			else
				{System.out.println("User not found");
				return new User("404","404",washplans.get(0),hostels.get(0));}
			
	}

 	static boolean realUser(User u)
 	{
 		if(u.name.equals("404"))
 			return false;
 		else 
 			return true;
 	}
 	void drop()
 	{Scanner sc = new Scanner(System.in);
 		User curUser = login(0);
 		System.out.println("Enter weight of clothes:");
 		double weight= Double.parseDouble(sc.nextLine());
 		System.out.println("Enter date of drop in dd/mm/yyyy format:");
 		
 		String date = sc.nextLine();
 		
 		System.out.println("Enter day of drop");
 		String day =sc.nextLine();
 		if(day.equalsIgnoreCase(curUser.hos.dropDate))
 		{Wash w = new Wash(date,weight);
 		curUser.addWash(w);
 		}
 		else {
 			System.out.println("Drop cancelled.  Cannot drop laundry on this date");
 		}
 	}
 	
 	
 	void lastDropped() 
 	{
 		User curUser = login(1);
 		if(realUser(curUser)==false)
 			return;
 		int size = curUser.washes.size();
 		if(size==0 || curUser.washes.get(size-1).collected==true)
 			{System.out.println("Laundry not dropped");
 			return;}
 		if(curUser.washes.get(size-1).washed==false)
 		{
 			System.out.println("Laundry in wash");
 		}
 		else
 		{
 			System.out.println("Laundry washed. Out for delivery");
 		}
	}
 	
 	void printDetails(User curUser)
 	{
 		System.out.println("");
 		System.out.println("Name:" + curUser.name);
 		System.out.println("Hostel:" + curUser.hos);
 		System.out.println("Alloted Drop Day:" + curUser.hos.dropDate);
 		System.out.println("Time of delivery:"+ curUser.hos.time);
 		System.out.println("Total Bill:" + curUser.charges);
 		int i=1;
 		if(curUser.washes.size()!=0)
 		{System.out.println("Laundry details");
 		for(Wash w:curUser.washes)
 		{
 			System.out.print(i+". ");
 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 			System.out.println("Date Dropped:"+sdf.format(w.date.getTime()));
 			
 			System.out.println("   Price:"+w.price);
 			System.out.println("   Extra Price:"+w.extraprice);
 			
 			
 			if(w.collected==false) 
 			{
 			if(w.washed==true)
 			System.out.println("	Ready to collect");
 			else
 			System.out.println("	In wash");	
 			}
 			else 
 			System.out.println("	Collected");	
 			System.out.println("");	}
 		}
 		else
 			System.out.println("No Laundry Added");
 	}
 	
 	void status()
 	{
 		User curUser = login(1);
 		if(realUser(curUser)==false)
 			return;
 		System.out.println("");	
 		System.out.println("STATUS");
 		
 		new main_sc().printDetails(curUser);
 		}
 	
 	void receive()
 	{
 		Scanner sc=new Scanner(System.in);
 		User curUser = login(1);
 		if(realUser(curUser)==false)
 			return;
 		System.out.println("Enter date of drop in dd/mm/yyyy format:");
 		String date = sc.nextLine();
 		String sp[]=date.split("/");
 		Calendar entry  = new GregorianCalendar(Integer.parseInt(sp[2]), Integer.parseInt(sp[1])-1, Integer.parseInt(sp[0]));
 		int f=0;
 		for(Wash w: curUser.washes)
 		{
 			if(entry.equals(w.date) && w.washed==true)
 					{
 				w.collected=true;
 				f=1;
 				
 					}
 		}
 		
 		if(f==1)
 			System.out.println("Laundry received successfully");
 		else
 			System.out.println("Laundry could not be received");
 		
 	}
 	
 	void adminLogin()
 	{
 		Scanner sc=new Scanner(System.in);
 		System.out.println("Enter username:");
 		String usernm=sc.nextLine();
 		System.out.println("Enter password:");
 		String password=sc.nextLine();
 		if(usernm.equals("admin") && password.equals("admin"))
 			{adminlogged=true;
 			System.out.println("Welcome admin!");
 			}
 		else 
 			System.out.println("Wrong username or password");
 	}
 	
 	void washdetail()
 	{Scanner sc = new Scanner(System.in);
 		User u = login(1);
			if(realUser(u)==false)
	 			return;
		System.out.println("Enter date of drop in dd/mm/yyyy format:");
	 	String date = sc.nextLine();
	 	String sp[]=date.split("/");
	 	Calendar entry  = new GregorianCalendar(Integer.parseInt(sp[2]), Integer.parseInt(sp[1])-1, Integer.parseInt(sp[0]));
	 
	 	int f=0;
	 	for(Wash w:u.washes)
	 	{if(entry.equals(w.date))
	 		{
	 		if(w.collected==false) 
	 		{
	 			if(w.washed==true)
	 				System.out.println("Ready to collect");
	 			else
	 				System.out.println("In wash");	
	 		}
	 		else 
	 			System.out.println("Collected");	
	 		System.out.println("");
	 		f=1;
	 		
	 		}
	 	
	 	}
	 	if(f==0)
	 		System.out.println("Laundry not dropped on this date");
	 	}
 	
 	void changeStatus()
 	{
 		Scanner sc = new Scanner(System.in);
 		User u = login(1);
			if(realUser(u)==false)
	 			return;
		System.out.println("Enter date of drop in dd/mm/yyyy format:");
	 	String date = sc.nextLine();
	 	String sp[]=date.split("/");
	 	Calendar entry  = new GregorianCalendar(Integer.parseInt(sp[2]), Integer.parseInt(sp[1])-1, Integer.parseInt(sp[0]));
	 
	 	int f=0;
	 	System.out.println("Enter status to set:");
	 	String status=sc.nextLine();
	 	for(Wash w:u.washes)
	 	{if(entry.equals(w.date))
	 		{
	 		if(status.equalsIgnoreCase("washed"))
	 		{
	 			w.washed=true;
	 			f=1;
	 		}
	 		if(status.equalsIgnoreCase("collected"))
	 		{
	 			w.collected=true;
	 			f=1;
	 		}
	 		}
	 	
	 	}
	 	
	 	if(f==0)
	 		System.out.println("Laundry not dropped on this date");
 	}
 	
 	
 	void schedule()
 	{
 		Scanner sc = new Scanner(System.in);
 		System.out.println("Enter hostel name:");
 		String hos=sc.nextLine();
 		for(Hostel h : hostels)
 		{
 			if(h.name.equalsIgnoreCase(hos))
 			{
 				System.out.println("Enter day of delivery:");
 				String day = sc.nextLine();
 				System.out.println("Enter time of delivery");
 				String time = sc.nextLine();
 				h.dropDate=day;
 				h.time=time;
 				return;
 			}
 		}
 		System.out.println("Hostel not found");
 	}
 	
 	void totRev()
 	{
 		for(Hostel h : hostels)
 		{
 			System.out.println(h.name +": " +h.earnings);
 		}
 	}
 	public static void main( String args[]) throws FileNotFoundException, IOException, ClassNotFoundException 
	 {
 		
 		
		
		File usrF = new File("users.txt");
	      if(usrF.isFile())
	      {ObjectInputStream oi= new ObjectInputStream(new FileInputStream(usrF));
	      users = (ArrayList <User>) oi.readObject();}
 		
	      
	      File wplF = new File("washplans.txt");
	      if(usrF.isFile())
	      {ObjectInputStream oi= new ObjectInputStream(new FileInputStream(wplF));
	      washplans = (ArrayList <Washplan>) oi.readObject();}
 		
	      File hF = new File("hostels.txt");
	      if(usrF.isFile())
	      {ObjectInputStream oi= new ObjectInputStream(new FileInputStream(hF));
	      hostels = (ArrayList <Hostel>) oi.readObject();}
 		
	      
	      
		 Scanner sc = new Scanner(System.in);
		 	String choice;
	 	
		 	do
		 	{
		 		System.out.println("Enter Menu choice");
		 		choice = sc.nextLine();
		 		int flag=0;
		 		
		 		if(choice.equals("S"))
		 		{
		 			login(0);flag=1;
		 		}
		 		
		 		if(choice.equals("D"))
		 		{
		 			new main_sc().drop();flag=1;
		 		}
		 		
		 		if(choice.equals("C"))
		 		{
		 			new main_sc().lastDropped();flag=1;
		 		}
		 		if(choice.equals("B"))
		 		{
		 			new main_sc().status();flag=1;
		 		}
		 		
		 		if(choice.equals("R"))
		 		{
		 			new main_sc().receive();flag=1;
		 		}
		 		
		 		if(choice.equals("L1"))
		 		{
		 			new main_sc().adminLogin();flag=1;
		 		}
		 		
		 		if(adminlogged==true)
		 		{
		 			if(choice.equals("AC"))
			 		{
			 			for(User u: users)
			 			{
			 				new main_sc().printDetails(u);flag=1;
			 			}
			 		}
		 			
		 			if(choice.equals("T"))
			 		{
			 			
		 				new main_sc().washdetail();
		 				flag=1;
			 		}
		 			
		 			if(choice.equals("U"))
			 		{
			 			
		 				new main_sc().changeStatus();
		 				flag=1;
			 		}
		 			
		 			if(choice.equals("SA"))
			 		{
		 				flag=1;
		 				new main_sc().schedule();
		 				//work on this a bit
			 			
			 		}
		 			if(choice.equals("RA"))
			 		{
			 			
		 				new main_sc().totRev();
		 				flag=1;
			 		}
		 			if(choice.equals("L2"))
			 		{
			 			
		 				adminlogged=false;
		 				System.out.println("Logged out successfully");
		 				flag=1;
			 			
			 		}
		 		}
		 		if(flag==0)
		 			System.out.println("Invalid choice");
		 	
		 		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(usrF));
		 		 o.writeObject(users);
		 		 
		 		o = new ObjectOutputStream(new FileOutputStream(wplF));
		 		o.writeObject(washplans);
		 		
		 		o = new ObjectOutputStream(new FileOutputStream(hF));
		 		o.writeObject(hostels);
		 		
		 	}
		 	while(choice.equals("exit")==false);
	    }

	
	
		 	}


