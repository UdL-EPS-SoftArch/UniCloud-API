package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class CreateDegreeStepDefs {


    final StepDefs stepDefs;

    final DegreeRepository degreeRepository;


    public CreateDegreeStepDefs(StepDefs stepDefs, DegreeRepository degreeRepository ){
        this.degreeRepository = degreeRepository;
        this.stepDefs = stepDefs;
    }

    @When("I create a degree with name {string} and faculty {string}")
    public void iCreateANewDegree(String name, String faculty) throws Exception {
        Degree degree = new Degree();
        degree.setName(name);
        degree.setFaculty(faculty);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/degrees")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(degree))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
    @And("There is a degree created with name {string} and faculty {string}")
    public void thereIsDegreeNameFaculty(String name, String faculty){
        Degree degree = new Degree();
        degree.setName(name);
        degree.setFaculty(faculty);
        degreeRepository.save(degree);
    }
}
