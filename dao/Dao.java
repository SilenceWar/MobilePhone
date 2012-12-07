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
	
	public void addPhone(Phone phone)
	{
		if (phones != null)
			this.phones.add(phone);
		else
			System.out.println("Phone points to null.");
	}
	
	public ArrayList<Phone> getPhones() 
	{
		return new ArrayList<Phone>(phones);
	}
	
}
