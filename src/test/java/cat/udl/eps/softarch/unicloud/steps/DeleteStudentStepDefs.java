package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.repository.UserRepository;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en_scouse.An;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class DeleteStudentStepDefs {
    final StepDefs stepDefs;
    final UserRepository userRepository;

    public DeleteStudentStepDefs(StepDefs stepDefs, UserRepository userRepository) {
        this.stepDefs = stepDefs;
        this.userRepository = userRepository;
    }

    @When("I delete the student with the username {string}")
    public void iDeleteStudentWithUsername(String username) throws Throwable{
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/students/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It does not exist a student with username {string}")
    public void itDoesNotExistAStudentWithUsername(String username) throws Throwable{
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/students/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("It has not been deleted a student with username {string}")
    public void itHasNotBeenDeletedAUserWithUsername(String username) throws Throwable{
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/students/{username}", username)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(jsonPath("$.id", is(username)));
    }
}
