package com.example.demo.Movie;

public class Answer {
    public int questionId;
    public boolean yesOrNo;
    public int spielId;

    public Answer(int questionId, boolean yesOrNo, int spielId) {
        this.questionId = questionId;
        this.yesOrNo = yesOrNo;
        this.spielId = spielId;
    }
}
