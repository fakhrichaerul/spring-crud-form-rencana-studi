package com.fakhri.formrencanastudi.dto;

import lombok.Data;

@Data
public class MahasiswaResponse {

    private Integer id;
    private String namaMahasiswa;
    private JurusanResponse jurusan;
}
