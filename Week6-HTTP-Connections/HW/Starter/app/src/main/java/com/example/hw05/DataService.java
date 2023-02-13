package com.example.hw05;

import java.io.Serializable;
import java.util.ArrayList;

public class DataService {

    static public final ArrayList<City> cities = new ArrayList<City>(){{
        add(new City("US","Charlotte", 35.227085, -80.843124));
        add(new City("US","Chicago", 41.878113, -87.629799));
        add(new City("US","New York", 40.712776, -74.005974));
        add(new City("US","Miami", 25.775080, -80.194702));
        add(new City("US","San Francisco", 37.7577627,-122.4726194));
        add(new City("US","Baltimore", 39.2843941,-76.8907338));
        add(new City("US","Houston", 29.816881,-95.6821564));
        add(new City("UK","London", 51.5285578,-0.2420223));
        add(new City("UK","Bristol", 51.4684051,-2.7311412));
        add(new City("UK","Cambridge", 52.1985335,-0.1502405));
        add(new City("UK","Liverpool", 53.4118724,-3.1862854));
        add(new City("AE","Abu Dhabi", 24.3865717,54.277754));
        add(new City("AE","Dubai", 25.0757582,54.9466216));
        add(new City("AE","Sharjah", 5.3196104,55.405013));
        add(new City("JP","Tokyo", 35.5040298,138.6460612));
        add(new City("JP","Kyoto", 35.0981152,135.5784441));
        add(new City("JP","Hashimoto", 34.3128568,135.5334912));
        add(new City("JP","Osaka", 34.677578,135.4158263));
    }};

    static class City implements Serializable {
        private String country;
        private String city;
        private double lat, lon;

        public City(String country, String city, double lat, double lon) {
            this.country = country;
            this.city = city;
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return city + ", " + country;
        }
    }
}
