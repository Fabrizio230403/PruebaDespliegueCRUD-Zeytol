package com.CRUDProductos.CRUDSpringBoot.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CRUDProductos.CRUDSpringBoot.modelo.Producto;

@Repository
public interface IProducto extends CrudRepository<Producto, Integer>{

}

 