package com.moono.dto;

public class UserDTO {
    private String id;
	private String pwd;
    private String nickName;
    private boolean oneMission;
    private boolean twoMission;
    private boolean threeMission;
    private boolean fourMission;
    private boolean fiveMission;

    // Getters and Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isOneMission() {
        return oneMission;
    }
    public void setOneMission(boolean oneMission) {
        this.oneMission = oneMission;
    }

    public boolean isTwoMission() {
        return twoMission;
    }
    public void setTwoMission(boolean twoMission) {
        this.twoMission = twoMission;
    }

    public boolean isThreeMission() {
        return threeMission;
    }
    public void setThreeMission(boolean threeMission) {
        this.threeMission = threeMission;
    }

    public boolean isFourMission() {
        return fourMission;
    }
    public void setFourMission(boolean fourMission) {
        this.fourMission = fourMission;
    }

    public boolean isFiveMission() {
        return fiveMission;
    }
    public void setFiveMission(boolean fiveMission) {
        this.fiveMission = fiveMission;
    }
}