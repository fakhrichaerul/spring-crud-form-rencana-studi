package com.fakhri.formrencanastudi.controller;

import com.fakhri.formrencanastudi.dto.MataKuliahRequest;
import com.fakhri.formrencanastudi.dto.MataKuliahResponse;
import com.fakhri.formrencanastudi.service.MataKuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mata-kuliah")
public class MataKuliahController {

    private MataKuliahService mataKuliahService;

    @Autowired
    public MataKuliahController(MataKuliahService mataKuliahService) {
        this.mataKuliahService = mataKuliahService;
    }

    @PostMapping
    public MataKuliahResponse create(@RequestBody MataKuliahRequest request){
        return mataKuliahService.create(request);
    }

    @PutMapping(value = "/{id}")
    public MataKuliahResponse update(@PathVariable(value = "id") Integer id,
                                     @RequestBody MataKuliahRequest request) throws Exception{
        return mataKuliahService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id") Integer id) throws Exception{
        mataKuliahService.delete(id);
        return "Success";
    }

    @GetMapping
    public List<MataKuliahResponse> read(){
        return mataKuliahService.read();
    }
}
