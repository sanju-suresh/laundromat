package laundro;

import java.io.Serializable;
import java.util.*;

public class Wash implements Serializable{
Calendar date;
double weight;
boolean washed;
Calendar delDate;
boolean collected;
double price;
double extraprice;
public Wash(String dt, double weight) {
	String sp[]=dt.split("/");
	date = new GregorianCalendar(Integer.parseInt(sp[2]), Integer.parseInt(sp[1])-1, Integer.parseInt(sp[0]));
	this.weight = weight;
	washed = false;
	
	delDate = (Calendar) date.clone();
	delDate.add(Calendar.DATE, 5);
	collected=false;
	price=extraprice=0;
}


}
