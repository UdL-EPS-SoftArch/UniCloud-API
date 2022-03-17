package cat.udl.eps.softarch.unicloud.steps;

import org.springframework.core.io.Resource;
import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import cat.udl.eps.softarch.unicloud.domain.Resource.ResourceType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateResourceStepDefs {

    String newResourcesUri;
    final StepDefs stepDefs;
    final ResourceRepository resourceRepository;
    public static String id;
    final WebApplicationContext wac;


    CreateResourceStepDefs(StepDefs stepDefs, ResourceRepository resourceRepository, WebApplicationContext wac) {
        this.stepDefs = stepDefs;
        this.resourceRepository = resourceRepository;
        this.wac = wac;
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

    @When("I create a resource with name {string}, description {string} and filename {string}, and resource type {string} for the subject id {int}")
    public void iCreateAResourceWithNameDescriptionAndFilenameAndResourceTypeForTheSubjectId(String name, String description, String filename, ResourceType resourceType, int subjectId) throws IOException {
        Resource file = wac.getResource("classpath:" + filename);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        FileCopyUtils.copy(file.getInputStream(), output);

        cat.udl.eps.softarch.unicloud.domain.Resource newResource = new cat.udl.eps.softarch.unicloud.domain.Resource();
        newResource.setName(name);
        newResource.setDescription(description);
        newResource.setResourceType(resourceType);


        /*stepDefs.result = stepDefs.mockMvc.perform(
                        patch("/resources/{id}", datasetFile == null ? 0 : datasetFile.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(message)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());*/
    }
}
