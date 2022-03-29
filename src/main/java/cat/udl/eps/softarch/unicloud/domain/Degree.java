package cat.udl.eps.softarch.unicloud.domain;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Degree extends UriEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String faculty;

    @ManyToOne()
    @JsonIdentityReference(alwaysAsId = true)
    @NotNull
    private University university;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Degree degree = (Degree) o;

        if (!Objects.equals(name, degree.name)) return false;
        if (!Objects.equals(faculty, degree.faculty)) return false;
        return Objects.equals(university, degree.university);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, faculty, university);
    }
}
