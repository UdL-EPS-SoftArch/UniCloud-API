package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateUniversityStepDefs {
    String newResourcesUri;
    final StepDefs stepDefs;
    final UniversityRepository universityRepository;
    public static String id;

    CreateUniversityStepDefs(StepDefs stepDefs, UniversityRepository universityRepository) {
        this.stepDefs = stepDefs;
        this.universityRepository = universityRepository;
    }

    @When("I create a new university with name {string}, acronym {string}, country {string}, city {string}")
    public void iCreateANewUniversityWithNameAcronymCountryCity(String name, String acronym, String country, String city) throws Exception {
        University university = new University();
        university.setName(name);
        university.setAcronym(acronym);
        university.setCountry(country);
        university.setCity(city);

        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/universities")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(stepDefs.mapper.writeValueAsString(university))
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print());
        newResourcesUri = stepDefs.result.andReturn().getResponse().getHeader("Location");

    }

    @And("The university count is {int}")
    public void theUniversityCountIs(int code) {
        Assert.assertEquals(code, universityRepository.count());
    }

    @And("A new university has been created")
    public void aNewUniversityHasBeenCreated() throws Exception {
        id = stepDefs.result.andReturn().getResponse().getHeader("Location");
        assert id != null;
        stepDefs.result = stepDefs.mockMvc.perform(
                                get(id)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                                .andDo(print())
                                .andExpect(status().isOk());
    }

    @And("There is a university with name {string}, acronym {string}, country {string}, city {string}")
    public void thereIsAUniversityWithNameAcronymCountryCity(String name, String acronym, String country, String city) {
        University university = new University();
        university.setName(name);
        university.setAcronym(acronym);
        university.setCountry(country);
        university.setCity(city);
        universityRepository.save(university);
    }
}
