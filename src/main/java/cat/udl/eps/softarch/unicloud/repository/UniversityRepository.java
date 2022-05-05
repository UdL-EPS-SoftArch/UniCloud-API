package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityRepository extends PagingAndSortingRepository<University, Long> {
    List<University> findByName(String name);
    List<University> findByCountry(String country);
    List<University> findByCity(String city);
    List<University> findByAcronym(String acronym);
    List<University> findByNameContaining(String name);
    Page<University> findByNameContainingOrAcronymContainingOrCountryContainingOrCityContaining(String name, String acronym, String country, String city, Pageable pageable);
}
