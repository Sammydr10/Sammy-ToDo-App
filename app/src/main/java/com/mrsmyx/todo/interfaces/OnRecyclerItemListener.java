package com.mrsmyx.todo.interfaces;
import android.view.*;
import android.content.*;
import android.app.*;

public interface OnRecyclerItemListener
{
	public void OnLongClick(View view, int position, Object object);
	public void OnClick(View view, int position, Object object);
	public void OnSimpleLongClick(View view, int position);
	public void OnSimpleClick(View view, int position);
    public void OnIntentRecieved(Intent intent, ActivityOptions activityOps, int position);
	
}
