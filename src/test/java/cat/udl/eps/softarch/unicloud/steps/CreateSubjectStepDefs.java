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

import java.math.BigDecimal;

public class CreateSubjectStepDefs {

    String newResourcesUri;
    final StepDefs stepDefs;
    final SubjectRepository subjectRepository;

    CreateSubjectStepDefs(StepDefs stepDefs, SubjectRepository subjectRepository){
        this.stepDefs = stepDefs;
        this.subjectRepository = subjectRepository;
    }

    @When("I create a new Subject with name {string}, course {bigdecimal} and optional {boolean}")
    public void iCreateANewSubjectWithNameCourseOptional(String name, BigDecimal course, Boolean optional) throws Exception{
        Subject subject = new Subject();
        subject.setName(name);
        subject.setCourse(course);
        subject.setOptional(optional);

        stepDefs.result = stepDefs.mockMvc.perform(
                post("/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(subject))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print());
        newResourcesUri = stepDefs.result.andReturn().getResponse().getHeader("Location");
    }

    @And("It has not been created any subject yet")
    public void itHasNotBeenCreatedAnySubjectYet(){
        Assert.assertEquals(0, subjectRepository.count());
    }

    @And("It has not been created any new subject")
    public void itHasNotBeenCreatedAnyNewSubject(){
        Assert.assertEquals(1, subjectRepository.count());
    }

    @And("It has been created a new subject")
    public void itHasBeenCreatedANewSubject() throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get(newResourcesUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                        .andDo(print())
                        .andExpect(status().isOk());
    }
}
