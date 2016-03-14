package in.masukang.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.masukang.todolist.adapter.TodoListAdapter;
import in.masukang.todolist.models.TodoItem;

public class MainActivity extends AppCompatActivity {

    TodoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.todo_list_view);

        adapter = new TodoListAdapter(this, getTodoItems());

        listView.setAdapter(adapter);

        Button addTodo = (Button) findViewById(R.id.add_todo_button);

        final Context context = this;

        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Add New Task!");

                final EditText input = new EditText(context);
                alert.setView(input);

                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        String content = input.getText().toString();

                        adapter.add(content);

                        Toast.makeText(getApplicationContext(), "Added new task", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

                alert.show();

            }
        });
    }

    public ArrayList<TodoItem> getTodoItems() {
        ArrayList<TodoItem> todoItems = new ArrayList<TodoItem>();

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String json = sharedPref.getString("todoItems", "");

        if (json != null && json.length() != 0) {
            Gson gson = new Gson();

            try {
                TodoItem[] todoItemsArray = gson.fromJson(json, TodoItem[].class);
                todoItems = new ArrayList<TodoItem>(Arrays.asList(todoItemsArray));
            } catch (Exception e) {
                Log.d("Caught Exception: ", e.getMessage());
            }
        } else {
            todoItems = getDummyData();
        }

        return todoItems;
    }

    public ArrayList<TodoItem> getDummyData() {
        ArrayList<TodoItem> todoItems = new ArrayList<TodoItem>();

        todoItems.add(new TodoItem("Ngisi workshop"));
        todoItems.add(new TodoItem("Nulis modul"));
        todoItems.add(new TodoItem("Ngerjain PR"));

        return todoItems;
    }


}
