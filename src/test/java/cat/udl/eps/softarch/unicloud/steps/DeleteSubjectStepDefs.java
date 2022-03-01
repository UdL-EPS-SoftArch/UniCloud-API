package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Subject;
import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteSubjectStepDefs {
    final StepDefs stepDefs;
    final SubjectRepository subjectRepository;
    public static String id;

    DeleteSubjectStepDefs(StepDefs stepDefs, SubjectRepository subjectRepository){
        this.stepDefs = stepDefs;
        this.subjectRepository = subjectRepository;
    }

    @When("I remove a Subject with name {string}")
    public void removeSubject(String name) throws Exception{
        Subject subject = subjectRepository.findByName(name).get(0);
        assert subject.getId() != null;
        stepDefs.result = stepDefs.mockMvc.perform(delete("/subjects/"+subject.getId().toString()).contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I remove a non-existent Subject")
    public void removeNonExistentSubject() throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(delete("/subjects/111").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Then("The subject with name {string} has not been removed")
    public void notRemovedSubject(String name){
        List<Subject> subjects = subjectRepository.findByName(name);
        assert subjects.size() == 1;
    }

    @Then("The subject with name {string} has been removed")
    public void removedSubject(String name){
        List<Subject> subjects = subjectRepository.findByName(name);
        assert subjects.size() == 0;
    }

}
