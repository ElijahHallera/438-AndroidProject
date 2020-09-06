package software.engineering.project1gradecalculator.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities={User.class}, version=1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    // singleton design
    private static RoomDB instance;

    public abstract AppDao dao();

    public static RoomDB getRoomDB(final Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class,
                    "RoomDB") // database name
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public void loadData(Context context){
        List<User> user_list = RoomDB.getRoomDB(context).dao().getAllUsers();
        if (user_list.size() == 0) {
            Log.d("RoomDB", "loading data ");
            loadUsers(context);
        }
    }

    //populate database with a few accounts
    private void loadUsers(Context context) {
        AppDao dao = getRoomDB(context).dao();

        User alice = new User("Alice", "Blue", "A@lice5", "@cSit100");
        User brian = new User("Brian", "Green", "$BriAn7","123aBc##");
        User chris = new User("Chris", "Yellow", "!chriS12!", "CHrIS12!!");
        dao.addUser(alice);
        dao.addUser(brian);
        dao.addUser(chris);
        Log.d("RoomDB", "3 users added to database");
    }


}