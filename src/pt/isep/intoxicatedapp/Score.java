package pt.isep.intoxicatedapp;

public class Score {
	private int id;
	private double score;
	
	public Score(int id, double score){
		this.id = id;
		this.score = score;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public void setScore(double score){
		this.score = score;
	}
	
	public double getScore(){
		return score;
	}
}
