package com.simarjot.task.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.simarjot.task.network.model.QuestionDto;
import com.simarjot.task.network.model.server_response.Failure;
import com.simarjot.task.network.model.server_response.ServerResponse;
import com.simarjot.task.network.model.server_response.Success;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionRepository {
    private static QuestionRepository instance = null;
    private static final String unknownErrorMessage = "An Unknown Error Occurred. Please try again later.";
    private final QuestionService questionService;

    //private constructor for enforcing singleton pattern
    private QuestionRepository(QuestionService questionService) {
        this.questionService = questionService;
    }

    public LiveData<ServerResponse<List<QuestionDto>>> getQuizzes(int userId, int classId, String date) {
        MutableLiveData<ServerResponse<List<QuestionDto>>> liveData =
                new MutableLiveData<>();

        Call<JsonObject> call = questionService.getQuizzes(date, userId, classId);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        JsonObject responseBody = response.body();
                        List<QuestionDto> questionDtoList = parseResponse(responseBody);
                        if (questionDtoList == null) {
                            liveData.setValue(new Failure<>("No data found"));
                        } else {
                            liveData.setValue(new Success<>(questionDtoList));
                        }
                    } else {
                        liveData.setValue(new Failure<>(unknownErrorMessage));
                    }
                } else {
                    liveData.setValue(new Failure<>(unknownErrorMessage));
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                String error = getError(t);
                liveData.setValue(new Failure<>(error));
            }
        });
        return liveData;
    }

    /**
     * parses the json Object response into List of QuestionDtos
     *
     * @param response response in JsonObject form
     * @return null if data is empty, List of QuestionDto otherwise
     */
    private List<QuestionDto> parseResponse(JsonObject response) {
        final boolean isSuccessful = response.get("status").getAsBoolean();
        if (!isSuccessful) return null;
        JsonArray body = response.get("body").getAsJsonArray();
        if (body.size() == 0) return null;
        JsonObject outerJson = body.get(0).getAsJsonObject();
        JsonArray questionObjects = outerJson.get("questions_list").getAsJsonArray();
        if (questionObjects.size() == 0) return null;
        List<QuestionDto> questionDtoList = new ArrayList<>();
        for (JsonElement jsonElement : questionObjects) {
            QuestionDto questionDto = new Gson().fromJson(jsonElement, QuestionDto.class);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;
    }

    private String getError(Throwable throwable) {
        if (throwable instanceof IOException || throwable instanceof UnknownHostException) {
            return "Error Connecting to server.";
        } else if (throwable instanceof SocketTimeoutException) {
            return "Error while connecting to internet.";
        } else return unknownErrorMessage;
    }

    public static QuestionRepository getInstance(QuestionService questionService) {
        if (instance == null) {
            instance = new QuestionRepository(questionService);
        }
        return instance;
    }
}
