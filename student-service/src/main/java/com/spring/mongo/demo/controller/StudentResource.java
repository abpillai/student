package com.spring.mongo.demo.controller;



import com.spring.mongo.demo.model.Student;
import com.spring.mongo.demo.repository.StudentRepository;
import com.spring.mongo.demo.service.StudentService;
import com.spring.mongo.demo.util.HeaderUtil;
import com.spring.mongo.demo.util.ResponseUtil;
import com.spring.mongo.demo.web.rest.errors.BadRequestAlertException;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import tech.jhipster.web.util.HeaderUtil;
//import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.company.project.student.domain.Student}.
 */
@RestController
@RequestMapping("/api")
public class StudentResource {

    private final Logger log = LoggerFactory.getLogger(StudentResource.class);

    private static final String ENTITY_NAME = "student";
    
    @Autowired
	private StudentService studentService;
    
    
    

   
  
    private final StudentRepository studentRepository;

    public StudentResource(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * {@code POST  /students} : Create a new student.
     *
     * @param student the student to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new student, or with status {@code 400 (Bad Request)} if the student has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) throws URISyntaxException {
        log.debug("REST request to save Student : {}", student);
        if (student.getId() != null) {
            throw new BadRequestAlertException("A new student cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Student result = studentRepository.save(student);
        return ResponseEntity
            .created(new URI("/api/students/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("student", true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    
    /**
     * {@code GET  /students} : get all the students.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of students in body.
     */
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        log.debug("REST request to get all Students");
        return studentRepository.findAll();
    }
    
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable String id) {
        log.debug("REST request to get Student : {}", id);
        Optional<Student> student = studentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(student);
    }

	@GetMapping("/students/byLastName/{lastName}")
	public Student getStudentBylName(@PathVariable String lastName) {
		return studentService.getStudentByLastName(lastName);
	}
	
	
	@GetMapping("/students/one-by-firstName/{firstName}")
	public Student getStudentByfName(@PathVariable String firstName) {
		return studentService.getStudentByFirstName(firstName);
	}
	
	@GetMapping("/one-by-depName/{depName}")
	public Student getStudentBydName(@PathVariable String depName) {
		return studentService.getStudentByDepName(depName);
	}

    
}
