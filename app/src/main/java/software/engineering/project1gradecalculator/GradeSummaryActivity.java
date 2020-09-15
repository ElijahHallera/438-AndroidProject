package software.engineering.project1gradecalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import software.engineering.project1gradecalculator.model.Assignment;
import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.RoomDB;
import software.engineering.project1gradecalculator.model.User;

public class GradeSummaryActivity extends AppCompatActivity {
    //the course the user selected from home page
//        Course currentCourse =

    //get all assignments with matching primary key of current course
    List<Assignment> courseAssignments = new ArrayList<>();
    List<Course> test = new ArrayList<>();
//        courseAssignments = RoomDB.getRoomDB(this).dao().getAllAssignmentsByCourse(currentCourse.getPrimaryKey);
    //get all grade categories with matching primary key of current course
//        List<Category> courseCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_summary);
        
        RecyclerView rvAssignment = findViewById(R.id.assignment_recycler_view);
        rvAssignment.setLayoutManager( new LinearLayoutManager(this));
        rvAssignment.setAdapter( new Adapter1() );

        RecyclerView rvSummary = findViewById(R.id.summary_recycler_view);
        rvSummary.setLayoutManager( new LinearLayoutManager(this));
        rvSummary.setAdapter( new Adapter2() );

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
            holder.bind(test.get(position));
        }

        @Override
        public int getItemCount() { return test.size(); }
    }

    //summary item holder
    private class ItemHolder2 extends RecyclerView.ViewHolder {

        public ItemHolder2(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(Course c) {
            TextView item = itemView.findViewById(R.id.item_id);
            item.setText(c.toString());
        }
    }

}
