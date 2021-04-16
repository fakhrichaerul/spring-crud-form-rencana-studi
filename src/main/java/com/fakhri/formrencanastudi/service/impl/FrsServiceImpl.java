package com.fakhri.formrencanastudi.service.impl;

import com.fakhri.formrencanastudi.dto.*;
import com.fakhri.formrencanastudi.model.FRS;
import com.fakhri.formrencanastudi.model.Jurusan;
import com.fakhri.formrencanastudi.model.Mahasiswa;
import com.fakhri.formrencanastudi.model.MataKuliah;
import com.fakhri.formrencanastudi.repository.FrsRepository;
import com.fakhri.formrencanastudi.repository.MahasiswaRepository;
import com.fakhri.formrencanastudi.repository.MataKuliahRepository;
import com.fakhri.formrencanastudi.service.FrsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FrsServiceImpl implements FrsService {

    private FrsRepository frsRepository;
    private MahasiswaRepository mahasiswaRepository;
    private MataKuliahRepository mataKuliahRepository;

    public FrsServiceImpl(FrsRepository frsRepository, MahasiswaRepository mahasiswaRepository, MataKuliahRepository mataKuliahRepository) {
        this.frsRepository = frsRepository;
        this.mahasiswaRepository = mahasiswaRepository;
        this.mataKuliahRepository = mataKuliahRepository;
    }

    @Override
    public FrsResponse create(FrsRequest request) throws Exception {
        Optional<Mahasiswa> findMahasiswa = mahasiswaRepository.findById(request.getIdMahasiswa());
        if (findMahasiswa.isEmpty()){
            throw new Exception("id mahasiswa not found");
        }
        Mahasiswa mahasiswa = findMahasiswa.get();

        Optional<MataKuliah> findMataKuliah = mataKuliahRepository.findById(request.getIdMataKuliah());
        if (findMataKuliah.isEmpty()){
            throw new Exception("id mata kuliah not found");
        }
        MataKuliah mataKuliah = findMataKuliah.get();

        FrsResponse response = processCreateFrs(mahasiswa, mataKuliah, request);

        return response;
    }

    private FrsResponse processCreateFrs(Mahasiswa mahasiswa, MataKuliah mataKuliah, FrsRequest request) {
        FRS frs = buildFrsModelFromRequest(request, mahasiswa, mataKuliah);
        FRS savedFrs = frsRepository.save(frs);
        mahasiswaRepository.save(mahasiswa);
        mataKuliahRepository.save(mataKuliah);
        return buildFrsResponseFromModel(savedFrs);
    }

    private FrsResponse buildFrsResponseFromModel(FRS savedFrs) {
        FrsResponse response = new FrsResponse();
        response.setId(savedFrs.getId());
        response.setJadwal(savedFrs.getJadwal());
        MahasiswaResponse mahasiswaResponse = buildMahasiswaResponseFromModel(savedFrs.getMahasiswa());
        response.setMahasiswa(mahasiswaResponse);
        MataKuliahResponse mataKuliahResponse = buildMataKuliahResponseFromModel(savedFrs.getMataKuliah());
        response.setMataKuliah(mataKuliahResponse);
        return response;
    }

    private MataKuliahResponse buildMataKuliahResponseFromModel(MataKuliah mataKuliah) {
        MataKuliahResponse response = new MataKuliahResponse();
        response.setId(mataKuliah.getId());
        response.setNamaMK(mataKuliah.getNamaMK());
        response.setSks(mataKuliah.getSks());
        return response;
    }

    private MahasiswaResponse buildMahasiswaResponseFromModel(Mahasiswa mahasiswa) {
        MahasiswaResponse response = new MahasiswaResponse();
        response.setId(mahasiswa.getId());
        response.setNamaMahasiswa(mahasiswa.getNamaMahasiswa());
        JurusanResponse jurusanResponse = buildJurusanResponseFromModel(mahasiswa.getJurusan());
        response.setJurusan(jurusanResponse);
        return response;
    }

    private JurusanResponse buildJurusanResponseFromModel(Jurusan jurusan) {
        JurusanResponse response = new JurusanResponse();
        response.setId(jurusan.getId());
        response.setNamaJurusan(jurusan.getNamaJurusan());
        return response;
    }

    private FRS buildFrsModelFromRequest(FrsRequest request, Mahasiswa mahasiswa, MataKuliah mataKuliah) {
        FRS frs = new FRS();
        frs.setJadwal(request.getJadwal());
        frs.setMahasiswa(mahasiswa);
        frs.setMataKuliah(mataKuliah);
        return frs;
    }

    @Override
    public FrsResponse update(Integer id, FrsRequest request) throws Exception {
        Optional<Mahasiswa> findMahasiswa = mahasiswaRepository.findById(request.getIdMahasiswa());
        if (findMahasiswa.isEmpty()){
            throw new Exception("idMahasiswa not found");
        }
        Mahasiswa mahasiswa = findMahasiswa.get();

        Optional<MataKuliah> findMataKuliah = mataKuliahRepository.findById(request.getIdMataKuliah());
        if (findMataKuliah.isEmpty()){
            throw new Exception("idMataKuliah not found");
        }
        MataKuliah mataKuliah = findMataKuliah.get();

        Optional<FRS> findFrs = frsRepository.findById(id);
        if (findFrs.isEmpty()){
            throw new Exception("id not found");
        }
        findFrs.get().setJadwal(request.getJadwal());
        findFrs.get().setMahasiswa(mahasiswa);
        findFrs.get().setMataKuliah(mataKuliah);
        FRS savedFrs = frsRepository.save(findFrs.get());
        MahasiswaResponse mahasiswaResponse = buildMahasiswaResponseFromModel(mahasiswa);
        MataKuliahResponse mataKuliahResponse = buildMataKuliahResponseFromModel(mataKuliah);
        FrsResponse response = buildFrsResponseFromModel(savedFrs);

        return response;
    }

    @Override
    public void delate(Integer id) throws Exception {
        Optional<FRS> findFrs = frsRepository.findById(id);
        if (findFrs.isEmpty()){
            throw new Exception("id not found");
        }
        frsRepository.delete(findFrs.get());
    }

    @Override
    public List<FrsResponse> read() {
        Iterable<FRS> frsIterable = frsRepository.findAll();
        List<FrsResponse> frsResponseList = new ArrayList<>();
        frsIterable.forEach(frs -> {
            FrsResponse response = buildFrsResponseFromModel(frs);
            frsResponseList.add(response);
        });

        return frsResponseList;
    }
}
