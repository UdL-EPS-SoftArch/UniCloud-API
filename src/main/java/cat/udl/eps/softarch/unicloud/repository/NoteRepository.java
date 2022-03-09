package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Note;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

    List<Note> findByName(@Param("name") String name);
    List<Note> findByNameContaining(@Param("name") String name);

    List<Note> findBySubjects(@Param("subject_name") String subject_name);
    List<Note> findBySubjectsContaining(@Param("subject_name") String subject_name);
}
