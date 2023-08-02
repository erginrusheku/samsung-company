package samsungcompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import samsungcompany.controller.KitchenController;
import samsungcompany.dto.KitchenDTO;
import samsungcompany.mapper.KitchenMapper;
import samsungcompany.service.KitchenService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(KitchenController.class)
public class KitchenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KitchenService kitchenService;

    @Mock
    private KitchenMapper kitchenMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        KitchenDTO kitchenDTO1 = new KitchenDTO(1L,"Toskan steel",2,3,4,700);
        KitchenDTO kitchenDTO2 = new KitchenDTO(2L,"Regiana Steel", 2,4,3,800);
        List<KitchenDTO> kitchenDTOList = Arrays.asList(kitchenDTO1,kitchenDTO2);

        when(kitchenService.getAll()).thenReturn(kitchenDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/kitchen"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Toskan steel"))
                .andExpect(jsonPath("$[0].height").value(2))
                .andExpect(jsonPath("$[0].weight").value(3))
                .andExpect(jsonPath("$[0].width").value(4))
                .andExpect(jsonPath("$[0].price").value(700))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Regiana Steel"))
                .andExpect(jsonPath("$[1].height").value(2))
                .andExpect(jsonPath("$[1].weight").value(4))
                .andExpect(jsonPath("$[1].width").value(3))
                .andExpect(jsonPath("$[1].price").value(800));
    }

    @Test
    public void testAddKitchen() throws Exception {
        // Given
        KitchenDTO kitchenDTO = new KitchenDTO(1L, "Toskan steel", 2, 3, 4, 700);
        // When
        when(kitchenService.addKitchen(kitchenDTO)).thenReturn(kitchenDTO);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/kitchen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Toskan steel\", \"height\": 2, \"weight\": 3,\"width\": 4, \"price\": 700 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Toskan steel"))
                .andExpect(jsonPath("$.height").value(2))
                .andExpect(jsonPath("$.weight").value(3))
                .andExpect(jsonPath("$.width").value(4))
                .andExpect(jsonPath("$.price").value(700));
    }

    @Test
    public void testGetBId() throws Exception {
        Long id = 1L;
        KitchenDTO kitchenDTO = new KitchenDTO(1L, "Toskan steel", 2, 3, 4, 700);

        when(kitchenService.getById(id)).thenReturn(kitchenDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/kitchen/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Toskan steel"))
                .andExpect(jsonPath("$.height").value(2))
                .andExpect(jsonPath("$.weight").value(3))
                .andExpect(jsonPath("$.width").value(4))
                .andExpect(jsonPath("$.price").value(700));

    }

    @Test
    public void testUpdateKitchen() throws Exception {
        // Given
        Long id = 1L;
        KitchenDTO kitchenDTO2 = new KitchenDTO(1L, "Regiana Steel", 2, 4, 3, 800);

        when(kitchenService.updateKitchen(id, kitchenDTO2)).thenReturn(kitchenDTO2);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.put("/kitchen/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1,\"name\": \"Regiana Steel\", \"height\": 2, \"weight\": 4, \"width\": 3, \"price\": 800 }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Regiana Steel"))
                .andExpect(jsonPath("$.height").value(2))
                .andExpect(jsonPath("$.weight").value(4))
                .andExpect(jsonPath("$.width").value(3))
                .andExpect(jsonPath("$.price").value(800));
    }

    @Test
    public void testDeleteById() throws Exception {

        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/kitchen/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
