package com.fakhri.formrencanastudi.dto;

import lombok.Data;

import java.time.DayOfWeek;

@Data
public class FrsResponse {

    private Integer id;
    private DayOfWeek jadwal;
    private MahasiswaResponse mahasiswa;
    private MataKuliahResponse mataKuliah;
}
