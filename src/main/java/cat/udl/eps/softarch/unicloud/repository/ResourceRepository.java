package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Degree;
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

    List<Resource> findBySubjectsName(@Param("subject") String subject);
    List<Resource> findBySubjectsNameContaining(@Param("subject") String subject);

    /*List<Resource> findBySubjectsDegreeName(@Param("degree") String degree);
    List<Resource> findBySubjectsDegreeNameContaining(@Param("degree") String degree);

    List<Resource> findBySubjectsDegreeUniversityName(@Param("university") String university);
    List<Resource> findBySubjectsDegreeUniversityNameContaining(@Param("university") String university);*/
}
