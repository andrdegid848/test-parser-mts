package agusev.parser.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movie", schema = "public")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Integer position;

    private String genre;

    @Column(name = "year")
    private Integer yearOfCreation;

    private String country;

    private Float rating;
}
