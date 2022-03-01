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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteRatingStepDefs {
    final StepDefs stepDefs;
    final RatingRepository ratingRepository;

    DeleteRatingStepDefs(StepDefs stepDefs, RatingRepository ratingRepository) {
        this.stepDefs = stepDefs;
        this.ratingRepository = ratingRepository;
    }

    @When("I delete a Rating with id {int}")
    public void iDeleteARatingWithId(int arg0) {
    }

    @And("A rating made by student {string} does not exist")
    public void aRatingMadeByStudentDoesNotExist(String student) {
        Assert.assertEquals(0,ratingRepository.findbyAuthor(student).size());
    }
}
