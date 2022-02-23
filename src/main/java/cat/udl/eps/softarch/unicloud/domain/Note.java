package cat.udl.eps.softarch.unicloud.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Note extends Resource {
    private String file;
}
