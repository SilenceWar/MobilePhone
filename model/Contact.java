package model;

public class Contact implements Comparable<Contact>
{
	private String name;
	private String phoneNumber;
	
	public Contact (String name, String phoneNumber)
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int compareTo(Contact contact) 
	{
		if (contact == null) {
			System.out.println("compareTo(): Contact points to null.");
			return 0;
		}
		return this.name.compareTo(contact.getName());
	}
}
