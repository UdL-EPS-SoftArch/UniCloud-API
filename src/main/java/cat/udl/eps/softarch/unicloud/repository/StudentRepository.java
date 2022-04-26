package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Admin;
import cat.udl.eps.softarch.unicloud.domain.Student;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;

public interface StudentRepository extends PagingAndSortingRepository<Student, String> {
    List<Student> findByUsernameContaining(@Param("text") String text);
    Student findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
