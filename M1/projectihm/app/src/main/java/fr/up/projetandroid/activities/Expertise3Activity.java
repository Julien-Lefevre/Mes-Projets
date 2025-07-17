package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
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

public class Expertise3Activity extends AppCompatActivity {

    public static String TAG = "Quizz IA - Expertise 3 Activity";

    private ProgressBar progressBar;
    private TextView textViewProgression;
    private Button validateButton;

    //private CheckBox answer1;
    private CheckBox answer2;
    private CheckBox answer3;
    private CheckBox answer4;
    private CheckBox answer5;
    private TextView question;
    private SessionData sessionData;
    private DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expertise3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionData = getIntent().getParcelableExtra("session_data");
        dbOperations = DbOperations.getInstance(this);

        progressBar = findViewById(R.id.expertise3ActivityProgressBar);
        progressBar.setProgress(sessionData.getProgression());

        textViewProgression = findViewById(R.id.expertise3ActivityTextViewPourcentageProgress);
        textViewProgression.setText(sessionData.getProgression() + " %");

        validateButton = findViewById(R.id.expertise3ActivityButtonValider);

        question = findViewById(R.id.expertise3ActivityTextViewTheQuestion);

        //answer1 = findViewById(R.id.expertise3ActivityCheckBox1);
        answer2 = findViewById(R.id.expertise3ActivityCheckBox2);
        answer3 = findViewById(R.id.expertise3ActivityCheckBox3);
        answer4 = findViewById(R.id.expertise3ActivityCheckBox4);
        answer5 = findViewById(R.id.expertise3ActivityCheckBox5);
        Log.d(TAG, "score experstise : " + sessionData.getScoreExpertise());

        validateButton.setOnClickListener(v -> {
            // Récupérer l'ID du bouton sélectionné
            //if (!answer1.isActivated())
            //    sessionData.increaseScore(ScoreType.EXPERTISE, 5);
            if (!answer2.isChecked())
                sessionData.increaseScore(ScoreType.EXPERTISE, 5);
            if (answer3.isChecked())
                sessionData.increaseScore(ScoreType.EXPERTISE, 5);
            if (!answer4.isChecked())
                sessionData.increaseScore(ScoreType.EXPERTISE, 5);
            if (answer5.isChecked())
                sessionData.increaseScore(ScoreType.EXPERTISE, 5);

            Log.d(TAG, "score experstise : " + sessionData.getScoreExpertise());

            addDataInBD();

            next();

        });
    }


    public void next(){
        Log.d(TAG, "Launch the destructionIA activity");
        Intent intent = new Intent(this, Expertise2Activity.class);
        sessionData.increaseProgression();
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void addDataInBD(){
        Question q = new Question();
        q.setSessionId(sessionData.getSessionId());
        q.setQuestionText(question.getText().toString());

        Answer reponse1 = new Answer();
        reponse1.setAnswer(answer2.getText().toString());
        reponse1.setAnswerValid(false);
        if(answer2.isChecked()){
            Log.d(TAG, "Bonne réponse, score différentiation += 1");
            reponse1.setUserAnswer(true);
        } else {
            reponse1.setUserAnswer(false);
        }

        Answer reponse2 = new Answer();
        reponse2.setAnswer(answer3.getText().toString());
        reponse2.setAnswerValid(true);
        if(answer3.isChecked()){
            Log.d(TAG, "Mauvaise réponse, score différentiation -= 1");
            reponse2.setUserAnswer(true);
        } else {
            reponse2.setUserAnswer(false);
        }

        Answer reponse3 = new Answer();
        reponse3.setAnswer(answer4.getText().toString());
        reponse3.setAnswerValid(false);
        if(answer4.isChecked()){
            Log.d(TAG, "Mauvaise réponse, score différentiation -= 1");
            reponse3.setUserAnswer(true);
        } else {
            reponse3.setUserAnswer(false);
        }

        Answer reponse4 = new Answer();
        reponse4.setAnswer(answer5.getText().toString());
        reponse4.setAnswerValid(true);
        if(answer5.isChecked()){
            Log.d(TAG, "Mauvaise réponse, score différentiation -= 1");
            reponse4.setUserAnswer(true);
        } else {
            reponse4.setUserAnswer(false);
        }

        dbOperations.insertQuestionAndAnswers(q,reponse1, reponse2,reponse3, reponse4);
    }
}