package com.example.android.interviewassistant.Model;

/**
 * Created by usman on 14/03/2017.
 */

public class Ranking {
    private int Id;
    private double Score;

    public Ranking(int id, double score) {
        Id = id;
        Score = score;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }
}