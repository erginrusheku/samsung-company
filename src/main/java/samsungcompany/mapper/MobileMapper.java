package samsungcompany.mapper;

import org.springframework.stereotype.Component;
import samsungcompany.dto.MobileDTO;
import samsungcompany.model.Mobile;

@Component
public class MobileMapper {

    public MobileDTO toDto(Mobile mobile){

        MobileDTO mobileDTO = new MobileDTO();
        mobileDTO.setId(mobile.getId());
        mobileDTO.setName(mobile.getName());
        mobileDTO.setStorage(mobile.getStorage());
        mobileDTO.setRAM(mobile.getRAM());
        mobileDTO.setBattery(mobile.getBattery());
        mobileDTO.setPrice(mobile.getPrice());

        return mobileDTO;
    }

    public Mobile toEntity(MobileDTO mobileDTO){

        Mobile mobile = new Mobile();
        mobile.setId(mobileDTO.getId());
        mobile.setName(mobileDTO.getName());
        mobile.setStorage(mobileDTO.getStorage());
        mobile.setRAM(mobileDTO.getRAM());
        mobile.setBattery(mobileDTO.getBattery());
        mobile.setPrice(mobileDTO.getPrice());

        return mobile;
    }
}
