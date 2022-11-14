package com.vmware.testharness.modern.fib;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FibController.class)
public class FibControllerTest {

    @MockBean
    FibService fibService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetByIndex() throws Exception {

        when(fibService.get(1)).thenReturn(1);

        ResultActions result = mockMvc.perform(get("/fib?index=1"))
                .andExpect(status().isOk());

        verify(fibService).get(1);

    }

    @Test
    public void testGetByBadIndex() throws Exception {

        when(fibService.get(-1)).thenThrow(new FibException("negative number"));

        ResultActions result = mockMvc.perform(get("/fib?index=-1"))
                .andExpect(status().isBadRequest());

        verify(fibService).get(-1);

    }

}
