package exemple.eurekafeignclient;

import exemple.eurekafeignclient.controller.VoitureController;
import exemple.eurekafeignclient.entities.Client;
import exemple.eurekafeignclient.entities.Voiture;
import exemple.eurekafeignclient.service.ClientService;
import exemple.eurekafeignclient.service.VoitureService;
import exemple.eurekafeignclient.repository.VoitureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class VoitureControllerTest {

    @Mock
    private VoitureService voitureService;

    @Mock
    private ClientService clientService;

    @Mock
    private VoitureRepository voitureRepository;

    @InjectMocks
    private VoitureController voitureController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetVoitures() {
        // Arrange
        Voiture voiture = new Voiture();
        voiture.setId(1L);
        voiture.setMarque("Toyota");
        voiture.setMatricule("A25333");
        voiture.setModel("Corolla");
        Client client = new Client();
        client.setId(1L);
        client.setNom("John Doe");
        voiture.setClient(client);

        when(voitureService.findAll()).thenReturn(Collections.singletonList(voiture));
        when(clientService.getClient(voiture.getId_client())).thenReturn(client);

        // Act
        var result = voitureController.getVoitures();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Toyota", result.get(0).getMarque());
        verify(voitureService, times(1)).findAll();
    }

    @Test
    void testGetVoitureById() {
        // Arrange
        Voiture voiture = new Voiture();
        voiture.setId(1L);
        voiture.setMarque("Toyota");
        voiture.setMatricule("A25333");
        voiture.setModel("Corolla");
        Client client = new Client();
        client.setId(1L);
        client.setNom("John Doe");
        voiture.setClient(client);

        when(voitureRepository.findById(1L)).thenReturn(Optional.of(voiture));
        when(clientService.getClient(voiture.getId_client())).thenReturn(client);

        // Act
        ResponseEntity<Object> response = voitureController.getVoitureById(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        Voiture returnedVoiture = (Voiture) response.getBody();
        assertEquals("Toyota", returnedVoiture.getMarque());
        verify(voitureRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveVoiture() {
        // Arrange
        Voiture voiture = new Voiture();
        voiture.setMarque("Renault");
        voiture.setMatricule("B63456");
        voiture.setModel("Magane");
        int clientId = 1;

        Client client = new Client();
        client.setId(1L);
        client.setNom("John Doe");

        when(clientService.getClient(clientId)).thenReturn(client);
        when(voitureService.save(voiture)).thenReturn(voiture);

        // Act
        ResponseEntity<Object> response = voitureController.save(clientId, voiture);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(voitureService, times(1)).save(voiture);
    }

    @Test
    void testUpdateVoiture() {
        // Arrange
        Long id = 1L;
        Voiture updatedVoiture = new Voiture();
        updatedVoiture.setMarque("Peugeot");
        updatedVoiture.setMatricule("A554444");
        updatedVoiture.setModel("301");
        updatedVoiture.setId_client(1);

        Voiture existingVoiture = new Voiture();
        existingVoiture.setId(id);
        existingVoiture.setMarque("Toyota");
        existingVoiture.setMatricule("A25333");
        existingVoiture.setModel("Corolla");

        Client client = new Client();
        client.setId(1L);
        client.setNom("John Doe");

        when(voitureRepository.findById(id)).thenReturn(Optional.of(existingVoiture));
        when(clientService.getClient(updatedVoiture.getId_client())).thenReturn(client);
        when(voitureRepository.save(existingVoiture)).thenReturn(existingVoiture);

        // Act
        ResponseEntity<Object> response = voitureController.update(id, updatedVoiture);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(voitureRepository, times(1)).findById(id);
        verify(voitureRepository, times(1)).save(existingVoiture);
    }
}
