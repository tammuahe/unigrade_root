package com.tlu.unigrade.init;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.tlu.unigrade.entity.Course;
import com.tlu.unigrade.entity.Enrollment;
import com.tlu.unigrade.entity.Grade;
import com.tlu.unigrade.entity.Lecturer;
import com.tlu.unigrade.entity.Program;
import com.tlu.unigrade.entity.Section;
import com.tlu.unigrade.entity.Semester;
import com.tlu.unigrade.entity.Student;
import com.tlu.unigrade.entity.User;
import com.tlu.unigrade.enums.Department;
import com.tlu.unigrade.enums.EnrollmentStatus;
import com.tlu.unigrade.enums.Role;
import com.tlu.unigrade.enums.SemesterNumber;
import com.tlu.unigrade.enums.StudentStatus;
import com.tlu.unigrade.repository.CourseRepository;
import com.tlu.unigrade.repository.EnrollmentRepository;
import com.tlu.unigrade.repository.GradeRepository;
import com.tlu.unigrade.repository.LecturerRepository;
import com.tlu.unigrade.repository.ProgramRepository;
import com.tlu.unigrade.repository.SectionRepository;
import com.tlu.unigrade.repository.SemesterRepository;
import com.tlu.unigrade.repository.StudentRepository;
import com.tlu.unigrade.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepo;
    private final ProgramRepository programRepo;
    private final CourseRepository courseRepo;
    private final SemesterRepository semesterRepo;
    private final LecturerRepository lecturerRepo;
    private final SectionRepository sectionRepo;
    private final StudentRepository studentRepo;
    private final GradeRepository gradeRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final PasswordEncoder encoder;

    @PostConstruct
    void init() {
        if (userRepo.findByUsername("student1").isEmpty()) {
            initializeDatabase();
        }
    }

    private void initializeDatabase() {
        // 1. Initialize Programs
        Program softwareEngineering = createProgram("Software Engineering");
        Program computerScience = createProgram("Computer Science");
        Program informationTechnology = createProgram("Information Technology");

        // 2. Initialize Courses
        Course java = createCourse("CS101", "Java Programming", 3);
        Course dataStructures = createCourse("CS102", "Data Structures", 4);
        Course database = createCourse("CS103", "Database Management", 3);
        Course webDevelopment = createCourse("CS104", "Web Development", 3);
        Course algorithms = createCourse("CS105", "Algorithms", 4);
        Course systemDesign = createCourse("CS106", "System Design", 3);

        // Add courses to programs
        softwareEngineering.setCourses(Arrays.asList(java, dataStructures, database, webDevelopment, systemDesign));
        computerScience.setCourses(Arrays.asList(java, dataStructures, algorithms, database, systemDesign));
        informationTechnology.setCourses(Arrays.asList(java, webDevelopment, database));

        programRepo.saveAll(Arrays.asList(softwareEngineering, computerScience, informationTechnology));

        // 3. Initialize Semesters
        Semester sem1_2024 = createSemester(2024, SemesterNumber.SEMESTER_1);
        Semester sem2_2024 = createSemester(2024, SemesterNumber.SEMESTER_2);
        Semester sem1_2025 = createSemester(2025, SemesterNumber.SEMESTER_3);

        semesterRepo.saveAll(Arrays.asList(sem1_2024, sem2_2024, sem1_2025));

        // 4. Initialize Lecturers
        Lecturer lecturer1 = createLecturer("Dr. John Smith", "john.smith@tlu.edu", Department.COMPUTER_SCIENCE);
        Lecturer lecturer2 = createLecturer("Prof. Sarah Johnson", "sarah.johnson@tlu.edu", Department.COMPUTER_SCIENCE);
        Lecturer lecturer3 = createLecturer("Dr. Mike Brown", "mike.brown@tlu.edu", Department.INFORMATION_TECHNOLOGY);
        Lecturer lecturer4 = createLecturer("Prof. Emma Davis", "emma.davis@tlu.edu", Department.COMPUTER_SCIENCE);

        lecturerRepo.saveAll(Arrays.asList(lecturer1, lecturer2, lecturer3, lecturer4));

        // 5. Initialize Sections
        Section section1 = createSection(java, sem1_2024, lecturer1);
        Section section2 = createSection(dataStructures, sem1_2024, lecturer2);
        Section section3 = createSection(database, sem2_2024, lecturer3);
        Section section4 = createSection(webDevelopment, sem2_2024, lecturer4);
        Section section5 = createSection(algorithms, sem1_2025, lecturer1);
        Section section6 = createSection(systemDesign, sem1_2025, lecturer2);

        sectionRepo.saveAll(Arrays.asList(section1, section2, section3, section4, section5, section6));

        // 6. Initialize Students
        Student student1 = createStudent("John", "Doe", "john.doe@student.tlu.edu", 
            LocalDate.of(2003, 5, 15), softwareEngineering, StudentStatus.ACTIVE);
        Student student2 = createStudent("Jane", "Smith", "jane.smith@student.tlu.edu", 
            LocalDate.of(2003, 8, 22), softwareEngineering, StudentStatus.ACTIVE);
        Student student3 = createStudent("Michael", "Johnson", "michael.johnson@student.tlu.edu", 
            LocalDate.of(2002, 3, 10), computerScience, StudentStatus.ACTIVE);
        Student student4 = createStudent("Emily", "Brown", "emily.brown@student.tlu.edu", 
            LocalDate.of(2003, 11, 28), informationTechnology, StudentStatus.ACTIVE);
        Student student5 = createStudent("David", "Wilson", "david.wilson@student.tlu.edu", 
            LocalDate.of(2003, 7, 5), softwareEngineering, StudentStatus.ACTIVE);

        studentRepo.saveAll(Arrays.asList(student1, student2, student3, student4, student5));

        // 7. Initialize Grades
        Grade grade1 = createGrade(new BigDecimal("8.5"), new BigDecimal("7.5"), new BigDecimal("8.0"));
        Grade grade2 = createGrade(new BigDecimal("9.0"), new BigDecimal("8.5"), new BigDecimal("9.2"));
        Grade grade3 = createGrade(new BigDecimal("7.5"), new BigDecimal("7.0"), new BigDecimal("7.8"));
        Grade grade4 = createGrade(new BigDecimal("8.0"), new BigDecimal("8.5"), new BigDecimal("8.7"));
        Grade grade5 = createGrade(new BigDecimal("9.5"), new BigDecimal("9.0"), new BigDecimal("9.3"));
        Grade grade6 = createGrade(new BigDecimal("8.2"), new BigDecimal("8.0"), new BigDecimal("8.5"));
        Grade grade7 = createGrade(new BigDecimal("7.8"), new BigDecimal("7.5"), new BigDecimal("7.9"));
        Grade grade8 = createGrade(new BigDecimal("9.1"), new BigDecimal("9.2"), new BigDecimal("9.0"));

        gradeRepo.saveAll(Arrays.asList(grade1, grade2, grade3, grade4, grade5, grade6, grade7, grade8));

        // 8. Initialize Enrollments
        Enrollment enrollment1 = createEnrollment(student1, section1, grade1, EnrollmentStatus.COMPLETED);
        Enrollment enrollment2 = createEnrollment(student1, section2, grade2, EnrollmentStatus.COMPLETED);
        Enrollment enrollment3 = createEnrollment(student2, section1, grade3, EnrollmentStatus.COMPLETED);
        Enrollment enrollment4 = createEnrollment(student2, section3, grade4, EnrollmentStatus.IN_PROGRESS);
        Enrollment enrollment5 = createEnrollment(student3, section2, grade5, EnrollmentStatus.COMPLETED);
        Enrollment enrollment6 = createEnrollment(student4, section4, grade6, EnrollmentStatus.IN_PROGRESS);
        Enrollment enrollment7 = createEnrollment(student5, section1, grade7, EnrollmentStatus.COMPLETED);
        Enrollment enrollment8 = createEnrollment(student3, section5, grade8, EnrollmentStatus.IN_PROGRESS);

        enrollmentRepo.saveAll(Arrays.asList(enrollment1, enrollment2, enrollment3, enrollment4, 
                                            enrollment5, enrollment6, enrollment7, enrollment8));

        // 9. Initialize Users
        User user1 = createUser("john.doe", "123456", Role.STUDENT, student1.getId());
        User user2 = createUser("jane.smith", "123456", Role.STUDENT, student2.getId());
        User user3 = createUser("michael.johnson", "123456", Role.STUDENT, student3.getId());
        User user4 = createUser("emily.brown", "123456", Role.STUDENT, student4.getId());
        User user5 = createUser("david.wilson", "123456", Role.STUDENT, student5.getId());
        User lecturer1_user = createUser("john.smith", "123456", Role.STUDENT, null);
        User lecturer2_user = createUser("sarah.johnson", "123456", Role.STUDENT, null);
        User admin = createUser("admin", "admin123", Role.ADMIN, null);

        userRepo.saveAll(Arrays.asList(user1, user2, user3, user4, user5, lecturer1_user, lecturer2_user, admin));
    }

    private Program createProgram(String name) {
        Program program = new Program();
        program.setName(name);
        return programRepo.save(program);
    }

    private Course createCourse(String code, String name, int credit) {
        Course course = new Course();
        course.setCode(code);
        course.setName(name);
        course.setCredit(credit);
        return courseRepo.save(course);
    }

    private Semester createSemester(int year, SemesterNumber semesterNumber) {
        Semester semester = new Semester();
        semester.setYear(year);
        semester.setSemesterNumber(semesterNumber);
        return semesterRepo.save(semester);
    }

    private Lecturer createLecturer(String name, String email, Department department) {
        Lecturer lecturer = new Lecturer();
        lecturer.setName(name);
        lecturer.setEmail(email);
        lecturer.setDepartment(department);
        return lecturerRepo.save(lecturer);
    }

    private Section createSection(Course course, Semester semester, Lecturer lecturer) {
        Section section = new Section();
        section.setCourse(course);
        section.setSemester(semester);
        section.setLecturer(lecturer);
        return sectionRepo.save(section);
    }

    private Student createStudent(String firstName, String lastName, String email, 
                                 LocalDate dateOfBirth, Program program, StudentStatus status) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setDateOfBirth(dateOfBirth);
        student.setProgram(program);
        student.setStatus(status);
        return studentRepo.save(student);
    }

    private Grade createGrade(BigDecimal continuous, BigDecimal midterm, BigDecimal finalGrade) {
        Grade grade = new Grade();
        grade.setContinuousGrade(continuous);
        grade.setMidtermGrade(midterm);
        grade.setFinalGrade(finalGrade);
        return gradeRepo.save(grade);
    }

    private Enrollment createEnrollment(Student student, Section section, Grade grade, EnrollmentStatus status) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setSection(section);
        enrollment.setGrade(grade);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setStatus(status);
        return enrollmentRepo.save(enrollment);
    }

    private User createUser(String username, String password, Role role, Long studentId) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        user.setStudentId(studentId);
        return userRepo.save(user);
    }
}

