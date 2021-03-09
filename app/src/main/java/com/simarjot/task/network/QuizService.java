package com.simarjot.task.network;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QuizService {

    @POST("quiz/quiz_list.php")
    @FormUrlEncoded
    Call<JSONObject> getQuizzes(
            @Field("date") String date,
            @Field("user_id") int userId,
            @Field("class_id") int classId
    );

}
