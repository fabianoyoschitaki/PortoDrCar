package com.portoseguro.portodrcar.model;

public class ItemGaveta {
	
	private String titulo;
	private int icone;
	private String count = "0";
	// boolean to set visiblity of the counter
	private boolean isCounterVisible = false;
	
	public ItemGaveta(){}

	public ItemGaveta(String titulo, int icone){
		this.titulo = titulo;
		this.icone = icone;
	}
	
	public ItemGaveta(String titulo, int icone, boolean isCounterVisible, String count){
		this.titulo = titulo;
		this.icone = icone;
		this.isCounterVisible = isCounterVisible;
		this.count = count;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	
	public int getIcone(){
		return this.icone;
	}
	
	public String getCount(){
		return this.count;
	}
	
	public boolean getCounterVisibility(){
		return this.isCounterVisible;
	}
	
	public void setTitulo(String titulo){
		this.titulo = titulo;
	}
	
	public void setIcone(int icone){
		this.icone = icone;
	}
	
	public void setCount(String count){
		this.count = count;
	}
	
	public void setCounterVisibility(boolean isCounterVisible){
		this.isCounterVisible = isCounterVisible;
	}
}
