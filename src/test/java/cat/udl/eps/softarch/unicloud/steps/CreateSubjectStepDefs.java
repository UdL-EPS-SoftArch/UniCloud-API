package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Subject;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import cat.udl.eps.softarch.unicloud.repository.ResourceRepository;
import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CreateSubjectStepDefs {

    String newResourcesUri;
    final StepDefs stepDefs;
    final SubjectRepository subjectRepository;
    final DegreeRepository degreeRepository;
    final ResourceRepository resourceRepository;
    public static String id;

    CreateSubjectStepDefs(StepDefs stepDefs, SubjectRepository subjectRepository, DegreeRepository degreeRepository, ResourceRepository resourceRepository){
        this.stepDefs = stepDefs;
        this.subjectRepository = subjectRepository;
        this.degreeRepository = degreeRepository;
        this.resourceRepository = resourceRepository;
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

    @When("I create a new Subject with name {string}, course {int} and optional {string} associated with the name of the degree {string}")
    public void iCreateANewSubjectAssociatedWithDegree(String name, Integer course, String optional, String nameDegree) throws Exception{
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCourse(course);
        subject.setOptional(Boolean.parseBoolean(optional));
        subject.setDegrees(degreeRepository.findByName(nameDegree));
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(subject))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        newResourcesUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("It has been associated the degree named {string} to the subject")
    public void itHasBeenAssignedADegreeToSubject(String nameDegree) throws Exception{
        newResourcesUri = newResourcesUri + "/degrees";
        stepDefs.result = stepDefs.mockMvc.perform(
                get(newResourcesUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$._embedded.degrees",hasSize(1)));

    }
/*
    @When("I create a new Subject with name {string}, course {int} and optional {string} associated with name of the resources {string}")
    public void iCreateANewSubjectAssociatedWithResources(String name, Integer course, String optional, String nameResource) throws Exception{
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

 */

    @And("It has been associated the resource name {string} to the subject")
    public void itHasBeenAssignedAResourceToSubject(String nameResource) throws Exception{
        newResourcesUri = newResourcesUri + "/resources";
        stepDefs.result = stepDefs.mockMvc.perform(
                get(newResourcesUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$._embedded.resources", hasSize(1)));
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
