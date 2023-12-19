package agusev.parser.parser;

import agusev.parser.database.entity.Movie;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class MovieParser {

    private static final MovieParser INSTANCE = new MovieParser();
    private static final String URL = "https://www.kinoafisha.info/rating/movies";

    public static synchronized MovieParser getINSTANCE() {
        return INSTANCE;
    }

    public List<Movie> parseMovie() {
        List<Movie> movies = new ArrayList<>();
        StringBuilder dynamicURL = new StringBuilder(URL);
        int currentPage = 0;

        while (true) {
            try {
                if (currentPage > 0) {
                    dynamicURL.append("?page=").append(currentPage);
                }

                Document document = Jsoup.connect(dynamicURL.toString()).get();
                Elements ratingElements = document.select(".movieItem_itemRating.miniRating.miniRating-good");
                Elements movieElements = document.select(".movieItem_info");

                if (movieElements.isEmpty() || ratingElements.isEmpty()) {
                    break;
                }

                for (Element element : movieElements) {
                    String title = element.select("a.movieItem_title").text();
                    String position = element.select("span.movieItem_position").text();
                    String genre = element.select("span.movieItem_genres").text();
                    String[] yearAndCountry = element.select("span.movieItem_year").text().split(",");
                    String rating = ratingElements.get(currentPage).text();

                    movies.add(Movie.builder()
                            .title(title)
                            .position(Integer.parseInt(position))
                            .genre(genre)
                            .yearOfCreation(Integer.parseInt(yearAndCountry[0]))
                            .country(yearAndCountry.length > 1 ? yearAndCountry[1] : null)
                            .rating(Float.parseFloat(rating))
                            .build());
                }

                dynamicURL = new StringBuilder(URL);
                currentPage++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return movies;
    }
}
