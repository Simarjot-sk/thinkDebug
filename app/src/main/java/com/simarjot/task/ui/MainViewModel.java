package com.simarjot.task.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.simarjot.task.network.QuestionRepository;
import com.simarjot.task.network.QuestionService;
import com.simarjot.task.network.ServiceCreator;
import com.simarjot.task.network.model.QuestionDto;
import com.simarjot.task.network.model.server_response.Failure;
import com.simarjot.task.network.model.server_response.Success;
import com.simarjot.task.ui.state.DataFetched;
import com.simarjot.task.ui.state.Error;
import com.simarjot.task.ui.state.Loading;
import com.simarjot.task.ui.state.State;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final QuestionRepository questionRepository;
    private final MutableLiveData<State<List<QuestionDto>>> questionsState =
            new MutableLiveData<>();

    public MainViewModel() {
        QuestionService questionService = ServiceCreator.createService(QuestionService.class);
        questionRepository = QuestionRepository.getInstance(questionService);
    }

    public LiveData<State<List<QuestionDto>>> getState() {
        return questionsState;
    }

    public void loadQuestions() {
        questionsState.setValue(new Loading<>());
        questionRepository.getQuizzes(71696, 102, "2021-03-09").observeForever(value -> {
            if (value instanceof Success) {
                List<QuestionDto> questionDtoList = ((Success<List<QuestionDto>>) value).getData();
                questionsState.setValue(new DataFetched<>(questionDtoList));
            } else {
                String reason = ((Failure<List<QuestionDto>>) value).getReason();
                questionsState.setValue(new Error<>(reason));
            }
        });
    }
}
