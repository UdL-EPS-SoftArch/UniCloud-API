package cat.udl.eps.softarch.unicloud.steps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;

import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteUniversityStepDefs {
    final StepDefs stepDefs;
    final UniversityRepository universityRepository;

    DeleteUniversityStepDefs(StepDefs stepDefs, UniversityRepository universityRepository) {
        this.stepDefs = stepDefs;
        this.universityRepository = universityRepository;
    }

    @When("I remove a University with name {string}")
    public void removeUniversity(String name) throws Exception {
        University university = universityRepository.findByName(name).get(0);
        assert university.getId() != null;
        stepDefs.result = stepDefs.mockMvc.perform(delete("/universities/"+university.getId().toString()).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
    @When("I remove a non-existent University")
    public void removeNonExistent() throws  Exception {
        stepDefs.result = stepDefs.mockMvc.perform(delete("/universities/666").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
    @Then("The University with name {string} has not been removed")
    public void notRemovedCheck(String name) throws Exception {
        List<University> universities = universityRepository.findByName(name);
        assert universities.size() == 1;
    }

    @And("The University with name {string} has been removed")
    public void removedCheck(String name) throws Exception {
        List<University> universities = universityRepository.findByName(name);
        assert universities.size() == 0;
    }
}
