package fr.up.projetandroid.utils;

import android.content.Context;
import android.util.Log;

import fr.up.projetandroid.dao.AnswerDAO;
import fr.up.projetandroid.dao.QuestionDAO;
import fr.up.projetandroid.database.AppDatabase;
import fr.up.projetandroid.entities.Answer;
import fr.up.projetandroid.entities.Question;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DbOperations {
    public static String TAG = "Quizz IA - DBOperations";
    private static DbOperations INSTANCE;
    private AppDatabase db;
    private QuestionDAO questionDAO;
    private AnswerDAO answerDAO;

    private DbOperations(Context context){
        this.db = AppDatabase.getInstance(context);
        this.questionDAO = db.questionDAO();
        this.answerDAO = db.answerDAO();
    }

    public static DbOperations getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new DbOperations(context.getApplicationContext());
        }
        return INSTANCE;
    }

    public void insertQuestionAndAnswers(Question question, Answer... answers){
        Single<Long> c = questionDAO.insertAsync(question).subscribeOn(Schedulers.io());
        Disposable d = c.subscribeOn(Schedulers.io())
                .subscribe(
                        (questionId) -> {
                            Log.d(TAG, "Insertion question ok en bd");

                            for(Answer answer : answers)
                            {
                                answer.setQuestionId(questionId.intValue());
                            }
                            Completable c2 = answerDAO.insertAllAsync(answers).subscribeOn(Schedulers.io());
                            Disposable d2 = c2.subscribeOn(Schedulers.io())
                                    .subscribe(
                                            () -> {
                                                Log.d(TAG, "Insertion ok en bd");
                                            }, throwable -> {
                                                Log.e(TAG, throwable.getMessage());
                                            }
                                    );
                        }, throwable -> {
                            Log.e(TAG, throwable.getMessage());
                        }
                );
    }
}
