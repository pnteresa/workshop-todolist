package in.masukang.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import in.masukang.todolist.adapter.TodoListAdapter;
import in.masukang.todolist.models.TodoItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.todo_list_view);

        listView.setAdapter(new TodoListAdapter(this, getDummyData()));
    }

    public ArrayList<TodoItem> getDummyData() {
        ArrayList<TodoItem> todoItems = new ArrayList<TodoItem>();

        todoItems.add(new TodoItem("Ngisi workshop"));
        todoItems.add(new TodoItem("Nulis modul"));
        todoItems.add(new TodoItem("Ngerjain PR"));

        return todoItems;
    }


}
