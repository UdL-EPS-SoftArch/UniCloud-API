package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.domain.University;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DegreeRepository extends PagingAndSortingRepository<Degree, Long> {

    List<Degree> findByFaculty(String nameFaculty);

    List<Degree> findByFacultyContaining(String nameFaculty);

    List<Degree> findByName(String nameDegree);

    List<Degree> findByUniversityNameContaining(String nameUni);

    List<Degree> findByNameContaining(String nameDegree);

    List<Degree> findByUniversity(University university);
}
