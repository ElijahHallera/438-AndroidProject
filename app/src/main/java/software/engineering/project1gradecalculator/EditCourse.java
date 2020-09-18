package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.RoomDB;

public class EditCourse extends AppCompatActivity {

    private EditText id, inst, title, desc;
    private Button enter, back;
    private String uname = MainActivity.currentUser;
    private List<Course> clist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        final RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class,"RoomDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        clist = db.dao().userCourses(uname);
        id = findViewById(R.id.ET_ECcid);
        inst = findViewById(R.id.ET_ECins);
        title = findViewById(R.id.ET_ECtitle);
        desc = findViewById(R.id.ET_ECdesc);

        back = findViewById(R.id.BTN_ECback);
        enter = findViewById(R.id.BTN_ECenter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Check the field that should be edited */
                for (Course c: clist) {
                    if (c.getCourseID() == Integer.parseInt(id.getText().toString()) ) {
                        if (!title.getText().toString().equals("")) {
                            c.setTitle(title.getText().toString());
                        }
                        if (!inst.getText().toString().equals("")) {
                            c.setInstructor(inst.getText().toString());
                        }
                        if (!desc.getText().toString().equals("")) {
                            c.setDescription(desc.getText().toString());
                        }
                        db.dao().editCourse(c);
                    }
                }

                Toast.makeText(EditCourse.this, "Courses updated",
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(EditCourse.this, CoursePage.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditCourse.this, CoursePage.class);
                startActivity(intent);
            }
        });

    }
}
