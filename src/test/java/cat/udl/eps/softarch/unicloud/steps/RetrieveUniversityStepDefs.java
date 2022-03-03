package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RetrieveUniversityStepDefs {
    final UniversityRepository universityRepository;
    final StepDefs stepDefs;

    public RetrieveUniversityStepDefs(UniversityRepository universityRepository, StepDefs stepDefs) {
        this.universityRepository = universityRepository;
        this.stepDefs = stepDefs;
    }

    @When("I list all the existing universities in the app")
    public void iListAllTheExistingUniversitiesInTheApp() {
    }

    @When("I list the university with id {string}")
    public void iListTheUniversityWithId(String id) {

    }

    @When("I list the university with name {string}")
    public void iListTheUniversityWithName(String name) {

    }

    @When("I list the university containing name {string}")
    public void iListTheUniversityContainingName(String name) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/datasets/search/findByNameContaining?name={name}", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the university with acronym {string}")
    public void iListTheUniversityWithAcronym(String arg0) {

    }

    @When("I list the university with city {string}")
    public void iListTheUniversityWithCity(String arg0) {

    }

    @When("I list the university with country {string}")
    public void iListTheUniversityWithCountry(String arg0) {
    }
}
