package com.mrsmyx.todo;
import android.content.*;
import com.mrsmyx.todo.objects.*;
import java.util.*;
import org.json.*;

public class Global
{
    
    public static List<ToDoObject> todos= new ArrayList<ToDoObject>();
    public static SharedPreferences shared;
    
    public static void addTodoObject(ToDoObject todo){
        todos.add(todo);
        try
        {
            saveTodo();
        }
        catch (JSONException e)
        {}
    }
    public static void changeTodoObject(ToDoObject todo, int position){
        todos.set(position, todo);
        try
        {
            saveTodo();
        }
        catch (JSONException e)
        {}
    }
    public static void removeTodo(int position){
        todos.remove(position);
        try
        {
            saveTodo();
        }
        catch (JSONException e)
        {}
    }
    
    public static void saveTodo() throws JSONException{
        
        JSONArray jsonArray = new JSONArray();
        for(ToDoObject todo : todos){
            JSONObject json = new JSONObject();
            json.put("title",todo.getTitle());
            json.put("sdesc",todo.getSDesc());
            json.put("ldesc",todo.getLDesc());
            json.put("time",todo.getTime());
            json.put("icon",todo.getIcon());
            json.put("finished", todo.isFinished());
            jsonArray.put(json);
        }
        SharedPreferences.Editor edit = getShared().edit();
        edit.putString("todos",jsonArray.toString());
        edit.commit();
    }
    
    public static List<ToDoObject> getTodos() throws JSONException{
        List<ToDoObject> j= new ArrayList<ToDoObject>();
        String json = getShared().getString("todos","");
        JSONArray jsonArray = new JSONArray(json);
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject jobj = jsonArray.getJSONObject(i);
            String title = jobj.getString("title"), 
            sdesc = jobj.getString("sdesc"),
            ldesc = jobj.getString("ldesc");
            long time = jobj.getLong("time");
            int icon = jobj.getInt("icon");
            boolean finished = jobj.getBoolean("finished");   
            ToDoObject todo = new ToDoObject(title, sdesc,ldesc,time,icon);
            todo.setFinished(finished);
            j.add(todo);
        }
        return todos = j;      
     }
    
    public static SharedPreferences getShared(){
        return shared;
    }
    public static void setShared(SharedPreferences shard){
        shared = shard;
    }
}
