package com.fakhri.formrencanastudi.controller;

import com.fakhri.formrencanastudi.dto.FrsRequest;
import com.fakhri.formrencanastudi.dto.FrsResponse;
import com.fakhri.formrencanastudi.repository.FrsRepository;
import com.fakhri.formrencanastudi.service.FrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frs")
public class FrsController {

    private FrsService frsService;

    @Autowired
    public FrsController(FrsService frsService) {
        this.frsService = frsService;
    }

    @PostMapping
    public FrsResponse create(@RequestBody FrsRequest request) throws Exception{
        return frsService.create(request);
    }

    @PutMapping(value = "/{id}")
    public FrsResponse update(@PathVariable(value = "id")Integer id,
                               @RequestBody FrsRequest request) throws Exception{
        return frsService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable(value = "id")Integer id)throws Exception{
        frsService.delate(id);
        return "Success";
    }

    @GetMapping
    public List<FrsResponse> read(){
        return frsService.read();
    }
}
