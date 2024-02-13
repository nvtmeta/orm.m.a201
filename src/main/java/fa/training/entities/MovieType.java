package fa.training.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movie_type")
@Table(name = "movie_type", schema = "MovieTheater")
public class MovieType {

    @EmbeddedId
    private MovieTypeId id;

    @Column(name = "mt_description", columnDefinition = "varchar(255)", nullable = false)
    private String mtDescription;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", nullable = false, insertable = false, updatable = false)
    @MapsId("movieId")
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", nullable = false, insertable = false, updatable = false)
    @MapsId("typeId")
    private Type type;


}
