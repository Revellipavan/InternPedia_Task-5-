package com.example.quizmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Question and answers data
    private final String[] questions = {
            "What is the base class for all Android widgets?",
            "Which method is used to start an activity?",
            "What is the use of content provider in Android?"
    };

    private final String[][] options = {
            {"View", "Widget", "Component", "Layout"},
            {"startActivity()", "onCreate()", "onStart()", "init()"},
            {"To manage the data", "To store the data", "To send the data", "None of the above"}
    };

    private final String[] correctAnswers = {"View", "startActivity()", "To manage the data"};

    private int currentQuestionIndex = 0;
    private int score = 0;

    private TextView questionTextView;
    private RadioGroup optionsGroup;
    private RadioButton option1, option2, option3, option4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        optionsGroup = findViewById(R.id.optionsGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        Button nextButton = findViewById(R.id.nextButton);

        loadQuestion();

        nextButton.setOnClickListener(v -> {
            checkAnswer();
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                loadQuestion();
            } else {
                Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        option1.setText(options[currentQuestionIndex][0]);
        option2.setText(options[currentQuestionIndex][1]);
        option3.setText(options[currentQuestionIndex][2]);
        option4.setText(options[currentQuestionIndex][3]);
        optionsGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedOptionId = optionsGroup.getCheckedRadioButtonId();
        if (selectedOptionId != -1) {
            RadioButton selectedOption = findViewById(selectedOptionId);
            String selectedAnswer = selectedOption.getText().toString();
            if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
                score++;
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        }
    }
}
