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
import samsungcompany.controller.MobileController;
import samsungcompany.dto.MobileDTO;
import samsungcompany.mapper.MobileMapper;
import samsungcompany.service.MobileService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MobileController.class)
public class MobileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MobileService mobileService;

    @Mock
    private MobileMapper mobileMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() throws Exception {
        MobileDTO mobileDTO1 = new MobileDTO(1L,"Samsung S10+",32, 8, 4100, 300);
        MobileDTO mobileDTO2 = new MobileDTO(2L,"Samsung S22", 128, 64,5000,1400);
        List<MobileDTO> mobileDTOS = Arrays.asList(mobileDTO1,mobileDTO2);

        when(mobileService.getAll()).thenReturn(mobileDTOS);

        mockMvc.perform(get("/mobile")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Samsung S10+"))
                .andExpect(jsonPath("$[0].storage").value(32))
                .andExpect(jsonPath("$[0].ram").value(8))
                .andExpect(jsonPath("$[0].battery").value(4100))
                .andExpect(jsonPath("$[0].price").value(300))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Samsung S22"))
                .andExpect(jsonPath("$[1].storage").value(128))
                .andExpect(jsonPath("$[1].ram").value(64))
                .andExpect(jsonPath("$[1].battery").value(5000))
                .andExpect(jsonPath("$[1].price").value(1400));
    }

    @Test
    public void testAddMobile() throws Exception {

        MobileDTO mobileDTO = new MobileDTO(1L,"Samsung S10+",32, 8, 4100, 300);

        when(mobileService.addMobile(mobileDTO)).thenReturn(mobileDTO);


        mockMvc.perform(MockMvcRequestBuilders.post("/mobile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Samsung S10+\", \"storage\": 32, \"ram\": 8, \"battery\": 4100,\"price\": 300 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Samsung S10+"))
                .andExpect(jsonPath("$.storage").value(32))
                .andExpect(jsonPath("$.ram").value(8))
                .andExpect(jsonPath("$.battery").value(4100))
                .andExpect(jsonPath("$.price").value(300));
    }

    @Test
    public void testGetById() throws Exception {
        Long id = 1L;
        MobileDTO mobileDTO = new MobileDTO(id, "Samsung S10+", 32, 8, 4100, 300);

        when(mobileService.getById(id)).thenReturn(mobileDTO);

        mockMvc.perform(get("/mobile/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Samsung S10+"))
                .andExpect(jsonPath("$.storage").value(32))
                .andExpect(jsonPath("$.ram").value(8))
                .andExpect(jsonPath("$.battery").value(4100))
                .andExpect(jsonPath("$.price").value(300));
    }

    @Test
    public void testUpdateMobile() throws Exception {
        Long id = 1L;
        MobileDTO mobileDTO = new MobileDTO(id, "Samsung S22", 128, 64, 5000, 1400);

        when(mobileService.updateMobile(id, mobileDTO)).thenReturn(mobileDTO);

        mockMvc.perform(put("/mobile/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Samsung S22\", \"storage\": 128, \"ram\": 64, \"battery\": 5000, \"price\": 1400 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Samsung S22"))
                .andExpect(jsonPath("$.storage").value(128))
                .andExpect(jsonPath("$.ram").value(64))
                .andExpect(jsonPath("$.battery").value(5000))
                .andExpect(jsonPath("$.price").value(1400));
    }

    @Test
    public void testDeleteById() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/mobile/{id}", id)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
