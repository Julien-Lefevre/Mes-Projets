package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.up.projetandroid.Parcelable.ScoreType;
import fr.up.projetandroid.Parcelable.SessionData;
import fr.up.projetandroid.R;
import fr.up.projetandroid.entities.Answer;
import fr.up.projetandroid.entities.Question;
import fr.up.projetandroid.utils.DbOperations;

public class SondageActivity extends AppCompatActivity {

    public static String TAG = "Quizz IA - Sondage Activity";

    private Button buttonRetour;
    private ProgressBar progressBar;
    private TextView pourcentage;
    private RatingBar ratingBar;
    private SeekBar seekBar;
    private TextView questionAvisIA;
    private TextView questionUtilisationIA;
    private DbOperations dbOperations;

    private SessionData sessionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sondage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionData = getIntent().getParcelableExtra("session_data");
        Log.d(TAG, "Session pseudo : " + sessionData.getPseudo() + " | score differenciation : " + sessionData.getScoreDifferenciation());
        dbOperations = DbOperations.getInstance(this);

        progressBar = findViewById(R.id.sondageActivityProgressBar);
        progressBar.setProgress(sessionData.getProgression());

        ratingBar = findViewById(R.id.sondageActivityRatingBar);
        seekBar = findViewById(R.id.sondageActivitySeekBar);
        questionAvisIA = findViewById(R.id.sondageActivityTextViewAvisIA);
        questionUtilisationIA = findViewById(R.id.sondageActivityTextViewUtilisationIa);

        pourcentage = findViewById(R.id.sondageActivityTextViewPourcentage);
        pourcentage.setText(sessionData.getProgression() + " %");
    }


    public void next(View v) {
        computeResult();
        addDataInBD();
        Log.d(TAG, "Launch the third activity");
        Intent intent = new Intent(this, Expertise3Activity.class);
        sessionData.increaseProgression();
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void computeResult(){
        float score = ratingBar.getRating();
        if(score >= 2.5f){
            Log.d(TAG, "Bonne réponse, score SURVIE += " + (int) (score * 2));
            sessionData.increaseScore(ScoreType.SURVIE, (int) (score * 2));
        } else {
            Log.d(TAG, "Mauvaise réponse, score SURVIE -= " + (int) (score * 2));
            sessionData.decreaseScore(ScoreType.SURVIE, (int) (score * 2));
        }

        int seekBarValue = seekBar.getProgress();
        if(seekBarValue > 5){
            Log.d(TAG, "Bonne réponse, score EXPERTISE += " + seekBarValue);
            sessionData.increaseScore(ScoreType.EXPERTISE, seekBarValue);
        } else {
            Log.d(TAG, "Mauvaise réponse, score EXPERTISE -= " + seekBarValue);
            sessionData.decreaseScore(ScoreType.EXPERTISE, seekBarValue);
        }
    }

    private void addDataInBD(){
        Question q = new Question();
        q.setRange(true);
        q.setSessionId(sessionData.getSessionId());
        q.setQuestionText(questionAvisIA.getText().toString());
        float score = ratingBar.getRating();

        Answer reponse1 = new Answer();
        reponse1.setAnswer(score + "/5");
        reponse1.setUserAnswer(true);
        if(score >= 2.5f)
            reponse1.setAnswerValid(true);
        else
            reponse1.setAnswerValid(false);


        Answer reponse2 = new Answer();
        reponse2.setAnswer("de " + ratingBar.getMin() + " à " + ratingBar.getMax());
        reponse2.setAnswerValid(false);
        reponse2.setUserAnswer(false);

        dbOperations.insertQuestionAndAnswers(q,reponse1, reponse2);

        Question q2 = new Question();
        q2.setRange(true);
        q2.setSessionId(sessionData.getSessionId());
        q2.setQuestionText(questionUtilisationIA.getText().toString());
        int seekBarValue = seekBar.getProgress();
        Answer q2Reponse1 = new Answer();
        q2Reponse1.setAnswer(seekBarValue + "/10");
        q2Reponse1.setUserAnswer(true);
        if(seekBarValue >= 5)
            q2Reponse1.setAnswerValid(true);
        else
            q2Reponse1.setAnswerValid(false);


        Answer q2Reponse2 = new Answer();
        q2Reponse2.setAnswer("de " + seekBar.getMin() + " à " + seekBar.getMax());
        q2Reponse2.setAnswerValid(false);
        q2Reponse2.setUserAnswer(false);

        dbOperations.insertQuestionAndAnswers(q2,q2Reponse1, q2Reponse2);

    }
}