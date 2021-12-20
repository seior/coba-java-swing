package com.buku.entity;

public class Buku {

    private Integer id;

    private String nama;

    private String genre;

    private String tahunTerbit;

    private String publisher;


    public Buku() {
    }

    public Buku(String nama, String genre, String tahunTerbit, String publisher) {
        this.nama = nama;
        this.genre = genre;
        this.tahunTerbit = tahunTerbit;
        this.publisher = publisher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Buku{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", genre='" + genre + '\'' +
                ", tahunTerbit='" + tahunTerbit + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
