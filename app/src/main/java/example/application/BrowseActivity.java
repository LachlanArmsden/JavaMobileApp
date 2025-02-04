package example.application;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import example.data.Events;

import java.util.ArrayList;
import java.util.List;

public class BrowseActivity extends AppCompatActivity {

    private TextView titleTextview;
    private Button logoutButton;
    private String selectedEventName;
    private String selectedEventDesc;
    private String selectedEventType;

    private List<Events> eventList = new ArrayList<Events>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        titleTextview = findViewById(R.id.browse_title_textview);
        logoutButton = findViewById(R.id.browse_logout_button);
        ListView listView = findViewById(R.id.browse_listview);

        logoutButton.setOnClickListener(v -> onLogoutButtonClick());

        populateActivitiesList();
        populateListView();
        registerClick();
    }

    private void populateActivitiesList() {
        eventList.add(new Events("QUT Event",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                        "tempor incididunt ut labore et dolore magna aliqua. Senectus et netus et " +
                        "malesuada fames ac turpis egestas. Ullamcorper velit sed ullamcorper morbi tincidunt. " +
                        "Libero nunc consequat interdum varius sit amet. Turpis tincidunt id aliquet risus " +
                        "feugiat in ante metus dictum. Varius morbi enim nunc faucibus a pellentesque sit. " +
                        "Feugiat in fermentum posuere urna nec. Nulla facilisi nullam vehicula ipsum. ",
                "Event", 30));

        eventList.add(new Events("Skylight Event",
                "This is the description for the Skylight Event.", "Event", 20));
        eventList.add(new Events("Australia Event",
                "This is the description for the Australia Event.", "Event", 100));
        eventList.add(new Events("ANZAC Day Event",
                "This is the description for the ANZAC Day Event.", "Event", 72));
        eventList.add(new Events("Cloud Event",
                "This is the description for the Cloud Event.", "Event", 2));
        eventList.add(new Events("QUT Garden's Point Tour",
                "This is the description for the QUT Garden's Point Tour.", "Tour", 56));
        eventList.add(new Events("Brisbane Tour",
                "This is the description for the Brisbane Tour.", "Tour", 34));
        eventList.add(new Events("Pacific Ocean Tour",
                "This is the description for the Pacific Ocean Tour.", "Tour", 99));
        eventList.add(new Events("Grand Tour",
                "This is the description for the Grand Tour.", "Tour", 100));
        eventList.add(new Events("Test Tour",
                "This is the description for the Test Tour", "Tour", 0));
    }

    // Populates the listview with all the activities
    private void populateListView() {
        ArrayAdapter<Events> adapter = new activityListAdapter();
        ListView list = (ListView) findViewById(R.id.browse_listview);
        list.setAdapter(adapter);
    }


    // Defines the layout of the ListView, also sets the contents of each item within the ListView
    private class activityListAdapter extends ArrayAdapter<Events> {
        public activityListAdapter() {
            super(BrowseActivity.this, R.layout.item_browse, eventList);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;

            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_browse, parent, false);
            }

            Events currentActivity = eventList.get(position);

            TextView nameText = (TextView) itemView.findViewById(R.id.vBrowse_name_textview);
            nameText.setText(currentActivity.getEventName());

            TextView typeText = (TextView) itemView.findViewById(R.id.vBrowse_type_textview);
            typeText.setText(currentActivity.getEventType());

            TextView capacityText = (TextView) itemView.findViewById(R.id.vBrowse_capacity_textview);
            String capacityString = currentActivity.getEventBooked() + " / " + currentActivity.getEventCapacity();
            capacityText.setText(capacityString);

            return itemView;
        }
    }

    // Method is called when any item in ListView is clicked
    private void registerClick() {
        ListView list = (ListView) findViewById(R.id.browse_listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Events clickedActivity = eventList.get(i);

                selectedEventName = clickedActivity.getEventName();
                selectedEventDesc = clickedActivity.getEventDesc();
                selectedEventType = clickedActivity.getEventType();
                onSelectedEventButtonClick();
            }
        });
    }

    private void onLogoutButtonClick() {
        returnToLogin();
        finish();
    }

    private void returnToLogin() {
        Intent intent = new Intent(BrowseActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void onSelectedEventButtonClick() {
        goToSelectedEvent();
        finish();
    }

    private void goToSelectedEvent() {
        Intent intent = new Intent(BrowseActivity.this, SelectedEventActivity.class);
        intent.putExtra("SELECTED_NAME", selectedEventName);
        intent.putExtra("SELECTED_DESC", selectedEventDesc);
        intent.putExtra("SELECTED_TYPE", selectedEventType);
        startActivity(intent);
    }
}
