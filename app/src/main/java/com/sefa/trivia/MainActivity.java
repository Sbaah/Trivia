package com.sefa.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.material.snackbar.Snackbar;
import com.sefa.trivia.controller.AppController;
import com.sefa.trivia.data.AnswerListAsyncResponse;
import com.sefa.trivia.data.Repository;
import com.sefa.trivia.databinding.ActivityMainBinding;
import com.sefa.trivia.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    private int currentQuestionIndex=0;
    List<Question> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);


        questionList= new Repository().getQuestion(questionArrayList -> {
        binding.questionTextview.setText(questionArrayList.get(currentQuestionIndex).getAnswer());

        // binding.textViewOutOf.setText(String.format(getString(R.string.text_formatted), currentQuestionIndex, questionArrayList.size()));
            updateCounter(questionArrayList);
    });
    binding.buttonNext.setOnClickListener(view -> {
        currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
        updateQuestion();
    });

    binding.buttonTrue.setOnClickListener(view -> {
        checkAnswer(true);
        updateQuestion();

    });

    binding.buttonFalse.setOnClickListener(view -> {
        checkAnswer(false);
        updateQuestion();
    });

    }

    private void checkAnswer(boolean userChoseCorrect) {
        boolean answer = questionList.get(currentQuestionIndex).isAnswerTrue();
        int snackMessageId = 0;
        if (userChoseCorrect == answer) {
            snackMessageId = R.string.correct_answer;
            //fadeAnimation();
        } else {
            snackMessageId = R.string.incorrect;
            //shakeAnimation();
        }
        Snackbar.make(binding.cardView, snackMessageId, Snackbar.LENGTH_SHORT)
                .show();
    }

    private void updateQuestion() {
        String question = questionList.get(currentQuestionIndex).getAnswer();
        binding.questionTextview.setText(question);
        updateCounter((ArrayList<Question>) questionList);
    }

    private void updateCounter(ArrayList<Question> questionArrayList) {
        binding.textViewOutOf.setText(String.format(getString(R.string.text_formatted),
                currentQuestionIndex, questionArrayList.size()));
    }


}