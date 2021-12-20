package com.buku.repository.impl;

import com.buku.entity.Buku;
import com.buku.repository.BukuRepository;
import com.buku.utils.CheckError;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BukuRepositoryImpl implements BukuRepository {

    Connection connection;

    public BukuRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Buku save(Buku buku) {
        String SQL = "insert into buku (nama, genre, tahun_terbit, publisher) values (?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, buku.getNama());
            statement.setString(2, buku.getGenre());
            statement.setString(3, buku.getTahunTerbit());
            statement.setString(4, buku.getPublisher());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                buku.setId(generatedKeys.getInt(1));
            }

            statement.close();
            generatedKeys.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return buku;
    }

    @Override
    public Buku update(Buku buku) {
        String SQL = "update buku set nama = ?, genre = ?, tahun_terbit = ?, publisher = ? where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, buku.getNama());
            statement.setString(2, buku.getGenre());
            statement.setString(3, buku.getTahunTerbit());
            statement.setString(4, buku.getPublisher());
            statement.setInt(5, buku.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return buku;
    }

    @Override
    public void remove(Integer id) {
        String SQL = "delete from buku where id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeByName(String name) {
        String SQL = "delete from buku where nama = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setString(1, name);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void removeAll() {
        CheckError.env();

        String SQL = "delete from buku";

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Buku getById(Integer id) {
        String SQL = "select * from buku where id = ?";
        Buku buku = new Buku();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                buku.setId(result.getInt(1));
                buku.setNama(result.getString(2));
                buku.setGenre(result.getString(3));
                buku.setTahunTerbit(result.getString(4));
                buku.setPublisher(result.getString(5));
            }

            return buku;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Buku[] getAll() {
        String SQL = "select * from buku";

        List<Buku> bukus = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Buku buku = new Buku();

                buku.setId(result.getInt(1));
                buku.setNama(result.getString(2));
                buku.setGenre(result.getString(3));
                buku.setTahunTerbit(result.getString(4));
                buku.setPublisher(result.getString(5));

                bukus.add(buku);
            }

            return listBukuToArray(bukus);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Buku[] listBukuToArray(List<Buku> bukus) {
        Buku[] newBukus = new Buku[bukus.size()];

        for (int i = 0; i < bukus.size(); i++) {
            newBukus[i] = bukus.get(i);
        }

        return newBukus;
    }

}
