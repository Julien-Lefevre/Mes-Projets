package fr.up.projetandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.up.projetandroid.dao.AnswerDAO;
import fr.up.projetandroid.dao.QuestionDAO;
import fr.up.projetandroid.dao.SessionDAO;
import fr.up.projetandroid.entities.Answer;
import fr.up.projetandroid.entities.Question;
import fr.up.projetandroid.entities.Session;

@Database(entities = {Session.class, Question.class, Answer.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SessionDAO sessionDAO();
    public abstract QuestionDAO questionDAO();
    public abstract AnswerDAO answerDAO();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
