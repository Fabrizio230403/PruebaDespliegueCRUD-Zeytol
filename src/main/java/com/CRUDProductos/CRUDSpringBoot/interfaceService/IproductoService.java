package com.CRUDProductos.CRUDSpringBoot.interfaceService;

import java.util.List;
import java.util.Optional;

import com.CRUDProductos.CRUDSpringBoot.modelo.Producto;

public interface IproductoService {
	public List<Producto>listar();
	public Optional<Producto>listarId(int id);
	public int save(Producto p);
	public void delete(int id);
	
}
