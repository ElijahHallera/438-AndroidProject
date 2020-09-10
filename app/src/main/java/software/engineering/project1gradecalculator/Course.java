package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Course extends AppCompatActivity {

    private Button add_course, edit_course, delete_course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        add_course = findViewById(R.id.btn_AC);
        edit_course = findViewById(R.id.BTN_editCourse);
        delete_course = findViewById(R.id.BTN_deleteCourse);

        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course.this, AddCourse.class);
                startActivity(intent);
            }
        });

        edit_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course.this, EditCourse.class);
                startActivity(intent);
            }
        });

        delete_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Course.this, DeleteCourse.class);
                startActivity(intent);
            }
        });
    }
}
