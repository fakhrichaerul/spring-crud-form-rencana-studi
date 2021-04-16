package com.fakhri.formrencanastudi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "mahasiswa")
@Data
public class Mahasiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nama_mahasiswa")
    private String namaMahasiswa;

    @ManyToOne
    @JoinColumn(name = "jurusan")
    private Jurusan jurusan;
}
