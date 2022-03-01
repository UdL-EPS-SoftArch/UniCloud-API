package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateRatingStepDefs {
    String newResourcesUri;
    final StepDefs stepDefs;
    final RatingRepository ratingRepository;
    public static String id;

    public CreateRatingStepDefs(StepDefs stepDefs, RatingRepository ratingRepository) {
        this.stepDefs = stepDefs;
        this.ratingRepository = ratingRepository;
    }


    @When("I register a new rating with rating {int} and comment {string}")
    public void iRegisterANewRatingWithRatingAndComment(int arg0, String arg1) throws Exception {
        Rating rating = new Rating();
        rating.setRating(new BigDecimal(arg0));
        rating.setComment(arg1);


        stepDefs.result = stepDefs.mockMvc.perform(
                post("/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(rating))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print());
        newResourcesUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("A new rating has not been created")
    public void aNewRatingHasNotBeenCreated() {
        Assert.assertEquals(0, ratingRepository.count());
    }


    @And("A new rating has been created")
    public void aNewRatingHasBeenCreated() throws Exception {

        id = stepDefs.result.andReturn().getResponse().getHeader("Location");
        assert id != null;
        stepDefs.result = stepDefs.mockMvc.perform(
                get(id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
