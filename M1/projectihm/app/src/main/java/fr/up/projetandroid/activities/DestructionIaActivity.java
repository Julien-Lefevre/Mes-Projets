package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Switch;
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

public class DestructionIaActivity extends AppCompatActivity {

    public static String TAG = "Quizz IA - Destruction IA Activity";

    private ProgressBar progressBar;
    private TextView textViewProgression;
    private Button validateButton;
    private SessionData sessionData;
    private DbOperations dbOperations;
    private RadioGroup radiogroup;

    //private RadioButton radioButtonYesAnswer;
    //private RadioButton radioButtonNoAnswer;
    private Switch switchQuestion2;
    private TextView iaWillLive;
    private TextView question1;
    private TextView question2;
    private TextView q2answer1;
    private TextView q2answer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_destruction_ia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sessionData = getIntent().getParcelableExtra("session_data");
        dbOperations = DbOperations.getInstance(this);

        progressBar = findViewById(R.id.destructionIaActivityProgressBar);
        progressBar.setProgress(sessionData.getProgression());

        textViewProgression = findViewById(R.id.destructionIaActivityTextViewProgressPourcentage);
        textViewProgression.setText(sessionData.getProgression() + " %");

        validateButton = findViewById(R.id.destructionIaActivityButtonValidate);

        //radioButtonYesAnswer = findViewById(R.id.destructionIaActivityRadioButtonYes);
        //radioButtonNoAnswer = findViewById(R.id.destructionIaActivityRadioButtonNo);
        radiogroup = findViewById(R.id.destructioniaActivityRadioGroupQuestion1);

        switchQuestion2 = findViewById(R.id.destructionIaActivitySwitchQuestion2);
        iaWillLive = findViewById(R.id.destructionIaActivityTextViewCaseNo);
        question1 = findViewById(R.id.destructionIaActivityTextViewQuestion1);
        question2 = findViewById(R.id.destructionIaActivityTextViewCaseYes);
        q2answer1 = findViewById(R.id.destructionIaActivityTextView1);
        q2answer2 = findViewById(R.id.destructionIaActivityTextView2);


        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Vérifier quel RadioButton est coché
                if (checkedId == R.id.destructionIaActivityRadioButtonYes) {
                    // Action si le RadioButton "Oui" est coché
                    //handleRadioButtonYesChecked();
                    iaWillLive.setVisibility(TextView.GONE);
                    question2.setVisibility(TextView.VISIBLE);
                    switchQuestion2.setVisibility(Switch.VISIBLE);
                    q2answer1.setVisibility(TextView.VISIBLE);
                    q2answer2.setVisibility(TextView.VISIBLE);
                } else if (checkedId == R.id.destructionIaActivityRadioButtonNo) {
                    // Action si le RadioButton "Non" est coché
                    //handleRadioButtonNoChecked();
                    iaWillLive.setVisibility(TextView.VISIBLE);
                    question2.setVisibility(TextView.GONE);
                    switchQuestion2.setVisibility(Switch.GONE);
                    q2answer1.setVisibility(TextView.GONE);
                    q2answer2.setVisibility(TextView.GONE);
                }
            }
        });

        switchQuestion2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Action si le Switch est activé (ON)
                    Toast.makeText(DestructionIaActivity.this, R.string.destructionIaSwitchYes, Toast.LENGTH_SHORT).show();
                } else {
                    // Action si le Switch est désactivé (OFF)
                    //handleSwitchOff();
                    Toast.makeText(DestructionIaActivity.this,sessionData.getPseudo() + R.string.destructionIaSwitchNo, Toast.LENGTH_SHORT).show();
                }
            }
        });


        validateButton.setOnClickListener(v -> {
            // Récupérer l'ID du bouton sélectionné
            int selectedId = radiogroup.getCheckedRadioButtonId();

            // Vérifier si une option est sélectionnée
            if (selectedId == -1) {
                Toast.makeText(this, R.string.expertise1ActivityToastMessage, Toast.LENGTH_SHORT).show();
                return;
            }

            // Récupérer l'instance du RadioButton sélectionné
            //RadioButton selectedRadioButton = findViewById(selectedId);

            // Comparer avec la bonne réponse (instance)
            if (selectedId == R.id.destructionIaActivityRadioButtonYes) {
                sessionData.increaseScore(ScoreType.PEUR, 10);
                //sessionData.increaseScore(ScoreType.SURVIE, 10);
                if (switchQuestion2.isChecked()){
                    sessionData.increaseScore(ScoreType.PEUR, 10);
                }
                else {
                    sessionData.increaseScore(ScoreType.PEUR, -5);
                    sessionData.increaseScore(ScoreType.SURVIE, 8);
                }
            }
            if (selectedId == R.id.destructionIaActivityRadioButtonNo) {
                //setup le retour
                sessionData.increaseScore(ScoreType.SURVIE, 10);
            }



            next();

        });



    }

    public void next(){
        Log.d(TAG, "Launch the MainActivity activity");
        Intent intent = new Intent(this, EndActivity.class);
        sessionData.increaseProgression();
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void addDataInBD(){

        // bouton oui non
        // si bouton oui alors switch oui non avec question en etes vous sur
        Question q1 = new Question();
        q1.setSessionId(sessionData.getSessionId());
        q1.setQuestionText(question1.getText().toString());

        Answer reponse1 = new Answer();
        reponse1.setAnswer("Oui");
        reponse1.setAnswerValid(false);

        Answer reponse2 = new Answer();
        reponse2.setAnswer("Non");
        reponse2.setAnswerValid(true);

        int checkedId = radiogroup.getCheckedRadioButtonId();
        boolean nextQuestion = false;

        if (checkedId == R.id.destructionIaActivityRadioButtonYes) {
            reponse1.setUserAnswer(true);
            reponse2.setUserAnswer(false);
            nextQuestion = true;
        } else {
            reponse1.setUserAnswer(false);
            reponse2.setUserAnswer(true);
        }

        dbOperations.insertQuestionAndAnswers(q1,reponse1, reponse2);

        if(nextQuestion){
            Question q2 = new Question();
            q1.setSessionId(sessionData.getSessionId());
            q1.setQuestionText(question2.getText().toString());

            Answer reponse3 = new Answer();
            reponse3.setAnswer(q2answer1.getText().toString());
            reponse3.setAnswerValid(false);

            Answer reponse4 = new Answer();
            reponse4.setAnswer(q2answer2.getText().toString());
            reponse4.setAnswerValid(true);

            if (switchQuestion2.isChecked()) {
                reponse3.setUserAnswer(true);
                reponse4.setUserAnswer(false);
            } else {
                reponse3.setUserAnswer(false);
                reponse4.setUserAnswer(true);
            }

            dbOperations.insertQuestionAndAnswers(q2,reponse3, reponse4);
        }
    }

    public void toast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }
}