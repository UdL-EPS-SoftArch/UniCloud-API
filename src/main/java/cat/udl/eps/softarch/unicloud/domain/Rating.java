package cat.udl.eps.softarch.unicloud.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

        @ManyToOne
        @JsonIdentityReference(alwaysAsId = true)
        private Student author;


        @ManyToOne
        private Resource resourceRated;

        @NotNull
        @Max(10)
        @Min(0)
        private BigDecimal rating;

        @NotBlank
        private String comment;


}
