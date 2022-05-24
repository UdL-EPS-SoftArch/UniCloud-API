package cat.udl.eps.softarch.unicloud.config;

import cat.udl.eps.softarch.unicloud.domain.*;
import cat.udl.eps.softarch.unicloud.repository.*;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile("test")
public class BBDDInitialization {
    final AdminRepository adminRepository;
    final StudentRepository studentRepository;
    final UniversityRepository universityRepository;
    final DegreeRepository degreeRepository;
    final SubjectRepository subjectRepository;
    final ResourceRepository resourceRepository;
    final RatingRepository ratingRepository;
    final UserRepository userRepository;


    @Value("${default-password}")
    String defaultPassword;

    public BBDDInitialization(AdminRepository adminRepository,
                              StudentRepository studentRepository,
                              UniversityRepository universityRepository,
                              DegreeRepository degreeRepository,
                              SubjectRepository subjectRepository,
                              ResourceRepository resourceRepository,
                              RatingRepository ratingRepository,
                              UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.degreeRepository = degreeRepository;
        this.subjectRepository = subjectRepository;
        this.resourceRepository = resourceRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
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
        University university = new University();
        university.setName("Universitat Lleida");
        university.setAcronym("ULL");
        university.setCity("Barcelona");
        university.setCountry("Spain");
        universityRepository.save(university);

        University university2 = new University();
        university2.setName("Universidad de Huelva");
        university2.setAcronym("UHU");
        university2.setCity("Huelva");
        university2.setCountry("Spain");
        universityRepository.save(university2);

        University university3 = new University();
        university3.setName("Universidad Politecnica de Catalunya");
        university3.setAcronym("UPC");
        university3.setCity("Barcelona");
        university3.setCountry("Spain");
        universityRepository.save(university3);

        University university4 = new University();
        university4.setName("Universidad de Barcelona");
        university4.setAcronym("UAB");
        university4.setCity("Barcelona");
        university4.setCountry("Spain");
        universityRepository.save(university4);

        Degree degree1 = new  Degree();
        degree1.setName("lleida");
        degree1.setUniversity(university4);
        degree1.setFaculty("eps");
        degreeRepository.save(degree1);

        List<Degree> degree_list = new ArrayList<>();
        degree_list.add(degree1);

        Subject subject1 = new  Subject();
        subject1.setName("arqui");
        subject1.setOptional(true);
        subject1.setCourse(3);
        subject1.setDegrees(degree_list);
        subjectRepository.save(subject1);

        List<Subject> subjects_list = new ArrayList<>();
        subjects_list.add(subject1);

        Resource resource = new Resource();
        resource.setName("gei");
        resource.setDescription("Aixo es una prova");
        resource.setFile("file");
        resource.setOwner(studentRepository.findById("student").get());
        resource.setSubjects(subjects_list);
        resource.setResourceType(Resource.ResourceType.note);
        resourceRepository.save(resource);

    }
}