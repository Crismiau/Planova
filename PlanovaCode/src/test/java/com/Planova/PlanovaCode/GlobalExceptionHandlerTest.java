package com.Planova.PlanovaCode;

import com.Planova.PlanovaCode.exception.DuplicateResourceException;
import com.Planova.PlanovaCode.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @RestController
    @RequestMapping("/test")
    @Component
    static class TestController {
        @GetMapping("/not-found")
        public void throwNotFound() {
            throw new ResourceNotFoundException("Test not found");
        }

        @GetMapping("/duplicate")
        public void throwDuplicate() {
            throw new DuplicateResourceException("Test duplicate");
        }

        @GetMapping("/bad-request")
        public void throwIllegalArgument() {
            throw new IllegalArgumentException("Test bad request");
        }

        @GetMapping("/generic")
        public void throwGeneric() throws Exception {
            throw new Exception("Test generic error");
        }
    }

    @Test
    public void testResourceNotFoundException() throws Exception {
        mockMvc.perform(get("/test/not-found").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Resource not found"))
                .andExpect(jsonPath("$.detail").value("Test not found"));
    }

    @Test
    public void testDuplicateResourceException() throws Exception {
        mockMvc.perform(get("/test/duplicate").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.title").value("Duplicate resource"))
                .andExpect(jsonPath("$.detail").value("Test duplicate"));
    }

    @Test
    public void testIllegalArgumentException() throws Exception {
        mockMvc.perform(get("/test/bad-request").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Bad request"))
                .andExpect(jsonPath("$.detail").value("Test bad request"));
    }

    @Test
    public void testGenericException() throws Exception {
        mockMvc.perform(get("/test/generic").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.title").value("An unexpected error occurred"))
                .andExpect(jsonPath("$.detail").value("Test generic error"));
    }
}
