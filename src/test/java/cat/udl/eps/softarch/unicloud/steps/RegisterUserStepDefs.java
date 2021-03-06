package cat.udl.eps.softarch.unicloud.steps;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.softarch.unicloud.domain.Admin;
import cat.udl.eps.softarch.unicloud.domain.Student;
import cat.udl.eps.softarch.unicloud.domain.User;
import cat.udl.eps.softarch.unicloud.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.MediaType;

public class RegisterUserStepDefs {

  private StepDefs stepDefs;
  private UserRepository userRepository;

  public RegisterUserStepDefs(StepDefs stepDefs, UserRepository userRepository) {
    this.stepDefs = stepDefs;
    this.userRepository = userRepository;
  }


  @Given("^There is no registered user with username \"([^\"]*)\"$")
  public void thereIsNoRegisteredUserWithUsername(String user) {
    Assert.assertFalse("User \""
                    +  user + "\"shouldn't exist",
                    userRepository.existsById(user));
  }

  @Given("^There is a registered user with username \"([^\"]*)\" and password \"([^\"]*)\" and email \"([^\"]*)\"$")
  public void thereIsARegisteredUserWithUsernameAndPasswordAndEmail(String username, String password, String email) {
    if (!userRepository.existsById(username)) {
      User user = new User();
      user.setEmail(email);
      user.setUsername(username);
      user.setPassword(password);
      user.encodePassword();
      userRepository.save(user);
    }
  }

  @Given("There is a registered admin with username {string} and password {string} and email {string}")
  public void thereIsARegisteredAdminWithUsernameAndPasswordAndEmail(String username, String password, String email) {
    if (!userRepository.existsById(username)) {
      User admin = new Admin();
      admin.setEmail(email);
      admin.setUsername(username);
      admin.setPassword(password);
      admin.encodePassword();
      userRepository.save(admin);
    }
  }

  @And("^I can login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void iCanLoginWithUsernameAndPassword(String username, String password) throws Throwable {
    AuthenticationStepDefs.currentUsername = username;
    AuthenticationStepDefs.currentPassword = password;

    stepDefs.result = stepDefs.mockMvc.perform(
        get("/identity", username)
            .accept(MediaType.APPLICATION_JSON)
            .with(AuthenticationStepDefs.authenticate()))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @And("^I cannot login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void iCannotLoginWithUsernameAndPassword(String username, String password) throws Throwable {
    AuthenticationStepDefs.currentUsername = username;
    AuthenticationStepDefs.currentPassword = password;

    stepDefs.result = stepDefs.mockMvc.perform(
        get("/identity", username)
            .accept(MediaType.APPLICATION_JSON)
            .with(AuthenticationStepDefs.authenticate()))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @When("^I register a new user with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void iRegisterANewUserWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {
    User user = new User();
    user.setUsername(username);
    user.setEmail(email);

    stepDefs.result = stepDefs.mockMvc.perform(
            post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(new JSONObject(
                            stepDefs.mapper.writeValueAsString(user)
                    ).put("password", password).toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andDo(print());
  }

  @And("^It has been created a user with username \"([^\"]*)\" and email \"([^\"]*)\", the password is not returned$")
  public void itHasBeenCreatedAUserWithUsername(String username, String email) throws Throwable {
    stepDefs.result = stepDefs.mockMvc.perform(
            get("/users/{username}", username)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andDo(print())
            .andExpect(jsonPath("$.email", is(email)))
            .andExpect(jsonPath("$.password").doesNotExist());
  }

  @And("^It has not been created a user with username \"([^\"]*)\"$")
  public void itHasNotBeenCreatedAUserWithUsername(String username) throws Throwable {
    stepDefs.result = stepDefs.mockMvc.perform(
            get("/users/{username}", username)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(AuthenticationStepDefs.authenticate()))
            .andExpect(status().isNotFound());
  }

  @Given("There is a registered student with username {string} and password {string} and email {string}")
  public void thereIsARegisteredStudentWithUsernameAndPasswordAndEmail(String username, String password, String email) {
    if (!userRepository.existsById(username)) {
      User student = new Student();
      student.setEmail(email);
      student.setUsername(username);
      student.setPassword(password);
      student.encodePassword();
      userRepository.save(student);
    }
  }
}
