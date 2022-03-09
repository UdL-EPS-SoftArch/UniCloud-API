package cat.udl.eps.softarch.unicloud.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Resource extends UriEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Length(min = 1, max = 100)
    private String name;

    @Length(max = 2000)
    private String description;

    @NotNull
    @ManyToOne()
    @JsonIdentityReference(alwaysAsId = true)
    private Student owner;

    @NotNull
    @ManyToMany
    @JsonIdentityReference(alwaysAsId = true)
    private List<Subject> subjects;

}
