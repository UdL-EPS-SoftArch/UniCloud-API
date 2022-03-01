package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteDegreeStepDefs {

    final StepDefs stepDefs;

    final DegreeRepository degreeRepository;

    public DeleteDegreeStepDefs(StepDefs stepDefs, DegreeRepository degreeRepository){
        this.stepDefs = stepDefs;
        this.degreeRepository = degreeRepository;
    }

    @When("I delete a degree with name {string}")
    public void iDeleteDegreeName(String name) throws Exception{
        Degree degree = degreeRepository.findByName(name).get(0);
        assert degree.getId() != null;
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/degrees/" + degree.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I delete a degree with faculty {string}")
    public void iDeleteDegreeFaculty(String faculty) throws Exception{
        Degree degree = degreeRepository.findByFaculty(faculty).get(0);
        assert degree.getId() != null;
        stepDefs.result = stepDefs.mockMvc.perform(
                        delete("/degrees/" + degree.getId().toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I delete a non-existent degree")
    public void iDeleteNonExistentDegree() throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(delete("/degrees/999").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }




}
