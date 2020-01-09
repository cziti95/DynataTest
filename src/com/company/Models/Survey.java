package com.company.Models;

public class Survey {

    private int surveyID;
    private String name;
    private int expectedCompletes;
    private int completionPoint;
    private int filteredPoint;

    public int getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(int surveyID) {
        this.surveyID = surveyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpectedCompletes() {
        return expectedCompletes;
    }

    public void setExpectedCompletes(int expectedCompletes) {
        this.expectedCompletes = expectedCompletes;
    }

    public int getCompletionPoint() {
        return completionPoint;
    }

    public void setCompletionPoint(int completionPoint) {
        this.completionPoint = completionPoint;
    }

    public int getFilteredPoint() {
        return filteredPoint;
    }

    public void setFilteredPoint(int filteredPoint) {
        this.filteredPoint = filteredPoint;
    }

    public Survey() {
    }

    public Survey(String[] items) {
        this.surveyID = Integer.parseInt(items[0]);
        this.name = items[1];
        this.expectedCompletes = Integer.parseInt(items[2]);
        this.completionPoint = Integer.parseInt(items[3]);
        this.filteredPoint = Integer.parseInt(items[4]);
    }

    @Override
    public String toString() {
        return "Survey{" +
                "surveyID=" + surveyID +
                ", name='" + name + '\'' +
                ", expectedCompletes=" + expectedCompletes +
                ", completionPoint=" + completionPoint +
                ", filteredPoint=" + filteredPoint +
                '}';
    }
}
