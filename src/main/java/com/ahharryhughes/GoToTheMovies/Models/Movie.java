package com.ahharryhughes.GoToTheMovies.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by ahhughes8 on 8/2/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    public String title;
    public String poster_path;
    public String overview;
    public double popularity;

}
