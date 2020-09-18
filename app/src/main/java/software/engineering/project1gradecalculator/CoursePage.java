package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Delete;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.RoomDB;

public class CoursePage extends AppCompatActivity {

    private Button add_course;
    private Button delete_course, edit_course, back;
    private TextView text;
    private List<Course> courses;
    public static List<Course> avd;
    private String uname = MainActivity.currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        final RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class,"RoomDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        courses = db.dao().getAllCourses();
        text = findViewById(R.id.TV_CPtextV);
        avd = db.dao().userCourses(uname);
        String temp = "";

        for (Course c: avd) {
            //Log.d("TAG:", "log: "+c.getTitle()+"-"+c.getInstructor()+"-"+c.getDescription() );
            temp+= "Instructor: "+c.getInstructor()+"\nTitle: "+c.getTitle()+"\nDescription: "+c.getDescription()+"\nCourse ID:"+c.getCourseID()+"\n";
        }

        text.setText("You are enrolled in these courses: \n"+temp);
        add_course = findViewById(R.id.BTN_CPadd);
        delete_course = findViewById(R.id.dltidloop);
        edit_course = findViewById(R.id.BTN_CPedit);
        back = findViewById(R.id.BTN_CPback);

        add_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CoursePage.this, AddCourse.class);
                startActivity(intent);
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CoursePage.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        delete_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CoursePage.this, DeleteCourse.class);
                startActivity(intent);
            }
        });
        edit_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CoursePage.this, EditCourse.class);
                startActivity(intent);
            }
        });
    }//onCreate()
}//class