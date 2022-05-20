package cat.udl.eps.softarch.unicloud.handler;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.domain.Subject;
import cat.udl.eps.softarch.unicloud.exception.ConflictException;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RepositoryEventHandler
public class DegreeEventHandler {

    final DegreeRepository degreeRepository;

    final UniversityRepository universityRepository;

    final SubjectRepository subjectRepository;

    public DegreeEventHandler(DegreeRepository degreeRepository, UniversityRepository universityRepository, SubjectRepository subjectRepository){
        this.degreeRepository = degreeRepository;
        this.universityRepository = universityRepository;
        this.subjectRepository = subjectRepository;
    }

    @HandleBeforeCreate
    public void handleDegreeBeforeCreate(Degree degree){
        for (Degree d : degreeRepository.findAll()) {
            if(d.equals(degree))
                throw new ConflictException();

        }
    }

    @HandleBeforeDelete
    public void handleDegreeBeforeDelete(Degree degree){
        for(Subject subject: subjectRepository.findByDegrees(degree)){
            List<Degree> degrees = subject.getDegrees();
            degrees.remove(degree);
            if(degrees.isEmpty()){
                subjectRepository.delete(subject);
            }
        }

    }

}
