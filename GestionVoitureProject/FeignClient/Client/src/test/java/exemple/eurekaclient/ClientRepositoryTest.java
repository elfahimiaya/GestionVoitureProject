package exemple.eurekaclient;

import exemple.eurekaclient.entities.Client;
import exemple.eurekaclient.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void findAll_shouldReturnAllClients() {
        clientRepository.save(new Client(null, "aya", 22));
        clientRepository.save(new Client(null, "douaa", 22));

        List<Client> clients = clientRepository.findAll();

        assertEquals(5, clients.size());
        assertEquals("aya", clients.get(0).getNom());
        assertEquals("douaa", clients.get(1).getNom());
    }

    @Test
    void findById_shouldReturnClient() {
        Client savedClient = clientRepository.save(new Client(null, "aya", 22));
        Optional<Client> client = clientRepository.findById(savedClient.getId());

        assertTrue(client.isPresent());
        assertEquals("aya", client.get().getNom());
    }
}
