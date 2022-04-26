package cat.udl.eps.softarch.unicloud.handler;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.User;
import cat.udl.eps.softarch.unicloud.exception.ForbiddenException;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class ResourceEventHandler {

    final ResourceRepository resourceRepository;

    final RatingRepository ratingRepository;

    public ResourceEventHandler(ResourceRepository resourceRepository, RatingRepository ratingRepository) {
        this.resourceRepository = resourceRepository;
        this.ratingRepository = ratingRepository;
    }

    @HandleBeforeDelete
    public void handleRatingsAfterDelete(Resource resource) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean sameId = user.getId().equals(resource.getOwner().getId());
        if (!sameId) throw new ForbiddenException();

        for (Rating rating: ratingRepository.findAll()) {
            if (rating.getResourceRated().getName().equals(resource.getName()))
                ratingRepository.deleteById(rating.getId());
        }
    }

    @HandleBeforeSave
    public void handleUserAuthenticationBeforeModification(Resource resource) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean sameId = user.getId().equals(resource.getOwner().getId());
        if (!sameId) throw new ForbiddenException();
    }
}
