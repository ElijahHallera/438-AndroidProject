package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import software.engineering.project1gradecalculator.model.RoomDB;

public class HomePageActivity extends AppCompatActivity {

    private Button Logout;
    private Button courses;
    private Button editAccount;
    TextView display_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class,"RoomDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        Logout = findViewById(R.id.Logout_button);
        courses = findViewById(R.id.BTN_goToCourse);
        editAccount = findViewById(R.id.edit_button);

        //Pulls the Logged in User from the Log in Page.
        display_username = (TextView) findViewById(R.id.display_username);
        String displayName = db.dao().getUserByName(MainActivity.currentUser).getFirstName();
        display_username.setText("Welcome " + displayName + "!");


        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, Course.class);
                startActivity(intent);
            }
        });

        editAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, EditAccount.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                MainActivity.currentUser = null;
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
