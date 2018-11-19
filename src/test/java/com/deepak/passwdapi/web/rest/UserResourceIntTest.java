package com.deepak.passwdapi.web.rest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.deepak.passwdapi.PasswdapiApplication;
import com.deepak.passwdapi.domain.User;
import com.deepak.passwdapi.repository.UserRepository;
import com.deepak.passwdapi.service.UserService;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PasswdapiApplication.class)
public class UserResourceIntTest {

    private static final String DEFAULT_NAME = "root";
    private static final String DEFAULT_COMMENT = "root";
    private static final String DEFAULT_HOME = "/root";
    private static final String DEFAULT_SHELL = "/bin/bash";
    

    private static final Long DEFAULT_UID = 0L;
    private static final Long DEFAULT_GID = 0L;

    

    @Autowired
    private UserRepository userRepository;

    
    @Autowired
    private UserService userService;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restUserMockMvc;

    private User user;

    @Before
    public void setup() {
        UserResource userResource = new UserResource(userService, userRepository);

        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(userResource)
            .setMessageConverters(jacksonMessageConverter)
            .build();
    }

    /**
     * Create a User.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which has a required relationship to the User entity.
     */
    public static User createEntity() {
        User user = new User();
        user.setComment(DEFAULT_COMMENT);
        user.setGid(DEFAULT_GID);
        user.setHome(DEFAULT_HOME);
        user.setName(DEFAULT_NAME);
        user.setShell(DEFAULT_SHELL);
        user.setUid(DEFAULT_UID);
        return user;
    }

    @Before
    public void initTest() {
        user = createEntity();
       
    }

    

    @Ignore
    @Test
    public void getAllUsers() throws Exception {
       
        // Get all the users
        restUserMockMvc.perform(get("/api/users")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Ignore
    @Test
    public void getUser() throws Exception {
        

        // Get the user
        restUserMockMvc.perform(get("/api/users/{id}", user.getUid()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.uid").value(user.getUid()));
    }

}
