package com.fakhri.formrencanastudi.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "mata_kuliah")
@Data
public class MataKuliah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nama_mata_kuliah")
    private String namaMK;

    @Column(name = "sks")
    private Integer sks;
}
