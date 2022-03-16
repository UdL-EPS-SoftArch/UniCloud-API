package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.UniversityRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ModifyUniversityStepDefs {

    final UniversityRepository universityRepository;
    final StepDefs stepDefs;

    public ModifyUniversityStepDefs(UniversityRepository universityRepository, StepDefs stepDefs) {
        this.universityRepository = universityRepository;
        this.stepDefs = stepDefs;
    }


    @When("I modify an university with name {string} changing field {string} to {string}")
    public void iModifyAnUniversityWithNameChangingFieldTo(String name,String field, String value) throws Exception {
        JSONObject modifyData = new JSONObject();
        modifyData.put(field, value);

        List<University> universities = universityRepository.findByName(name);
        String id = universities.size() > 0 ? universities.get(0).getId().toString():"666";

        stepDefs.result = stepDefs.mockMvc.perform(patch("/universities/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(modifyData.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("The university with name {string} has the acronym {string}")
    public void theUniversityWithNameHasTheAcronym(String name, String acronym) {
        University university = universityRepository.findByName(name).get(0);
        assert university.getAcronym().equals(acronym);
    }

    @And("The university with name {string} has the country {string}")
    public void theUniversityWithNameHasTheCountry(String name, String country) {
        University university = universityRepository.findByName(name).get(0);
        assert university.getCountry().equals(country);
    }

    @And("The university with name {string} has the city {string}")
    public void theUniversityWithNameHasTheCity(String name, String city) {
        University university = universityRepository.findByName(name).get(0);
        assert university.getCity().equals(city);
    }

    @And("A university with name {string} exists")
    public void aUniversityWithNameExists(String name){
         assert universityRepository.findByName(name).size() == 1;
    }



}
