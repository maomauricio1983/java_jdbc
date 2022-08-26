package org.mauricio.jdbc.repositorio;

import org.mauricio.jdbc.modelo.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {


    List<T> listar();

    T porId(Long id) throws SQLException;

    void guardar(Producto producto);


    void eliminar(Long id);



}
