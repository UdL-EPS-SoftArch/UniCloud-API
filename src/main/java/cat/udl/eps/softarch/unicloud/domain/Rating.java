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

public class Rating extends UriEntity<Long>{

        @GeneratedValue(strategy = GenerationType.AUTO)
        @Id
        private Long id;

        @ManyToOne
        @NotNull
        @JsonIdentityReference(alwaysAsId = true)
        private Student author;


        @ManyToOne
        @NotNull
        @JsonIdentityReference(alwaysAsId = true)
        private Resource resourceRated;

        @NotNull
        @Max(10)
        @Min(0)
        private BigDecimal rating;

        @NotBlank
        private String comment;


}
