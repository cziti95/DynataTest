package com.company.Models;

public class Participation {
    private int memberID;
    private int surveyID;
    private STATUS status;
    private int length;

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(int surveyID) {
        this.surveyID = surveyID;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Participation() {
    }

    public Participation(String[] items) {
        this.memberID = Integer.parseInt(items[0]);
        this.surveyID = Integer.parseInt(items[1]);
        switch (Integer.parseInt(items[2])){
            case 1: this.status = STATUS.NOT_ASKED;
                    break;
            case 2: this.status = STATUS.REJECTED;
                    break;
            case 3: this.status = STATUS.FILTERED;
                    break;
            case 4: this.status = STATUS.COMPLETED;
                    break;
        }
        if (items.length > 3)
            this.length= Integer.parseInt(items[3]);
    }

    @Override
    public String toString() {
        return "Participation{" +
                "memberID=" + memberID +
                ", surveyID=" + surveyID +
                ", status=" + status +
                ", length=" + length +
                '}';
    }
}
