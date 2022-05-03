package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Admin;
import cat.udl.eps.softarch.unicloud.domain.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends PagingAndSortingRepository<Admin, String> {
    List<Admin> findByUsernameContaining(@Param("text") String text);
    Admin findByEmailAndPassword(@Param("email") String email,@Param("password") String password);
}
