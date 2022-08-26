package org.mauricio.jdbc.repositorio;

import org.mauricio.jdbc.modelo.Categoria;
import org.mauricio.jdbc.modelo.Producto;
import org.mauricio.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repository {

    //obtiene la conexion
    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }


    //lista todos los productos
    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();

        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM productos as p inner join categorias as c ON (p.categoria_id = c.id)")) {

            while (rs.next()) {
                Producto p = plantillaProducto(rs);
                productos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }


    //lista el producto por id
    @Override
    public Producto porId(Long id) {
        Producto producto = null;
        try (PreparedStatement stmt = getConnection().prepareStatement("SELECT p.*, c.nombre as categoria FROM productos as p inner join categorias as c ON (p.categoria_id = c.id) WHERE p.id = ?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = plantillaProducto(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {

            sql = "update  productos set nombre = ?, precio = ?, categoria_id = ?  where id = ?";

        } else {

            sql = "insert into productos(nombre, precio, categoria_id, fecha_registro) values (?,?,?,?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategoria().getId());

            if (producto.getId() != null && producto.getId() > 0) {

                stmt.setLong(4, producto.getId());
            } else {

                stmt.setDate(4, new Date(producto.getfechaRegistro().getTime()));


            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;
    }


    @Override
    public void eliminar(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("delete from productos where id = ?")){
                stmt.setLong(1, id);
                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

}


    private Producto plantillaProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setfechaRegistro(rs.getDate("fecha_registro"));
        Categoria categoria = new Categoria();
        categoria.setId(rs.getLong("categoria_id"));
        categoria.setNombre(rs.getString("categoria"));
        p.setCategoria(categoria);
        return p;
    }
}
