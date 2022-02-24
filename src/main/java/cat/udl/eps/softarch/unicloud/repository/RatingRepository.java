package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.domain.University;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;


public interface RatingRepository extends PagingAndSortingRepository<Rating, Long> {
    //List<Rating> findByMinimumRating(BigDecimal minRating);
}
