package pt.isep.intoxicatedapp;

public class Advice {
	private int id;
	private String category;
	private String text;
	
	public Advice(int id, String category, String text){
		this.id = id;
		this.category = category;
		this.text = text;		
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
}
