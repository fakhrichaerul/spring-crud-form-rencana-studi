package com.fakhri.formrencanastudi.controller;

import com.fakhri.formrencanastudi.dto.MahasiswaRequest;
import com.fakhri.formrencanastudi.dto.MahasiswaResponse;
import com.fakhri.formrencanastudi.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    private MahasiswaService mahasiswaService;

    @Autowired
    public MahasiswaController(MahasiswaService mahasiswaService) {
        this.mahasiswaService = mahasiswaService;
    }

    @PostMapping
    public MahasiswaResponse create(@RequestBody MahasiswaRequest request) throws Exception{
        return mahasiswaService.create(request);
    }

    @PutMapping(value = "/{id}")
    public MahasiswaResponse update(@PathVariable(value = "id") Integer id,
                                    @RequestBody MahasiswaRequest request)throws Exception{
        return mahasiswaService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id") Integer id)throws Exception{
        mahasiswaService.delete(id);
        return "Success";
    }

    @GetMapping
    public List<MahasiswaResponse> read(){
        return mahasiswaService.read();
    }


}
