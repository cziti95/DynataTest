package com.company.Models;

public class Member {
    private int memberID;
    private String name;
    private String email;
    private boolean isActive;

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Member() {
    }

    public Member(String[] items) {
        this.memberID = Integer.parseInt(items[0]);
        this.name = items[1];
        this.email = items[2];
        this.isActive = Integer.parseInt(items[3]) == 1 ? true : false;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "memberID=" + memberID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
