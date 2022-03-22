package com.sefa.trivia.data;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.sefa.trivia.controller.AppController;
import com.sefa.trivia.model.Question;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    // Instantiation of Variables
    JsonArrayRequest jsonArrayRequest;
    ArrayList<Question> questionArrayList = new ArrayList<>();
    String url ="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";

    public List<Question> getQuestion(final AnswerListAsyncResponse callBack){

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                for(int i=0; i<response.length(); i++){
                    try {
                        Question question = new Question(response.getJSONArray(i).get(0).toString(),
                                response.getJSONArray(i).getBoolean(1));

                        // Add Question to the arrayList/List
                        questionArrayList.add(question);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // when data it ready use the interface to send it to the main activity
                    if(null != callBack)callBack.processFinished(questionArrayList);

                }
                }, error -> {

        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questionArrayList;
    }
}
