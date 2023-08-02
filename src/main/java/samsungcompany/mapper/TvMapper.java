package samsungcompany.mapper;

import org.springframework.stereotype.Component;
import samsungcompany.dto.TvDTO;
import samsungcompany.model.Tv;

@Component
public class TvMapper {

    public TvDTO toDto(Tv tv){

        TvDTO tvDTO = new TvDTO();
        tvDTO.setId(tv.getId());
        tvDTO.setName(tv.getName());
        tvDTO.setInch(tv.getInch());
        tvDTO.setPrice(tv.getPrice());

        return tvDTO;
    }

    public Tv toEntity(TvDTO tvDTO){

        Tv tv = new Tv();
        tv.setId(tvDTO.getId());
        tv.setName(tvDTO.getName());
        tv.setInch(tvDTO.getInch());
        tv.setPrice(tvDTO.getPrice());

        return tv;
    }
}
