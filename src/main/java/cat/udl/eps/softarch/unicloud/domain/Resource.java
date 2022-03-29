package cat.udl.eps.softarch.unicloud.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(max = 5 * 1024 * 1024) //5 MB
    private String file;

    @NotNull
    private ResourceType resourceType;

    @ManyToOne()
    @JsonIdentityReference(alwaysAsId = true)
    private Student owner;

    @ManyToMany
    @JsonIdentityReference(alwaysAsId = true)
    private List<Subject> subjects;

    public enum ResourceType {
        assignment ("ASSIGNMENT"),
        test ("TEST"),
        note ("NOTE");

        private String type;

        ResourceType(String type) {
            this.type = type;
        }

        public static ResourceType fromString(String type) {
            for(ResourceType rt : ResourceType.values()){
                if(rt.type.equalsIgnoreCase(type))
                    return rt;
            }
            return null;
        }
    }

}
