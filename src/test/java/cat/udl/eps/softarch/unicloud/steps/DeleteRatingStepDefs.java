package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Rating;
import cat.udl.eps.softarch.unicloud.repository.RatingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRatingStepDefs {
    final StepDefs stepDefs;
    final RatingRepository ratingRepository;

    DeleteRatingStepDefs(StepDefs stepDefs, RatingRepository universityRepository) {
        this.stepDefs = stepDefs;
        this.ratingRepository = universityRepository;
    }

    @When("I remove a Rating with id {int}")
    public void iRemoveARatingWithId(int arg0) throws Exception {
        Rating rating = ratingRepository.findById((long) arg0).get();
        assert rating.getId() != null;

    }
}
