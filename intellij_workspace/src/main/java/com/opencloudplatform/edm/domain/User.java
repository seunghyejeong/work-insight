package com.opencloudplatform.edm.domain;

import java.util.Objects;

public class User {
    private Integer userIdx;
    private String userName;
    private String userEmail;

    public User (){}

    public User(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userIdx=" + userIdx +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userIdx, user.userIdx) && Objects.equals(userName, user.userName) && Objects.equals(userEmail, user.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userIdx, userName, userEmail);
    }

    public int getUseridx() {
        return userIdx;
    }

    public void setUseridx(int useridx) {
        this.userIdx = useridx;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getUseremail() {
        return userEmail;
    }

    public void setUseremail(String useremail) {
        this.userEmail = useremail;
    }


}
