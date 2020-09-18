package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

     private Button courses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        courses = findViewById(R.id.BTN_goToCourse);

        courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, CoursePage.class);
                startActivity(intent);
            }
        });
    }//onCreate()
}//class
