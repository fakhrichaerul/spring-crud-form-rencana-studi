package com.fakhri.formrencanastudi.service;

import com.fakhri.formrencanastudi.dto.FrsRequest;
import com.fakhri.formrencanastudi.dto.FrsResponse;

import java.util.List;

public interface FrsService {

    FrsResponse create(FrsRequest request) throws Exception;

    FrsResponse update(Integer id, FrsRequest request) throws Exception;

    void delate(Integer id) throws Exception;

    List<FrsResponse> read();
}
