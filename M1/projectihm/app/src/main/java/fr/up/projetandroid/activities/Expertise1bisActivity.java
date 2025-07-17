package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

public class Expertise1bisActivity extends AppCompatActivity {
    public static String TAG = "Quizz IA - Expertise 1 Activity";

    private ProgressBar progressBar;
    private TextView textViewProgression;
    private RadioGroup radioGroup;

    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Button validateButton;
    private TextView question;
    private SessionData sessionData;
    private DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expertise1bis);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sessionData = getIntent().getParcelableExtra("session_data");
        dbOperations = DbOperations.getInstance(this);

        progressBar = findViewById(R.id.expertise1ActivityProgressBar);
        progressBar.setProgress(sessionData.getProgression());

        textViewProgression = findViewById(R.id.expertiseActivityTextViewPourcentage);
        textViewProgression.setText(sessionData.getProgression() + " %");

        question = findViewById(R.id.expertise1ActivityQuestion1TextView);

        radioGroup = findViewById(R.id.expertise1ActivityRadioGroup);
        validateButton = findViewById(R.id.expertise1ActivityButtonValider);
        //seekbar = findViewById(R.id.expertise1ActivitySeekBar);
        radioButton1 = findViewById(R.id.expertise1ActivityRadioButton1);
        radioButton2 = findViewById(R.id.expertise1ActivityRadioButton2);
        radioButton3 = findViewById(R.id.expertise1ActivityRadioButton3);
        radioButton4 = findViewById(R.id.expertise1ActivityRadioButton4);

        validateButton.setOnClickListener(v -> {
            // Récupérer l'ID du bouton sélectionné
            int selectedId = radioGroup.getCheckedRadioButtonId();

            // Vérifier si une option est sélectionnée
            if (selectedId == -1) {
                Toast.makeText(this, R.string.expertise1ActivityToastMessage, Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d(TAG, "EXPERTISE : " + sessionData.getScoreExpertise());

            // Récupérer l'instance du RadioButton sélectionné
            RadioButton selectedRadioButton = findViewById(selectedId);

            // Comparer avec la bonne réponse (instance)
            if (selectedRadioButton == radioButton1) {
                sessionData.increaseScore(ScoreType.EXPERTISE, 10);
            }
            if (selectedRadioButton == radioButton2) {
                //setup le retour
                sessionData.increaseScore(ScoreType.EXPERTISE, 5);
            }
            if (selectedRadioButton == radioButton3) {
                //setup le retour
                sessionData.increaseScore(ScoreType.EXPERTISE, 2);
            }
            if (selectedRadioButton == radioButton4) {
                //setup le retour
                sessionData.increaseScore(ScoreType.EXPERTISE, 0);
            }

            Log.d(TAG, "EXPERTISE : " + sessionData.getScoreExpertise());


            //int seekRes = seekbar.getProgress();
            //sessionData.increaseScore(ScoreType.EXPERTISE, seekRes%10);

            addDataInBD();

            next();

        });

    }

    public void next(){
        Log.d(TAG, "Launch the Expertise 2 activity");
        Intent intent = new Intent(this, SondagePeurActivity.class);
        sessionData.increaseProgression();
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void addDataInBD(){
        Question q = new Question();
        q.setSessionId(sessionData.getSessionId());
        q.setQuestionText(question.getText().toString());

        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);

        Answer reponse1 = new Answer();
        reponse1.setAnswer(radioButton1.getText().toString());
        reponse1.setAnswerValid(true);
        reponse1.setUserAnswer(selectedRadioButton == radioButton1);

        Answer reponse2 = new Answer();
        reponse2.setAnswer(radioButton2.getText().toString());
        reponse2.setAnswerValid(true);
        reponse2.setUserAnswer(selectedRadioButton == radioButton1);

        Answer reponse3 = new Answer();
        reponse3.setAnswer(radioButton3.getText().toString());
        reponse3.setAnswerValid(true);
        reponse3.setUserAnswer(selectedRadioButton == radioButton1);

        Answer reponse4 = new Answer();
        reponse4.setAnswer(radioButton4.getText().toString());
        reponse4.setAnswerValid(true);
        reponse4.setUserAnswer(selectedRadioButton == radioButton1);

        dbOperations.insertQuestionAndAnswers(q,reponse1, reponse2, reponse3, reponse4);
    }
}
