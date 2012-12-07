package dao;

import java.util.ArrayList;
import model.Phone;

public class Dao 
{
	private ArrayList<Phone> phones;
	
	public Dao()
	{
		this.phones = new ArrayList<Phone>();
	}
	
	public static void addPhone(Phone phones)
	{
		
	}
	
	public void removePhone()
	{
		
	}
	
	public ArrayList<Phone> getAllPhones() 
	{
		return new ArrayList<Phone>(phones);
	}
	
}
