package com.example.hw05;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
    String description, icon;
    Double temp, temp_min, temp_max, pressure, humidity, speed, deg, cloudiness;

    public Weather(WeatherResponse res){
        this.temp = res.getMain().getTemp();
        this.temp_min = res.getMain().getTemp_min();
        this.temp_max = res.getMain().getTemp_max();
        this.pressure = res.getMain().getPressure();
        this.humidity = res.getMain().getHumidity();

        this.speed = res.getWind().getSpeed();
        this.deg = res.getWind().getDeg();

        if(res.getWeather().size() > 0){
            this.description = res.getWeather().get(0).getDescription();
            this.icon = res.getWeather().get(0).getIcon();
        }

        this.cloudiness = res.getClouds().getAll();
    }

    public Weather(JSONObject json) throws JSONException {
        JSONObject mainJsonObject = json.getJSONObject("main");
        this.temp = mainJsonObject.getDouble("temp");
        this.temp_min = mainJsonObject.getDouble("temp_min");
        this.temp_max = mainJsonObject.getDouble("temp_max");
        this.pressure = mainJsonObject.getDouble("pressure");
        this.humidity = mainJsonObject.getDouble("humidity");

        JSONObject windJsonObject = json.getJSONObject("wind");
        this.speed = windJsonObject.getDouble("speed");
        this.deg = windJsonObject.getDouble("deg");

        JSONArray weatherJsonArray = json.getJSONArray("weather");
        if(weatherJsonArray.length() > 0){
            JSONObject firstJson = weatherJsonArray.getJSONObject(0);
            this.description = firstJson.getString("description");
            this.icon = firstJson.getString("icon");
        }

        JSONObject cloudsJsonObject = json.getJSONObject("clouds");
        this.cloudiness = cloudsJsonObject.getDouble("all");

    }

    public Weather() {
    }

    public Double getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(Double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    public Double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public String getIconUrl(){
        return "https://openweathermap.org/img/wn/" + this.icon + "@2x.png";
    }
}
