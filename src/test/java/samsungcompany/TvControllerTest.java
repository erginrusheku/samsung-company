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
import samsungcompany.controller.TvController;
import samsungcompany.dto.TvDTO;
import samsungcompany.mapper.TvMapper;
import samsungcompany.service.TvService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TvController.class)
public class TvControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TvService tvService;

    @Mock
    TvMapper tvMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {

        TvDTO tvDTO1 = new TvDTO(1L,"Samsung NEO QLED",85,1300);
        TvDTO tvDTO2 = new TvDTO(2L,"Samsung NEO",75,1000);
        List<TvDTO> tvDTOS = Arrays.asList(tvDTO1,tvDTO2);

        when(tvService.getAll()).thenReturn(tvDTOS);

        mockMvc.perform(get("/tv")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Samsung NEO QLED"))
                .andExpect(jsonPath("$[0].inch").value(85))
                .andExpect(jsonPath("$[0].price").value(1300))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Samsung NEO"))
                .andExpect(jsonPath("$[1].inch").value(75))
                .andExpect(jsonPath("$[1].price").value(1000));
    }

    @Test
    public void testAddTv() throws Exception {

        TvDTO tvDTO = new TvDTO(1L,"Samsung NEO QLED",85,1300);

        when(tvService.addTv(tvDTO)).thenReturn(tvDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/tv")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Samsung NEO QLED\", \"inch\": 85, \"price\": 1300 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Samsung NEO QLED"))
                .andExpect(jsonPath("$.inch").value(85))
                .andExpect(jsonPath("$.price").value(1300));
    }

    @Test
    public void testGetById() throws Exception {
        Long id = 1L;
        TvDTO tvDTO = new TvDTO(id, "Samsung NEO QLED", 85, 1300);

        when(tvService.getById(id)).thenReturn(tvDTO);

        mockMvc.perform(get("/tv/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Samsung NEO QLED"))
                .andExpect(jsonPath("$.inch").value(85))
                .andExpect(jsonPath("$.price").value(1300));
    }

    @Test
    public void testUpdateTv() throws Exception {
        Long id = 1L;
        TvDTO tvDTO = new TvDTO(id, "Samsung QLED", 75, 1000);

        when(tvService.updateTv(id, tvDTO)).thenReturn(tvDTO);

        mockMvc.perform(put("/tv/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 1, \"name\": \"Samsung QLED\", \"inch\": 75, \"price\": 1000 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Samsung QLED"))
                .andExpect(jsonPath("$.inch").value(75))
                .andExpect(jsonPath("$.price").value(1000));
    }

    @Test
    public void testDeleteById() throws Exception {

        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/tv/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
