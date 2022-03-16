package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.*;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import cat.udl.eps.softarch.unicloud.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyResourceStepDefs {

    final StepDefs stepDefs;
    final ResourceRepository resourceRepository;

    public ModifyResourceStepDefs(StepDefs stepDefs, ResourceRepository resourceRepository, UserRepository userRepository, SubjectRepository subjectRepository) {
        this.stepDefs = stepDefs;
        this.resourceRepository = resourceRepository;
    }

    @When("I modify the resource with name {string} and the new name {string}")
    public void iModifyTheResourceWithNameAndTheNewName(String name, String new_name) throws Throwable {
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

    @And("A resource named {string} does not exist but a resource named {string} does")
    public void aResourceNamedDoesNotExistButAResourceNamedDoes(String name1, String name2) {
        Resource resource = resourceRepository.findByName(name1).get(0);
        assert resource == null;

        resource = resourceRepository.findByName(name2).get(0);
        assert resource != null && resource.getName().equals(name2);
    }

    @When("I modify the resource with name {string} and the new description {string}")
    public void iModifyTheResourceWithNameAndTheNewDescription(String name, String new_description) throws Throwable {
        JSONObject modifyData = new JSONObject();
        modifyData.put("description", new_description);

        List<Resource> resources = resourceRepository.findByName(name);
        String id = resources.size() > 0 ? resources.get(0).getId().toString():"666";

        stepDefs.result = stepDefs.mockMvc.perform(patch("/resources/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyData.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("The resource named {string} has a description {string}")
    public void theResourceNamedHasADescription(String name, String new_description) {
        Resource resource = resourceRepository.findByName(name).get(0);
        assert resource.getDescription().equals(new_description);
    }


    @When("I modify the resource with name {string} and the new file {string}")
    public void iModifyTheResourceWithNameAndTheNewFile(String name, String new_file) throws Throwable {
        JSONObject modifyData = new JSONObject();
        modifyData.put("file", new_file);

        List<Resource> resources = resourceRepository.findByName(name);
        String id = resources.size() > 0 ? resources.get(0).getId().toString():"666";

        stepDefs.result = stepDefs.mockMvc.perform(patch("/resources/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyData.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }


    @And("The resource named {string} has a file {string}")
    public void theResourceNamedHasAFile(String name, String new_file) {
        Resource resource = resourceRepository.findByName(name).get(0);
        assert resource.getDescription().equals(new_file);
    }


    @And("There is only one resource with name {string}")
    public void thereIsOnlyOneResourceWithName(String name) {
        List<Resource> resources = resourceRepository.findByName(name);
        assert resources.size() == 1;
    }
}
