package fr.up.projetandroid.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.up.projetandroid.entities.Answer;
import fr.up.projetandroid.entities.Question;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM questions")
    List<Question> getQuestions();

   @Query("SELECT * FROM questions WHERE session_id IN (:sessionId)")
    List<Question> getQuestionsWithSessionId(int[] sessionId);

    @Query("SELECT * FROM questions WHERE session_id IN (:sessionId)")
    Single<List<Question>> getQuestionsWithSessionIdAsync(int[] sessionId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(Question... questions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertAsync(Question question);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllAsync(Question... questions);

    @Delete
    int delete(Question questions);
}
