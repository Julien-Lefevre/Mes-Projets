package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import fr.up.projetandroid.Parcelable.SessionData;
import fr.up.projetandroid.R;

public class AiRankingActivity extends AppCompatActivity {
    public static String TAG = "Quizz IA - AiRankingActivity Activity";


    private ProgressBar progressBar;
    private TextView textViewProgression;
    private List<Spinner> answers;
    private ArrayAdapter<String> correctAnswers;

    private SessionData sessionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ai_ranking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionData = getIntent().getParcelableExtra("session_data");

        progressBar = findViewById(R.id.AiRankingActivityprogressBar);
        progressBar.setProgress(sessionData.getProgression());

        textViewProgression = findViewById(R.id.AiRankingActivityViewPourcentage);
        textViewProgression.setText(sessionData.getProgression() + " %");

        answers = new ArrayList<>();
        answers.add(findViewById(R.id.AiRankingActivitySpinnerAnswer1));
        answers.add(findViewById(R.id.AiRankingActivitySpinnerAnswer2));
        answers.add(findViewById(R.id.AiRankingActivitySpinnerAnswer3));
        answers.add(findViewById(R.id.AiRankingActivitySpinnerAnswer4));

        Adapter adapter = answers.get(0).getAdapter();
        ArrayAdapter<String> entries = (ArrayAdapter<String>) adapter;
        correctAnswers = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        correctAnswers.add(entries.getItem(2));
        correctAnswers.add(entries.getItem(1));
        correctAnswers.add(entries.getItem(0));
        correctAnswers.add(entries.getItem(3));
    }

    public boolean isRankingRight() {

        if (correctAnswers.getCount() != answers.size()) {
            return false;
        }
        for (int i = 0; i < correctAnswers.getCount(); i++) {
            if (!correctAnswers.getItem(i).trim().equals(answers.get(i).getSelectedItem().toString().trim())) {
                return false;
            }
        }
        return true;
    }

    public void next(View v){
        Log.d(TAG, "Launch the calendar activity RES :");
        boolean res = isRankingRight();

        Log.d(TAG, String.valueOf(res));
        Intent intent = new Intent(this, SondageActivity.class);
        sessionData.increaseProgression();
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void computeResult(){

    }

    private void addDataInBD(){
    }
}