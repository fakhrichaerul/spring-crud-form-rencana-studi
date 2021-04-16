package com.fakhri.formrencanastudi.service.impl;

import com.fakhri.formrencanastudi.dto.JurusanResponse;
import com.fakhri.formrencanastudi.dto.MahasiswaRequest;
import com.fakhri.formrencanastudi.dto.MahasiswaResponse;
import com.fakhri.formrencanastudi.model.Jurusan;
import com.fakhri.formrencanastudi.model.Mahasiswa;
import com.fakhri.formrencanastudi.repository.JurusanRepository;
import com.fakhri.formrencanastudi.repository.MahasiswaRepository;
import com.fakhri.formrencanastudi.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService {

    private MahasiswaRepository mahasiswaRepository;
    private JurusanRepository jurusanRepository;

    @Autowired
    public MahasiswaServiceImpl(MahasiswaRepository mahasiswaRepository, JurusanRepository jurusanRepository) {
        this.mahasiswaRepository = mahasiswaRepository;
        this.jurusanRepository = jurusanRepository;
    }

    @Override
    public MahasiswaResponse create(MahasiswaRequest request) throws Exception {
        return jurusanRepository.findById(request.getIdJurusan())
                .map(jurusan -> processCreateMahasiswa(jurusan, request))
                .orElseThrow(() -> new Exception("jurusan not found"));
    }

    private MahasiswaResponse processCreateMahasiswa(Jurusan jurusan, MahasiswaRequest request) {
        Mahasiswa mahasiswa = buildMahasiswaModelFromRequest(request, jurusan);
        Mahasiswa savedMahasiswa = mahasiswaRepository.save(mahasiswa);
        jurusanRepository.save(jurusan);
        return buildMahasiswaResponseFromModel(savedMahasiswa);
    }

    private MahasiswaResponse buildMahasiswaResponseFromModel(Mahasiswa savedMahasiswa) {
        MahasiswaResponse response = new MahasiswaResponse();
        response.setId(savedMahasiswa.getId());
        response.setNamaMahasiswa(savedMahasiswa.getNamaMahasiswa());
        JurusanResponse jurusanResponse = buildJurusanResponseFromModel(savedMahasiswa.getJurusan());
        response.setJurusan(jurusanResponse);
        return response;
    }

    private JurusanResponse buildJurusanResponseFromModel(Jurusan jurusan) {
        JurusanResponse response = new JurusanResponse();
        response.setId(jurusan.getId());
        response.setNamaJurusan(jurusan.getNamaJurusan());
        return response;
    }

    private Mahasiswa buildMahasiswaModelFromRequest(MahasiswaRequest request, Jurusan jurusan) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNamaMahasiswa(request.getNamaMahasiswa());
        mahasiswa.setJurusan(jurusan);
        return mahasiswa;
    }

    @Override
    public MahasiswaResponse update(Integer id, MahasiswaRequest request) throws Exception {
        Optional<Jurusan> findJurusan = jurusanRepository.findById(request.getIdJurusan());
        if (findJurusan.isEmpty()){
            throw new Exception("id jurusan not found");
        }
        Jurusan jurusan = findJurusan.get();

        Optional<Mahasiswa> findMahasiswa = mahasiswaRepository.findById(id);
        if (findMahasiswa.isEmpty()){
            throw new Exception("id not found");
        }
        findMahasiswa.get().setNamaMahasiswa(request.getNamaMahasiswa());
        findMahasiswa.get().setJurusan(jurusan);
        Mahasiswa savedMahasiswa = mahasiswaRepository.save(findMahasiswa.get());
        JurusanResponse jurusanResponse = buildJurusanResponseFromModel(jurusan);
        MahasiswaResponse response = buildMahasiswaResponseFromModel(savedMahasiswa);

        return response;
    }

    @Override
    public void delete(Integer id) throws Exception {
        Optional<Mahasiswa> findMahasiswa = mahasiswaRepository.findById(id);
        if (findMahasiswa.isEmpty()){
            throw new Exception("id not found");
        }
        mahasiswaRepository.delete(findMahasiswa.get());
    }

    @Override
    public List<MahasiswaResponse> read() {
        Iterable<Mahasiswa> mahasiswaIterable = mahasiswaRepository.findAll();
        List<MahasiswaResponse> mahasiswaResponseList = new ArrayList<>();
        mahasiswaIterable.forEach(mahasiswa -> {
            MahasiswaResponse response = buildMahasiswaResponseFromModel(mahasiswa);
            mahasiswaResponseList.add(response);

        });
        return mahasiswaResponseList;
    }
}
