package dao;

import java.util.ArrayList;
import model.Phone;

public class Dao 
{
	private static ArrayList<Phone> phones;
	
	public Dao()
	{
		phones = new ArrayList<Phone>();
	}
	

	public static void addPhone(Phone phone)
	{
		if (phones != null)
			phones.add(phone);
		else
			System.out.println("Phone points to null.");
	}
	
	public static ArrayList<Phone> getPhones() 
	{
		return new ArrayList<Phone>(phones);
	}
	
}
