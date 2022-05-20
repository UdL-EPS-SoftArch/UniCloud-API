package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.domain.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends PagingAndSortingRepository<Subject, Long> {

    List<Subject> findByName(@Param("nameSubject") String name);
    List<Subject> findByCourse(@Param("courseSubject") Integer course);
    List<Subject> findByOptional(@Param("optionSubject") Boolean optional);
    List<Subject> findByDegrees(@Param("degreeSubject")Degree degree);
}
