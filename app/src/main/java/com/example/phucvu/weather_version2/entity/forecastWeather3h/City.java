package com.example.phucvu.weather_version2.entity.forecastWeather3h;

/**
 * Created by phucvu on 11/10/16.
 */

public class City {
    private int id;
    private String name;
    private Coord coord;
    private String country;
    private int population;
    private Sys sys;

    /**
     *
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     *
     * @param coord
     *     The coord
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     *
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     *     The population
     */
    public int getPopulation() {
        return population;
    }

    /**
     *
     * @param population
     *     The population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     *
     * @return
     *     The sys
     */
    public Sys getSys() {
        return sys;
    }

    /**
     *
     * @param sys
     *     The sys
     */
    public void setSys(Sys sys) {
        this.sys = sys;
    }

}
