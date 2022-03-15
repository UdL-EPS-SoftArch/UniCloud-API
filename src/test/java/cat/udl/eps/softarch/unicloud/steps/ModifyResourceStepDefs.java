package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyResourceStepDefs {

    final StepDefs stepDefs;
    final ResourceRepository resourceRepository;

    public ModifyResourceStepDefs(StepDefs stepDefs, ResourceRepository resourceRepository) {
        this.stepDefs = stepDefs;
        this.resourceRepository = resourceRepository;
    }

    @When("I modify the resource with name {string} and the new name {string}")
    public void iModifyTheResourceWithNameAndTheNewName(String name, String new_name) throws Exception {
        JSONObject modifyData = new JSONObject();
        modifyData.put("name", new_name);

        List<Resource> resources = resourceRepository.findByName(name);
        String id = resources.size() > 0 ? resources.get(0).getId().toString():"666";

        stepDefs.result = stepDefs.mockMvc.perform(patch("/resources/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyData.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("A resource named {string] does not exist, but a resource named {string} does")
    public void aResourceNamedDoesNotExistButAResourceNamedDoes(String name1, String name2) {
        Resource resource = resourceRepository.findByName(name1).get(0);
        assert resource == null;

        resource = resourceRepository.findByName(name2).get(0);
        assert resource != null && resource.getName().equals(name2);
    }
}
