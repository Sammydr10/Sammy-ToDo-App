package com.mrsmyx.todo;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.getbase.floatingactionbutton.*;
import com.mrsmyx.todo.adapters.recyclers.*;
import com.mrsmyx.todo.dialog.*;
import com.mrsmyx.todo.interfaces.*;
import com.mrsmyx.todo.objects.*;
import java.util.*;

import android.widget.SearchView;
import android.widget.Toolbar;

public class MainActivity extends Activity implements OnTodoListener, OnClickListener, OnRecyclerItemListener
{

    @Override
    public void OnIntentRecieved(Intent intent, ActivityOptions activityOps, int position)
    {
        startActivity(intent, activityOps.toBundle());
        // TODO: Implement this method
    }

    @Override
    public void OnAddTodoItem(ToDoObject todoObject)
    {
        homeAdapter.addTodoItem(todoObject);
        // TODO: Implement this method
    }

    @Override
    public void OnRemoveTodoItem(int position)
    {
        // TODO: Implement this method
    }
    
AddTodoDialogFragment addTodoDialogFragment;
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO: Implement this method
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.event:
                   if(addTodoDialogFragment == null) addTodoDialogFragment = new AddTodoDialogFragment(this);
                   addTodoDialogFragment.show(getFragmentManager(),"");
                return true;
        }
        return false;
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // TODO: Implement this method
        
        
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.global_menu,menu);
        
        return true;
    }

    
    
    
	@Override
	public void OnLongClick(View view, int position, Object object)
	{
		// TODO: Implement this method
	}

    public AlertDialog.Builder showDialog(String title, String desc, int icon){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(desc);
        dialog.setPositiveButton("OK", null);
        return dialog;
    }
    
    
	@Override
	public void OnClick(View view, int position, Object object)
	{
		//Launch Dialog
        ToDoObject todo = (ToDoObject)object;
        showDialog(todo.getTitle(), String.format("Title: %s\nDescription: %s\nSummary: %s",todo.getSDesc(), todo.getLDesc(), new Date(todo.getTime()).toLocaleString()), R.drawable.ic_create_black_48dp).show();
     }

	@Override
	public void OnSimpleLongClick(View view, int position)
	{
		// TODO: Implement this method
	}

	@Override
	public void OnSimpleClick(View view, int position)
	{
		// TODO: Implement this method
	}
	

	@Override
	public void onClick(View p1)
	{
        switch(p1.getId()){
            case R.id.maininclude:
                if(addTodoDialogFragment == null) addTodoDialogFragment = new AddTodoDialogFragment(this);
                addTodoDialogFragment.show(getFragmentManager(),"");       
                break;
        }		
	}

    void makeToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
	Toolbar toolbar;
	ImageView image;
	RecyclerView recycler;
    FloatingActionButton fab;
	HomeRecyclerAdapter homeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
        Global.setShared(getSharedPreferences("TODO",MODE_PRIVATE));
        try{
            List<ToDoObject> to = Global.getTodos();
            if(to != null){
        System.out.println("The Size : " + to.size());
        }else{
            Log.e("size","null");
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        toolbar = (Toolbar) findViewById(R.id.mainToolbar);
         fab = (FloatingActionButton)findViewById(R.id.maininclude);
         fab.setOnClickListener(this);
        
        setActionBar(toolbar);
        
       // getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);
        
		image =(ImageView) findViewById(R.id.mainImageView);
		image.setOnClickListener(this);
		recycler = (RecyclerView) findViewById(R.id.recycler);
		recycler.setLayoutManager(new LinearLayoutManager(this));
		recycler.setAdapter(homeAdapter = new HomeRecyclerAdapter(this, this, this,getMenuInflater()));
	}
}
