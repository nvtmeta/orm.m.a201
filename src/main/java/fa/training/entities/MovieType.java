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

    @Column(name = "mt_description", columnDefinition = "varchar(255)", nullable = false)
    private String mtDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
}
