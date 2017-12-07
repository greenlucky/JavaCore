package com.lamdevops.stream.pojo;

public class Player {
    private int year;
    private String teamID;
    private String lgID;
    private String playerID;
    private int salary;

    public Player() {
    }

    public Player(int year, String teamID, String lgID, String playerID, int salary) {
        this.year = year;
        this.teamID = teamID;
        this.lgID = lgID;
        this.playerID = playerID;
        this.salary = salary;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getLgID() {
        return lgID;
    }

    public void setLgID(String lgID) {
        this.lgID = lgID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "GroupByPlayer{" +
                "year=" + year +
                ", teamID='" + teamID + '\'' +
                ", lgID='" + lgID + '\'' +
                ", playerID='" + playerID + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
