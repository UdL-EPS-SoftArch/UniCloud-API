package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Assignment;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AssignmentRepository extends PagingAndSortingRepository<Assignment, Long> {

    List<Assignment> findByName(@Param("name") String name);
    List<Assignment> findByNameContaining(@Param("name") String name);

    List<Assignment> findBySubjectsName(@Param("subject") String subject);
    List<Assignment> findBySubjectsNameContaining(@Param("subject") String subject);

    List<Assignment> findBySubjectsDegreeName(@Param("degree") String degree);
    List<Assignment> findBySubjectsDegreeNameContaining(@Param("degree") String degree);

    List<Assignment> findBySubjectsDegreeUniversityName(@Param("university") String university);
    List<Assignment> findBySubjectsDegreeUniversityNameContaining(@Param("university") String university);
}
