package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.an.E;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRatingStepDefs {
    final StepDefs stepDefs;
    final RatingRepository ratingRepository;

    DeleteRatingStepDefs(StepDefs stepDefs, RatingRepository ratingRepository) {
        this.stepDefs = stepDefs;
        this.ratingRepository = ratingRepository;
    }

    @When("I delete a Rating with id {int}")
    public void iDeleteARatingWithId(int arg0) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(delete("/rating/{id}",arg0).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The rating with id {int} does not exist")
    public void theRatingWithIdDoesNotExist(int arg0) {
        boolean existsrating = ratingRepository.existsById((long)arg0);
        assert !existsrating;
    }
}
