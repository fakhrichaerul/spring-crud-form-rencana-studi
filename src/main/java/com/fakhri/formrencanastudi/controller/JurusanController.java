package com.fakhri.formrencanastudi.controller;

import com.fakhri.formrencanastudi.dto.JurusanRequest;
import com.fakhri.formrencanastudi.dto.JurusanResponse;
import com.fakhri.formrencanastudi.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jurusan")
public class JurusanController {

    private JurusanService jurusanService;

    @Autowired
    public JurusanController(JurusanService jurusanService) {
        this.jurusanService = jurusanService;
    }

    @PostMapping
    public JurusanResponse create(@RequestBody JurusanRequest request){
        return jurusanService.create(request);
    }

    @PutMapping(value = "/{id}")
    public JurusanResponse update(@PathVariable(value = "id") Integer id,
                                  @RequestBody JurusanRequest request) throws Exception{
        return jurusanService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id") Integer id) throws Exception{
        jurusanService.delete(id);
        return "Success";
    }

    @GetMapping
    public List<JurusanResponse> read(){
        return jurusanService.read();
    }
}
