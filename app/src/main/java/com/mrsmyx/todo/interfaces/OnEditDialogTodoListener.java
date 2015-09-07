package com.mrsmyx.todo.interfaces;
import com.mrsmyx.todo.objects.*;

public interface OnEditDialogTodoListener
{
    public void OnEditTodo(ToDoObject todo, int position);
    public void OnDeleteTodo(int position);
}
