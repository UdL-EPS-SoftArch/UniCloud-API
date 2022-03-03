package cat.udl.eps.softarch.unicloud.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)

public class Rating {

        @GeneratedValue(strategy = GenerationType.AUTO)
        @Id
        private Long id;

        @NotNull
        private BigDecimal rating;

        @NotBlank
        private String comment;

}
