package fa.training.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "type")
@Table(name = "type", schema = "MovieTheater")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer id;

    @Column(name = "type_name", columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String typeName;

    @Column(name = "type_description", columnDefinition = "varchar(255)", nullable = false)
    private String typeDescription;

    @OneToMany(mappedBy = "type")
    private Set<MovieType> movieType;

}
