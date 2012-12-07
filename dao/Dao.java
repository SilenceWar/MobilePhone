package dao;

import java.util.ArrayList;
import model.Phone;

public class Dao 
{
	private static ArrayList<Phone> phones = new ArrayList<>();
	

	public static void addPhone(Phone phone)
	{
		if (!phones.contains(phone))
			phones.add(phone);
	}
	
	public static ArrayList<Phone> getPhones() 
	{
		return new ArrayList<Phone>(phones);
	}
	
}
