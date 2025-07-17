package fr.up.projetandroid.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "answers")
public class Answer {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "answer")
    private String answer;

    @ColumnInfo(name = "is_user_answer")
    private boolean isUserAnswer;

    @ColumnInfo(name = "is_answer_valid")
    private boolean isAnswerValid;

    @ColumnInfo(name = "question_id")
    private int questionId;

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isUserAnswer(){return isUserAnswer;}

    public boolean isAnswerValid() {
        return isAnswerValid;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAnswerValid(boolean isAnswerValid) {
        this.isAnswerValid = isAnswerValid;
    }

    public void setUserAnswer(boolean isUserAnswer) {
        this.isUserAnswer = isUserAnswer;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
