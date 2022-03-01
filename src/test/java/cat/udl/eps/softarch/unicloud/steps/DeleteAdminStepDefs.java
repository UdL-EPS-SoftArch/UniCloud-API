package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class DeleteAdminStepDefs {
    final StepDefs stepDefs;
    final UserRepository userRepository;

    DeleteAdminStepDefs(StepDefs stepDefs, UserRepository userRepository) {
        this.stepDefs = stepDefs;
        this.userRepository = userRepository;
    }

    @When("I delete the administrator with username {string}")
    public void iDeleteAdminWithUsername(String username) throws Throwable{
        stepDefs.result = stepDefs.mockMvc.perform( delete("/admin/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());

    }

    @And("It does not exist a administrator with username {string}")
    public void itDoesNotExistAAdminWithUsername(String username) throws Throwable{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/admin/{username}", username)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(status().isNotFound());


    }

    @And("It has not been deleted a admin with username {string}")
    public void itHasNotBeenDeletedAAdminWithUsername(String username) throws Throwable{
        stepDefs.result = stepDefs.mockMvc.perform(
                        get("/admin/{username}", username)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(username)));


    }

}