package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class ModifyUserStepDef {

    final StepDefs stepDefs;
    final UserRepository userRepository;

    ModifyUserStepDef(StepDefs stepDefs, UserRepository userRepository) {
        this.stepDefs = stepDefs;
        this.userRepository = userRepository;
    }

    @When("I change the email of user {string} to {string}")
    public void iChangeTheEmailOfUserTo(String username, String email) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/users/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new JSONObject().put("email", email)).toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }

    @And("It has been updated the email of user {string} to {string}")
    public void itHasBeenUpdatedTheEmailOfUserTo(String username, String email) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/users/{username}", username)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.email", is(email)));
    }

    @And("Email of user {string} remains {string}")
    public void emailOfUserRemains(String username, String email) throws Exception {
        itHasBeenUpdatedTheEmailOfUserTo(username, email);
    }
}


