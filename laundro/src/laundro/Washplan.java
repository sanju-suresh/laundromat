package laundro;

import java.io.Serializable;

public class Washplan implements Serializable{
	int cost;
	int washes;
	String code;
	int maxWeight;
Washplan(int co, int wa, String cod,int maxW)
{
	cost = co;
	washes = wa;
	code = cod;
	maxWeight = maxW;
	}
public String toString()
{ return ("Code:"+ code+" Cost per Kg:"+cost+" washes:"+washes + " Max Weight:"+ maxWeight);
}
}
