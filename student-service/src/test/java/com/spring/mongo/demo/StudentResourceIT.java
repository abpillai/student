package com.spring.mongo.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.mongo.demo.model.Student;
import com.spring.mongo.demo.repository.StudentRepository;

/**
 * Integration tests for the {@link StudentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StudentResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEP_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/students";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_API_URL_FIRSTNAME = "/api" + "/one-by-firstName/"+DEFAULT_FIRST_NAME;
    private static final String ENTITY_API_URL_LASTNAME = "/api" + "/one-by-lastName/"+DEFAULT_LAST_NAME;
    private static final String ENTITY_API_URL_DEPNAME = "/api" + "/one-by-depName/"+DEFAULT_DEP_NAME;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc restStudentMockMvc;

    private Student student;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createEntity() {
        Student student = new Student().firstName(DEFAULT_FIRST_NAME).lastName(DEFAULT_LAST_NAME).depName(DEFAULT_DEP_NAME);
        return student;
    }

  
    @BeforeEach
    public void initTest() {
        studentRepository.deleteAll();
        student = createEntity();
    }

    @Test
    void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();
        // Create the Student
        restStudentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testStudent.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testStudent.getDepName()).isEqualTo(DEFAULT_DEP_NAME);
    }

    
    @Test
    void getAllStudents() throws Exception {
        // Initialize the database
        studentRepository.save(student);

        // Get all the studentList
        restStudentMockMvc
            .perform(get(ENTITY_API_URL))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].depName").value(hasItem(DEFAULT_DEP_NAME)));
    }

    @Test
    void getStudentByFirstName() throws Exception {
        // Initialize the database
        studentRepository.save(student);

        // Get the student
        restStudentMockMvc
            .perform(get(ENTITY_API_URL_FIRSTNAME, student.getFirstName()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            //.andExpect(jsonPath("$.id").value(student.getId()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.depName").value(DEFAULT_DEP_NAME));
    }
    
    @Test
    void getStudentByLastName() throws Exception {
        // Initialize the database
        studentRepository.save(student);

        // Get the student
        restStudentMockMvc
            .perform(get(ENTITY_API_URL_LASTNAME, student.getLastName()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            //.andExpect(jsonPath("$.id").value(student.getId()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.depName").value(DEFAULT_DEP_NAME));
    }
    
    @Test
    void getStudentByDepName() throws Exception {
        // Initialize the database
        studentRepository.save(student);

        // Get the student
        restStudentMockMvc
            .perform(get(ENTITY_API_URL_DEPNAME, student.getDepName()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            //.andExpect(jsonPath("$.id").value(student.getId()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.depName").value(DEFAULT_DEP_NAME));
    }

    

    
}
