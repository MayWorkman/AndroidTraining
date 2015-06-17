package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {
		mItems.add(item);
		notifyDataSetChanged();
	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Get the current ToDoItem
		final ToDoItem toDoItem = mItems.get(position);

		RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(
				R.layout.todo_item, parent, false);

		if (convertView == null) {

			LayoutInflater mInflater = LayoutInflater.from(mContext);
			//convertView = itemLayout.inflate(R.layout.todo_item,  parent, false);
		}

		// Fill in specific ToDoItem data
		// Remember that the data that goes in this View corresponds
		// to the user interface elements defined in the layout file

		// Display Title in TextView
		final TextView titleView = (TextView) itemLayout.findViewById(R.id.titleView);
		titleView.setText(toDoItem.getTitle());

		// Set up Status CheckBox
		final CheckBox statusView = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
		if(toDoItem.getStatus().equals(ToDoItem.Status.DONE)){
			statusView.setChecked(true);
		}
		else{
			statusView.setChecked(false);
		}

		statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					statusView.setChecked(true);
				}
				else{
					statusView.setChecked(false);
				}
			}
		});

		// Display Priority in a TextView
		final TextView priorityView = (TextView) itemLayout.findViewById(R.id.PriorityLabel);
		if(toDoItem.getPriority() == ToDoItem.Priority.HIGH) {
			priorityView.setText(ToDoItem.Priority.HIGH.toString());
		}
		else if(toDoItem.getPriority() == ToDoItem.Priority.MED) {
			priorityView.setText(ToDoItem.Priority.MED.toString());
		}
		else{
			priorityView.setText(ToDoItem.Priority.LOW.toString());
		}

		// Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and time String
		final TextView dateView = (TextView) itemLayout.findViewById(R.id.dateView);
		dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));

		// Return the View just created
		return itemLayout;
	}
}