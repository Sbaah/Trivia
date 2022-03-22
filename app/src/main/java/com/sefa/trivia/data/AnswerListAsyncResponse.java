package com.sefa.trivia.data;

import com.sefa.trivia.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {

    void processFinished (ArrayList<Question> questionArrayList);

}
