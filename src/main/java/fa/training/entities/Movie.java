package fa.training.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movie")
@Table(name = "movie", schema = "MovieTheater")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(name = "actor", columnDefinition = "varchar(255)", nullable = false)
    private String actor;

    @Column(name = "content", columnDefinition = "varchar(1000)", nullable = false)
    private String content;

    @Column(name = "director", columnDefinition = "varchar(255)", nullable = false)
    private String director;

    @Column(name = "duration", precision = 5, scale = 2, nullable = false)
    private BigDecimal duration;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @Column(name = "movie_production_company", columnDefinition = "varchar(255)", nullable = false)
    private String movieProductionCompany;

    @Column(name = "version", columnDefinition = "varchar(255)", nullable = false)
    private String version;

    @Column(name = "movie_name_eng", columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String movieNameEng;

    @Column(name = "movie_name_vn", columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String movieNameVn;

    @Column(name = "large_image", columnDefinition = "varchar(255)", nullable = false)
    private String largeImage;

    @Column(name = "small_image", columnDefinition = "varchar(255)", nullable = false)
    private String smallImage;

    @OneToMany(mappedBy = "movie_id")
    private Set<MovieType> movieType;

}
