package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.Test;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepository extends PagingAndSortingRepository<Test, Long> {

    List<Test> findByName(@Param("name") String name);
    List<Test> findByNameContaining(@Param("name") String name);

    List<Test> findBySubjects(@Param("subject_name") String subject_name);
    List<Test> findBySubjectsContaining(@Param("subject_name") String subject_name);
}
