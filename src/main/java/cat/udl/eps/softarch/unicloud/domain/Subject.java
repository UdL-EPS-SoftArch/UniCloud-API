package cat.udl.eps.softarch.unicloud.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;



@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Subject extends UriEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @Min(value = 1)
    @Max(value = 5)
    private Integer course;

    private Boolean optional = true;

    @ManyToMany
    @JsonIdentityReference(alwaysAsId = true)
    private List<Resource> resources;

    @ManyToMany
    @JsonIdentityReference(alwaysAsId = true)
    private List<Degree> degrees;

    /*@ManyToMany
    @JsonIdentityReference(alwaysAsId = true)
    private Admin admin;*/

}





