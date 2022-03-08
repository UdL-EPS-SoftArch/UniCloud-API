package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


public class DeleteUserStepDef {
    final StepDefs stepDefs;
    final UserRepository userRepository;

    public DeleteUserStepDef(StepDefs stepDefs, UserRepository userRepository) {
        this.stepDefs = stepDefs;
        this.userRepository = userRepository;
    }

    @When("I delete the user with username {string}")
    public void iDeleteTheUserWithUsername(String username) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/users/{username}", username)
                        .with(AuthenticationStepDefs.authenticate()));
    }

    @And("It does not exist a user with username {string}")
    public void itDoesNotExistAUserWithUsername(String username) throws Exception {
    }

    @And("It has not been deleted a user with username {string}")
    public void itHasNotBeenDeletedAUserWithUsername(String username) throws Exception {
    }
}
