package samsungcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import samsungcompany.dto.MobileDTO;
import samsungcompany.service.MobileService;

import java.util.List;

@RestController
@RequestMapping("/mobile")
public class MobileController {

    private final MobileService mobileService;

    @Autowired
    public MobileController(MobileService mobileService) {
        this.mobileService = mobileService;
    }

    @GetMapping
    public List<MobileDTO> getAll() {
        return mobileService.getAll();
    }

    @PostMapping
    public MobileDTO addMobile(@RequestBody MobileDTO mobileDTO) {
        return mobileService.addMobile(mobileDTO);
    }

    @GetMapping("/{id}")
    public MobileDTO getById(@PathVariable Long id) {
        return mobileService.getById(id);
    }

    @PutMapping("/{id}")
    public MobileDTO updateMobile(@PathVariable Long id, @RequestBody MobileDTO mobileDTO) {
        return mobileService.updateMobile(id, mobileDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        mobileService.deleteById(id);
    }
}
