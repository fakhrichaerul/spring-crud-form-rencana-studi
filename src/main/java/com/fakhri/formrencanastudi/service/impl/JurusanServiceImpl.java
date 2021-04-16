package com.fakhri.formrencanastudi.service.impl;

import com.fakhri.formrencanastudi.dto.JurusanRequest;
import com.fakhri.formrencanastudi.dto.JurusanResponse;
import com.fakhri.formrencanastudi.model.Jurusan;
import com.fakhri.formrencanastudi.repository.JurusanRepository;
import com.fakhri.formrencanastudi.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JurusanServiceImpl implements JurusanService {

    private JurusanRepository jurusanRepository;

    @Autowired
    public JurusanServiceImpl(JurusanRepository jurusanRepository) {
        this.jurusanRepository = jurusanRepository;
    }

    @Override
    public JurusanResponse create(JurusanRequest request) {
        Jurusan jurusan = buildJurusanModelFromRequest(request);
        Jurusan savedJurusan = jurusanRepository.save(jurusan);
        JurusanResponse response = buildJurusanResponseFromModel(savedJurusan);

        return response;
    }

    private JurusanResponse buildJurusanResponseFromModel(Jurusan savedJurusan) {
        JurusanResponse response = new JurusanResponse();
        response.setId(savedJurusan.getId());
        response.setNamaJurusan(savedJurusan.getNamaJurusan());
        return response;
    }

    private Jurusan buildJurusanModelFromRequest(JurusanRequest request) {
        Jurusan jurusan = new Jurusan();
        jurusan.setNamaJurusan(request.getNamaJurusan());
        return jurusan;
    }

    @Override
    public JurusanResponse update(Integer id, JurusanRequest request) throws Exception {
        Optional<Jurusan> findJurusan = jurusanRepository.findById(id);
        if(findJurusan.isEmpty()){
            throw new Exception("id not found");
        }
        findJurusan.get().setNamaJurusan(request.getNamaJurusan());
        Jurusan savedJurusan = jurusanRepository.save(findJurusan.get());
        JurusanResponse response = buildJurusanResponseFromModel(savedJurusan);

        return response;
    }

    @Override
    public void delete(Integer id) throws Exception {
        Optional<Jurusan> findJurusan = jurusanRepository.findById(id);
        if (findJurusan.isEmpty()){
            throw new Exception("id not found");
        }
        jurusanRepository.delete(findJurusan.get());
    }

    @Override
    public List<JurusanResponse> read() {
        Iterable<Jurusan> jurusanList = jurusanRepository.findAll();
        List<JurusanResponse> responseList = new ArrayList<>();

        jurusanList.forEach(jurusan -> {
            JurusanResponse response = buildJurusanResponseFromModel(jurusan);
            responseList.add(response);
        });
        return responseList;
    }
}
