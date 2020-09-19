package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import software.engineering.project1gradecalculator.model.Assignment;
import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.RoomDB;

public class CoursePage extends AppCompatActivity {

    Adapter adapter;
    public static Course selectedCourse;

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

        RecyclerView rvAssignment = findViewById(R.id.course_recycler_view);
        rvAssignment.setLayoutManager( new LinearLayoutManager(this));
        adapter = new Adapter();
        rvAssignment.setAdapter( adapter );

        courses = RoomDB.getRoomDB(CoursePage.this).dao().getAllCourses();
//        text = findViewById(R.id.TV_CPtextV);
        avd = RoomDB.getRoomDB(CoursePage.this).dao().userCourses(uname);
        String temp = "";

        for (Course c: avd) {
            //Log.d("TAG:", "log: "+c.getTitle()+"-"+c.getInstructor()+"-"+c.getDescription() );
            temp+= "Instructor: "+c.getInstructor()+"\nTitle: "+c.getTitle()+"\nDescription: "+c.getDescription()+"\nCourse ID:"+c.getCourseID()+"\n";
        }

//        text.setText("You are enrolled in these courses: \n"+temp);
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

    //course recycler adapter
    private class Adapter  extends RecyclerView.Adapter<CoursePage.ItemHolder> {

        @Override
        public CoursePage.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(CoursePage.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CoursePage.ItemHolder holder, int position){
            holder.bind(avd.get(position));
        }

        @Override
        public int getItemCount() { return avd.size(); }
    }

    //assignment item holder
    private class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(Course c) {
            TextView item = itemView.findViewById(R.id.item_id);
            item.setText(c.toString());
            //make item clickable
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //save selected course
                    selectedCourse = avd.get(getAdapterPosition());

                    //switch to new activity
                    Intent intent = new Intent(CoursePage.this, GradeSummaryActivity.class);
                    startActivity(intent);
                    finish();
            }
            });
        }
    }


}//class