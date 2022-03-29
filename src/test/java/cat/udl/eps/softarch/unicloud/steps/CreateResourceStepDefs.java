package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Student;
import cat.udl.eps.softarch.unicloud.domain.Subject;
import cat.udl.eps.softarch.unicloud.domain.User;
import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import cat.udl.eps.softarch.unicloud.repository.UserRepository;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateResourceStepDefs {

    String newResourcesUri;
    final StepDefs stepDefs;
    final ResourceRepository resourceRepository;
    final SubjectRepository subjectRepository;
    final UserRepository userRepository;
    public static String id;
    final WebApplicationContext wac;


    CreateResourceStepDefs(StepDefs stepDefs, ResourceRepository resourceRepository, SubjectRepository subjectRepository, UserRepository userRepository, WebApplicationContext wac) {
        this.stepDefs = stepDefs;
        this.resourceRepository = resourceRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
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

    private Subject createSubject(int id){
        Subject subj = new Subject();
        subj.setId((long)id);
        subj.setName("Exemple");
        subj.setOptional(true);
        BigDecimal bd = new BigDecimal(2);
        subj.setCourse(bd);
        subjectRepository.save(subj);
        return subj;
    }

    @When("I create a resource with name {string}, description {string}, filename {string}, and resource type {string} for the subject id {int}")
    public void iCreateAResourceWithNameDescriptionFilenameAndResourceTypeForTheSubjectId(String name, String description, String filename, String resourceType, int subjectId) throws Exception {
        cat.udl.eps.softarch.unicloud.domain.Resource newResource = createResourceParams(name, description, filename, resourceType, subjectId);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/resources")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(newResource))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        newResourcesUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    private cat.udl.eps.softarch.unicloud.domain.Resource createResourceParams(String name, String description, String filename, String resourceType, int subjectId) throws IOException {
        Resource file = wac.getResource("classpath:" + filename);

        String fileData = "";
        if(!filename.equals("") && file.exists()){
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            FileCopyUtils.copy(file.getInputStream(), output);
            fileData = output.toString();
        }

        cat.udl.eps.softarch.unicloud.domain.Resource newResource = new cat.udl.eps.softarch.unicloud.domain.Resource();
        newResource.setName(name);
        newResource.setDescription(description);
        newResource.setResourceType(ResourceType.fromString(resourceType));
        newResource.setFile(fileData);

        Subject subj = createSubject(subjectId);
        List<Subject> listSubj = new ArrayList<>();
        listSubj.add(subj);
        newResource.setSubjects(listSubj);

        return newResource;
    }

    @And("There is a registered resource with name {string} by the user {string}, with description {string}, file {string}, and resource type {string} for the subject id {int}")
    public void thereIsARegisteredResourceWithNameByTheUserWithDescriptionFileAndResourceTypeForTheSubject(String name, String user, String description, String filename, String resourceType, int subjectId) throws IOException {
        cat.udl.eps.softarch.unicloud.domain.Resource newResource = createResourceParams(name, description, filename, resourceType, subjectId);
        User st = userRepository.findByUsernameContaining(user).get(0);
        newResource.setOwner((Student)st);
        resourceRepository.save(newResource);
    }
}
