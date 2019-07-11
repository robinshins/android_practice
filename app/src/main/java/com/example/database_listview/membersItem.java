package com.example.database_listview;

public class membersItem {
    private String name;
    private String gender;
    private String month; // 몇개월치인지
    private String availabledate; // 만기 날짜
    private String phonenumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAvailabledate() {
        return availabledate;
    }

    public void setAvailabledate(String availabledate) {
        this.availabledate = availabledate;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
