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

public class ModifyUniversityStepsDefs {

    final UniversityRepository universityRepository;
    final StepDefs stepDefs;

    public ModifyUniversityStepsDefs(UniversityRepository universityRepository, StepDefs stepDefs) {
        this.universityRepository = universityRepository;
        this.stepDefs = stepDefs;
    }


    @When("I modify an university with name {string} changing field acronym to {string}")
    public void iModifyAnUniversityWithNameChangingFieldTo(String name, String acronym) throws Exception {
        JSONObject modifyData = new JSONObject();
        modifyData.put("acronym", acronym);

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



}
