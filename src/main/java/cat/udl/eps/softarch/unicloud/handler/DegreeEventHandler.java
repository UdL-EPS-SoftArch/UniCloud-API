package cat.udl.eps.softarch.unicloud.handler;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.exception.ConflictException;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler
public class DegreeEventHandler {

    final DegreeRepository degreeRepository;

    public DegreeEventHandler(DegreeRepository degreeRepository){
        this.degreeRepository = degreeRepository;
    }

    @HandleBeforeCreate
    public void handleDegreeBeforeCreate(Degree degree){
        for (Degree d : degreeRepository.findAll()) {
            if(d.equals(degree)){
                throw new ConflictException();
            }
        }
    }
}
