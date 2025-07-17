package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import fr.up.projetandroid.Parcelable.ScoreType;
import fr.up.projetandroid.Parcelable.SessionData;
import fr.up.projetandroid.R;
import fr.up.projetandroid.dao.AnswerDAO;
import fr.up.projetandroid.dao.QuestionDAO;
import fr.up.projetandroid.database.AppDatabase;
import fr.up.projetandroid.entities.Answer;
import fr.up.projetandroid.entities.Question;
import fr.up.projetandroid.utils.DbOperations;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ImageActivity extends AppCompatActivity {
    public static String TAG = "Quizz IA - Image Activity";

    private ProgressBar progressBar;
    private TextView textViewProgression;
    private ImageView imageView;
    private RadioButton reponseOui;
    private RadioButton reponseNon;
    private TextView question;

    private SessionData sessionData;
    private AppDatabase db;
    private QuestionDAO questionDAO;
    private AnswerDAO answerDAO;
    private DbOperations dbOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = AppDatabase.getInstance(this);
        questionDAO = db.questionDAO();
        answerDAO = db.answerDAO();
        dbOperations = DbOperations.getInstance(this);

        sessionData = getIntent().getParcelableExtra("session_data");

        progressBar = findViewById(R.id.imageActivityProgressBar);
        progressBar.setProgress(sessionData.getProgression());

        textViewProgression = findViewById(R.id.imageActivityTextViewPourcentage);
        textViewProgression.setText(sessionData.getProgression() + " %");

        imageView = findViewById(R.id.imageActivityImageView);
        imageView.setImageResource(R.drawable.ia1);
        imageView.setAdjustViewBounds(true);

        question = findViewById(R.id.imageActivityTextViewQuestionDescription);

        reponseOui = findViewById(R.id.imageActivityRadioButtonOui);
        reponseOui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "reponse oui");
            }
        });

        reponseNon = findViewById(R.id.imageActivityRadioButtonNon);
        reponseNon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void back(View v){
        finish();
    }

    public void next(View v){
        if (!reponseOui.isChecked() && !reponseNon.isChecked()){
            Toast.makeText(this, R.string.expertise1ActivityToastMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        computeResult();
        addDataInBD();
        Log.d(TAG, "Launch the second activity");
        Intent intent = new Intent(this, CalendarActivity.class);
        sessionData.increaseProgression();
        Log.d(TAG, "Session pseudo : " + sessionData.getPseudo() + " | score differenciation : " + sessionData.getScoreDifferenciation());
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void computeResult(){
        if(reponseOui.isChecked()){
            Log.d(TAG, "Bonne réponse, score différentiation += 1");
            sessionData.increaseScore(ScoreType.DIFFERENCIATION, 15);
        } else {
            Log.d(TAG, "Mauvaise réponse, score différentiation -= 1");
            sessionData.increaseScore(ScoreType.DIFFERENCIATION, -1);
        }
    }

    private void addDataInBD(){
        Question q = new Question();
        q.setSessionId(sessionData.getSessionId());
        q.setQuestionText(question.getText().toString());

        Answer reponse1 = new Answer();
        reponse1.setAnswer("oui");
        reponse1.setAnswerValid(true);
        if(reponseOui.isChecked()){
            Log.d(TAG, "Bonne réponse, score différentiation += 1");
            reponse1.setUserAnswer(true);
        } else {
            reponse1.setUserAnswer(false);
        }

        Answer reponse2 = new Answer();
        reponse2.setAnswer("non");
        reponse2.setAnswerValid(false);
        if(reponseNon.isChecked()){
            Log.d(TAG, "Mauvaise réponse, score différentiation -= 1");
            reponse2.setUserAnswer(true);
        } else {
            reponse2.setUserAnswer(false);
        }

        dbOperations.insertQuestionAndAnswers(q,reponse1, reponse2);

    }
}