package com.simarjot.task.ui;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.simarjot.task.network.QuestionRepository;
import com.simarjot.task.network.QuestionService;
import com.simarjot.task.network.ServiceCreator;
import com.simarjot.task.network.model.QuestionDto;
import com.simarjot.task.network.model.server_response.Failure;
import com.simarjot.task.network.model.server_response.Success;

import java.util.List;


public class MainViewModel extends ViewModel {
    private final QuestionRepository questionRepository;

    public MainViewModel() {
        QuestionService questionService = ServiceCreator.createService(QuestionService.class);
        questionRepository = QuestionRepository.getInstance(questionService);
    }

    public void logQuestions() {
        questionRepository.getQuizzes(71696, 102, "2021-03-09").observeForever(value -> {
            if (value instanceof Success) {
                Log.d("kalsi", ((Success<List<QuestionDto>>) value).getData().toString());
            } else {
                Log.d("kalsi", "failed");
            }
        });
    }
}
