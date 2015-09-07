package com.mrsmyx.todo.dialog;
import android.app.*;
import android.os.Bundle;
import android.view.View.*;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrsmyx.todo.R;
import com.mrsmyx.todo.interfaces.OnEditDialogTodoListener;
import com.mrsmyx.todo.objects.ToDoObject;


public class EditTodoDialogFragment extends DialogFragment implements OnClickListener 
{
    ToDoObject todo;
    int pos;
    OnEditDialogTodoListener oedtl;
    public EditTodoDialogFragment(ToDoObject homeRecyclerAdapter, int pos, OnEditDialogTodoListener oedtl)
    {
        this.todo = homeRecyclerAdapter;
        this.pos = pos;
        this.oedtl = oedtl;
    }

    

    public void setPos(int pos)
    {
        this.pos = pos;
    }

    public int getPos()
    {
        return pos;
    }

    @Override
    public void onClick(View p1)
    {
        switch(p1.getId()){
            
        case R.id.search_img:
        
        break;
        case R.id.search_savebtn:
            todo.setTitle(title.getText().toString());
            todo.setSDesc(sdesc.getText().toString());
            todo.setLDesc(ldesc.getText().toString());
            oedtl.OnEditTodo(todo, pos);
            dismiss();
            break;

        case R.id.search_cancelbtn:
            dismiss();
            break;
        }
        // TODO: Implement this method
    }
    ImageView img;
    EditText title, sdesc, ldesc;
    Button cancel, save;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // TODO: Implement this method
        View view = inflater.inflate(R.layout.search_activity,container,false);
        img = (ImageView) view.findViewById(R.id.search_img);  
      //  img.setImageResource(todo.getIcon());
        title = (EditText) view.findViewById(R.id.search_title);
        sdesc = (EditText) view.findViewById(R.id.search_sdesc);
        ldesc = (EditText) view.findViewById(R.id.search_ldesc);
        save = (Button) view.findViewById(R.id.search_savebtn);
        cancel = (Button) view.findViewById(R.id.search_cancelbtn);
        
        save.setOnClickListener(this);
        cancel.setOnClickListener(this);
		img.setOnClickListener(this);
        
        title.setText(todo.getTitle(),TextView.BufferType.EDITABLE);
        sdesc.setText(todo.getSDesc(),TextView.BufferType.EDITABLE);
        ldesc.setText(todo.getLDesc(), TextView.BufferType.EDITABLE);
        
        return view;
    }
    
    
    
}
