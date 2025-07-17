package fr.up.projetandroid.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Entity(tableName = "sessions")
public class Session {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "pseudo")
    private String pseudo;

    // timestamp en seconde (revient a faire : System.currentTimeMillis() / 1000)
    // limite à une date jusqu'à 2038 (il faudrait utiliser un long)
    // mais pas besoins d'aller aussi loin donc un int suffit et prend moins de place
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "score_peur")
    private int scorePeur;

    @ColumnInfo(name = "score_expertise")
    private int scoreExpertise;

    @ColumnInfo(name = "score_survie")
    private int scoreSurvie;

    @ColumnInfo(name = "score_quizz")
    private int scoreQuizz;

    @ColumnInfo(name = "score_differenciation")
    private int scoreDifferenciation;

    public String getPseudo(){
        return pseudo;
    }

    public String getDate() {
        return date;
    }

    public int getScorePeur() {
        return scorePeur;
    }

    public int getScoreExpertise() {
        return scoreExpertise;
    }

    public int getScoreSurvie() {
        return scoreSurvie;
    }

    public int getScoreQuizz() {
        return scoreQuizz;
    }
    public int getScoreDifferenciation() {
        return scoreDifferenciation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        this.date = format.format(date);
    }

    public void setScorePeur(int scorePeur) {
        this.scorePeur = scorePeur;
    }

    public void setScoreExpertise(int scoreExpertise) {
        this.scoreExpertise = scoreExpertise;
    }

    public void setScoreSurvie(int scoreSurvie) {
        this.scoreSurvie = scoreSurvie;
    }

    public void setScoreQuizz(int scoreQuizz) {
        this.scoreQuizz = scoreQuizz;
    }
    public void setScoreDifferenciation(int scoreDifferenciation) {
        this.scoreDifferenciation = scoreDifferenciation;
    }
}
