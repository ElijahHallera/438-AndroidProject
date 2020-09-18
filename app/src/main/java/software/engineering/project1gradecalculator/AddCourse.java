package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import software.engineering.project1gradecalculator.model.RoomDB;
import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.User;

public class AddCourse extends AppCompatActivity {

    private List<Course> xyz;
    private EditText instructor, title, description, course_id;
    private List <Course> courses;
    private List <User> users;
    private Button add;
    String uname = MainActivity.currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        Log.d("<LOG>","log: <IN> AddCourse");
        final RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class,"RoomDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        instructor = findViewById(R.id.ET_instructor);
        title = findViewById(R.id.ET_title);
        description = findViewById(R.id.ET_courseDescription);
        course_id = findViewById(R.id.ET_courseId);
        add = findViewById(R.id.BTN_addCourse);
        courses = db.dao().getAllCourses();




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course new_course = new Course(instructor.getText().toString(), title.getText().toString(),
                        description.getText().toString(), Integer.parseInt(course_id.getText().toString()),
                        uname);
                if ( validate(new_course)) {
                    db.dao().addCourse(new_course);
                    //xyz = db.dao().userCourses(uname);
                    Toast.makeText(AddCourse.this, "Course was succesfully added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCourse.this, CoursePage.class);
                    startActivity(intent);
                }

            }
        });
        Log.d("Log","log: "+uname);
        for (Course c: courses ) {
            Log.d(" <All Courses> ", "log Primary Key: "+c.getPrimaryKey()+" || " +c.getTitle()+"" +
                    " || " +c.getInstructor()+" || " +c.getDescription()+" || " +c.getCourseID());
        }

        if(courses == null){
            Log.d("Course <null> Tag", "Courses are null <INITIALIZE NECESSARY>");
        }


    }//onCreate()

    //This will validate if the users input is valid.
    private Boolean validate(Course c){
        //Check to see if all fields were inputted
        if( c.getInstructor().isEmpty() || c.getDescription().isEmpty() || c.getTitle().isEmpty() ){
            Toast.makeText(AddCourse.this, "Please Fill in all fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}//class
