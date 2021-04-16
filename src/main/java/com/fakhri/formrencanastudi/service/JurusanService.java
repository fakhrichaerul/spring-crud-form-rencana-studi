package com.fakhri.formrencanastudi.service;

import com.fakhri.formrencanastudi.dto.JurusanRequest;
import com.fakhri.formrencanastudi.dto.JurusanResponse;

import java.util.List;

public interface JurusanService {

    JurusanResponse create(JurusanRequest request);

    JurusanResponse update(Integer id, JurusanRequest request) throws Exception;

    void delete(Integer id) throws Exception;

    List<JurusanResponse> read();
}
