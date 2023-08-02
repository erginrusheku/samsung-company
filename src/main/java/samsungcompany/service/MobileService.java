package samsungcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import samsungcompany.dto.MobileDTO;
import samsungcompany.mapper.MobileMapper;
import samsungcompany.model.Mobile;
import samsungcompany.repository.MobileRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileService {

    private final MobileRepository mobileRepository;

    private final MobileMapper mobileMapper;

    @Autowired
    public MobileService(MobileRepository mobileRepository, MobileMapper mobileMapper) {
        this.mobileRepository = mobileRepository;
        this.mobileMapper = mobileMapper;
    }

    public List<MobileDTO> getAll() {

        List<Mobile> mobiles = mobileRepository.findAll();
        List<MobileDTO> mobileDTOS = new ArrayList<>();

        for(Mobile mobile: mobiles){
            MobileDTO mobileDTO = mobileMapper.toDto(mobile);
            mobileDTOS.add(mobileDTO);
        }
        return mobileDTOS;
    }

    public MobileDTO addMobile(MobileDTO mobileDTO) {
        Mobile mobile = mobileMapper.toEntity(mobileDTO);
        Mobile savedMobile = mobileRepository.save(mobile);

        return  mobileMapper.toDto(savedMobile);
    }

    public MobileDTO getById(Long id)  {
        Mobile mobile = mobileRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Accessories not found"));

        return mobileMapper.toDto(mobile);
    }

    public MobileDTO updateMobile(Long id, MobileDTO mobileDTO) {
        Mobile existingMobile = mobileRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Accessories not found"));

        existingMobile.setName(mobileDTO.getName());
        existingMobile.setStorage(mobileDTO.getStorage());
        existingMobile.setRAM(mobileDTO.getRAM());
        existingMobile.setBattery(mobileDTO.getBattery());
        existingMobile.setPrice(mobileDTO.getPrice());

        Mobile savedMobile = mobileRepository.save(existingMobile);

        return mobileMapper.toDto(savedMobile);

    }

    public void deleteById(Long id) { mobileRepository.deleteById(id); }
}
