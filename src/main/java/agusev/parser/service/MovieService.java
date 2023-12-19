package agusev.parser.service;

import agusev.parser.database.entity.Movie;
import agusev.parser.database.repository.MovieRepository;
import agusev.parser.parser.MovieParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void parseAndSave() {
        List<Movie> movies = MovieParser.getINSTANCE().parseMovie();

        for (Movie movie : movies) {
            Optional<Movie> maybeMovie = movieRepository.findByTitle(movie.getTitle());
            if (maybeMovie.isEmpty()) {
                movieRepository.saveAndFlush(movie);
            }
        }
    }
}
