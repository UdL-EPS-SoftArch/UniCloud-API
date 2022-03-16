package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Note;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.Subject;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

    List<Note> findByName(@Param("name") String name);
    List<Note> findByNameContaining(@Param("name") String name);

    /*List<Note> findBySubjectsName(@Param("subject") String subject);
    List<Note> findBySubjectsNameContaining(@Param("subject") String subject);

    List<Note> findBySubjectsDegreeName(@Param("degree") String degree);
    List<Note> findBySubjectsDegreeNameContaining(@Param("degree") String degree);

    List<Note> findBySubjectsDegreeUniversityName(@Param("university") String university);
    List<Note> findBySubjectsDegreeUniversityNameContaining(@Param("university") String university);*/
}
