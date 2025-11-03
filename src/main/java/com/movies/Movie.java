package com.movies;

public class Movie {
	private int id;
	private String name;
    private String content;  
    private int length;
    private String type;
    private String summary;
    private int cadmin_id;
   
   //getters and setters of movie object + a toString for system display
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCadmin_id() {
		return cadmin_id;
	}

	public void setCadmin_id(int cadmin_id) {
		this.cadmin_id = cadmin_id;
	}    
	
	  @Override
	    public String toString() {
	        return "Movie [Movie ID=" + id + ", Name=" + name
	                + ", Content=" + content +  ", Length="
	                + length + ", Type=" + type + ", Summary=" + summary + ", Content Admin Id=" + cadmin_id + "]";
	    }

}
