package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.domain.Resource;
import cat.udl.eps.softarch.unicloud.domain.Subject;
import cat.udl.eps.softarch.unicloud.repository.AdminRepository;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CreateSubjectStepDefs {

    String newResourcesUri;
    final StepDefs stepDefs;
    final SubjectRepository subjectRepository;
    final DegreeRepository degreeRepository;
    final ResourceRepository resourceRepository;
    final AdminRepository adminRepository;
    public static String id;

    CreateSubjectStepDefs(StepDefs stepDefs, SubjectRepository subjectRepository, DegreeRepository degreeRepository,
                          ResourceRepository resourceRepository, AdminRepository adminRepository){
        this.stepDefs = stepDefs;
        this.subjectRepository = subjectRepository;
        this.degreeRepository = degreeRepository;
        this.resourceRepository = resourceRepository;
        this.adminRepository = adminRepository;
    }

    @When("I create a new subject with name {string} and course {int} and optional {string}")
    public void iCreateANewSubjectWithNameCourseOptional(String name, Integer course, String optional) throws Exception{
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCourse(course);
        subject.setOptional(Boolean.parseBoolean(optional));

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(subject))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print());
        newResourcesUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("A new subject has not been created")
    public void aNewSubjectHasNotBeenCreated(){
        Assert.assertEquals(0, subjectRepository.count());
    }

    @And("A new subject has not been added")
    public void aNewSubjectHasNotBeenAdded(){
        Assert.assertEquals(1, subjectRepository.count());
    }

    @And("A new subject has been created")
    public void aNewSubjectHasBeenCreated() throws Exception{
        id = stepDefs.result.andReturn().getResponse().getHeader("Location");
        assert id != null;
        stepDefs.result = stepDefs.mockMvc.perform(
                        get(id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print())
                        .andExpect(status().isOk());
    }

    @And("I associate the previous created subject to admin with username {string}")
    public void associatedASubjectToAdminWithUsername(String username) throws Exception{
        newResourcesUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
        URI uri = new URI(newResourcesUri);
        String path = uri.getPath();
        String idStr = path.substring(path.lastIndexOf('/') + 1);
        Long id = Long.parseLong(idStr);

        Subject subject = subjectRepository.findById(id).get();
        /*subject.setAdmin(adminRepository.findById(username).get());*/

        stepDefs.result = stepDefs.mockMvc.perform(patch(newResourcesUri)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(stepDefs.mapper.writeValueAsString(subject))
                .accept(MediaType.APPLICATION_JSON)
                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It has been created a subject associated to admin with username {string}")
    public void itHasBeenAssociatedASubjectToAdminWithUsername(String username) throws Exception{
        newResourcesUri = newResourcesUri + "/admin";
        stepDefs.result = stepDefs.mockMvc.perform(get(newResourcesUri)
                .accept(MediaType.APPLICATION_JSON)
                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.id",is(username)));
    }
    @When("I create a new subject with name {string}, course {int} and optional {string} associated to degree with name {string}")
    public void iCreateANewSubjectWithNameCourseOptionalAndNameDegree(String name, Integer course, String optional, String nameDegree) throws Exception{
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCourse(course);
        subject.setOptional(Boolean.parseBoolean(optional));
        subject.setDegree((List<Degree>) degreeRepository.findByName(nameDegree).get(0));
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(subject))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It has been created a subject associated to degree with name {string}")
    public void itHasBeenCreatedASubjectWithNameAndNameDegree(String nameDegree) throws Exception {
        newResourcesUri = newResourcesUri + "/degrees";
        stepDefs.result = stepDefs.mockMvc.perform(
                get(newResourcesUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.nameDegree",is(nameDegree)));
    }

    private Resource createResource(int id){
        Resource resource = new Resource();
        resource.setId((long) id);
        resource.setName("Resource");
        resource.setDescription("Description");
        resourceRepository.save(resource);
        return resource;
    }

    @When("I create a new subject with name {string}, course {int} and optional {string} associated to resource with id {int}")
    public void iCreateANewSubjectWithNameCourseOptionalAndName(String name, Integer course, String optional, int resourceId) throws IOException{
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCourse(course);
        subject.setOptional(Boolean.parseBoolean(optional));
    }

    @And("There is a Subject with name {string},course {int} and optional {string}")
    public void thereIsASubjectWithNameCourseOptional(String name, Integer course, String optional){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCourse(course);
        subject.setOptional(Boolean.parseBoolean(optional));
        subjectRepository.save(subject);
    }

}
