package fr.up.projetandroid.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.up.projetandroid.R;
import fr.up.projetandroid.utils.SessionsRecyclerViewAdapter;
import fr.up.projetandroid.dao.SessionDAO;
import fr.up.projetandroid.database.AppDatabase;
import fr.up.projetandroid.entities.Session;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HistoryActivity extends AppCompatActivity {

    public static String TAG = "Quizz IA - History activity";
    private AppDatabase db;
    private SessionDAO sessionDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = AppDatabase.getInstance(this);
        sessionDAO = db.sessionDAO();

        Single<List<Session>> single;

        single = sessionDAO.getSessionsAsync();
        Disposable disposable = single.subscribeOn(Schedulers.io())
                .subscribe(
                        (sessions) -> {
                            Log.d(TAG, "nb sessions reçues : " + sessions.size());
                            RecyclerView recyclerView = findViewById(R.id.historyActivityRecyclerView);
                            SessionsRecyclerViewAdapter adapter = new SessionsRecyclerViewAdapter(sessions);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(this));
                        },
                        throwable -> {
                            Log.e(TAG, throwable.getStackTrace().toString());
                        }
                );
    }

    public void back(View v) {
        finish();
    }
}