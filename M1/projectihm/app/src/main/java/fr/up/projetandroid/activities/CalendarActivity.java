package fr.up.projetandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.up.projetandroid.Parcelable.SessionData;
import java.util.Calendar;

import fr.up.projetandroid.R;

public class CalendarActivity extends AppCompatActivity {
    public static String TAG = "Quizz IA - AiRankingActivity Activity";

    private ProgressBar progressBar;
    private TextView textViewProgression;
    private CalendarView calendarAnswer;

    private SessionData sessionData;

    private final int correctMonth = 1;
    private final int correctYear = 2021;
    int selectedDay;
    int selectedMonth;
    int selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sessionData = getIntent().getParcelableExtra("session_data");

        progressBar = findViewById(R.id.CalendarActivityprogressBar);
        progressBar.setProgress(sessionData.getProgression());
        textViewProgression = findViewById(R.id.CalendarActivityViewPourcentage);
        textViewProgression.setText(sessionData.getProgression() + " %");
        calendarAnswer = findViewById(R.id.CalendarActivityCalendarAnswer);
        // Écouter les changements de date dans le CalendarView
        calendarAnswer.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                selectedDay = dayOfMonth;
                selectedMonth = month + 1;
                selectedYear = year;

                Log.d(TAG, "Selected Month: " + selectedDay + ", Selected Month: " + selectedMonth + ", Selected Year: " + selectedYear);
            }
        });
    }

    private boolean isDateCorrect(){
        return (selectedMonth == correctMonth) && (selectedYear == correctYear);
    }

    public void next(View v){
        Log.d(TAG, "Launch the calendar activity");
        Intent intent = new Intent(this, TimeQuantityActivity.class);
        sessionData.increaseProgression();
        intent.putExtra("session_data", sessionData);
        startActivity(intent);
    }

    private void computeResult(){
        Long date = calendarAnswer.getDate();
    }

    private void addDataInBD(){

    }
}