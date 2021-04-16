package com.fakhri.formrencanastudi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "jurusan")
@Data
public class Jurusan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nama_jurusan")
    private String namaJurusan;
}
