package com.example.hw05;

import java.util.ArrayList;

public class WeatherResponse {

    ArrayList<WeatherInfo> weather;
    MainInfo main;
    WindInfo wind;
    CloudInfo clouds;

    public WeatherResponse() {
    }

    public ArrayList<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<WeatherInfo> weather) {
        this.weather = weather;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public WindInfo getWind() {
        return wind;
    }

    public void setWind(WindInfo wind) {
        this.wind = wind;
    }

    public CloudInfo getClouds() {
        return clouds;
    }

    public void setClouds(CloudInfo clouds) {
        this.clouds = clouds;
    }

    static class WeatherInfo {
        String description, icon, main;
        public WeatherInfo() {
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

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }
    }

    static class MainInfo{
        Double temp, temp_min, temp_max, pressure, humidity;

        public MainInfo() {
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
    }

    static class WindInfo{
        Double speed, deg;

        public WindInfo() {
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
    }

    static class CloudInfo{
        Double all;

        public CloudInfo() {
        }

        public Double getAll() {
            return all;
        }

        public void setAll(Double all) {
            this.all = all;
        }
    }
}
