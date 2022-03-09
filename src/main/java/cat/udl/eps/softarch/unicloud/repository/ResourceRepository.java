package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;
import java.util.List;

@RepositoryRestResource
public interface ResourceRepository extends PagingAndSortingRepository<Resource, Long> {

    List<Resource> findByName(@Param("name") String name);
    List<Resource> findByNameContaining(@Param("name") String name);

    List<Resource> findBySubjects(@Param("subject_name") Subject subject_name);
    List<Resource> findBySubjectsContaining(@Param("subject_name") Subject subject_name);
}
