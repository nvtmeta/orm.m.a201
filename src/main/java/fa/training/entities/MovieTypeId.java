package fa.training.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieTypeId implements Serializable {

    @Column(name = "movie_id")
    private Integer movieId;

    @Column(name = "type_id")
    private Integer typeId;

}
