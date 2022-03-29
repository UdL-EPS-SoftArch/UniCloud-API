package cat.udl.eps.softarch.unicloud.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @Override
    public Long getId() {
        return id;
    }

}
