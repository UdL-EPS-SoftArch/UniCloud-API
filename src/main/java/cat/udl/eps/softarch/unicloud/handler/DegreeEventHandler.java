package cat.udl.eps.softarch.unicloud.handler;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.exception.ConflictException;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;


@Component
@RepositoryEventHandler
public class DegreeEventHandler {

    final DegreeRepository degreeRepository;

    final UniversityRepository universityRepository;

    public DegreeEventHandler(DegreeRepository degreeRepository, UniversityRepository universityRepository){
        this.degreeRepository = degreeRepository;
        this.universityRepository = universityRepository;
    }

    @HandleBeforeCreate
    public void handleDegreeBeforeCreate(Degree degree){
        for (Degree d : degreeRepository.findAll()) {
            if(d.equals(degree))
                throw new ConflictException();

        }
    }

}
