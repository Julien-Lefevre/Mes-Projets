package fr.up.projetandroid.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.up.projetandroid.entities.Session;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface SessionDAO {
    @Query("SELECT * FROM sessions")
    List<Session> getSessions();

    @Query("SELECT * FROM sessions")
    Single<List<Session>> getSessionsAsync();

    @Query("SELECT * FROM sessions WHERE id IN (:ids)")
    List<Session> loadAllByIds(int[] ids);

    @Query("SELECT * FROM sessions WHERE pseudo LIKE :pseudo")
    List<Session> loadByPseudo(String pseudo);

    @Query("SELECT * FROM sessions WHERE pseudo LIKE :pseudo AND date LIKE :date")
    List<Session> find(String pseudo, int date);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(Session... sessions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertAsync(Session session);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllAsync(Session... sessions);

    @Update
    Completable updateSession(Session session);

    @Delete
    int delete(Session sessions);
}
