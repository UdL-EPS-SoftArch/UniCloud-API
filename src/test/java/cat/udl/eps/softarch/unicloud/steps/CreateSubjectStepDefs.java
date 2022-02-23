package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Subject;
import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateSubjectStepDefs {

    String newResourcesUri;
    final StepDefs stepDefs;
    final SubjectRepository subjectRepository;
    public static String id;

    CreateSubjectStepDefs(StepDefs stepDefs, SubjectRepository subjectRepository){
        this.stepDefs = stepDefs;
        this.subjectRepository = subjectRepository;
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

    @And("There is a Subject with name {string},course {int} and optional {string}")
    public void thereIsASubjectWithNameCourseOptional(String name, Integer course, String optional){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCourse(course);
        subject.setOptional(Boolean.parseBoolean(optional));
        subjectRepository.save(subject);
    }

}