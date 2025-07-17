package fr.up.projetandroid.activities;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.List;

import fr.up.projetandroid.Parcelable.SessionData;
import fr.up.projetandroid.R;
import fr.up.projetandroid.dao.AnswerDAO;
import fr.up.projetandroid.dao.QuestionDAO;
import fr.up.projetandroid.dao.SessionDAO;
import fr.up.projetandroid.database.AppDatabase;
import fr.up.projetandroid.entities.Answer;
import fr.up.projetandroid.entities.Question;
import fr.up.projetandroid.entities.Session;
import fr.up.projetandroid.utils.PdfGenerator;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EndActivity extends AppCompatActivity {

    public static String TAG = "Quizz IA - End activity";
    private AppDatabase db;
    private SessionDAO sessionDAO;
    private QuestionDAO questionDAO;
    private AnswerDAO answerDAO;
    private SessionData sessionData;

    private TextView textViewPseudo;
    private TextView textViewPeur;
    private TextView textViewExpertise;
    private TextView textViewSurvie;
    private TextView textViewQuizz;
    private TextView textViewDifferenciation;

    private ProgressBar progressBarPeur;
    private ProgressBar progressBarExpertise;
    private ProgressBar progressBarSurvie;
    private ProgressBar progressBarQuizz;
    private ProgressBar progressBarDifferenciation;
    private ProgressBar load;
    private Button downloadPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_end);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = AppDatabase.getInstance(this);
        sessionDAO = db.sessionDAO();
        questionDAO = db.questionDAO();
        answerDAO = db.answerDAO();
        sessionData = getIntent().getParcelableExtra("session_data");

        Session session = sessionData.toEntity();
        Completable c = sessionDAO.updateSession(session).subscribeOn(Schedulers.io());
        Disposable d = c.subscribeOn(Schedulers.io())
                .subscribe(
                        () -> {
                            Log.d(TAG, "Insertion ok en bd");
                        }, throwable -> {
                            Log.e(TAG, throwable.getMessage());
                        }
                );

        textViewPseudo = findViewById(R.id.endActivityTextViewPseudo);
        textViewPseudo.setText(sessionData.getPseudo());
        textViewPeur = findViewById(R.id.endActivityTextViewScorePeur);
        textViewPeur.setText(sessionData.getScorePeur() + "/100");
        textViewExpertise = findViewById(R.id.endActivityTextViewScoreExpertise);
        textViewExpertise.setText(sessionData.getScoreExpertise() + "/100");
        textViewSurvie = findViewById(R.id.endActivityTextViewScoreSurvie);
        textViewSurvie.setText(sessionData.getScoreSurvie() + "/100");
        textViewQuizz = findViewById(R.id.endActivityTextViewScoreQuizz);
        textViewQuizz.setText(sessionData.getScoreQuizz() + "/100");
        textViewDifferenciation = findViewById(R.id.endActivityTextViewScoreDifferenciation);
        textViewDifferenciation.setText(sessionData.getScoreDifferenciation() + "/100");

        progressBarPeur = findViewById(R.id.endActivityProgressBarPeur);
        progressBarPeur.setProgress(sessionData.getScorePeur());
        progressBarExpertise = findViewById(R.id.endActivityProgressBarExpertise);
        progressBarExpertise.setProgress(sessionData.getScoreExpertise());
        progressBarSurvie = findViewById(R.id.endActivityProgressBarSurvie);
        progressBarSurvie.setProgress(sessionData.getScoreSurvie());
        progressBarQuizz = findViewById(R.id.endActivityProgressBarQuizz);
        progressBarQuizz.setProgress(sessionData.getScoreQuizz());
        progressBarDifferenciation = findViewById(R.id.endActivityProgressBarDifferenciation);
        progressBarDifferenciation.setProgress(sessionData.getScoreDifferenciation());
        load = findViewById(R.id.progressBar);
        downloadPdf = findViewById(R.id.endActivityButtonDownloadPDF);
    }

    public void generatePdf(View v) throws InterruptedException {
        downloadPdf.setText("");
        load.setVisibility(View.VISIBLE);
        Single<List<Question>> singleQuestion = questionDAO.getQuestionsWithSessionIdAsync(new int[]{sessionData.getSessionId()}).subscribeOn(Schedulers.io());
        Disposable dQuestion = singleQuestion.subscribeOn(Schedulers.io())
                .subscribe(
                        questionList -> {
                            int[] questionIds = new int[questionList.size()];
                            for (int i = 0; i < questionList.size(); i++) {
                                questionIds[i] = questionList.get(i).getId(); // Supposons que getId() existe
                            }
                            Single<List<Answer>> singleAnswer = answerDAO.loadAllByQuestionIdsAsync(questionIds).subscribeOn(Schedulers.io());
                            Disposable dAnswer = singleAnswer.subscribeOn(Schedulers.io())
                                    .subscribe(
                                            answerlist -> {
                                                PdfGenerator.generatePdf(this, sessionData, questionList, answerlist);
                                            }, throwable ->  Log.e(TAG, throwable.getMessage()));
                        }, throwable -> Log.e(TAG, throwable.getMessage())
                );
        downloadPdf.setText("Download score to pdf");
        load.setVisibility(View.INVISIBLE);
        partager();
    }

    public void backToMenu(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finishAffinity();
    }

    private void partager(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/pdf");

        File file = new File("/storage/emulated/0/Android/data/fr.up.projetandroid/files/Documents/resultats.pdf");
        Uri pdfUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName()+".fileprovider", file);

        intent.setClipData(ClipData.newRawUri("", pdfUri));
        intent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivity(Intent.createChooser(intent,"Share PDF file"));
    }
}