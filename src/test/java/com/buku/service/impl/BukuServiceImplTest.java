package com.buku.service.impl;

import com.buku.entity.Buku;
import com.buku.exception.BukuNotFoundException;
import com.buku.model.CreateBukuRequest;
import com.buku.model.UpdateBukuRequest;
import com.buku.repository.BukuRepository;
import com.buku.repository.impl.BukuRepositoryImpl;
import com.buku.service.BukuService;
import com.buku.utils.Database;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BukuServiceImplTest {

    BukuService service;

    @BeforeEach
    void setUp() {
        BukuRepository repository = new BukuRepositoryImpl(Database.getConnection());

        service = new BukuServiceImpl(repository);
    }

    @BeforeAll
    static void beforeAll() {
        BukuRepository repository = new BukuRepositoryImpl(Database.getConnection());

        BukuService service = new BukuServiceImpl(repository);

        service.deleteAll();
    }


    @Test
    void createBuku() throws BukuNotFoundException {
        CreateBukuRequest bukuRequest = new CreateBukuRequest("Laskar Pelangi", "SOL", "2009", "gatau");

        Buku buku = service.createBuku(bukuRequest);

        Buku findBuku = service.getById(buku.getId());

        assertNotNull(buku);
        assertEquals(buku.getId(), findBuku.getId());
        assertEquals(buku.getNama(), findBuku.getNama());

    }

    @Test
    void updatebBukuSuccess() throws BukuNotFoundException {
        CreateBukuRequest bukuRequest = new CreateBukuRequest("Laskar Pelangi", "SOL", "2009", "gatau");

        Buku buku = service.createBuku(bukuRequest);

        UpdateBukuRequest updateBukuRequest = new UpdateBukuRequest(buku.getId(), "Laskar Biru", "SOL", "2012", "gatau");

        service.updatebBuku(updateBukuRequest);

        Buku updateBuku = service.getById(buku.getId());

        assertEquals(updateBuku.getId(), buku.getId());
        assertNotEquals(updateBuku.getNama(), buku.getNama());
    }

    @Test
    void updatebBukuFail() throws BukuNotFoundException {
        CreateBukuRequest bukuRequest = new CreateBukuRequest("Laskar Pelangi", "SOL", "2009", "gatau");

        Buku buku = service.createBuku(bukuRequest);

        BukuNotFoundException bukuNotFoundException = assertThrows(BukuNotFoundException.class, () -> {
            UpdateBukuRequest updateBukuRequest = new UpdateBukuRequest(0, "Laskar Biru", "SOL", "2012", "gatau");

            service.updatebBuku(updateBukuRequest);
        });

        assertEquals(bukuNotFoundException.getMessage(), "buku not found");
    }

    @Test
    void deleteBukuSuccess() {
        CreateBukuRequest bukuRequest = new CreateBukuRequest("Laskar Pelangi", "SOL", "2009", "gatau");

        Buku buku = service.createBuku(bukuRequest);

        assertDoesNotThrow(() -> {
            service.deleteBuku(buku.getId());

        });
    }

    @Test
    void deleteBukuFail() {
        CreateBukuRequest bukuRequest = new CreateBukuRequest("Laskar Pelangi", "SOL", "2009", "gatau");

        Buku buku = service.createBuku(bukuRequest);

        BukuNotFoundException bukuNotFoundException = assertThrows(BukuNotFoundException.class, () -> {
            service.deleteBuku(0);
        });

        assertEquals(bukuNotFoundException.getMessage(), "buku not found");
    }

    @Test
    void getById() throws BukuNotFoundException {
        CreateBukuRequest bukuRequest = new CreateBukuRequest("Laskar Pelangi", "SOL", "2009", "gatau");

        Buku buku = service.createBuku(bukuRequest);

        Buku findBuku = service.getById(buku.getId());

        assertNotNull(buku);
        assertEquals(buku.getId(), findBuku.getId());
        assertEquals(buku.getNama(), findBuku.getNama());
    }

    @Test
    void getAll() {
        CreateBukuRequest bukuRequest = new CreateBukuRequest("Laskar Pelangi", "SOL", "2009", "gatau");

        service.createBuku(bukuRequest);
        service.createBuku(bukuRequest);
        service.createBuku(bukuRequest);

        Buku[] all = service.getAll();

        assertEquals(all.length, 3);
    }
}