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
import com.deepak.passwdapi.domain.Group;
import com.deepak.passwdapi.repository.GroupRepository;
import com.deepak.passwdapi.service.GroupService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PasswdapiApplication.class)
public class GroupResourceIntTest {

    private static final String DEFAULT_NAME = "docker";
    

    private static final Long DEFAULT_GID = 1002L;

    

    @Autowired
    private GroupRepository groupRepository;

    
    @Autowired
    private GroupService groupService;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restGroupMockMvc;

    private Group group;

    @Before
    public void setup() {
        GroupResource groupResource = new GroupResource(groupService, groupRepository);

        this.restGroupMockMvc = MockMvcBuilders.standaloneSetup(groupResource)
            .setMessageConverters(jacksonMessageConverter)
            .build();
    }

    /**
     * Create a Group.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which has a required relationship to the Group entity.
     */
    public static Group createEntity() {
        Group group = new Group();
        group.setGid(DEFAULT_GID);
        group.setName(DEFAULT_NAME);
        return group;
    }

    @Before
    public void initTest() {
        group = createEntity();
       
    }

    

    @Ignore
    @Test
    public void getAllGroups() throws Exception {
       
        // Get all the groups
        restGroupMockMvc.perform(get("/api/groups")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    @Ignore
    @Test
    public void getGroup() throws Exception {
        

        // Get the group
        restGroupMockMvc.perform(get("/api/groups/{id}", group.getGid()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.uid").value(group.getGid()));
    }
}
