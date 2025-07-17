package fr.up.projetandroid.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "question_text")
    private String questionText;

    @ColumnInfo(name = "session_id")
    private int sessionId;

    @ColumnInfo(name = "is_range")
    private boolean isRange = false;

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getSessionId() {
        return sessionId;
    }

    public boolean isRange() {
        return isRange;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setRange(boolean range) {
        isRange = range;
    }
}
