package org.mauricio.jdbc;

import org.mauricio.jdbc.modelo.Categoria;
import org.mauricio.jdbc.modelo.Producto;
import org.mauricio.jdbc.repositorio.ProductoRepositorioImpl;
import org.mauricio.jdbc.repositorio.Repository;
import org.mauricio.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.Date;


public class EjemploJdbc {

    public static void main(String[] args) {


        try (Connection conn = ConexionBaseDatos.getInstance()) {

            Repository<Producto> repository = new ProductoRepositorioImpl();

/*            //metodo listar todos
            System.out.println("========= Listar ========");
            repository.listar().forEach(System.out::println);*/

/*            //metodo listar por id
            System.out.println("======== porId ==========");
            System.out.println(repository.porId(2L));*/

/*            //metodo crear
            System.out.println("======== insertar nuevo producto =========");
            Producto producto = new Producto();
            producto.setNombre("teclado mecanco");
            producto.setPrecio(5000);
            producto.setfechaRegistro(new Date());
            // asignamos una categoria al producto
            Categoria categoria = new Categoria();
            categoria.setId(3L);
            producto.setCategoria(categoria);
            // ***********************************
            repository.guardar(producto);
            System.out.println("producto guardado con exito!");*/
//
//            //metodo listar todos
//            System.out.println("========= Listar nueva ========");
//            repository.listar().forEach(System.out::println);
//
//            System.out.println("======== Eliminar por Id =======");
//            repository.eliminar(4L);
//            System.out.println("producto eliminado con exito!");


/*            System.out.println("======== Modificar =======");
            Producto producto1 = new Producto();
            producto1.setId(5L);
            producto1.setNombre("teclado digital");
            producto1.setPrecio(2000);
            //producto1.setfechaRegistro(new Date()); //No se actualiza
            repository.guardar(producto1);
            System.out.println("producto guardado con exito!");*/


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


