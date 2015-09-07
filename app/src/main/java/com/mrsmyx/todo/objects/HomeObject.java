package com.mrsmyx.todo.objects;
import android.view.*;
import java.util.*;

public class HomeObject
{
	int image;
	String title;
	List<View> views;
	
	public void addViews(View... view){
		if(views == null) views = new ArrayList<View>();
		for(View v : view){
			views.add(v);
		}
	}
	
	public HomeObject(View... view){
		views = new ArrayList<View>();
		for(View v : view){
			views.add(v);
		}
	}
	public View getViewById(int id){
		View v = null;
		for(View vi : views){
			if(vi.getId() == id){
				return vi;
			}
		}
		return v;
	}
	
	public HomeObject(int image, String title)
	{
		this.image = image;
		this.title = title;
	}

	public void setImage(int image)
	{
		this.image = image;
	}

	public int getImage()
	{
		return image;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}
	
}
