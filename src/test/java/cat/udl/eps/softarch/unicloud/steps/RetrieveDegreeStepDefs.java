package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.Degree;
import cat.udl.eps.softarch.unicloud.repository.DegreeRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class RetrieveDegreeStepDefs {

    final StepDefs stepDefs;

    final DegreeRepository degreeRepository;

    Degree degree;

    public RetrieveDegreeStepDefs(StepDefs stepDefs, DegreeRepository degreeRepository){
        this.stepDefs = stepDefs;
        this.degreeRepository = degreeRepository;
    }

    @When("I list all the degrees")
    public void iListAllDegrees() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/")
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
    }

    @And("The number of the returned degrees are {int}")
    public void theNumberOfTheReturnedDegreesAre(int number) throws Exception {
        stepDefs.result.andExpect(jsonPath("$._embedded.degrees", hasSize(number)));
    }

    @When("I list the degree with id {long}")
    public void iListTheDegreeWithId(Long id) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/" + id)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
        if(id <= degreeRepository.count())
            degree = degreeRepository.findById(id).get();
    }

    //NAME

    @When("I list the degree with name {string}")
    public void iListTheDegreeWithName(String name) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/search/findByName?nameDegree={name}", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the degree with containing name {string}")
    public void iListTheDegreeWithContainingName(String name) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/search/findByNameContaining?nameDegree={name}", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    //FACULTY NAME

    @When("I list the degree with faculty name {string}")
    public void iListTheDegreeWithFaculty(String faculty) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/search/findByFaculty?nameFaculty={faculty}", faculty)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the degree with containing faculty name {string}")
    public void iListTheDegreeWithContainingFacultyName(String faculty) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/search/findByFacultyContaining?nameFaculty={faculty}", faculty)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    //UNIVERSITY NAME

    @When("I list the degree with university name {string}")
    public void iListTheDegreeWithUniversityName(String uniName) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/search/findByUniversityName?nameUni={uniName}", uniName)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the degree with containing university name {string}")
    public void iListTheDegreeWithContainingUniversityName(String uniName) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/degrees/search/findByUniversityNameContaining?nameUni={uniName}", uniName)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It returns the degree with id {long}")
    public void itReturnsTheDegreeWithId(Long id) {
        Degree degreeTemp = degreeRepository.findById(id).get();
        assert degree.equals(degreeTemp);

    }
    @And("The number of returned degrees are {int}")
    public void theNumberOfReturnedDegreesAre(int num) throws Exception {
        stepDefs.result.andExpect(jsonPath("$._embedded.degrees", hasSize(num)));
    }

}
