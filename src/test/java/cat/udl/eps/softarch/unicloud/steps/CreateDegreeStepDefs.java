package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class CreateDegreeStepDefs {


    final StepDefs stepDefs;

    final DegreeRepository degreeRepository;

    final UniversityRepository universityRepository;


    public CreateDegreeStepDefs(StepDefs stepDefs, DegreeRepository degreeRepository, UniversityRepository universityRepository ){
        this.degreeRepository = degreeRepository;
        this.stepDefs = stepDefs;
        this.universityRepository = universityRepository;
    }

    @When("I create a degree with name {string} and faculty {string} and university {string}")
    public void iCreateANewDegree(String name, String faculty, String uniName) throws Exception {
        Degree degree = new Degree();
        degree.setName(name);
        degree.setFaculty(faculty);
        List<University> universityList = universityRepository.findByName(uniName);
        if(!universityList.isEmpty()){
            degree.setUniversity(universityList.get(0));
        }

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/degrees")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(degree))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
    @And("There is a degree created with name {string} and faculty {string} and university {string}")
    public void thereIsDegreeNameFaculty(String name, String faculty, String uniName){
        Degree degree = new Degree();
        degree.setName(name);
        degree.setFaculty(faculty);
        degree.setUniversity(universityRepository.findByName(uniName).get(0));
        degreeRepository.save(degree);
    }
}
