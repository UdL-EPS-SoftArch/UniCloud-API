package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DegreeRepository extends PagingAndSortingRepository<Degree, Long> {

    List<Degree> findByFaculty(@Param("nameFaculty") String nameFaculty);

    List<Degree> findByName(@Param("nameDegree") String nameDegree);
}
