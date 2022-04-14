package cat.udl.eps.softarch.unicloud.handler;


import cat.udl.eps.softarch.unicloud.domain.*;
import cat.udl.eps.softarch.unicloud.exception.UnauthorizedException;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.cache.SpringCacheBasedUserCache;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

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

    @HandleBeforeDelete
    public void handleRatingBeforeDelete(Rating rating) {

        System.out.println("ENTRO AL HANDLER BEFORE DELETE");
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser instanceof Admin){
        }else if(currentUser instanceof Student){
            if (!currentUser.getUsername().equals(rating.getAuthor().getUsername())){
                System.out.println("NOT EQUALS STUDENTS");
                throw new UnauthorizedException();}
        }

    }


    @HandleAfterCreate
    public void handleRatingAfterCreate(Rating rating) {
        this.ratingRepository.save(rating);
    }

}
