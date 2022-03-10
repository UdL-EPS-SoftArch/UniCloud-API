package cat.udl.eps.softarch.unicloud.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.bytebuddy.asm.Advice;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class University extends UriEntity<Long> {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    private String acronym;
    @NotBlank
    private String country;
    @NotBlank
    private String city;

}
