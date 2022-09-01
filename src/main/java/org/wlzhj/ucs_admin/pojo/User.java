package org.wlzhj.ucs_admin.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private int id;
    private String userName;
    private String userPassword;
    private String gender;
    private String phone;
    private String email;
    private String avatar;
    private Date birthday;
    private BigDecimal money;
    private Date jointime;

    public User() {
    }

    public User(int id, String userName, String userPassword, String gender, String phone, String email, String avatar, Date birthday, BigDecimal money, Date jointime) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.birthday = birthday;
        this.money = money;
        this.jointime = jointime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday=" + birthday +
                ", money=" + money +
                ", jointime=" + jointime +
                '}';
    }
}
