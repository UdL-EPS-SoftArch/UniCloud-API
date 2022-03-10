package cat.udl.eps.softarch.unicloud.repository;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.University;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;


public interface RatingRepository extends PagingAndSortingRepository<Rating, Long> {
    List<Rating> findByRatingGreaterThan(BigDecimal minRating);
    //List<Rating> findById(long id);
    //List<Rating> findByMinMax (BigDecimal minRating, BigDecimal maxRating);
    //List<Rating> findbyMax (BigDecimal maxRating);
    //List<Rating> findbyAuthor (String name);
    //List<Rating> findbyResource (Resource resource);

}
