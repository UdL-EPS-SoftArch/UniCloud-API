package cat.udl.eps.softarch.unicloud.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class DeleteUserStepDef {
    @When("I delete the user with username {string}")
    public void iDeleteTheUserWithUsername(String arg0) {
    }

    @And("It does not exist a user with username {string}")
    public void itDoesNotExistAUserWithUsername(String arg0) {
    }

    @And("It has not been deleted a user with username {string}")
    public void itHasNotBeenDeletedAUserWithUsername(String arg0) {
    }
}
