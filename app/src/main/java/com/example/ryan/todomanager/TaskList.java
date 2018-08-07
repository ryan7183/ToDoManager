package com.example.ryan.todomanager;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;

public class TaskList implements Serializable {
    Task[] taskList;

    TaskList(Task[] tasks)
    {
        taskList=tasks;
    }

    public void addTask(Context context){
        Task[] newTaskList = new Task[taskList.length+1];
        for(int i=0;i<taskList.length;i++){
            newTaskList[i]=taskList[i];
        }
        newTaskList[newTaskList.length-1]=new Task("");
        this.taskList=newTaskList;
        SaveLoadData.save("taskInfo.tasks",this,context);
    }

    public void removeTask(int index,Context context){
        Task[] newTaskList = new Task[taskList.length-1];
        int i=0;

        for(int q=0;q<taskList.length;q++){
            if(q!=index){
                newTaskList[i] = taskList[q];
                i=i+1;
            }
        }

        taskList=newTaskList;
        SaveLoadData.save("taskInfo.tasks",this,context);

    }

    /*public void updateTask(Task t,int pos){
        taskList[pos]=t;
    }

    public Task getTask(int pos){
        return taskList[pos];
    }*/
}
