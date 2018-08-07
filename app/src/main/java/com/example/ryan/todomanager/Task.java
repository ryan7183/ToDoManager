package com.example.ryan.todomanager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task implements Serializable {

    Task[] subTasks;
    String notes;
    String name;
    String startDateString;
    SimpleDateFormat dateFormat;
    String endDateString;
    Date startDate;
    Date endDate;

    Task(String name){
        this.name=name;
        startDate = Calendar.getInstance().getTime();
        endDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance();//new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        startDateString= dateFormat.format(startDate);
        endDateString=dateFormat.format(endDate);
    }

    public void addSubTask(){

    }
    /*public String getName(){
        return name;
    }

    public void setName(String n){
        name=n;
    }*/
}
