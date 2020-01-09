package com.company.Models;

public class Statistics {
    private int surveyID;
    private String surveyName;
    private int numberOfFillers;
    private int numberOfFiltered;
    private int numberOfRejectors;
    private double averageFillTime;

    public int getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(int surveyID) {
        this.surveyID = surveyID;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public int getNumberOfFillers() {
        return numberOfFillers;
    }

    public void setNumberOfFillers(int numberOfFillers) {
        this.numberOfFillers = numberOfFillers;
    }

    public int getNumberOfFiltered() {
        return numberOfFiltered;
    }

    public void setNumberOfFiltered(int numberOfFiltered) {
        this.numberOfFiltered = numberOfFiltered;
    }

    public int getNumberOfRejectors() {
        return numberOfRejectors;
    }

    public void setNumberOfRejectors(int numberOfRejectors) {
        this.numberOfRejectors = numberOfRejectors;
    }

    public double getAverageFillTime() {
        return averageFillTime;
    }

    public void setAverageFillTime(double averageFillTime) {
        this.averageFillTime = averageFillTime;
    }

    public Statistics() {
    }

    public Statistics(int surveyID, String surveyName, int numberOfFillers, int numberOfFiltered, int numberOfRejectors, double averageFillTime) {
        this.surveyID = surveyID;
        this.surveyName = surveyName;
        this.numberOfFillers = numberOfFillers;
        this.numberOfFiltered = numberOfFiltered;
        this.numberOfRejectors = numberOfRejectors;
        this.averageFillTime = averageFillTime;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "surveyID=" + surveyID +
                ", surveyName='" + surveyName + '\'' +
                ", numberOfFillers=" + numberOfFillers +
                ", numberOfFiltered=" + numberOfFiltered +
                ", numberOfRejectors=" + numberOfRejectors +
                ", averageFillTime=" + averageFillTime +
                '}';
    }
}
