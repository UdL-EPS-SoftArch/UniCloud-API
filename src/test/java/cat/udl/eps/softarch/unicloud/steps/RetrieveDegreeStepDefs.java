package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class RetrieveDegreeStepDefs {

    final StepDefs stepDefs;

    final DegreeRepository degreeRepository;

    public RetrieveDegreeStepDefs(StepDefs stepDefs, DegreeRepository degreeRepository){
        this.stepDefs = stepDefs;
        this.degreeRepository = degreeRepository;
    }

    @When("I list all the degrees")
    public void iListAllDegrees() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/")
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
    }

    @And("The number of the returned degrees are {int}")
    public void theNumberOfTheReturnedDegreesAre(int number) throws Exception {
        stepDefs.result.andExpect(jsonPath("$._embedded.degrees", hasSize(number)));
    }

    @When("I list the degree with id {string}")
    public void iListTheDegreeWithId(String id) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/" + id)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the degree with name {string}")
    public void iListTheDegreeWithName(String name) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/search/findByName?name={name}", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
