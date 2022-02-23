package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Resource;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResourceRepository extends PagingAndSortingRepository<Resource, Long> {
}
