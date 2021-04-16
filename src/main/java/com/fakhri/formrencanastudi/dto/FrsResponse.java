package com.fakhri.formrencanastudi.dto;

import lombok.Data;

@Data
public class FrsResponse {

    private Integer id;
    private String jadwal;
    private MahasiswaResponse mahasiswa;
    private MataKuliahResponse mataKuliah;
}
