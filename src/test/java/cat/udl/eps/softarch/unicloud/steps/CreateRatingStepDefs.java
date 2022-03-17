package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.Student;
import cat.udl.eps.softarch.unicloud.domain.User;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateRatingStepDefs {
    String newResourcesUri;
    final StepDefs stepDefs;
    final RatingRepository ratingRepository;
    final ResourceRepository resourceRepository;
    public static String id;

    public CreateRatingStepDefs(StepDefs stepDefs, RatingRepository ratingRepository, ResourceRepository resourceRepository) {
        this.stepDefs = stepDefs;
        this.ratingRepository = ratingRepository;
        this.resourceRepository = resourceRepository;
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



    @And("A new rating has been created as {string}")
    public void aNewRatingHasBeenCreatedAs(String author) throws Throwable {
        id = stepDefs.result.andReturn().getResponse().getHeader("Location");
        assert id != null;
        stepDefs.result = stepDefs.mockMvc.perform(
                        get(id)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isOk());

        JSONObject response = new JSONObject(stepDefs.result.andReturn().getResponse().getContentAsString());
        String authorByHref = response.getJSONObject("_links").getJSONObject("author").getString("href");

        assertProvidedByEqualsToExpectedUser(authorByHref, author);
    }

    public void assertProvidedByEqualsToExpectedUser(String authorByHref, String author) throws Throwable{
        stepDefs.mockMvc.perform(
                        get(authorByHref)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(author)));
    }

    @When("I register a new rating with rating {int} and comment {string} referenced to resource with name {string}")
    public void iRegisterANewRatingWithRatingAndCommentReferencedToResourceWithName(int arg0, String arg1, String arg2) throws Exception {

        Rating rating = new Rating();
        rating.setRating(new BigDecimal(arg0));
        rating.setComment(arg1);

        List<Resource> resource = this.resourceRepository.findByName(arg2);
        if(resource.size()!=0)
            rating.setResourceRated(resource.get(0));


        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/ratings")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(rating))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        newResourcesUri = stepDefs.result.andReturn().getResponse().getHeader("Location");


    }
}
