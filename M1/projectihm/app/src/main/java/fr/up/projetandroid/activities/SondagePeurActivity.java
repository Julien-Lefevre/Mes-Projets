package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

public class SondagePeurActivity extends AppCompatActivity {

    public static String TAG = "Quizz IA - Sondage peur activity";

    private ProgressBar progressBar;
    private TextView pourcentage;

    private TextView question1, question2;
    private Spinner reponsesQ1;
    private CheckBox progres, danger, incontrolable,
            fiabilite, erreur, pratique,
            intelligence, surveillance;

    private SessionData sessionData;
    private DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sondage_peur);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sessionData = getIntent().getParcelableExtra("session_data");
        dbOperations = DbOperations.getInstance(this);

        progressBar = findViewById(R.id.peurActivityProgressBar);
        progressBar.setProgress(sessionData.getProgression());

        pourcentage = findViewById(R.id.peurActivityTextViewPourcentage);
        pourcentage.setText(sessionData.getProgression() + " %");

        question1 = findViewById(R.id.peurActivityTextViewQuestionControlleIA);
        question2 = findViewById(R.id.peurActivityTextViewQuestionMots);

        reponsesQ1 = findViewById(R.id.peurActivitySpinnerReponses);
        progres = findViewById(R.id.peurActivityCheckBoxProgres);
        danger = findViewById(R.id.peurActivityCheckBoxDanger);
        incontrolable = findViewById(R.id.peurActivityCheckBoxIncontrolable);
        fiabilite = findViewById(R.id.peurActivityCheckBoxFiabilite);
        erreur = findViewById(R.id.peurActivityCheckBoxErreur);
        pratique = findViewById(R.id.peurActivityCheckBoxPratique);
        intelligence = findViewById(R.id.peurActivityCheckBoxIntelligence);
        surveillance = findViewById(R.id.peurActivityCheckBoxSurveillance);
    }

    public void next(View v){
        if (reponsesQ1.getSelectedItem().toString() == ""){
            Toast.makeText(this, R.string.expertise1ActivityToastMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        computeResult();
        addDataInBD();
        Log.d(TAG, "Launch the end activity");
        Intent intent = new Intent(this, DestructionIaActivity.class);
        sessionData.increaseProgression();
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void computeResult(){
        String selectedValue = reponsesQ1.getSelectedItem().toString();
        if(selectedValue.equals("Oui")){
            sessionData.increaseScore(ScoreType.PEUR,10);
        } else if (selectedValue.equals("Non")) {
            sessionData.increaseScore(ScoreType.PEUR,1);
        } else {
            sessionData.increaseScore(ScoreType.PEUR, 5);
        }

        if(progres.isChecked())
            sessionData.increaseScore(ScoreType.PEUR, 0);
        if(danger.isChecked())
            sessionData.increaseScore(ScoreType.PEUR, 15);
        if(incontrolable.isChecked())
            sessionData.increaseScore(ScoreType.PEUR, 5);
        if(fiabilite.isChecked())
            sessionData.increaseScore(ScoreType.PEUR, 0);
        if(erreur.isChecked())
            sessionData.increaseScore(ScoreType.PEUR, 10);
        if(pratique.isChecked())
            sessionData.increaseScore(ScoreType.PEUR, 2);
        if(intelligence.isChecked())
            sessionData.increaseScore(ScoreType.PEUR, 1);
        if(surveillance.isChecked())
            sessionData.increaseScore(ScoreType.PEUR, 5);
    }

    private void addDataInBD(){
        Question q1 = new Question();
        q1.setSessionId(sessionData.getSessionId());
        q1.setQuestionText(question1.getText().toString());

        String selectedValue = reponsesQ1.getSelectedItem().toString();

        Answer reponse1 = new Answer();
        reponse1.setAnswer("Oui");
        reponse1.setAnswerValid(true);

        Answer reponse2 = new Answer();
        reponse2.setAnswer("Non");
        reponse2.setAnswerValid(false);

        Answer reponse3 = new Answer();
        reponse3.setAnswer("Je ne sais pas");
        reponse3.setAnswerValid(false);

        if(selectedValue.equals("Oui")){
            reponse1.setUserAnswer(true);
            reponse2.setUserAnswer(false);
            reponse3.setUserAnswer(false);
        } else if (selectedValue.equals("Non")) {
            reponse1.setUserAnswer(false);
            reponse2.setUserAnswer(true);
            reponse3.setUserAnswer(false);
        } else {
            reponse1.setUserAnswer(false);
            reponse2.setUserAnswer(false);
            reponse3.setUserAnswer(true);
        }

        dbOperations.insertQuestionAndAnswers(q1,reponse1, reponse2, reponse3);

        Question q2 = new Question();
        q2.setSessionId(sessionData.getSessionId());
        q2.setQuestionText(question2.getText().toString());


        Answer reponseProgres = new Answer();
        reponseProgres.setAnswer(progres.getText().toString());
        reponseProgres.setAnswerValid(true);
        reponseProgres.setUserAnswer(progres.isChecked());

        Answer reponseDanger= new Answer();
        reponseDanger.setAnswer(danger.getText().toString());
        reponseDanger.setAnswerValid(false);
        reponseDanger.setUserAnswer(danger.isChecked());

        Answer reponseIncontrolable = new Answer();
        reponseIncontrolable.setAnswer(incontrolable.getText().toString());
        reponseIncontrolable.setAnswerValid(false);
        reponseIncontrolable.setUserAnswer(incontrolable.isChecked());

        Answer reponseFiablilite = new Answer();
        reponseFiablilite.setAnswer(fiabilite.getText().toString());
        reponseFiablilite.setAnswerValid(true);
        reponseFiablilite.setUserAnswer(fiabilite.isChecked());

        Answer reponseErreur = new Answer();
        reponseErreur.setAnswer(erreur.getText().toString());
        reponseErreur.setAnswerValid(false);
        reponseErreur.setUserAnswer(erreur.isChecked());

        Answer reponsePratique = new Answer();
        reponsePratique.setAnswer(pratique.getText().toString());
        reponsePratique.setAnswerValid(false);
        reponsePratique.setUserAnswer(pratique.isChecked());

        Answer reponseIntelligence = new Answer();
        reponseIntelligence.setAnswer(intelligence.getText().toString());
        reponseIntelligence.setAnswerValid(true);
        reponseIntelligence.setUserAnswer(intelligence.isChecked());

        Answer reponseSurveillance = new Answer();
        reponseSurveillance.setAnswer(surveillance.getText().toString());
        reponseSurveillance.setAnswerValid(false);
        reponseSurveillance.setUserAnswer(surveillance.isChecked());

        dbOperations.insertQuestionAndAnswers(q2,reponseProgres, reponseDanger,
                reponseIncontrolable, reponseFiablilite,
                reponseErreur, reponsePratique, reponseIntelligence, reponseSurveillance);
    }
}