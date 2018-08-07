package com.example.ryan.todomanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TaskView extends AppCompatActivity {
    Task task;
    TaskList taskList;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        position = getIntent().getIntExtra("POSITION",-1);
        taskList = (TaskList) SaveLoadData.load("taskInfo.tasks",this.getApplicationContext());
        task = taskList.taskList[position];

        String name = task.name;
        String notes=task.notes;

        TextView taskNameView = findViewById(R.id.taskName);
        taskNameView.setText(name);
        TextView taskNotesView = findViewById(R.id.task_notes_txt);
        taskNotesView.setText(notes);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.addSubTask();
                Intent intent = new Intent(view.getContext(), TaskView.class);
                intent.putExtra("POSITION",task.subTasks.length-1);
                startActivity(intent);
            }

            public TaskList loadTasksLists(Context context){
                return (TaskList)SaveLoadData.load("taskInfo.tasks",context);
            }
        });

        //Save button listener
        Button saveBtn = (Button) findViewById(R.id.save_changes_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               saveTask(v);
            }
        });

        //Delete button listener
        Button deleteBtn = (Button) findViewById(R.id.delete_task_btn);

       deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask(v);
            }
        });




    }

    public void deleteTask(View v){
        taskList.removeTask(position,this.getApplicationContext());
        finish();
    }

    public void saveTask(View v){
        EditText nameInput   = (EditText)findViewById(R.id.taskName);
        EditText notesInput   = (EditText)findViewById(R.id.task_notes_txt);
        task.name=nameInput.getText().toString();
        task.notes=notesInput.getText().toString();
        taskList.taskList[position]=task;
        SaveLoadData.save("taskInfo.tasks",taskList,this.getApplicationContext());
    }
}
