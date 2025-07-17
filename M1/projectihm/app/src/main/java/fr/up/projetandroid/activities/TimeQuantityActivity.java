package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
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

public class TimeQuantityActivity extends AppCompatActivity {
    public static String TAG = "Quizz IA - Quantity Time Activity";

    private Button buttonRetour;
    private ProgressBar progressBar;
    private TextView textViewProgression;
    private TextView pourcentage;

    private SessionData sessionData;

    private List<TextView> choices;
    private SeekBar nbChoice;
    private String finalAnswer;
    private String goodAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_time_quantity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionData = getIntent().getParcelableExtra("session_data");

        progressBar = findViewById(R.id.TimeQuantityActivityprogressBar);
        progressBar.setProgress(sessionData.getProgression());
        textViewProgression = findViewById(R.id.TimeQuantityActivityViewPourcentage);
        textViewProgression.setText(sessionData.getProgression() + " %");

        choices = new ArrayList<>();
        choices.add(findViewById(R.id.TimeQuantityActivityChoice1));
        choices.add(findViewById(R.id.TimeQuantityActivityChoice2));
        choices.add(findViewById(R.id.TimeQuantityActivityChoice3));
        choices.add(findViewById(R.id.TimeQuantityActivityChoice4));
        choices.add(findViewById(R.id.TimeQuantityActivityChoice5));
        choices.add(findViewById(R.id.TimeQuantityActivityChoice6));

        nbChoice = findViewById(R.id.TimeQuantityActivityAnswerChoiceSeekbar);
        goodAnswer = choices.get(5).getText().toString();
    }

    public void next(View v){
        finalAnswer = choices.get(nbChoice.getProgress()).getText().toString();
        Log.d(TAG, "Launch the calendar activity");
        Log.d(TAG, choices.get(nbChoice.getProgress()).getText().toString());
        Intent intent = new Intent(this, AiRankingActivity.class);
        sessionData.increaseProgression();
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void computeResult(){

    }

    private void addDataInBD(){

    }
}