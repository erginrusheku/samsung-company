package samsungcompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import samsungcompany.dto.MobileDTO;
import samsungcompany.mapper.MobileMapper;
import samsungcompany.model.Mobile;
import samsungcompany.repository.MobileRepository;
import samsungcompany.service.MobileService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MobileServiceTest {

    @InjectMocks
    private MobileService mobileService;

    @Mock
    private MobileRepository mobileRepository;

    @Mock
    private MobileMapper mobileMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {

        Mobile mobile1 = new Mobile(1L,"Samsung S10+",32, 8, 4100, 300);
        Mobile mobile2 = new Mobile(2L,"Samsung S22", 128, 64,5000,1400);
        List<Mobile> mobiles = Arrays.asList(mobile1,mobile2);

        when(mobileRepository.findAll()).thenReturn(mobiles);
        when(mobileMapper.toDto(mobile1)).thenReturn(new MobileDTO(1L,"Samsung S10+",32, 8, 4100, 300));
        when(mobileMapper.toDto(mobile2)).thenReturn(new MobileDTO(2L,"Samsung S22", 128, 64,5000,1400));

        List<MobileDTO> result = mobileService.getAll();

        assertEquals(mobiles.size(), result.size());
    }

    @Test
    public void testAddMobile() {

        Mobile mobile = new Mobile(1L, "Samsung S10+", 32, 8, 4100, 300);
        MobileDTO mobileDTO = new MobileDTO(1L, "Samsung S10+", 32, 8, 4100, 300);

        when(mobileRepository.save(mobile)).thenReturn(mobile);
        when(mobileMapper.toDto(mobile)).thenReturn(mobileDTO);
        when(mobileMapper.toEntity(mobileDTO)).thenReturn(mobile);

        MobileDTO result = mobileService.addMobile(mobileDTO);

        assertEquals(mobileDTO, result);
    }

    @Test
    public void testGetById() {

        Long id = 1L;
        Mobile mobile = new Mobile(id, "Samsung S10+", 32, 8, 4100, 300);
        MobileDTO mobileDTO = new MobileDTO(id, "Samsung S10+", 32, 8, 4100, 300);

        when(mobileRepository.findById(id)).thenReturn(Optional.of(mobile));
        when(mobileMapper.toDto(mobile)).thenReturn(mobileDTO);

        MobileDTO result = mobileService.getById(id);

        assertEquals(mobileDTO, result);
    }

    @Test
    public void testUpdateKitchen() {

        Long id = 1L;
        Mobile mobile = new Mobile(id, "Samsung S22", 128, 64, 5000, 1400);
        MobileDTO mobileDTO = new MobileDTO(id, "Samsung S22", 128, 64, 5000, 1400);

        when(mobileRepository.findById(id)).thenReturn(Optional.of(new Mobile()));
        when(mobileRepository.save(any(Mobile.class))).thenReturn(mobile);
        when(mobileMapper.toDto(mobile)).thenReturn(mobileDTO);

        MobileDTO result = mobileService.updateMobile(id, mobileDTO);

        assertEquals(mobileDTO, result);
    }

    @Test
    public void testDeleteById() {

        Long id = 1L;

        mobileService.deleteById(id);

        verify(mobileRepository, times(1)).deleteById(id);
    }
}
