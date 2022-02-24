package cat.udl.eps.softarch.unicloud.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)

public class Rating {

        @GeneratedValue(strategy = GenerationType.AUTO)
        @Id
        private Long id;

        @NotBlank
        @Column(unique = true)
        private BigDecimal rating;

        @NotBlank
        private String comment;

}
