package com.example.myretrofit;

public class Today {
    public String wind;
    public String week;
    public String city;
    public String date_y;
    public String dressing_index;

    public Today(String wind, String week, String city, String date_y, String dressing_index) {
        this.wind = wind;
        this.week = week;
        this.city = city;
        this.date_y = date_y;
        this.dressing_index = dressing_index;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_y() {
        return date_y;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public String getDressing_index() {
        return dressing_index;
    }

    public void setDressing_index(String dressing_index) {
        this.dressing_index = dressing_index;
    }

    @Override
    public String toString() {
        return "Today{" +
                "wind='" + wind + '\'' +
                ", week='" + week + '\'' +
                ", city='" + city + '\'' +
                ", date_y='" + date_y + '\'' +
                ", dressing_index='" + dressing_index + '\'' +
                '}';
    }
}
