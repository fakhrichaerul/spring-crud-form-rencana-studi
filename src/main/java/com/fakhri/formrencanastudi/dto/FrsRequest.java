package com.fakhri.formrencanastudi.dto;

import lombok.Data;

import java.time.DayOfWeek;

@Data
public class FrsRequest {

    private DayOfWeek jadwal;
    private Integer idMahasiswa;
    private Integer idMataKuliah;
}
