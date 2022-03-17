package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class RetrieveSubjectStepDefs {

    final SubjectRepository subjectRepository;
    final StepDefs stepDefs;

    public RetrieveSubjectStepDefs(SubjectRepository subjectRepository, StepDefs stepDefs) {
        this.subjectRepository = subjectRepository;
        this.stepDefs = stepDefs;
    }
    @When("I list all the subjects")
    public void iListAllTheSubjects() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/subjects")
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the subject with id {string}")
    public void iListTheSubjectWithId(String id) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/subjects/" + id)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the subject with name {string}")
    public void iListTheSubjectWithName(String name) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/subjects/search/findByName?name={name}", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The number of returned subjects is {int}")
    public void theNumberOfReturnedSubjectsIs(int num) throws Exception{
        stepDefs.result.andExpect(jsonPath("$._embedded.subjects", hasSize(num)));
    }

    @And("It returns the subject with name {string}")
    public void itReturnsTheSubjectWithName(String name) throws Exception {
        stepDefs.result.andExpect(jsonPath("$.name", equalTo(name)));
    }
}
