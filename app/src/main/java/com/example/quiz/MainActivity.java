package com.example.quiz;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    private Button button_true, button_false, button_next;
    private TextView textView;
    private int quest_index = 0;
    private int count = 0;

    private TrueFalse[] questions = new TrueFalse[]{
            new TrueFalse(R.string.quest_1, true),
            new TrueFalse(R.string.quest_2, true),
            new TrueFalse(R.string.quest_3, false),
            new TrueFalse(R.string.quest_4, true),
            new TrueFalse(R.string.quest_5, false),
            new TrueFalse(R.string.quest_6, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_false = (Button) findViewById(R.id.button_false);
        button_true = (Button) findViewById(R.id.button_true);
        button_next = (Button) findViewById(R.id.button_next);
        textView = (TextView) findViewById(R.id.text_result);

//помещаем строку с вопросом из стринга
        final int question = questions[quest_index].getQuestion();
        textView.setText(question);

        button_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        button_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quest_index < questions.length - 1) {
                    quest_index++;
                    final int question = questions[quest_index].getQuestion();
                    textView.setText(question);
                    button_true.setVisibility(View.VISIBLE);
                    button_false.setVisibility(View.VISIBLE);
                    button_next.setVisibility(View.INVISIBLE);
                } else {
                    textView.setText("Викторина окончена! Результат: " + count + " из " + questions.length);
                    textView.setTextColor(Color.GREEN);
                    button_next.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    //вплывающие правильно или не правильно при сравнении с ответом
    private void checkAnswer(boolean answer_user) {
        boolean answer = questions[quest_index].isTrueQuestion();
        if (answer_user == answer) {
            Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_LONG).show();
            count++;
        } else
            Toast.makeText(MainActivity.this, "Не верно", Toast.LENGTH_LONG).show();
        button_true.setVisibility(View.INVISIBLE);
        button_false.setVisibility(View.INVISIBLE);
        button_next.setVisibility(View.VISIBLE);

    }
}
