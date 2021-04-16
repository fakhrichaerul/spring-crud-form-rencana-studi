package com.fakhri.formrencanastudi.service;

import com.fakhri.formrencanastudi.dto.MataKuliahRequest;
import com.fakhri.formrencanastudi.dto.MataKuliahResponse;

import java.util.List;

public interface MataKuliahService {

    MataKuliahResponse create(MataKuliahRequest request);

    MataKuliahResponse update(Integer id, MataKuliahRequest request) throws Exception;

    void delete(Integer id) throws Exception;

    List<MataKuliahResponse> read();
}
