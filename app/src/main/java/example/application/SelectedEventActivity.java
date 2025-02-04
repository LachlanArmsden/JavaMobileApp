package example.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SelectedEventActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView selectedName;
    private TextView selectedType;
    private TextView selectedDesc;


    private Button backButton;
    private Button bookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_events);

        titleTextView = findViewById(R.id.se_title_textview);
        selectedName = findViewById(R.id.se_name_textview);
        selectedType = findViewById(R.id.se_type_textview);
        selectedDesc = findViewById(R.id.se_desc_textview);

        backButton = findViewById(R.id.se_back_button);
        bookButton = findViewById(R.id.se_book_button);

        backButton.setOnClickListener(v -> onBackButtonClick());
        bookButton.setOnClickListener(v -> onBookButtonClick());

        loadSelected();
    }

    private void loadSelected() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("SELECTED_NAME");
        String type = intent.getStringExtra("SELECTED_TYPE");
        String desc = intent.getStringExtra("SELECTED_DESC");

        selectedName.setText(name);
        selectedType.setText(type);
        selectedDesc.setText(desc);
    }

    private void onBackButtonClick() {
        goToBrowse();
        finish();
    }

    private void goToBrowse() {
        Intent intent = new Intent(SelectedEventActivity.this, BrowseActivity.class);
        startActivity(intent);
    }

    private void onBookButtonClick() {

    }

    private void goToBook() {

    }

}
