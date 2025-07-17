package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import fr.up.projetandroid.Parcelable.SessionData;
import fr.up.projetandroid.R;
import fr.up.projetandroid.dao.SessionDAO;
import fr.up.projetandroid.database.AppDatabase;
import fr.up.projetandroid.entities.Session;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static String TAG = "Quizz IA - Page Acceuil";

    private Button bouttonCommencer;
    private EditText textEditPseudo;
    private TextView textViewErrorPseudo;
    private FloatingActionButton historyButton;
    private AppDatabase db;
    private SessionDAO sessionDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = AppDatabase.getInstance(this);
        sessionDAO = db.sessionDAO();
        bouttonCommencer = findViewById(R.id.mainActivityButtonCommencer);
        textEditPseudo = findViewById(R.id.editTextMainactivityPseudo);
        textViewErrorPseudo = findViewById(R.id.mainActivityTextViewErrorPseudo);
        historyButton = findViewById(R.id.mainActivityFloatingActionButtonHistory);

        textEditPseudo.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });
    }

    public void loadHistory(View v){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void next(View v){
        Intent intent = new Intent(this, ImageActivity.class);
        String pseudo = textEditPseudo.getText().toString();
        if(!pseudo.equals("")) {
            Log.d(TAG, "Launch the first question");
            Session session = new Session();
            session.setPseudo(pseudo);
            session.setDate(new Date());
            Single<Long> s = sessionDAO.insertAsync(session).subscribeOn(Schedulers.io());
            Disposable d = s.subscribeOn(Schedulers.io())
                    .subscribe(
                            (sessionId) -> {
                                Log.d(TAG, "Insertion ok en bd");
                                SessionData sessionData = new SessionData(pseudo, sessionId.intValue());
                                sessionData.increaseProgression();
                                intent.putExtra("session_data", sessionData);
                                startActivity(intent);
                            }, throwable -> {
                                Log.e(TAG, throwable.getMessage());
                            });
        } else {
            Log.d(TAG, "Empty username");
            textViewErrorPseudo.setText(R.string.error_pseudo);
        }
    }
}