package com.mrsmyx.todo.adapters.recyclers;
import android.app.*;
import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.cocosw.bottomsheet.*;
import com.mrsmyx.todo.*;
import com.mrsmyx.todo.dialog.*;
import com.mrsmyx.todo.interfaces.*;
import com.mrsmyx.todo.objects.*;
import java.util.*;

import android.widget.PopupMenu;
import com.mrsmyx.todo.R;
import android.util.*;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.HomeHolder>
{
	
	Context context;
	
    MenuInflater menuInflater;
	LayoutInflater inflater;
	OnRecyclerItemListener oril;
    Activity activity;
	public HomeRecyclerAdapter(Activity activity,Context context,  OnRecyclerItemListener onil, MenuInflater menuInflater)
	{
		this.context = context;	
        this.menuInflater = menuInflater;
		
		this.oril = onil;
        this.activity = activity;
		inflater = LayoutInflater.from(activity.getApplicationContext());
	}

	public void addNewOption(ToDoObject homeObject)
	{
		Global.addTodoObject(homeObject);
		notifyItemInserted(Global.todos.size());
		// TODO: Implement this method
	}
	
	
    
	@Override
	public HomeRecyclerAdapter.HomeHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		// TODO: Implement this method
		
		return new HomeHolder( inflater.inflate(R.layout.check_list_temp, p1, false));
	}

	@Override
	public void onBindViewHolder(HomeRecyclerAdapter.HomeHolder p1, int p2)
	{
		ToDoObject obj = Global.todos.get(p2);
		p1.text.setText(obj.getTitle());
		p1.text2.setText(obj.getSDesc());
      
        if(obj.isFinished()) obj.setIcon(R.drawable.ic_done_all_white_48dp);
        else if(!obj.isFinished()) obj.setIcon(R.drawable.ic_block_white_48dp);
            
          
        p1.image.setImageResource(obj.getIcon());
        
        Date date = new Date(obj.getTime());
        
        p1.date.setText(date.toLocaleString());
        //p1.image.setImageResource(obj.getImage());
		// TODO: Implement this method
	}

	@Override
	public int getItemCount()
	{
		// TODO: Implement this method
		return Global.todos.size();
	}
	
	
    public void addTodoItem(ToDoObject todo){
        Global.addTodoObject(todo);
        notifyItemInserted(Global.todos.size());
    }
    public void removeTodoItem(int pos){
        Global.removeTodo(pos);
        notifyItemRemoved(pos);
    }
    
    public HomeRecyclerAdapter getHomeClass(){
        return this;
    }
	
	public class HomeHolder extends RecyclerView.ViewHolder implements OnClickListener, OnLongClickListener, OnEditDialogTodoListener, PopupMenu.OnMenuItemClickListener
	{

        @Override
        public boolean onLongClick(View p1)
        {
            // TODO: Implement this method
            BottomSheet.Builder s = new BottomSheet.Builder(activity, R.style.BottomSheet_Dialog);
            s.title("Do you want to remove \""+ Global.todos.get(getPosition()).getTitle() +"\"?");
            s.grid();
            s.sheet(R.menu.menu_sheet);
            s.listener(new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface p1, int p2)
                    {
                        switch(p2){
                            case R.id.menu_yes:
                                    removeTodoItem(getPosition());
                                break;
                            case R.id.menu_no:
                                break;
                        }
                        // TODO: Implement this method
                    }


                });
            s.show();
            return false;
        }
        

        @Override
        public void OnEditTodo(ToDoObject todo, int position)
        {
            Global.changeTodoObject(todo, position);
            notifyItemChanged(position);
            // TODO: Implement this method
        }

        @Override
        public void OnDeleteTodo(int position)
        {
            // TODO: Implement this method
        }


        @Override
        public boolean onMenuItemClick(MenuItem p1)
        {
            switch(p1.getItemId()){
                case R.id.todo_edit:
                    try{
                     
                        new EditTodoDialogFragment(Global.todos.get(getPosition()),getPosition(),this).show(activity.getFragmentManager(),"");
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                    return true;
                case R.id.todo_end:
                        Global.todos.get(getPosition()).setFinished(true);
                        notifyDataSetChanged();
                    return true;
            }
            return false;
        }
        

        public void showMenu(View v){
            PopupMenu popup = new PopupMenu(context, v);
            popup.inflate(R.menu.event_menu);
            popup.setOnMenuItemClickListener(this);
            popup.show();
        }
		TextView text,text2,date;
		ImageView image,image2;
		@Override
		public void onClick(View p1)
		{
            switch(p1.getId()){
                
            case R.id.check_list_tempImageView:
                    //RelativeLayout r = (RelativeLayout)((ViewGroup)p1.getParent());
                    showMenu(p1);
                break;
            default:
			if(oril != null){
				Global.todos.get(getPosition()).addViews(text,text2,image);
                
				oril.OnClick(p1, getPosition(),Global.todos.get(getPosition()));
			}
            }
			// TODO: Implement this method
		}
		
		public HomeHolder(View item){
			super(item);
			item.setOnClickListener(this);
            item.setOnLongClickListener(this);
			image = (ImageView) item.findViewById(R.id.hometempImageView1);
			image2 = (ImageView) item.findViewById(R.id.check_list_tempImageView);
            date = (TextView) item.findViewById(R.id.checktemp);
            
            image2.setOnClickListener(this);
            text = (TextView) item.findViewById(R.id.hometempTextView1);
            text2 = (TextView) item.findViewById(R.id.hometempTextView2);
            
		}
	}
}
