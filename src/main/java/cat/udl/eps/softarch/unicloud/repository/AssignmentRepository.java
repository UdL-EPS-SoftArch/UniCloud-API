package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Assignment;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends PagingAndSortingRepository<Assignment, Long> {

    List<Assignment> findByName(@Param("name") String name);
    List<Assignment> findByNameContaining(@Param("name") String name);
}
