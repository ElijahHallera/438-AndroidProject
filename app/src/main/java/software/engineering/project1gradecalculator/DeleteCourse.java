package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.RoomDB;

public class DeleteCourse extends AppCompatActivity {



    private EditText courseIdText;
    private Button back_button, delete_button;
    String uname = MainActivity.currentUser;
    private List<Course> course_list = CoursePage.avd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);

        courseIdText = (EditText) findViewById(R.id.ET_DCDelete);
        back_button = findViewById(R.id.BTN_DCourseback);
        delete_button = findViewById(R.id.BTN_DCdelete);
        back_button = findViewById(R.id.BTN_DCourseback);
        //** Each user is only allowed to be at one course at a time so the courseId is the course # they'r in */

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Get user specific course list */
                course_list = RoomDB.getRoomDB(DeleteCourse.this).dao().userCourses(uname);
                /** Initialize variables */
                int courseId = 0;
                String courseTitle = "";
                List<Course> tempList = null;
                if ( courseIdText.getText().toString().equals("") ) {
                    Toast.makeText(DeleteCourse.this, "No courses were deleted.",
                            Toast.LENGTH_LONG).show();
                } else {
                    courseId = Integer.parseInt(courseIdText.getText().toString());


                    for (Course c : course_list) {
                        if (c.getCourseID() == courseId) {
                            courseTitle = c.getTitle();
                            RoomDB.getRoomDB(DeleteCourse.this).dao().deleteCourse(c);
                        }
                    } //for
                    if (courseTitle.equals("")) {
                        courseTitle += "No courses were deleted.";
                    }
                }
                Log.d("TO DELETE: courseId ","log: "+ courseId);
                Toast.makeText(DeleteCourse.this, courseTitle,
                        Toast.LENGTH_LONG).show();
                CoursePage.avd = tempList;
                Intent intent = new Intent(DeleteCourse.this, CoursePage.class);
                startActivity(intent);


            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteCourse.this, CoursePage.class);
                startActivity(intent);
            }
        });

    }
}
