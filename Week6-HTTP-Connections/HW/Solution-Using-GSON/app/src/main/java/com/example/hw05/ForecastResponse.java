package com.example.hw05;

import java.util.ArrayList;

public class ForecastResponse {
    ArrayList<ForecastWeatherResponse> list;

    public ForecastResponse() {
    }

    public ArrayList<ForecastWeatherResponse> getList() {
        return list;
    }

    public void setList(ArrayList<ForecastWeatherResponse> list) {
        this.list = list;
    }

    static class ForecastWeatherResponse extends WeatherResponse {
        String dt_txt;

        public ForecastWeatherResponse() {
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }
    }

    /*
          {
            "dt": 1676419200,
            "main": {
                "temp": 63.03,
                "feels_like": 61.05,
                "temp_min": 54.97,
                "temp_max": 63.03,
                "pressure": 1020,
                "sea_level": 1020,
                "grnd_level": 993,
                "humidity": 43,
                "temp_kf": 4.48
            },
            "weather": [
                {
                    "id": 803,
                    "main": "Clouds",
                    "description": "broken clouds",
                    "icon": "04n"
                }
            ],
            "clouds": {
                "all": 77
            },
            "wind": {
                "speed": 5.46,
                "deg": 160,
                "gust": 12.91
            },
            "visibility": 10000,
            "pop": 0,
            "sys": {
                "pod": "n"
            },
            "dt_txt": "2023-02-15 00:00:00"
        },
     */

}
