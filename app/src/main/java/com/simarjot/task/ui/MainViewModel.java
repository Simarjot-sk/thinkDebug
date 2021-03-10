package com.simarjot.task.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.simarjot.task.network.QuestionRepository;
import com.simarjot.task.network.QuestionService;
import com.simarjot.task.network.ServiceCreator;
import com.simarjot.task.network.model.Option;
import com.simarjot.task.network.model.QuestionDto;
import com.simarjot.task.network.model.server_response.Failure;
import com.simarjot.task.network.model.server_response.Success;
import com.simarjot.task.ui.model.Answer;
import com.simarjot.task.ui.model.state.DataFetched;
import com.simarjot.task.ui.model.state.Error;
import com.simarjot.task.ui.model.state.Loading;
import com.simarjot.task.ui.model.state.State;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class MainViewModel extends ViewModel {
    private final QuestionRepository questionRepository;
    private final MutableLiveData<State<List<QuestionDto>>> questionsState =
            new MutableLiveData<>();
    private final List<Option> selectedOptions = new LinkedList<>();

    public MainViewModel() {
        QuestionService questionService = ServiceCreator.createService(QuestionService.class);
        questionRepository = QuestionRepository.getInstance(questionService);
    }

    public LiveData<State<List<QuestionDto>>> getState() {
        return questionsState;
    }

    public void loadQuestions() {
        questionsState.setValue(new Loading<>());
        questionRepository.getQuizzes(71696, 102, "2021-03-9"/*getCurrentDate()*/).observeForever(value -> {
            if (value instanceof Success) {
                List<QuestionDto> questionDtoList = ((Success<List<QuestionDto>>) value).getData();
                questionsState.setValue(new DataFetched<>(questionDtoList));
            } else {
                String reason = ((Failure<List<QuestionDto>>) value).getReason();
                questionsState.setValue(new Error<>(reason));
            }
        });
    }

    private String getCurrentDate() {
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return format.format(calendar.getTime());
    }

    public void selectOption(Option option) {
        //removing option if an option already exists for the same question
        int optionIndex = -1;
        for (int i = 0; i < selectedOptions.size(); i++) {
            Option optionItem = selectedOptions.get(i);
            if (optionItem.getQuestionId() == option.getQuestionId()) {
                optionIndex = i;
            }
        }
        if (optionIndex != -1) {
            selectedOptions.remove(optionIndex);
        }

        //adding the new option
        selectedOptions.add(option);
    }

    public List<Option> getSelectedOptions() {
        return selectedOptions;
    }
}
