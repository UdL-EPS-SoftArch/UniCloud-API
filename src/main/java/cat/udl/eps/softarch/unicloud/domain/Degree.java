package cat.udl.eps.softarch.unicloud.domain;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

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
    private University university;

    @Override
    public Long getId() {
        return id;
    }
}
