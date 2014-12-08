package pt.isep.intoxicatedapp;

public class Contact {
	private int id;
	private String name;
	private String phone_number;
	
	public Contact(int id, String name, String phoneNumber){
		this.id = id;
		this.name = name;
		this.phone_number = phoneNumber;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phone_number = phoneNumber;
	}
	
	public String getPhoneNumber(){
		return phone_number;
	}

}
