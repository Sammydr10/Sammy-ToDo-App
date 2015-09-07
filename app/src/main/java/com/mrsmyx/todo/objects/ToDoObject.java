package com.mrsmyx.todo.objects;

import android.view.*;
import java.util.*;

public class ToDoObject
{
    private String title, 
        sDesc, lDesc;
    private long time;
    private boolean finished;
    private int icon;
    List<View> views;

    public void setIcon(int icon)
    {
        this.icon = icon;
    }

    public int getIcon()
    {
        return icon;
    }
    
    public View getViewById(int id){
        for(View v: views){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }
    public void addViews(View... view){
        for(View v : view){
            views.add(v);
        }
    }
    public ToDoObject(String title, String sDesc, String lDesc, long time, int icon)
    {
        this.icon = icon;
        this.title = title;
        this.sDesc = sDesc;
        this.lDesc = lDesc;
        this.time = time;
        this.finished = false;
        views = new ArrayList<View>();
    }

    public ToDoObject(String title, String sDesc, String lDesc, long time, boolean finished)
    {
        this.title = title;
        this.sDesc = sDesc;
        this.lDesc = lDesc;
        this.time = time;
        this.finished = finished;
        views = new ArrayList<View>();
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

    public void setSDesc(String sDesc)
    {
        this.sDesc = sDesc;
    }

    public String getSDesc()
    {
        return sDesc;
    }

    public void setLDesc(String lDesc)
    {
        this.lDesc = lDesc;
    }

    public String getLDesc()
    {
        return lDesc;
    }

    public void setTime(long time)
    {
        this.time = time;
    }

    public long getTime()
    {
        return time;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    public boolean isFinished()
    {
        return finished;
    }
}
