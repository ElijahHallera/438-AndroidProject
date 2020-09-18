package software.engineering.project1gradecalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;
import java.util.logging.LogRecord;

import software.engineering.project1gradecalculator.model.Assignment;
import software.engineering.project1gradecalculator.model.Category;
import software.engineering.project1gradecalculator.model.Course;
import software.engineering.project1gradecalculator.model.RoomDB;

public class EditOrDeleteAssignmentActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_edit_or_delete_assignment);

        RecyclerView rvCategory = findViewById(R.id.edit_select_category_recycler_view);
        rvCategory.setLayoutManager( new LinearLayoutManager(this));
        adapter1 = new Adapter1();
        rvCategory.setAdapter( adapter1 );

        final EditText assignmentName = findViewById(R.id.edit_assignment_name);
        final EditText maxScore = findViewById(R.id.edit_max_score);
        final EditText earnedScore = findViewById(R.id.edit_earned_score);

        //put old info into input fields
        assignmentName.setText(GradeSummaryActivity.selectedAssignment.getAssignmentName());
        maxScore.setText(GradeSummaryActivity.selectedAssignment.getMaxScore() + "");
        earnedScore.setText(GradeSummaryActivity.selectedAssignment.getEarnedScore() + "");

        Button edit_button = findViewById(R.id.edit_assignment);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredAssignmentName = assignmentName.getText().toString();
                enteredMaxScore = Double.parseDouble(maxScore.getText().toString());
                enteredEarnedScore = Double.parseDouble(earnedScore.getText().toString());

                //retrieve user assignment selection
                Assignment editedAssignment = GradeSummaryActivity.selectedAssignment;
                editedAssignment.setAssignmentName(enteredAssignmentName);
                editedAssignment.setMaxScore(enteredMaxScore);
                editedAssignment.setEarnedScore(enteredEarnedScore);
                editedAssignment.setCategoryID(selectedCategory.getCategoryID());
                editedAssignment.setCategoryName(selectedCategory.getName());

                //update db object
                RoomDB.getRoomDB(EditOrDeleteAssignmentActivity.this).dao().updateAssignment(editedAssignment);
                Intent intent = new Intent(EditOrDeleteAssignmentActivity.this, GradeSummaryActivity.class);
                startActivity(intent);
            }
        });
        Button delete_button = findViewById(R.id.delete_assignment);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                //delete db object
                                Assignment editedAssignment = GradeSummaryActivity.selectedAssignment;
                                RoomDB.getRoomDB(EditOrDeleteAssignmentActivity.this).dao().deleteAssignment(editedAssignment);
                                Intent intent = new Intent(EditOrDeleteAssignmentActivity.this, GradeSummaryActivity.class);
                                startActivity(intent);
                                finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                final AlertDialog.Builder builder1 = new AlertDialog.Builder(EditOrDeleteAssignmentActivity.this);
                                builder1.setTitle("Cancellation Aborted");
                                builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
//                                        finish();
                                        dialog.dismiss();
                                    }
                                });

                                AlertDialog dialog2 = builder1.create();
                                dialog2.show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(EditOrDeleteAssignmentActivity.this);
                builder.setMessage("Delete Assignment?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }

    //Category recycler adapter
    private class Adapter1  extends RecyclerView.Adapter<EditOrDeleteAssignmentActivity.ItemHolder1> {
        @Override
        public EditOrDeleteAssignmentActivity.ItemHolder1 onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(EditOrDeleteAssignmentActivity.this);
            return new ItemHolder1(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(EditOrDeleteAssignmentActivity.ItemHolder1 holder, int position){
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
