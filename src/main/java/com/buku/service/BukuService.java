package com.buku.service;

import com.buku.entity.Buku;
import com.buku.exception.BukuNotFoundException;
import com.buku.model.CreateBukuRequest;
import com.buku.model.UpdateBukuRequest;

public interface BukuService {

    public Buku createBuku(CreateBukuRequest request);

    public Buku updatebBuku(UpdateBukuRequest request) throws BukuNotFoundException;

    public void deleteBuku(int id) throws BukuNotFoundException;

    public void deleteBukuByName(String name);

    public void deleteAll();

    public Buku getById(int id) throws BukuNotFoundException;

    public Buku[] getAll();

}

