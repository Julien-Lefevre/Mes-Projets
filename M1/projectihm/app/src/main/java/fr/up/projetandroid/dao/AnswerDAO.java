package fr.up.projetandroid.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.up.projetandroid.entities.Answer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface AnswerDAO {
    @Query("SELECT * FROM answers")
    List<Answer> getAnswers();

    @Query("SELECT * FROM answers")
    Single<List<Answer>> getAnswersAsync();

    @Query("SELECT * FROM answers WHERE question_id IN (:questionId)")
    List<Answer> loadAllByQuestionIds(int[] questionId);

    @Query("SELECT * FROM answers WHERE question_id IN (:questionId)")
    Single<List<Answer>> loadAllByQuestionIdsAsync(int[] questionId);

    @Query("SELECT * FROM answers WHERE answer LIKE :text")
    List<Answer> find(String text);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(Answer... answers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllAsync(Answer... answers);

    @Delete
    int delete(Answer answers);
}
