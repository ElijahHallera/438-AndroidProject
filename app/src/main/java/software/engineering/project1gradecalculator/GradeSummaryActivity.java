package software.engineering.project1gradecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import software.engineering.project1gradecalculator.model.Assignment;
import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.RoomDB;
import software.engineering.project1gradecalculator.model.User;
import software.engineering.project1gradecalculator.model.Category;

public class GradeSummaryActivity extends AppCompatActivity {
    //save assignment user selects
    public static Assignment selectedAssignment;
    //the course the user selected from home page
    Course currentCourse = CoursePage.selectedCourse;
    //get all assignments with matching primary key of current course
    List<Assignment> courseAssignments = RoomDB.getRoomDB(this).dao().getAllAssignmentsByCourse(currentCourse.getPrimaryKey());
    //get all grade categories with matching primary key of current course
    List<Category> courseCategories = RoomDB.getRoomDB(this).dao().courseCategories(currentCourse.getPrimaryKey());
    Category selectedCategory;

    Adapter1 adapter1;
    Adapter2 adapter2;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_summary);

        //holds assignments
        RecyclerView rvAssignment = findViewById(R.id.assignment_recycler_view);
        rvAssignment.setLayoutManager( new LinearLayoutManager(this));
        adapter1 = new Adapter1();
        rvAssignment.setAdapter( adapter1 );

        //holds categories
        RecyclerView rvSummary = findViewById(R.id.summary_recycler_view);
        rvSummary.setLayoutManager( new LinearLayoutManager(this));
        adapter2 = new Adapter2();
        rvSummary.setAdapter( adapter2 );

        TextView msg = findViewById(R.id.course_name_text_view);
        msg.setText(currentCourse.getTitle());

        //add assignment button
        Button add_button = findViewById(R.id.add_assignment_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeSummaryActivity.this, AddAssignmentActivity.class);
                startActivity(intent);
            }
        });

        //back button
        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeSummaryActivity.this, CoursePage.class);
                startActivity(intent);
            }
        });

        //display course score in button
        Button score_button = findViewById(R.id.course_score_button);
        score_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseAssignments = RoomDB.getRoomDB(GradeSummaryActivity.this).dao().getAllAssignmentsByCourse(currentCourse.getPrimaryKey());
                // notify recycler view that list of assignments has changed
                adapter1.notifyDataSetChanged();
            }
        });

        //calculate grades
        double earnedSum = 0.0;
        double maxSum = 0.0;
        double result = 0.0;
        for(int i=0; i<courseCategories.size() ;i++){
            List<Assignment> temp = RoomDB.getRoomDB(GradeSummaryActivity.this).dao().getAllAssignmentsByCategory(courseCategories.get(i).getCategoryID());
            for(int k=0; k<temp.size(); k++){
                earnedSum += temp.get(k).getEarnedScore();
                maxSum += temp.get(k).getMaxScore();
            }
            result += (earnedSum/maxSum) * courseCategories.get(i).getWeight();
            courseCategories.get(i).setScore((Double.parseDouble(df2.format((earnedSum/maxSum) * courseCategories.get(i).getWeight()))));
        }

        currentCourse.setGrade(Double.parseDouble(df2.format(result)));

        //Display course score
        score_button.setText("Course Total: "+currentCourse.getGrade()+"/100");
    }

    //assignment recycler adapter
    private class Adapter1  extends RecyclerView.Adapter<ItemHolder1> {

        @Override
        public ItemHolder1 onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(GradeSummaryActivity.this);
            return new  ItemHolder1(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder1 holder, int position){
            holder.bind(courseAssignments.get(position));
        }

        @Override
        public int getItemCount() { return courseAssignments.size(); }
    }

    //assignment item holder
    private class ItemHolder1 extends RecyclerView.ViewHolder {

        public ItemHolder1(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(Assignment a) {
            TextView item = itemView.findViewById(R.id.item_id);
            item.setText(a.toString());
            //make item clickable
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //save selected assignment
                    selectedAssignment = courseAssignments.get(getAdapterPosition());
                    Intent intent = new Intent(GradeSummaryActivity.this, EditOrDeleteAssignmentActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    //summary recycler adapter
    private class Adapter2  extends RecyclerView.Adapter<ItemHolder2> {

        @Override
        public ItemHolder2 onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(GradeSummaryActivity.this);
            return new  ItemHolder2(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder2 holder, int position){
            holder.bind(courseCategories.get(position));
        }

        @Override
        public int getItemCount() { return courseCategories.size(); }
    }

    //summary item holder
    private class ItemHolder2 extends RecyclerView.ViewHolder {

        public ItemHolder2(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(Category c) {
            TextView item = itemView.findViewById(R.id.item_id);
            item.setText(c.toString());
            //make item clickable
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //save selected Category
                    selectedCategory = courseCategories.get(getAdapterPosition());

                    //show only assignments for that category
                    courseAssignments.clear();
                    courseAssignments = RoomDB.getRoomDB(GradeSummaryActivity.this).dao().getAllAssignmentsByCategory(selectedCategory.getCategoryID());
                    // notify recycler view that list of assignments has changed
                     adapter1.notifyDataSetChanged();
                }
            });
        }
    }

}
