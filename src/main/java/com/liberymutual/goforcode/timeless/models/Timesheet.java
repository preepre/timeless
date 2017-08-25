package com.liberymutual.goforcode.timeless.models;

public class Timesheet {
	
    private int id;
    private boolean isComplete;
	private String weekOf;
	private double mondayHours;
	private double tuesdayHours;
	private double wednesdayHours;
	private double thursdayHours;
	private double fridayHours;  


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public boolean getIsComplete() {
        return isComplete;
    }
    
    
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
    
    public String getWeekOf() {
    	return weekOf;
    }
    
    public void setWeekOf(String date) {
        weekOf = date;
    }
    
    public double getMondayHours() {
    	return mondayHours;
    }
    
    public void setMondayHours(double mon) {
    	mondayHours = mon;
    }
    
    public double getTuesdayHours() {
    	return tuesdayHours;
    }
    
    public void setTuesdayHours(double tues) {
    	tuesdayHours = tues;
    }
    
    public double getWednesdayHours() {
    	return wednesdayHours;
    }
    
    public void setWednesdayHours(double wed) {
    	wednesdayHours = wed;
    }
    
    public double getThursdayHours() {
    	return thursdayHours;
    }
    
    public void setThursdayHours(double thurs) {
    	thursdayHours = thurs;
    }
    
    public double getFridayHours() {
    	return fridayHours;
    }
    
    public void setFridayHours(double fri) {
    	fridayHours = fri;
    }
    
    public double getTotalWeeklyHours() {
    	return mondayHours + tuesdayHours + wednesdayHours + thursdayHours + fridayHours;
    }
   
}
