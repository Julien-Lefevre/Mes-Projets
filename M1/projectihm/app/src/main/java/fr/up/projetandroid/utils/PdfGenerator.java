package fr.up.projetandroid.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import fr.up.projetandroid.Parcelable.SessionData;
import fr.up.projetandroid.dao.QuestionDAO;
import fr.up.projetandroid.database.AppDatabase;
import fr.up.projetandroid.entities.Answer;
import fr.up.projetandroid.entities.Question;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PdfGenerator {
    private static float textPosY = 80;
    private static PdfDocument document;
    private static PdfDocument.PageInfo pageInfo;
    private static PdfDocument.Page page;
    private static Canvas canvas;

    private static final int PAGE_HEIGHT = 842;
    private static final int PAGE_WIDTH = 595;
    private static final int PAGE_MARGIN = 40;
    private static final int PAGE_MAXHEIGHT = PAGE_HEIGHT - PAGE_MARGIN;

    public static File generatePdf(Context context, SessionData sessionData, List<Question> questionList, List<Answer> answerList) {
        Log.d("pdf generator", "questionList size : " + questionList.size());

        document = new PdfDocument();
        pageInfo = new PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, 1).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(24);
        canvas.drawText("Résultats du Quiz", pageInfo.getPageWidth()/2, textPosY, paint);
        textPosY += 50;
        paint.setTextAlign(Paint.Align.LEFT);

        paint.setTextSize(18);
        canvas.drawText("Scores :", 50, textPosY, paint);
        textPosY += 30;

        paint.setTextSize(12);
        canvas.drawText("Peur :", 60, textPosY, paint);
        drawGauge(canvas, 170, textPosY, sessionData.getScorePeur());
        textPosY += 40;

        canvas.drawText("Expertise :", 60, textPosY, paint);
        drawGauge(canvas, 170, textPosY, sessionData.getScoreExpertise());
        textPosY += 40;

        canvas.drawText("Survie :", 60, textPosY, paint);
        drawGauge(canvas, 170, textPosY, sessionData.getScoreSurvie());
        textPosY += 40;

        canvas.drawText("Quizz :", 60, textPosY, paint);
        drawGauge(canvas, 170, textPosY, sessionData.getScoreQuizz());
        textPosY += 40;

        canvas.drawText("Differenciation :", 60, textPosY, paint);
        drawGauge(canvas, 170, textPosY, sessionData.getScoreDifferenciation());
        textPosY += 80;

        paint.setTextSize(18);
        canvas.drawText("Résumé :", 50, textPosY, paint);
        textPosY += 30;

       for(Question question : questionList){
           paint.setColor(Color.BLACK);
           paint.setTextSize(12);
           checkAvailableSpace();
           canvas.drawText("Question : ", 60, textPosY, paint);
           textPosY += 15;
           checkAvailableSpace();
           canvas.drawText(question.getQuestionText(), 70, textPosY, paint);
           textPosY += 15;

           List<Answer> questionAnswers = answerList.stream().filter(answer -> answer.getQuestionId() == question.getId()).collect(Collectors.toList());
           List<Answer> userResponse = new ArrayList<>(questionAnswers.size());

           paint.setColor(Color.BLUE);
           checkAvailableSpace();
           canvas.drawText("Réponses possibles :", 80, textPosY, paint);
           textPosY += 15;
           paint.setColor(Color.BLACK);
           int size = questionAnswers.size();
           for(int i = 0; i < size; ++i){
               Answer answer = questionAnswers.get(i);
               if(question.isRange()){
                   if(!answer.isUserAnswer()){
                       checkAvailableSpace();
                       canvas.drawText("- " + answer.getAnswer() + (i < size - 1 ? " ," : ""), 90, textPosY, paint);
                       textPosY += 15;
                   } else {
                       userResponse.add(answer);
                   }
               } else {
                   checkAvailableSpace();
                   canvas.drawText("- " + answer.getAnswer() + (i < size - 1 ? " ," : ""), 90, textPosY, paint);
                   if(answer.isUserAnswer()){
                       userResponse.add(answer);
                   }
                   textPosY += 15;
               }
           }

           paint.setColor(Color.BLUE);
           int userResponseSize = userResponse.size();
           if(userResponseSize > 1 ) {
               checkAvailableSpace();
               canvas.drawText("Vos réponses : ", 80, textPosY, paint);
           } else {
               checkAvailableSpace();
               canvas.drawText("Votre réponse : ", 80, textPosY, paint);
           }
           textPosY += 15;
           paint.setColor(Color.BLACK);
           for(int i = 0; i < userResponseSize; ++i){
               Answer answer = userResponse.get(i);
               if(answer.isAnswerValid())
                   paint.setColor(Color.GREEN);
               else
                   paint.setColor(Color.RED);
               checkAvailableSpace();
               canvas.drawText("- " + answer.getAnswer() + (i < userResponseSize - 1 ? " ," : ""), 90, textPosY, paint);
               textPosY += 15;
           }
           textPosY += 30;
       }
        document.finishPage(page);

        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "resultats.pdf");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            document.writeTo(fos);
            document.close();
            Log.d("PDF GENERATOR", "Fichier généré : " + file.getAbsolutePath());
            return file;
        } catch (IOException e) {
            Log.e("PDF GENERATOR", "Erreur lors de la création du PDF", e);
            return null;
        }
    }


    private static void drawGauge(Canvas canvas, float cx, float cy, float score) {
        float size = 180;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setColor(Color.GRAY);
        canvas.drawLine(cx, cy, cx + size, cy, paint);

        paint.setColor(Color.GREEN);
        float scoreLine = (score / 100) * size; // Conversion du score en angle
        canvas.drawLine(cx, cy , cx + scoreLine, cy, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(1);
        paint.setTextSize(12);
        canvas.drawText(score + " / 100",cx + size + 30,cy,paint);
    }

    private static void checkAvailableSpace(){
        if(textPosY > PAGE_MAXHEIGHT){
            document.finishPage(page);
            pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
            page = document.startPage(pageInfo);
            canvas = page.getCanvas();
            textPosY = 80; // Recommencer en haut
        }
    }
}