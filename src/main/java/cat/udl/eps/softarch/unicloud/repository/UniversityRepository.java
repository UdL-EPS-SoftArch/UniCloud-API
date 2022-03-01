package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.University;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UniversityRepository extends PagingAndSortingRepository<University, Long> {
    List<University> findByName(String name);
    List<University> findByCountry(String country);
    List<University> findByCity(String city);
}