package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyDegreeStepDefs {

    final StepDefs stepDefs;

    final DegreeRepository degreeRepository;

    public ModifyDegreeStepDefs(StepDefs stepDefs, DegreeRepository degreeRepository){
        this.stepDefs = stepDefs;
        this.degreeRepository = degreeRepository;
    }

    @When("I modify a degree with name {string} and university {string} changing field faculty with {string}")
    public void iModifyADegreeWithNameChangingFieldFacultyWith(String name, String uniName, String faculty) throws Exception {
        List<Degree> degreeList = degreeRepository.findByName(name);
        Degree degree = new Degree();
        String id = "";
        if(!degreeList.isEmpty()){
            for (Degree d : degreeList) {
                if(d.getUniversity().getName().equals(uniName)){
                    degree = d;
                    degree.setFaculty(faculty);
                    assert degree.getId() != null;
                    id = degree.getId().toString();
                    break;
                }
            }
        }
        stepDefs.result = stepDefs.mockMvc.perform(
                        put("/degrees/"+ id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(degree))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
    }

    @And("The Degree with name {string} has the faculty {string}")
    public void theDegreeWithNameHasTheFaculty(String name, String faculty) {
        Degree degree = degreeRepository.findByName(name).get(0);
        assert degree.getFaculty().equals(faculty);
    }

    @When("I modify a degree with faculty {string} and university {string} changing field name with {string}")
    public void iModifyADegreeWithFacultyChangingFieldNameWith(String faculty, String uniName, String name) throws Exception{
        List<Degree> degreeList = degreeRepository.findByFaculty(faculty);
        Degree degree = new Degree();
        String id = "";
        if(!degreeList.isEmpty()){
            for (Degree d : degreeList) {
                if(d.getUniversity().getName().equals(uniName)){
                    degree = d;
                    degree.setName(name);
                    assert degree.getId() != null;
                    id = degree.getId().toString();
                    break;
                }
            }
        }
        stepDefs.result = stepDefs.mockMvc.perform(
                        put("/degrees/"+ id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(degree))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
    }

    @And("The Degree with faculty {string} has the name {string}")
    public void theDegreeWithFacultyHasTheName(String faculty, String name) {
        Degree degree = degreeRepository.findByFaculty(faculty).get(0);
        assert degree.getName().equals(name);
    }
}
