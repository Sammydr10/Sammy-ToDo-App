package com.mrsmyx.todo.dialog;
import android.app.*;
import android.view.*;
import android.os.*;
import com.mrsmyx.todo.*;
import com.mrsmyx.todo.interfaces.*;
import android.widget.*;
import com.mrsmyx.todo.objects.*;

public class AddTodoDialogFragment extends DialogFragment implements View.OnClickListener
{

    @Override
    public void onClick(View p1)
    {
        switch(p1.getId()){
            case R.id.add_todo_dialogBtnAdd:
                    ToDoObject todo =
                        new ToDoObject(title.getText().toString(),
                            sDesc.getText().toString(), lDesc.getText().toString(), 
                            datePicker.getCalendarView().getDate(),R.drawable.ic_block_white_48dp);
                            onTodoListener.OnAddTodoItem(todo);
                            dismiss();
                break;
            case R.id.add_todo_dialogBtnClose:
                    dismiss();
                break;
        }
        // TODO: Implement this method
    }
    

    OnTodoListener onTodoListener;
    public AddTodoDialogFragment(OnTodoListener onTodoListener){
        this.onTodoListener = onTodoListener;
    }
    DatePicker datePicker;
    EditText title, sDesc, lDesc;
    Button add, close;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // TODO: Implement this method
       View view = inflater.inflate(R.layout.add_todo_dialog,container,false);
       getDialog().setTitle("Add Todo");
       getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
       add = (Button) view.findViewById(R.id.add_todo_dialogBtnAdd);
       close = (Button) view.findViewById(R.id.add_todo_dialogBtnClose);
       
        datePicker = (DatePicker) view.findViewById(R.id.add_todo_dialogDatePicker);
        title = (EditText) view.findViewById(R.id.add_todo_dialogTitle);
        sDesc = (EditText) view.findViewById(R.id.add_todo_dialogShortDesc);
        lDesc = (EditText) view.findViewById(R.id.add_todo_dialogLongDesc);
        
       add.setOnClickListener(this);
       close.setOnClickListener(this);
       return view;
    }
    
}
