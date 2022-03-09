package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Note;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends PagingAndSortingRepository<Note, Long> {

    List<Note> findByName(@Param("name") String name);
    List<Note> findByNameContaining(@Param("name") String name);
}
