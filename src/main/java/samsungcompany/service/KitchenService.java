package samsungcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samsungcompany.dto.KitchenDTO;
import samsungcompany.mapper.KitchenMapper;
import samsungcompany.model.Kitchen;
import samsungcompany.repository.KitchenRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class KitchenService {

    private final KitchenRepository kitchenRepository;

    private final KitchenMapper kitchenMapper;

    @Autowired
    public KitchenService(KitchenRepository kitchenRepository, KitchenMapper kitchenMapper) {
        this.kitchenRepository = kitchenRepository;
        this.kitchenMapper = kitchenMapper;
    }

    public List<KitchenDTO> getAll() {

        List<Kitchen> kitchens = kitchenRepository.findAll();
        List<KitchenDTO> kitchenDTOS = new ArrayList<>();

        for(Kitchen kitchen : kitchens){
            KitchenDTO kitchenDTO = kitchenMapper.toDto(kitchen);
            kitchenDTOS.add(kitchenDTO);
        }
        return kitchenDTOS;
    }

    public KitchenDTO addKitchen(KitchenDTO kitchenDTO) {

        Kitchen kitchen = kitchenMapper.toEntity(kitchenDTO);
        Kitchen savedKitchen = kitchenRepository.save(kitchen);

        return kitchenMapper.toDto(savedKitchen);
    }

    public KitchenDTO getById(Long id) {
        Kitchen kitchen = kitchenRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Accessories not found"));
        return kitchenMapper.toDto(kitchen);
    }

    public KitchenDTO updateKitchen(Long id, KitchenDTO kitchenDTO) {
        Kitchen existingKitchen = kitchenRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Accessories not found with id: " + id));

        existingKitchen.setName(kitchenDTO.getName());
        existingKitchen.setLength(kitchenDTO.getLength());
        existingKitchen.setHeight(kitchenDTO.getHeight());
        existingKitchen.setWidth(kitchenDTO.getWidth());
        existingKitchen.setCubic(kitchenDTO.getCubic());
        existingKitchen.setPrice(kitchenDTO.getPrice());

        Kitchen savedKitchen = kitchenRepository.save(existingKitchen);

        return kitchenMapper.toDto(savedKitchen);

    }

    public void deleteById(Long id) { kitchenRepository.deleteById(id); }
}
