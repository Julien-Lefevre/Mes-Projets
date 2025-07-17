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

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import fr.up.projetandroid.Parcelable.ScoreType;
import fr.up.projetandroid.Parcelable.SessionData;
import fr.up.projetandroid.R;
import fr.up.projetandroid.entities.Answer;
import fr.up.projetandroid.entities.Question;
import fr.up.projetandroid.utils.DbOperations;

public class Expertise2Activity extends AppCompatActivity {

    public static String TAG = "Quizz IA - Expertise 2 Activity";

    private ProgressBar progressBar;
    private TextView textViewProgression;

    private ChipGroup chipGroupAnswers;
    private TextView question1;
    private TextView question2;
    private Button validateButton;
    private SeekBar seekbar;
    private TextView seekBarProgress;
    private SessionData sessionData;
    private DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expertise2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionData = getIntent().getParcelableExtra("session_data");
        dbOperations = DbOperations.getInstance(this);

        progressBar = findViewById(R.id.expertise2ActivityProgressBar);
        progressBar.setProgress(sessionData.getProgression());

        textViewProgression = findViewById(R.id.expertise2ActivityProgressBarPourcentage);
        textViewProgression.setText(sessionData.getProgression() + " %");

        chipGroupAnswers = findViewById(R.id.expertise2ActivityChipGroup);
        validateButton = findViewById(R.id.expertise2ActivityButtonValider);
        question1 = findViewById(R.id.expertise2ActivityTextViewQuestion1);
        question2 = findViewById(R.id.expertise2ActivityTextViewQuestion2);

        seekbar = findViewById(R.id.expertise2ActivityseekBarQuestion1);
        seekBarProgress = findViewById(R.id.expertise2ActivitySeekBarQuestion1Pourcentage);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarProgress.setText(String.valueOf(progress) + "%"); // Convertit int en String
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optionnel : Exécuter du code au début du changement
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optionnel : Exécuter du code à la fin du changement
            }
        });

        validateButton.setOnClickListener(v -> {
            // Récupérer l'ID du bouton sélectionné
            StringBuilder selectedChips = new StringBuilder("Selected: ");

            for (int i = 0; i < chipGroupAnswers.getChildCount(); i++) {
                View child = chipGroupAnswers.getChildAt(i);
                if (child instanceof Chip) {
                    Chip chip = (Chip) child;
                    if (chip.isChecked()) {  // Vérifie si le chip est sélectionné
                        sessionData.increaseScore(ScoreType.EXPERTISE, 5);
                    }
                }
            }

            sessionData.increaseScore(ScoreType.EXPERTISE, seekbar.getProgress()%10);

            addDataInBD();

            next();

        });
    }

    public void next(){
        Log.d(TAG, "Launch the Expertise 3 activity");
        Intent intent = new Intent(this, Expertise1bisActivity.class);
        sessionData.increaseProgression();
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void addDataInBD(){
        Question q1 = new Question();
        q1.setSessionId(sessionData.getSessionId());
        q1.setQuestionText(question1.getText().toString());
        q1.setRange(true);

        Answer reponse1 = new Answer();
        reponse1.setAnswer("de " + seekbar.getMin() + " à " + seekbar.getMax());
        reponse1.setAnswerValid(true);
        reponse1.setUserAnswer(false);

        Answer reponse2 = new Answer();
        reponse2.setAnswer(String.valueOf(seekbar.getProgress()));
        reponse2.setAnswerValid(true);
        reponse2.setUserAnswer(true);

        dbOperations.insertQuestionAndAnswers(q1,reponse1, reponse2);

        Question q2 = new Question();
        q2.setSessionId(sessionData.getSessionId());
        q2.setQuestionText(question2.getText().toString());

        int chipCount = chipGroupAnswers.getChildCount();
        List<Answer> answerList = new ArrayList<>();
        for(int i = 0; i < chipCount; ++i){
            View child = chipGroupAnswers.getChildAt(i);
            if (child instanceof Chip) {
                Chip chip = (Chip) child;
                Answer reponse = new Answer();
                reponse.setAnswer(chip.getText().toString());
                reponse.setAnswerValid(true);
                if (chip.isChecked()) {  // Vérifie si le chip est sélectionné
                    reponse.setUserAnswer(true);
                }
                answerList.add(reponse);
            }
        }

        dbOperations.insertQuestionAndAnswers(q2, answerList.toArray(new Answer[0]));
    }
}