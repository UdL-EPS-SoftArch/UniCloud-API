package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class SearchResourceByNameStepDefs {

    final StepDefs stepDefs;
    final ResourceRepository resourceRepository;

    public SearchResourceByNameStepDefs(StepDefs stepDefs, ResourceRepository resourceRepository) {
        this.stepDefs = stepDefs;
        this.resourceRepository = resourceRepository;
    }

    @When("I search a resource by its exact name {string}")
    public void iSearchAResourceByItsExactName(String name) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/resources/search/findByName?name={name}", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("{int} resource has been retrieved and its name is {string}")
    public void resourceHasBeenRetrievedAndItsNameIs(int num, String name) throws Throwable{
        stepDefs.result.andExpect(jsonPath("$._embedded.resources", hasSize(num)));
        stepDefs.result.andExpect(jsonPath("$._embedded.resources[0].name", equalTo(name)));
    }

    @When("I search for resources containing the name {string}")
    public void iSearchForResourcesContainingTheName(String name) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/resources/search/findByNameContaining?name={name}", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("{int} resources have been retrieved and their name contain {string}")
    public void resourcesHaveBeenRetrievedAndTheirNameContain(int num, String name) throws Throwable {
        stepDefs.result.andExpect(jsonPath("$._embedded.resources", hasSize(num)));
        stepDefs.result.andExpect(jsonPath("$._embedded.resources[0].name", containsString(name)));
        stepDefs.result.andExpect(jsonPath("$._embedded.resources[1].name", containsString(name)));
    }
}
