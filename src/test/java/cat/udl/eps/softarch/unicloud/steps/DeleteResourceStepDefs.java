package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import cat.udl.eps.softarch.unicloud.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteResourceStepDefs {

    final StepDefs stepDefs;
    final ResourceRepository resourceRepository;

    public DeleteResourceStepDefs(StepDefs stepDefs, ResourceRepository resourceRepository) {
        this.stepDefs = stepDefs;
        this.resourceRepository = resourceRepository;
    }

    @When("I delete a resource with name {string}")
    public void iDeleteAResourceWithName(String name) throws Throwable {
        List<Resource> resources = resourceRepository.findByName(name);
        String id = resources.size() > 0 ? resources.get(0).getId().toString():"666";

        stepDefs.result = stepDefs.mockMvc.perform(delete("/resources/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("There is not a registered resource with name {string}")
    public void thereIsNotARegisteredResourceWithName(String name) {
        List<Resource> resources = resourceRepository.findByName(name);
        assert resources.size() == 0;
    }

    @And("The resource named {string} still exists")
    public void theResourceNamedStillExists(String name) {
        List<Resource> resources = resourceRepository.findByName(name);
        assert resources.size() == 1;
    }
}
