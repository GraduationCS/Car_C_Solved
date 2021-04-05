package com.example.car_s;

public class RepairmenHelperClass {

    String full_name,username,age_get,phone,city,job_type,experience,pass;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAge_get() {
        return age_get;
    }

    public void setAge_get(String age_get) {
        this.age_get = age_get;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public RepairmenHelperClass(String full_name, String username, String age_get, String phone, String city, String job_type, String experience, String pass) {

        this.full_name=full_name;
        this.username=username;
        this.age_get=age_get;
        this.phone=phone;
        this.city=city;
        this.job_type=job_type;
        this.experience=experience;
        this.pass=pass;

    }
}
