package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.request.TransactionRequest;
import com.eteration.simplebanking.services.accountService.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;



@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    AccountService accountService;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    public void testGetAccount() throws Exception {
        // Create a sample Account object
        Account account = new Account("Kerem Karaca", "669-7788");
        account.setBalance(950.0);

        // Mock the service method to return the sample account
        when(accountService.findAccount("669-7788")).thenReturn(account);

        // Perform the GET request to /account/v1/669-7788
        this.mockMvc.perform(get("/account/v1/669-7788"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber", is("669-7788")))
                .andExpect(jsonPath("$.owner", is("Kerem Karaca")))
                .andExpect(jsonPath("$.balance", is(950.0)));

        // Verify that the service method was called with the correct parameter
        verify(accountService, times(1)).findAccount("669-7788");
    }

    @Test
    public void testCredit() throws Exception {

        // Mock the service method to return a TransactionStatus
        when(accountService.deposit("669-7788", 1000.0)).thenReturn(new TransactionStatus("OK", UUID.fromString("67f1aada-637d-4469-a650-3fb6352527ba")));


        TransactionRequest anObject = new TransactionRequest();
        anObject.setAmount(1000.0);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);
        // Perform a POST request to /account/v1/credit/669-7788
        this.mockMvc.perform(post("/account/v1/credit/669-7788")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .accept(APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.approvalCode", is("67f1aada-637d-4469-a650-3fb6352527ba")));

        // Verify that the service method was called with the correct parameters
        verify(accountService, times(1)).deposit("669-7788", 1000.0);
    }

    @Test
    public void testDebit() throws Exception {
        // Mock the service method to return a TransactionStatus
        when(accountService.withdraw("669-7788", 50.0)).thenReturn(new TransactionStatus("OK", UUID.fromString("a66cce54-335b-4e46-9b49-05017c4b38dd")));

        TransactionRequest anObject = new TransactionRequest();
        anObject.setAmount(50);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);

        // Perform a POST request to /account/v1/debit/669-7788
        this.mockMvc.perform(post("/account/v1/debit/669-7788")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("OK")))
                .andExpect(jsonPath("$.approvalCode", is("a66cce54-335b-4e46-9b49-05017c4b38dd")));

        // Verify that the service method was called with the correct parameters
        verify(accountService, times(1)).withdraw("669-7788", 50.0);
    }


}
