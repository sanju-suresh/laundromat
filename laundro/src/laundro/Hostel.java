package laundro;
import java.util.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Hostel implements Serializable{
String name;
String dropDate;
double earnings;
String time;
public Hostel(String name)  {

	this.name = name;
	dropDate = "Monday";
	earnings=0;
	time="2:00 pm";
	
}
void editDrop(String day) throws Exception
{ dropDate = day; }

public String toString()
{ return (name);
}

void displayDrop()
{ 
System.out.println("Drop Date:"+ dropDate);  
	}
}
