package com.buku.service.impl;

import com.buku.entity.Buku;
import com.buku.exception.BukuNotFoundException;
import com.buku.model.CreateBukuRequest;
import com.buku.model.UpdateBukuRequest;
import com.buku.repository.BukuRepository;
import com.buku.service.BukuService;

public class BukuServiceImpl implements BukuService {

    BukuRepository repository;

    public BukuServiceImpl(BukuRepository repository) {
        this.repository = repository;
    }

    @Override
    public Buku createBuku(CreateBukuRequest request) {
        Buku buku = new Buku(
                request.getNama(), request.getGenre(), request.getTahunTerbit(), request.getPublisher()
        );

        return repository.save(buku);
    }

    @Override
    public Buku updatebBuku(UpdateBukuRequest request) throws BukuNotFoundException {
        Buku buku = new Buku(
                request.getNama(),
                request.getGenre(),
                request.getTahunTerbit(),
                request.getPublisher()
        );

        if (isExist(request.getId())) {
            buku.setId(request.getId());

            repository.update(buku);

            return buku;
        } else {
            throw new BukuNotFoundException();
        }
    }

    @Override
    public void deleteBuku(int id) throws BukuNotFoundException {
        if (isExist(id)) {
            repository.remove(id);
        } else {
            throw new BukuNotFoundException();
        }
    }

    @Override
    public void deleteBukuByName(String name) {
        repository.removeByName(name);
    }

    @Override
    public void deleteAll() {
        repository.removeAll();
    }

    @Override
    public Buku getById(int id) throws BukuNotFoundException {
        if (isExist(id)) {
            return repository.getById(id);
        } else {
            throw new BukuNotFoundException();
        }
    }

    @Override
    public Buku[] getAll() {
        return repository.getAll();
    }

    private boolean isExist(int id) {
        Buku buku = repository.getById(id);

        if (buku.getNama() == null) {
            return false;
        }

        return true;
    }

}

