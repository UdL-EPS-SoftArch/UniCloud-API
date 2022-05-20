package cat.udl.eps.softarch.unicloud.config;

import cat.udl.eps.softarch.unicloud.domain.Admin;
import cat.udl.eps.softarch.unicloud.domain.University;
import cat.udl.eps.softarch.unicloud.repository.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

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



    public BBDDInitialization(AdminRepository adminRepository,
                              StudentRepository studentRepository,
                              UniversityRepository universityRepository,
                              DegreeRepository degreeRepository,
                              SubjectRepository subjectRepository,
                              ResourceRepository resourceRepository,
                              RatingRepository ratingRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.universityRepository = universityRepository;
        this.degreeRepository = degreeRepository;
        this.subjectRepository = subjectRepository;
        this.resourceRepository = resourceRepository;
        this.ratingRepository = ratingRepository;
    }

    @PostConstruct
    public void initializeDatabase() {

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

    }
}

