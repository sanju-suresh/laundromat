package laundro;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class User implements Serializable{
	String name;
	Washplan w;
	String idnumber;
	Hostel hos;
	int charges;
	ArrayList<Wash> washes = new ArrayList<>();
	public User( String name, String id, Washplan w, Hostel h) {
		this.idnumber = id;
		this.name = name;
		this.w = w;
		hos=h;
		charges=0;
	}


	void addWash(Wash lo)
	{
		if(lo.weight>w.maxWeight)
		{
			lo.price=w.maxWeight*w.cost;
			charges+=lo.price;
			lo.extraprice=(lo.weight-w.maxWeight)*120;
			charges +=lo.extraprice;		
			System.out.println("Extra cost has been charged for overweight laundry");
		}
		else
		{
			lo.price=lo.weight*w.cost;
			charges+=lo.price;
		}
		
		hos.earnings+=lo.price+lo.extraprice;
		washes.add(lo);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Laundry added Successfully!!");
		System.out.println("Laundry will be delivered before  "+sdf.format(lo.delDate.getTime())); 
	}

}
