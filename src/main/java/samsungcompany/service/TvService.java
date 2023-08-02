package samsungcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samsungcompany.dto.TvDTO;
import samsungcompany.mapper.TvMapper;
import samsungcompany.model.Tv;
import samsungcompany.repository.TvRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TvService {

    private final TvRepository tvRepository;

    private final TvMapper tvMapper;

    @Autowired
    public TvService(TvRepository tvRepository, TvMapper tvMapper) {
        this.tvRepository = tvRepository;
        this.tvMapper = tvMapper;
    }

    public List<TvDTO> getAll() {

        List<Tv> tvList = tvRepository.findAll();
        List<TvDTO> tvDTOS = new ArrayList<>();

        for(Tv tv : tvList) {
            TvDTO tvDTO = tvMapper.toDto(tv);
            tvDTOS.add(tvDTO);
        }
        return tvDTOS;
    }

    public TvDTO addTv(TvDTO tvDTO) {
        Tv tv = tvMapper.toEntity(tvDTO);
        Tv savedTv = tvRepository.save(tv);

        return tvMapper.toDto(savedTv);
    }

    public TvDTO getById(Long id) {
        Tv tv = tvRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Accessories not found"));

        return tvMapper.toDto(tv);
    }

    public TvDTO updateTv(Long id, TvDTO tvDTO) {
        Tv existingTv = tvRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Accessories not found"));

        existingTv.setName(tvDTO.getName());
        existingTv.setInch(tvDTO.getInch());
        existingTv.setPrice(tvDTO.getPrice());

        Tv savedTv = tvRepository.save(existingTv);

        return tvMapper.toDto(savedTv);
    }

    public void deleteById(Long id) { tvRepository.deleteById(id); }
}
