package cat.udl.eps.softarch.unicloud.steps;

import cat.udl.eps.softarch.unicloud.domain.User;
import cat.udl.eps.softarch.unicloud.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.print.attribute.standard.Media;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ModifyUserStepDef {

    final StepDefs stepDefs;
    final UserRepository userRepository;
    public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.email", is(email)));
    }

    @When("I change the password of user {string} to {string}")
    public void iChangeThePasswordOfUserTo(String username, String password) throws Exception {
        String encodedPassword = passwordEncoder.encode(password);
        JSONObject newPassword = new JSONObject();
        newPassword.put("password", encodedPassword);
        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/users/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPassword.toString())
                        .with(AuthenticationStepDefs.authenticate())
        ).andDo(print());
    }

    @And("The password of user {string} has been updated to {string}")
    public void thePasswordOfUserHasBeenUpdatedTo(String arg0, String arg1) throws Exception {
    }

    @When("I change the username of user {string} to {string}")
    public void iChangeTheUsernameOfUserTo(String username, String name) throws Exception{
        JSONObject newName = new JSONObject();
        newName.put("name", name);
        stepDefs.result = stepDefs.mockMvc
                .perform(patch("/users/{username}", username)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newName.toString())
                .with(AuthenticationStepDefs.authenticate()))
        .andDo(print());
    }

    @And("The username of user {string} has been updated to {string}")
    public void theUsernameOfUserHasBeenUpdatedTo(String username, String name) throws Exception {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/users/{username}", username)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.user",is(name)));
    }

    @And("Email of user {string} remains {string}")
    public void emailOfUserRemains(String arg0, String arg1) throws Exception {
    }

    @When("I modify user {string} password from {string} to {string}")
    public void iModifyUserPasswordFromTo(String arg0, String arg1, String arg2) throws Exception {
    }
}
