package software.engineering.project1gradecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import software.engineering.project1gradecalculator.model.RoomDB;
import software.engineering.project1gradecalculator.model.User;

public class EditAccount extends AppCompatActivity {

    private Button updateInformation;
    TextView displayFirstName, displayLastName, displayUsername, displayPassword;
    private EditText updatedFirstName, updatedLastName, updatedPassword;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        final RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class,"RoomDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        //Displays the Users current information
        users = db.dao().getAllUsers();
        displayFirstName = (TextView) findViewById(R.id.currentFirstName);
        displayLastName = (TextView) findViewById(R.id.currentLastName);
        displayUsername = (TextView) findViewById(R.id.currentUsername);
        displayPassword = (TextView) findViewById(R.id.currentPassword);
        displayFirstName.setText("Current First Name: " + db.dao().getUserByName(MainActivity.currentUser).getFirstName());
        displayLastName.setText("Current Last Name: " + db.dao().getUserByName(MainActivity.currentUser).getLastName());
        displayUsername.setText("Current Username: " + db.dao().getUserByName(MainActivity.currentUser).getUsername());
        displayPassword.setText("Current Password: " + db.dao().getUserByName(MainActivity.currentUser).getPassword());

        //This takes in user information that they are changing
        updatedFirstName = findViewById(R.id.etNewFirstName);
        updatedLastName = findViewById(R.id.etNewLastName);
        updatedPassword = findViewById(R.id.etNewPassword);

        updateInformation = findViewById(R.id.updateAccount);
        updateInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //If the input texts are empty, prompt displays to enter all fields correctly. (Like a Verification)
                //If there is input, the parts will be changed
                if(updatedFirstName.getText().toString().isEmpty() || updatedLastName.getText().toString().isEmpty() ||
                        updatedPassword.getText().toString().isEmpty()) {
                    Toast.makeText(EditAccount.this, "Please Fill in all fields!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    for(int i = 0; i < users.size(); i++){
                        if(users.get(i).getUsername().equals(db.dao().getUserByName(MainActivity.currentUser).getUsername())){
                            db.dao().updateUser(updatedFirstName.getText().toString(), updatedLastName.getText().toString(), updatedPassword.getText().toString(), MainActivity.currentUser);
                            break;
                        }
                    }
                }

                Toast.makeText(EditAccount.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditAccount.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
    }
}