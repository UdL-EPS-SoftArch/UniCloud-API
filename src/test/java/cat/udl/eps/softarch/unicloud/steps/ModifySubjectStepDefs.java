package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Subject;
import cat.udl.eps.softarch.unicloud.repository.SubjectRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifySubjectStepDefs {
    final SubjectRepository subjectRepository;
    final StepDefs stepDefs;

    public ModifySubjectStepDefs(SubjectRepository subjectRepository, StepDefs stepDefs) {
        this.subjectRepository = subjectRepository;
        this.stepDefs = stepDefs;
    }


    @When("I modify a subject with name {string} changing field course to {int}")
    public void iModifyASubjectWithNameChangingFieldTo(String name, Integer course) throws Exception {
        JSONObject modifyData = new JSONObject();
        modifyData.put("course", course);

        List<Subject> subjects = subjectRepository.findByName(name);

        String id = subjects.size() > 0 ? subjects.get(0).getId().toString():"111";

        stepDefs.result = stepDefs.mockMvc.perform(patch("/subjects/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyData.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The field course of the subject with name {string} has the value {int}")
    public void theSubjectWithNameHasTheCourse(String name, Integer course) {
        Subject subject = subjectRepository.findByName(name).get(0);
        assert subject.getCourse().equals(course);
    }

    @When("I modify a subject with name {string} changing field optional to {string}")
    public void iModifyASubjectWithNameChangingFieldTo(String name, String optional) throws Exception {
        JSONObject modifyData = new JSONObject();
        modifyData.put("optional", Boolean.parseBoolean(optional));

        List<Subject> subjects = subjectRepository.findByName(name);
        String id = subjects.size() > 0 ? subjects.get(0).getId().toString():"111";

        stepDefs.result = stepDefs.mockMvc.perform(patch("/subjects/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyData.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The field optional of the subject with name {string} has the value {string}")
    public void theSubjectWithNameHasTheOptional(String name, String optional) {
        Subject subject = subjectRepository.findByName(name).get(0);
        assert subject.getOptional().equals(Boolean.parseBoolean(optional));
    }
}

