package com.example.hw05;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Forecast {
    String dt_txt, description, icon;
    Double temp_min, temp_max, temp, humidity;

    public Forecast(){

    }

    public Forecast(ForecastResponse.ForecastWeatherResponse res){
        this.temp = res.getMain().getTemp();
        this.temp_min = res.getMain().getTemp_min();
        this.temp_max = res.getMain().getTemp_max();
        this.humidity = res.getMain().getHumidity();

        if(res.getWeather().size() > 0){
            this.description = res.getWeather().get(0).getDescription();
            this.icon = res.getWeather().get(0).getIcon();
        }
        this.dt_txt = res.getDt_txt();
    }

    public Forecast(JSONObject jsonObject) throws JSONException {
        JSONObject main = jsonObject.getJSONObject("main");
        this.temp = main.getDouble("temp");
        this.temp_min = main.getDouble("temp_min");
        this.temp_max = main.getDouble("temp_max");
        this.humidity = main.getDouble("humidity");


        JSONArray weather = jsonObject.getJSONArray("weather");
        if(weather.length() > 0){
            JSONObject firstWeather = weather.getJSONObject(0);
            this.description = firstWeather.getString("description");
            this.icon = firstWeather.getString("icon");
        }

        dt_txt = jsonObject.getString("dt_txt");
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
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

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getIconUrl(){
        return "https://openweathermap.org/img/wn/" + this.icon + "@2x.png";
    }

}
