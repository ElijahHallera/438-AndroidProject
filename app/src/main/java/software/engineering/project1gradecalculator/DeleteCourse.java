package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Delete;
import androidx.room.Room;

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
        final RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class,"RoomDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        courseIdText = (EditText) findViewById(R.id.ET_DCDelete);
        back_button = findViewById(R.id.BTN_DCourseback);
        delete_button = findViewById(R.id.BTN_DCdelete);
        String text = courseIdText.getText().toString();
        int toDelete;
        back_button = findViewById(R.id.BTN_DCourseback);
        //** Each user is only allowed to be at one course at a time so the courseId is the course # they'r in */

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course_list = db.dao().userCourses(uname);

                int u = Integer.parseInt(courseIdText.getText().toString());
                String a1 = "before: ";
                String a2 = "after: ";
                for (Course c: course_list) {
                    a1 += c.getTitle()+"\n";
                }
                String x = "";
                List<Course> tempList = null;
                for(Course c: course_list) {
                    if (c.getCourseID() == u ) {
                        db.dao().deleteCourse(c);
                        x  = c.getTitle();
                        c.setTitle("");
                        //course_list.remove(course_list.indexOf(c));
                    }
                }
                for (Course c: course_list) {
                    a2 += c.getTitle()+"\n";
                }
                if ( x.equals("")) {
                    x+= "none";
                }
                Log.d("TO DELETE: u ","log: "+u);
                Log.d("TO DELETE: a1","log: "+a1);
                Log.d("TO DELETE: a2","log: "+a2);
                Toast.makeText(DeleteCourse.this, x,
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
