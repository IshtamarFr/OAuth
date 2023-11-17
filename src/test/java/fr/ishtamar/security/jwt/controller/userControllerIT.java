package fr.ishtamar.security.jwt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ishtamar.security.jwt.dto.AuthRequest;
import fr.ishtamar.security.jwt.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class userControllerIT {
    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper=new ObjectMapper();

    @Test
    public void testWelcomeIsOk() throws Exception {
        this.mockMvc.perform(get("/auth/welcome")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome")));
    }

    @Test
    public void testAddNewUserEmailIsAlreadyUsed() throws Exception {
        UserInfo mockUser=new UserInfo();
        mockUser.setName("Ishta");
        mockUser.setEmail("test@test.com");
        mockUser.setPassword("123456");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/addNewUser")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(mockUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGenerateToken() throws Exception {
        //[X-01] Choose getName or getEmail
        AuthRequest mockRequest=new AuthRequest();
        mockRequest.setUsername("test@test.com");
        mockRequest.setPassword("123456");
        this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/generateToken")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(mockRequest)))
                .andExpect(status().isOk());
    }
}
