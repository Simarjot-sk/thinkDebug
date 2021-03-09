package com.simarjot.task.network;

public class QuizRepository {
    private static QuizRepository instance = null;

    //private constructor for enforcing singleton pattern
    private QuizRepository() {}

    public void getQuizzes(){

    }

    public static QuizRepository getInstance() {
        if (instance == null) {
            instance = new QuizRepository();
        }
        return instance;
    }
}
