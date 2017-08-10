package com.ahharryhughes.GoToTheMovies.Controllers;

import com.ahharryhughes.GoToTheMovies.Models.Movie;
import com.ahharryhughes.GoToTheMovies.Models.ResultsPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ahhughes8 on 8/2/17.
 */

@Controller
public class MovieControllers {

    private String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=be2a38521a7859c95e2d73c48786e4bb";

    @RequestMapping("/home")
    public String getHome() {
        return "/home";
    }

    @RequestMapping("/now-playing")
    public String getNowPlaying(Model model) {

        try {
            model.addAttribute("movies", getMovies(url).results);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "/now-playing";
    }

    @RequestMapping("/medium-popular-long-name")
    public String getMediumPopularLongName(Model model) {
        List<Movie> movies = new ArrayList<>();

        try {
            movies = getMovies(url).results;
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Movie> filteredMovies = movies.stream()
                .filter(movie -> movie.popularity >= 30 && movie.popularity <= 80)
                .filter(movie -> movie.title.length() >= 10)
                .collect(Collectors.toList());

        model.addAttribute("movies", filteredMovies);

        return "/medium-popular-long-name";
    }

    private static ResultsPage getMovies(String route) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResultsPage results = restTemplate.getForObject(route, ResultsPage.class);

        return results;
    }

}



