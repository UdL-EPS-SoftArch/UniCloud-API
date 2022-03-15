package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class DeleteDegreeStepDefs {

    final StepDefs stepDefs;

    final DegreeRepository degreeRepository;

    public DeleteDegreeStepDefs(StepDefs stepDefs, DegreeRepository degreeRepository){
        this.stepDefs = stepDefs;
        this.degreeRepository = degreeRepository;
    }

    @When("I delete a degree with name {string} and university {string}")
    public void iDeleteDegreeName(String name, String uniName) throws Exception{
        List<Degree> degreeList = degreeRepository.findByName(name);
        Degree degree = new Degree();
        for(Degree degree1: degreeList){
            if (degree1.getUniversity().getName().equals(uniName)) {
                degree = degree1;
                break;
            }
        }
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


    @And("The degree with name {string} and university {string} doesn't exist")
    public void theDegreeWithNameDoesnTExist(String name, String uniName) {
        List<Degree> degreeList = degreeRepository.findByName(name);
        Degree degree = null;
        for(Degree degree1: degreeList){
            if (degree1.getName().equals(uniName)) {
                degree = degree1;
                break;
            }
        }
        assert degree == null;
    }

    @And("The degree with name {string} and university {string} exists")
    public void theDegreeWithNameExist(String name, String uniName) {
        List<Degree> degreeList = degreeRepository.findByName(name);
        Degree degree = null;
        for(Degree degree1: degreeList){
            if (degree1.getName().equals(uniName)) {
                degree = degree1;
                break;
            }
        }
        assert degree != null;
    }
}
