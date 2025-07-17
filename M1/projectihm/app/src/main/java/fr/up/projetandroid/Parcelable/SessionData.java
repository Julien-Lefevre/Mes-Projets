package fr.up.projetandroid.Parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.up.projetandroid.entities.Session;

public class SessionData implements Parcelable {

    private String pseudo;
    private int sessionId;
    private int progression;
    private int scorePeur;
    private int scoreExpertise;
    private int scoreSurvie;
    private int scoreQuizz;
    private int scoreDifferenciation;

    private final int NB_ACTIVITIES = 11;
    private final int STEP_PROGRESSION = 100/NB_ACTIVITIES;

    private boolean isProgressionIncreased = false;
//    private boolean isScorePeurIncreased = false;
//    private int ajoutScorePeur = 0;
//
//    private boolean isScoreExpertiseIncreased = false;
//    private int ajoutScoreExpertise = 0;
//
//    private boolean isScoreSurvieIncreased = false;
//    private int ajoutSScoreSurvie = 0;
//
//    private boolean isScoreQuizzIncreased = false;
//    private int ajoutScoreQuizz = 0;
//
//    private boolean isScoreDifferenciationIncreased = false;
//    private int ajoutScoreDifferenciation= 0;

    public SessionData(
            String pseudo,
            int sessionId,
            int progression,
            int scorePeur,
            int scoreExpertise,
            int scoreSurvie,
            int scoreQuizz,
            int scoreDifferenciation
    ) {
        this.pseudo = pseudo;
        this.sessionId = sessionId;
        this.progression = progression;
        this.scorePeur = scorePeur;
        this.scoreExpertise = scoreExpertise;
        this.scoreSurvie = scoreSurvie;
        this.scoreQuizz = scoreQuizz;
        this.scoreDifferenciation = scoreDifferenciation;
    }

    public SessionData(String pseudo, int sessionId){
        this.pseudo = pseudo;
        this.sessionId = sessionId;
        this.progression = 0;
        this.scorePeur = 50;
        this.scoreExpertise = 50;
        this.scoreSurvie = 50;
        this.scoreQuizz = 50;
        this.scoreDifferenciation = 50;
    }

    private SessionData(Parcel in) {
        pseudo = in.readString();
        sessionId = in.readInt();
        progression = in.readInt();
        scorePeur = in.readInt();
        scoreExpertise = in.readInt();
        scoreSurvie = in.readInt();
        scoreQuizz = in.readInt();
        scoreDifferenciation = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
         dest.writeString(pseudo);
         dest.writeInt(sessionId);
         dest.writeInt(progression);
         dest.writeInt(scorePeur);
         dest.writeInt(scoreExpertise);
         dest.writeInt(scoreSurvie);
         dest.writeInt(scoreQuizz);
         dest.writeInt(scoreDifferenciation);
    }

    public static final Parcelable.Creator<SessionData> CREATOR
            = new Parcelable.Creator<SessionData>() {
        public SessionData createFromParcel(Parcel in) {
            return new SessionData(in);
        }

        public SessionData[] newArray(int size) {
            return new SessionData[size];
        }
    };

    public void increaseScore(ScoreType type, int value){
        switch (type) {
            case PEUR:
//                if()
                scorePeur += value;
//                isScorePeurIncreased = true;
                break;
            case EXPERTISE:
                scoreExpertise += value;
                break;
            case SURVIE:
                scoreSurvie += value;
                break;
            case QUIZZ:
                scoreQuizz += value;
                break;
            case DIFFERENCIATION:
                scoreDifferenciation += value;
                break;
        }
    }

    public void decreaseScore(ScoreType type, int value){
        switch (type) {
            case PEUR:
                scorePeur -= value;
                break;
            case EXPERTISE:
                scoreExpertise -= value;
                break;
            case SURVIE:
                scoreSurvie -= value;
                break;
            case QUIZZ:
                scoreQuizz -= value;
                break;
            case DIFFERENCIATION:
                scoreDifferenciation -= value;
                break;
        }
    }

    public void increaseProgression(){
        if(!isProgressionIncreased){
            progression += STEP_PROGRESSION;
            isProgressionIncreased = true;
        }
    }

    public Session toEntity(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Session session = new Session();
        session.setId(sessionId);
        session.setPseudo(this.pseudo);
        session.setDate(format.format(new Date()));
        session.setScorePeur(this.scorePeur);
        session.setScoreExpertise(this.scoreExpertise);
        session.setScoreSurvie(this.scoreSurvie);
        session.setScoreQuizz(this.scoreQuizz);
        session.setScoreDifferenciation(this.scoreDifferenciation);

        return session;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getSessionId() {
        return sessionId;
    }

    public int getProgression() {
        return progression;
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
}
