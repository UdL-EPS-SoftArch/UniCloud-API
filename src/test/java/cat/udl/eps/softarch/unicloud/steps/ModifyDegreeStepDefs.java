package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyDegreeStepDefs {

    final StepDefs stepDefs;

    final DegreeRepository degreeRepository;

    public ModifyDegreeStepDefs(StepDefs stepDefs, DegreeRepository degreeRepository){
        this.stepDefs = stepDefs;
        this.degreeRepository = degreeRepository;
    }

    @When("I modify a degree with id {string} I set a name {string} and faculty {string}")
    public void iModifyDegreeIdNameFaculty(String id, String name, String faculty) throws Exception {
        Degree degree = new Degree();
        degree.setName(name);
        degree.setFaculty(faculty);
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/degrees/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stepDefs.mapper.writeValueAsString(degree))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
