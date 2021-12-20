package com.buku.repository;

import com.buku.entity.Buku;

public interface BukuRepository {

    public Buku save(Buku buku);

    public Buku update(Buku buku);

    public void remove(Integer id);

    public void removeByName(String name);

    public void removeAll();

    public Buku getById(Integer id);

    public Buku[] getAll();

}
