package in.masukang.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

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
        todoItems.add(new TodoItem(content));
        notifyDataSetChanged();
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

        TodoItem current = todoItems.get(position);
        holder.content.setText(current.getContent());
        holder.dateCreated.setText(current.getShortDateCreated());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // nanti akan diisi
            }
        });
        return rowView;
    }

    public class Holder {
        CheckBox content;
        TextView dateCreated;
    }
}
