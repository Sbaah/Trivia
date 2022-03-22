package com.sefa.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.sefa.trivia.controller.AppController;
import com.sefa.trivia.data.AnswerListAsyncResponse;
import com.sefa.trivia.data.Repository;
import com.sefa.trivia.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Instantiation of Variables


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    List<Question> questions = new Repository().getQuestion(questionArrayList -> {
        Log.d("Main", "onCreate"+ questionArrayList);

    });

    }
}