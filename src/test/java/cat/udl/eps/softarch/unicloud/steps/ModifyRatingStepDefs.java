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
    public void iModifyARatingWithId(int arg0) throws Exception {
        Rating rating = new Rating();
        rating.setRating(new BigDecimal(arg0));

        stepDefs.result = stepDefs.mockMvc.perform(delete("/rating/{id}",arg0).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @When("I modify the last rating created changing the comment to {string}")
    public void iModifyTheLastRatingCreatedChangingTheCommentTo(String arg0) throws Exception {
        JSONObject modifyData = new JSONObject();
        modifyData.put("comment", arg0);

        stepDefs.result = stepDefs.mockMvc.perform(patch(newResourceUri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyData.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Given("I add a new rating with rating {int} and comment {string}")
    public void iAddANewRatingWithRatingAndComment(int arg0, String arg1) throws Exception {
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
        newResourceUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }
}
