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

import java.util.List;

import software.engineering.project1gradecalculator.model.RoomDB;
import software.engineering.project1gradecalculator.model.User;

public class CreateAccountActivity extends AppCompatActivity {


    private EditText firstName;
    private EditText lastName;
    private EditText Username;
    private EditText Password;
    private Button Create_New_Account;

    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        final RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class,"RoomDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        firstName = findViewById(R.id.new_FirstName);
        lastName = findViewById(R.id.new_LastName);
        Username = findViewById(R.id.new_username);
        Password = findViewById(R.id.new_password);
        Create_New_Account = findViewById((R.id.new_account_create_button));

        users = db.dao().getAllUsers();
        //Just logging some stuff for safety checks
        if(users == null){
            Log.d("AYE THIS EMPTY", "YAH YEET");
        } else {
            for(int i = 0; i < users.size(); i++){
                Log.d("AAAAAAAAAAAAAAAAAAAAAA", users.get(i).getUsername());
            }
        }

        Create_New_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User new_user = new User();
                new_user.setFirstName(firstName.getText().toString());
                new_user.setLastName(lastName.getText().toString());
                new_user.setPassword(Password.getText().toString());
                new_user.setUsername(Username.getText().toString());
                if(validate(new_user)) {
                    db.dao().addUser(new_user);
                    Toast.makeText(CreateAccountActivity.this, "Account Registered!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    //This will validate if the users input is valid.
    private Boolean validate(User new_user){
        //Checks if any of the Input Fields are Empty.
        if(new_user.getFirstName().isEmpty() || new_user.getLastName().isEmpty() ||
                new_user.getUsername().isEmpty() || new_user.getPassword().isEmpty()){
            Toast.makeText(CreateAccountActivity.this, "Please Fill in all fields!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            //Check if the username already exists.
            for(int i = 0; i < users.size(); i++){
                if(new_user.getUsername().equals(users.get(i).getUsername())){
                    Username.setError("Username is already taken!");
                    return false;
                }
            }
        }
        return true;
    }
}
