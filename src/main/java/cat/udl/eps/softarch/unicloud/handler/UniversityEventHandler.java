package cat.udl.eps.softarch.unicloud.handler;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class UniversityEventHandler {
    final UniversityRepository universityRepository;

    final DegreeRepository degreeRepository;
    public UniversityEventHandler(UniversityRepository universityRepository,DegreeRepository degreeRepository){
        this.universityRepository = universityRepository;
        this.degreeRepository = degreeRepository;
    }

    @HandleBeforeDelete
    public void handleDegreeBeforeDelete(University university) {
        for (Degree degree: degreeRepository.findAll()) {
            if (degree.getUniversity().getId()!=null && degree.getUniversity().getId().equals(university.getId()))
                degreeRepository.deleteById(degree.getId());
        }
    }
}
