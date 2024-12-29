package exemple.eurekaclient;

import exemple.eurekaclient.controller.ClientController;
import exemple.eurekaclient.entities.Client;
import exemple.eurekaclient.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientRepository clientRepository;

    @BeforeEach
    void setup() {
        Client client1 = new Client(1L, "aya", 22);
        Client client2 = new Client(2L, "douaa", 22);

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client1, client2));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client1));
    }

    @Test
    void getClients_shouldReturnClientList() throws Exception {
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("aya"))
                .andExpect(jsonPath("$[1].nom").value("douaa"));
    }

    @Test
    void getClient_shouldReturnSingleClient() throws Exception {
        mockMvc.perform(get("/client/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("aya"));
    }
}
