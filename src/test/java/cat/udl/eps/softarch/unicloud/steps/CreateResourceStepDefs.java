package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateResourceStepDefs {

    String newResourcesUri;
    final StepDefs stepDefs;
    final ResourceRepository resourceRepository;
    public static String id;

    CreateResourceStepDefs(StepDefs stepDefs, ResourceRepository resourceRepository) {
        this.stepDefs = stepDefs;
        this.resourceRepository = resourceRepository;
    }

    @And("The resource count is {int}")
    public void theResourceCountIs(int num) {
        Assert.assertEquals(num, resourceRepository.count());
    }

    @And("A new resource has been created")
    public void aNewResourceHasBeenCreated() throws Exception {
        id = stepDefs.result.andReturn().getResponse().getHeader("Location");
        assert id != null;
        stepDefs.result = stepDefs.mockMvc.perform(
                                get(id)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print())
                                .andExpect(status().isOk());
    }

    @When("I create a Note with name {string}, description {string} and file with title {string} for the subject id {int}")
    public void iCreateAResourceWithNameDescriptionAndFileForTheSubject(String name, String description, String title, int subject) {

    }
}
