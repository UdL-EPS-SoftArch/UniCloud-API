package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyRatingStepDefs {
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


    @And("The rating with id {int} was modified")
    public void theRatingWithIdWasModified(int arg0) {
    }

    @And("The rating with id {int} was not modified")
    public void theRatingWithIdWasNotModified(int arg0) {Assert.assertEquals(0, ratingRepository.count());}
}
