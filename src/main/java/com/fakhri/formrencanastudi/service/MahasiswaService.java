package com.fakhri.formrencanastudi.service;

import com.fakhri.formrencanastudi.dto.MahasiswaRequest;
import com.fakhri.formrencanastudi.dto.MahasiswaResponse;

import java.util.List;

public interface MahasiswaService {

    MahasiswaResponse create(MahasiswaRequest request) throws Exception;

    MahasiswaResponse update(Integer id, MahasiswaRequest request) throws Exception;

    void delete(Integer id) throws Exception;

    List<MahasiswaResponse> read();
}
