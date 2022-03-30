package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.an.E;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRatingStepDefs {
    String newResourceUri;
    final StepDefs stepDefs;
    final RatingRepository ratingRepository;
    final ResourceRepository resourceRepository;

    DeleteRatingStepDefs(StepDefs stepDefs, RatingRepository ratingRepository, ResourceRepository resourceRepository) {
        this.stepDefs = stepDefs;
        this.ratingRepository = ratingRepository;
        this.resourceRepository = resourceRepository;
    }

    @When("I delete a Rating with id {int}")
    public void iDeleteARatingWithId(int arg0) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(delete("/rating/{id}", arg0).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("The rating with id {int} does not exist")
    public void theRatingWithIdDoesNotExist(int arg0) {
        boolean existsrating = ratingRepository.existsById((long) arg0);
        assert !existsrating;
    }


    @And("The rating with id {int} was not deleted")
    public void theRatingWithIdWasNotDeleted(int arg0) {
        boolean existsrating = ratingRepository.existsById((long) arg0);
        assert !existsrating;
    }

    @When("I delete the last created rating")
    public void iDeleteTheLastCreatedRating() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                        delete(newResourceUri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The rating was deleted")
    public void theRatingWasDeleted() {
        long total_ratings = ratingRepository.count();
        assert total_ratings == 0;
    }

    @And("I register rating with rating {int} and comment {string} and resource with name {string}")
    public void iRegisterRatingWithRatingAndCommentAndResourceWithName(int arg0, String arg1, String arg2) throws  Exception{
        Rating rating = new Rating();
        rating.setRating(new BigDecimal(arg0));
        rating.setComment(arg1);
        Resource resource = resourceRepository.findByName(arg2).get(0);
        rating.setResourceRated(resource);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/ratings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(rating))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");

    }
}
