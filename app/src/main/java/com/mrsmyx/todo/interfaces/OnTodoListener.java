package com.mrsmyx.todo.interfaces;
import com.mrsmyx.todo.objects.*;

public interface OnTodoListener
{
    public void OnAddTodoItem(ToDoObject todoObject);
    public void OnRemoveTodoItem(int position);
    
}
