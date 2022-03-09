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

    List<Assignment> findBySubjects(@Param("subject_names") List<Subject> subject_names);
    List<Assignment> findBySubjectsContaining(@Param("subject_names") List<Subject> subject_names);
}
