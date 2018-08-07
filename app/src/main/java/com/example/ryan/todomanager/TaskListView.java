package com.example.ryan.todomanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TaskListView extends AppCompatActivity {
    Task[] tasks;
    String[] nameArray;
    ListView listView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tasks = loadTasks();
        nameArray = new String[tasks.length];
        for(int i=0;i<tasks.length;i++){
            nameArray[i] = tasks[i].name;

        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nameArray);
        listView = (ListView) findViewById(R.id.listOfTasks);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Go to existing task
                Intent intent = new Intent(view.getContext(),TaskView.class);
                String selectedTask = (String) parent.getItemAtPosition(position);
                intent.putExtra("POSITION",position);
                startActivity(intent);

                //Create new task and insert into list

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskList tl = loadTasksLists(view.getContext());
                tl.addTask(view.getContext());
                Intent intent = new Intent(view.getContext(), TaskView.class);
                intent.putExtra("POSITION",tl.taskList.length-1);
                startActivity(intent);
            }

            public TaskList loadTasksLists(Context context){
                return (TaskList)SaveLoadData.load("taskInfo.tasks",context);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        tasks = loadTasks();
        nameArray = new String[tasks.length];
        for(int i=0;i<tasks.length;i++){

            nameArray[i] = tasks[i].name;
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nameArray);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener()  {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Go to existing task
                Intent intent = new Intent(view.getContext(),TaskView.class);
                String selectedTask = (String) parent.getItemAtPosition(position);
                intent.putExtra("POSITION",position);
                startActivity(intent);
            }
        });

    }

    public Task[] loadTasks(){
        /*Task[] tasks= new Task[20];
        for(int i=0;i<tasks.length;i++) {
            tasks[i] = new Task("Task "+i);
        }*/
        TaskList taskList=(TaskList)SaveLoadData.load("taskInfo.tasks",this.getApplicationContext());
        tasks=taskList.taskList;
        return tasks;
    }

    public TaskList loadTasksLists(){
        return (TaskList)SaveLoadData.load("taskInfo.tasks",this.getApplicationContext());
    }

    public void saveTasks(){
       /* Task[] tasks= new Task[20];
        for(int i=0;i<tasks.length;i++) {
            tasks[i] = new Task("Task "+i);
        }*/
        TaskList taskList= new TaskList(tasks);
        SaveLoadData.save("taskInfo.tasks",taskList,this.getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
