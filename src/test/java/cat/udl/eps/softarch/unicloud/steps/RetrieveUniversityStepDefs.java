package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.zh_cn.而且;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class RetrieveUniversityStepDefs {
    final UniversityRepository universityRepository;
    final StepDefs stepDefs;

    public RetrieveUniversityStepDefs(UniversityRepository universityRepository, StepDefs stepDefs) {
        this.universityRepository = universityRepository;
        this.stepDefs = stepDefs;
    }

    @When("I list all the existing universities in the app")
    public void iListAllTheExistingUniversitiesInTheApp() throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/universities")
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
    }

    @When("I list the university with id {string}")
    public void iListTheUniversityWithId(String id) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/universities/" + id)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
    }

    @When("I list the university with name {string}")
    public void iListTheUniversityWithName(String name) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/universities/search/findByName?name={name}", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
    }

    @When("I list the university containing name {string}")
    public void iListTheUniversityContainingName(String name) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/universities/search/findByNameContaining?name={name}", name)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the university with acronym {string}")
    public void iListTheUniversityWithAcronym(String acronym) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/universities/search/findByAcronym?acronym={acronym}", acronym)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the university with city {string}")
    public void iListTheUniversityWithCity(String city) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/universities/search/findByCity?city={city}", city)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("I list the university with country {string}")
    public void iListTheUniversityWithCountry(String country) throws Exception{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/universities/search/findByCountry?country={country}", country)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The number of returned universities are {int}")
    public void theNumberOfReturnedUniversitiesAre(int num) throws Exception {
        stepDefs.result.andExpect(jsonPath("$._embedded.universities", hasSize(num)));
    }

    @And("It returns the university with name {string}")
    public void itReturnsTheUniversityWithName(String name) throws Exception {
        stepDefs.result.andExpect(jsonPath("$.name", equalTo(name)));
    }
}
