package software.engineering.project1gradecalculator;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import software.engineering.project1gradecalculator.model.RoomDB;
import software.engineering.project1gradecalculator.model.User;

public class MainActivity extends AppCompatActivity {

    private List<User> users;

    private EditText Username;
    private EditText Password;
    private Button Login;
    private Button CreateAccount;

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RoomDB db = Room.databaseBuilder(getApplicationContext(), RoomDB.class,"RoomDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        //Checks if the database is empty and if so populate it with predefined accounts.
        users = db.dao().getAllUsers();
        if(users.size() <= 0) {
            User alice = new User("Alice", "Blue", "A@lice5", "@cSit100");
            User brian = new User("Brian", "Green", "$BriAn7","123aBc##");
            User chris = new User("Chris", "Yellow", "!chriS12!", "CHrIS12!!");
            db.dao().addUser(alice);
            db.dao().addUser(brian);
            db.dao().addUser(chris);
        }
        //Double check to make sure the list has all users.
        users = db.dao().getAllUsers();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Username = findViewById(R.id.etUsername);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.Login_Button);
        CreateAccount = findViewById(R.id.CreateAccount_Button);

        //This button verifies the users credentials before logging in.
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = Username.getText().toString();
                String inputPassword = Password.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Error Check input correctly!", Toast.LENGTH_SHORT).show();
                } else {
                    isValid = validate(inputName, inputPassword);
                    if(!isValid){
                    } else {
                        Toast.makeText(MainActivity.this, "Successful Login!", Toast.LENGTH_SHORT).show();

                        //Add code to new activity(new page)
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
//                        intent.putExtra("USERNAME", inputName);
                        startActivity(intent);
                    }
                }
            }
        });

        //Directs user to create account page
        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validate(String name, String password){
        for(int i = 0; i < users.size(); i++){
            if(name.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())){
                return true;
            } else if (name.equals(users.get(i).getUsername()) && !password.equals(users.get(i).getPassword())){
                Password.setError("Incorrect Password");
                return false;
            } else if (!!name.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())){
                Username.setError("Username does not Exist");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
