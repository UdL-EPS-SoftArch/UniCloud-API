package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, Long> {

    List<Resource> findByName(@Param("name") String name);
    List<Resource> findByNameContaining(@Param("name") String name);

    List<Resource> findBySubjects(@Param("subject_name") String subject_name);
    List<Resource> findBySubjectsContaining(@Param("subject_name") String subject_name);
}
