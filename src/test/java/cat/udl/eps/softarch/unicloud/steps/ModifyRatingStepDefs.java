package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyRatingStepDefs {
    String newResourceUri;
    final StepDefs stepDefs;
    final RatingRepository ratingRepository;
    public static String id;

    ModifyRatingStepDefs(StepDefs stepDefs, RatingRepository ratingRepository){
        this.stepDefs = stepDefs;
        this.ratingRepository = ratingRepository;
    }

    @When("I modify a Rating with id {int}")
    public void iModifyARatingWithId(int id) throws Exception {
        Rating rating = new Rating();
        rating.setRating(new BigDecimal(id));

        stepDefs.result = stepDefs.mockMvc.perform(patch("/rating/{id}",id).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @When("I modify the last rating created changing the comment to {string}")
    public void iModifyTheLastRatingCreatedChangingTheCommentTo(String comment) throws Exception {
        JSONObject modifyData = new JSONObject();
        modifyData.put("comment", comment);

        stepDefs.result = stepDefs.mockMvc.perform(patch(newResourceUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyData.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I modify the last rating created changing the rating to {int}")
    public void iModifyTheLastRatingCreatedChangingTheRatingTo(int newRating) throws Exception {
        JSONObject modifyData = new JSONObject();
        modifyData.put("rating", newRating);

        stepDefs.result = stepDefs.mockMvc.perform(patch(newResourceUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyData.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @Given("I add a new rating with rating {int} and comment {string}")
    public void iAddANewRatingWithRatingAndComment(int newRating, String comment) throws Exception {
        Rating rating = new Rating();
        rating.setRating(new BigDecimal(newRating));
        rating.setComment(comment);

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
