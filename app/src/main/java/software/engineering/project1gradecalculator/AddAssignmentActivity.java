package software.engineering.project1gradecalculator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import software.engineering.project1gradecalculator.model.Assignment;
import software.engineering.project1gradecalculator.model.Category;
import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.RoomDB;

public class AddAssignmentActivity extends AppCompatActivity {
    Adapter1 adapter1;
    Course currentCourse = CoursePage.selectedCourse;
    List<Category> courseCategories = RoomDB.getRoomDB(this).dao().courseCategories(currentCourse.getPrimaryKey());

    //variables to store user input
    Category selectedCategory;
    double enteredMaxScore;
    double enteredEarnedScore;
    String enteredAssignmentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        RecyclerView rvCategory = findViewById(R.id.select_category_recycler_view);
        rvCategory.setLayoutManager( new LinearLayoutManager(this));
        adapter1 = new Adapter1();
        rvCategory.setAdapter( adapter1 );

        Button create_button = findViewById(R.id.create_assignment);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call assignment constructor
                EditText assignmentName = findViewById(R.id.assignment_name);
                EditText maxScore = findViewById(R.id.max_score);
                EditText earnedScore = findViewById(R.id.earned_score);

                enteredAssignmentName = assignmentName.getText().toString();
                enteredMaxScore = Double.parseDouble(maxScore.getText().toString());
                enteredEarnedScore = Double.parseDouble(earnedScore.getText().toString());

                Assignment createdAssignment = new Assignment(
                        currentCourse.getPrimaryKey(),
                        selectedCategory.getCategoryID(),
                        selectedCategory.getName(),
                        enteredMaxScore,
                        enteredEarnedScore,
                        enteredAssignmentName
                );
                //add to db
                RoomDB.getRoomDB(AddAssignmentActivity.this).dao().addAssignment(createdAssignment);
                Intent intent = new Intent(AddAssignmentActivity.this, GradeSummaryActivity.class);
                startActivity(intent);
            }
        });
    }

    //Category recycler adapter
    private class Adapter1  extends RecyclerView.Adapter<AddAssignmentActivity.ItemHolder1> {
        @Override
        public AddAssignmentActivity.ItemHolder1 onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(AddAssignmentActivity.this);
            return new ItemHolder1(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(AddAssignmentActivity.ItemHolder1 holder, int position){
            holder.bind(courseCategories.get(position));
        }

        @Override
        public int getItemCount() { return courseCategories.size(); }
    }

    //Category item holder
    private class ItemHolder1 extends RecyclerView.ViewHolder {

        public ItemHolder1(LayoutInflater inflater, ViewGroup parent) {
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
                }
            });
        }
    }



}
