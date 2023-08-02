package samsungcompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import samsungcompany.dto.TvDTO;
import samsungcompany.mapper.TvMapper;
import samsungcompany.model.Tv;
import samsungcompany.repository.TvRepository;
import samsungcompany.service.TvService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TvServiceTest {

    @InjectMocks
    private TvService tvService;

    @Mock
    private TvRepository tvRepository;

    @Mock
    private TvMapper tvMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetAll() {

        Tv tv1 = new Tv(1L, "Samsung NEO QLED", 85, 1300);
        Tv tv2 = new Tv(2L, "Samsung QLED", 75, 1000);
        List<Tv> tvList = Arrays.asList(tv1,tv2);

        when(tvRepository.findAll()).thenReturn(tvList);
        when(tvMapper.toDto(tv1)).thenReturn(new TvDTO(1L, "Samsung NEO QLED", 85, 1300));
        when(tvMapper.toDto(tv2)).thenReturn(new TvDTO(2L, "Samsung QLED", 75, 1000));

        List<TvDTO> result = tvService.getAll();

        assertEquals(tvList.size(), result.size());
    }

    @Test
    public void testAddTv() {

        Tv tv = new Tv(1L, "Samsung NEO QLED", 85, 1300);
        TvDTO tvDTO = new TvDTO(1L, "Samsung NEO QLED", 85, 1300);

        when(tvRepository.save(tv)).thenReturn(tv);
        when(tvMapper.toDto(tv)).thenReturn(tvDTO);
        when(tvMapper.toEntity(tvDTO)).thenReturn(tv);

        TvDTO result = tvService.addTv(tvDTO);

        assertEquals(tvDTO, result);
    }

    @Test
    public void testGetById() {

        Long id = 1L;
        Tv tv = new Tv(1L, "Samsung NEO QLED", 85, 1300);
        TvDTO tvDTO = new TvDTO(1L, "Samsung NEO QLED", 85, 1300);

        when(tvRepository.findById(id)).thenReturn(Optional.of(tv));
        when(tvMapper.toDto(tv)).thenReturn(tvDTO);

        TvDTO result = tvService.getById(id);

        assertEquals(tvDTO, result);
    }

    @Test
    public void testUpdateTv() {

        Long id = 1L;
        Tv tv = new Tv(id, "Samsung QLED", 75, 1000);
        TvDTO tvDTO = new TvDTO(id, "Samsung QLED", 75, 1000);

        when(tvRepository.findById(id)).thenReturn(Optional.of(tv));
        when(tvRepository.save(any(Tv.class))).thenReturn(tv);
        when(tvMapper.toDto(tv)).thenReturn(tvDTO);

        TvDTO result = tvService.updateTv(id, tvDTO);

        assertEquals(tvDTO, result);
    }

    @Test
    public void testDeleteById() {

        Long id = 1L;

        tvService.deleteById(id);

        verify(tvRepository, times(1)).deleteById(id);
    }
}
