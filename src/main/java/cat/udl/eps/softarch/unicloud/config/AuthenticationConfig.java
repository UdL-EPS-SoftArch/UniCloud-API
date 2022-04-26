package cat.udl.eps.softarch.unicloud.config;

import cat.udl.eps.softarch.unicloud.domain.Admin;
import cat.udl.eps.softarch.unicloud.domain.Student;
import cat.udl.eps.softarch.unicloud.domain.User;
import cat.udl.eps.softarch.unicloud.repository.UserRepository;
import cat.udl.eps.softarch.unicloud.repository.AdminRepository;
import cat.udl.eps.softarch.unicloud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

  @Value("${default-password}")
  String defaultPassword;

  final BasicUserDetailsService basicUserDetailsService;
  final UserRepository userRepository;
  final AdminRepository adminRepository;
  final StudentRepository studentRepository;

  public AuthenticationConfig(BasicUserDetailsService basicUserDetailsService, UserRepository userRepository, AdminRepository adminRepository, StudentRepository studentRepository) {
    this.basicUserDetailsService = basicUserDetailsService;
    this.userRepository = userRepository;
    this.adminRepository = adminRepository;
    this.studentRepository = studentRepository;
  }

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(basicUserDetailsService)
        .passwordEncoder(User.passwordEncoder);

    // Sample user
    if (!userRepository.existsById("demo")) {
      User player = new User();
      player.setEmail("demo@sample.app");
      player.setUsername("demo");
      player.setPassword(defaultPassword);
      player.encodePassword();
      userRepository.save(player);
    }
    // Sample admin
    if(!adminRepository.existsById("adminDemo")){
      Admin admin = new Admin();
      admin.setEmail("adminDemo@sample.app");
      admin.setUsername("admin");
      admin.setPassword(defaultPassword);
      admin.encodePassword();
      adminRepository.save(admin);
    }
    // Sample student
    if(!studentRepository.existsById("Deme")){
      Student student = new Student();
      student.setEmail("studentDemo@sample.app");
      student.setUsername("student");
      student.setPassword(defaultPassword);
      student.encodePassword();
      studentRepository.save(student);
    }


  }
}
