package org.wlzhj.ucs_admin.pojo;

/**
 * @ClassName: Admin
 * @Author: Gbo601
 * @Date: 2022-2022/8/31 18:08
 * @Description: TODO
 */

public class Admin {
    private int id;
    private String adminName;
    private String adminPassword;
    private int powerLevel;
    private String avatar;

    public Admin() {
    }

    public Admin(int id, String adminName, String adminPassword, int powerLevel, String avatar) {
        this.id = id;
        this.adminName = adminName;
        this.adminPassword = adminPassword;
        this.powerLevel = powerLevel;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", powerLevel=" + powerLevel +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
