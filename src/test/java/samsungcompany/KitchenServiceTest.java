package samsungcompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import samsungcompany.dto.KitchenDTO;
import samsungcompany.mapper.KitchenMapper;
import samsungcompany.model.Kitchen;
import samsungcompany.repository.KitchenRepository;
import samsungcompany.service.KitchenService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class KitchenServiceTest {
    @InjectMocks
    private KitchenService kitchenService;

    @Mock
    private KitchenRepository kitchenRepository;

    @Mock
    private KitchenMapper kitchenMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetAll(){
        Kitchen kitchen1 = new Kitchen(1L,"Toskan steel",2,3,4,700);
        Kitchen kitchen2 = new Kitchen(2L,"Regiana Steel", 2,4,3,800);
        List<Kitchen> kitchens = Arrays.asList(kitchen1,kitchen2);

        when(kitchenRepository.findAll()).thenReturn(kitchens);
        when(kitchenMapper.toDto(kitchen1)).thenReturn(new KitchenDTO(1L,"Toskan steel",2,3,4,700));
        when(kitchenMapper.toDto(kitchen2)).thenReturn(new KitchenDTO(2L,"Regiana Steel", 2,4,3,800));

        List<KitchenDTO> result = kitchenService.getAll();

        assertEquals(kitchens.size(),result.size());
    }

    @Test
    public void testAddKitchen() throws Exception {
        Long id = 1L;
        Kitchen kitchen = new Kitchen(id,"Toskan steel",2,3,4,700);
        KitchenDTO kitchenDTO = new KitchenDTO(id,"Toskan steel",2,3,4,700);

        when(kitchenRepository.save(kitchen)).thenReturn(kitchen);
        when(kitchenMapper.toEntity(kitchenDTO)).thenReturn(kitchen);
        when(kitchenMapper.toDto(kitchen)).thenReturn(kitchenDTO);

        KitchenDTO result = kitchenService.addKitchen(kitchenDTO);

        assertEquals(kitchenDTO,result);

    }

    @Test
    public void testGetById() throws Exception{

        Long id = 1L;
        Kitchen kitchen = new Kitchen(id,"Toskan steel",2,3,4,700);
        KitchenDTO kitchenDTO = new KitchenDTO(id,"Toskan steel",2,3,4,700);

        when(kitchenRepository.findById(id)).thenReturn(Optional.of(kitchen));
        when(kitchenMapper.toDto(kitchen)).thenReturn(kitchenDTO);

        KitchenDTO result = kitchenService.getById(id);

        assertEquals(kitchenDTO,result);

    }

    @Test
    public void testUpdateKitchen() throws Exception {
        Long id = 1L;
        Kitchen kitchen = new Kitchen(id,"Regiana steel",2,4,3,800);
        KitchenDTO kitchenDTO = new KitchenDTO(id,"Toskan steel",2,4,3,800);

        when(kitchenMapper.toDto(kitchen)).thenReturn(kitchenDTO);
        when(kitchenRepository.findById(id)).thenReturn(Optional.of(new Kitchen()));
        when(kitchenRepository.save(any(Kitchen.class))).thenReturn(kitchen);

        KitchenDTO result = kitchenService.updateKitchen(id,kitchenDTO);

        assertEquals(kitchenDTO, result);
    }


    @Test
    public  void testDeleteKitchen() {
        Long id = 1L;

        kitchenService.deleteById(id);

        verify(kitchenRepository, times(1)).deleteById(id);
    }















}
