package com.fakhri.formrencanastudi.service.impl;

import com.fakhri.formrencanastudi.dto.MataKuliahRequest;
import com.fakhri.formrencanastudi.dto.MataKuliahResponse;
import com.fakhri.formrencanastudi.model.MataKuliah;
import com.fakhri.formrencanastudi.repository.MataKuliahRepository;
import com.fakhri.formrencanastudi.service.MataKuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MataKuliahServiceImpl implements MataKuliahService {

    private MataKuliahRepository mataKuliahRepository;

    @Autowired
    public MataKuliahServiceImpl(MataKuliahRepository mataKuliahRepository) {
        this.mataKuliahRepository = mataKuliahRepository;
    }

    @Override
    public MataKuliahResponse create(MataKuliahRequest request) {
        MataKuliah mataKuliah = buildMataKuliahModelFromRequest(request);
        MataKuliah savedMataKuliah = mataKuliahRepository.save(mataKuliah);
        MataKuliahResponse response = buildMataKuliahResponseFromModel(savedMataKuliah);

        return response;
    }

    private MataKuliahResponse buildMataKuliahResponseFromModel(MataKuliah savedMataKuliah) {
        MataKuliahResponse response = new MataKuliahResponse();
        response.setId(savedMataKuliah.getId());
        response.setNamaMK(savedMataKuliah.getNamaMK());
        response.setSks(savedMataKuliah.getSks());
        return response;
    }

    private MataKuliah buildMataKuliahModelFromRequest(MataKuliahRequest request) {
        MataKuliah mataKuliah = new MataKuliah();
        mataKuliah.setNamaMK(request.getNamaMK());
        mataKuliah.setSks(request.getSks());
        return mataKuliah;
    }

    @Override
    public MataKuliahResponse update(Integer id, MataKuliahRequest request) throws Exception {
        Optional<MataKuliah> findMataKuliah = mataKuliahRepository.findById(id);
        if (findMataKuliah.isEmpty()){
            throw new Exception("id not found");
        }
        findMataKuliah.get().setNamaMK(request.getNamaMK());
        findMataKuliah.get().setSks(request.getSks());
        MataKuliah savedMataKuliah = mataKuliahRepository.save(findMataKuliah.get());
        MataKuliahResponse response = buildMataKuliahResponseFromModel(savedMataKuliah);

        return response;
    }

    @Override
    public void delete(Integer id) throws Exception {
        Optional<MataKuliah> findMataKuliah = mataKuliahRepository.findById(id);
        if (findMataKuliah.isEmpty()){
            throw new Exception("id not found");
        }
        mataKuliahRepository.delete(findMataKuliah.get());
    }

    @Override
    public List<MataKuliahResponse> read() {
        Iterable<MataKuliah> mataKuliahIterable = mataKuliahRepository.findAll();
        List<MataKuliahResponse> responseList = new ArrayList<>();

        mataKuliahIterable.forEach(mataKuliah -> {
            MataKuliahResponse response = buildMataKuliahResponseFromModel(mataKuliah);
            responseList.add(response);
        });

        return responseList;
    }
}
