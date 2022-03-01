package cat.udl.eps.softarch.unicloud.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;



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

}





