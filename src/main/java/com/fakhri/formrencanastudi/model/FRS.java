package com.fakhri.formrencanastudi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "form_rencana_studi")
@Data
public class FRS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "jadwal")
    private String jadwal;

    @ManyToOne
    @JoinColumn(name = "mahasiswa")
    private Mahasiswa mahasiswa;

    @ManyToOne
    @JoinColumn(name = "mata_kuliah")
    private MataKuliah mataKuliah;
}
