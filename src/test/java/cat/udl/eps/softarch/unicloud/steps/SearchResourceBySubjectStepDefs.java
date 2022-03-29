package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class SearchResourceBySubjectStepDefs {
    final StepDefs stepDefs;
    final ResourceRepository resourceRepository;

    public SearchResourceBySubjectStepDefs(StepDefs stepDefs, ResourceRepository resourceRepository) {
        this.stepDefs = stepDefs;
        this.resourceRepository = resourceRepository;
    }

    @When("I search a resource by its exact subject {string}")
    public void iSearchAResourceByItsExactSubject(String subject) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/resources/search/findBySubjectsName?subject={subject}", subject)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("{int} resources have been retrieved")
    public void resourcesHaveBeenRetrieved(int num) throws Throwable {
        stepDefs.result.andExpect(jsonPath("$._embedded.resources", hasSize(num)));
    }

    @When("I search for resources containing the subject {string}")
    public void iSearchForResourcesContainingTheSubject(String subject) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/resources/search/findBySubjectsNameContaining?subject={subject}", subject)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
