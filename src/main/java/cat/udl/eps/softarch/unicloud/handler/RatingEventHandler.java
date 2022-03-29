package cat.udl.eps.softarch.unicloud.handler;


import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.Student;
import cat.udl.eps.softarch.unicloud.domain.User;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RepositoryEventHandler
public class RatingEventHandler {

    final Logger logger = LoggerFactory.getLogger(Rating.class);

    final RatingRepository ratingRepository;


    public RatingEventHandler(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @HandleBeforeCreate
    public void handleRatingPreCreate(Rating rating) {
        Student currentUser = (Student) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        rating.setAuthor(currentUser);


    }

    @HandleAfterCreate
    public void handleRatingAfterCreate(Rating rating) {
        this.ratingRepository.save(rating);
    }


}
