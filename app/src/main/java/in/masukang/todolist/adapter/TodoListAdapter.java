package in.masukang.todolist.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import in.masukang.todolist.MainActivity;
import in.masukang.todolist.R;
import in.masukang.todolist.models.TodoItem;

/**
 * Created by teresa on 3/13/2016.
 */
public class TodoListAdapter extends BaseAdapter {

    ArrayList<TodoItem> todoItems;
    Context context;

    public TodoListAdapter(Context context, ArrayList<TodoItem> todoItems) {
        this.context = context;
        this.todoItems = todoItems;
    }

    public void add(String content) {
        TodoItem newItem = new TodoItem(content);
        todoItems.add(newItem);

        saveItems();

        notifyDataSetChanged();
    }

    public void saveItems() {
        SharedPreferences sharedPref = ((MainActivity) context).getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(todoItems);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("todoItems", json);
        editor.commit();
    }


    @Override
    public int getCount() {
        return todoItems.size();
    }

    @Override
    public TodoItem getItem(int position) {
        return todoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = inflater.inflate(R.layout.list_item_todo, null);
        holder.content = (CheckBox) rowView.findViewById(R.id.item_checkbox);
        holder.dateCreated = (TextView) rowView.findViewById(R.id.date_text_view);

        final TodoItem current = todoItems.get(position);
        holder.content.setText(current.getContent());
        holder.dateCreated.setText(current.getShortDateCreated());

        holder.content.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                current.setIsCompleted(isChecked);
                saveItems();
            }
        });

        holder.content.setChecked(current.isCompleted());

        return rowView;
    }

    public class Holder {
        CheckBox content;
        TextView dateCreated;
    }
}
