package software.engineering.project1gradecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import software.engineering.project1gradecalculator.model.Assignment;
import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.RoomDB;
import software.engineering.project1gradecalculator.model.Category;

public class GradeSummaryActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_summary);
        
        RecyclerView rvAssignment = findViewById(R.id.assignment_recycler_view);
        rvAssignment.setLayoutManager( new LinearLayoutManager(this));
        adapter1 = new Adapter1();
        rvAssignment.setAdapter( adapter1 );

        RecyclerView rvSummary = findViewById(R.id.summary_recycler_view);
        rvSummary.setLayoutManager( new LinearLayoutManager(this));
        adapter2 = new Adapter2();
        rvSummary.setAdapter( adapter2 );

        TextView msg = findViewById(R.id.course_name_text_view);
        msg.setText(currentCourse.getTitle());

        Button add_button = findViewById(R.id.add_assignment_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeSummaryActivity.this, AddAssignmentActivity.class);
                startActivity(intent);
            }
        });

        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeSummaryActivity.this, CoursePage.class);
                startActivity(intent);
            }
        });

        // notify recycler view that list of assignments has changed
        // adapter1.notifyDataSetChanged();
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
                }
            });
        }
    }

}
