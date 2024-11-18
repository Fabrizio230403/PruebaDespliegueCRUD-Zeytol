package com.CRUDProductos.CRUDSpringBoot.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String nombre;
		private String descripcion;
		private double precio;
		private int stock;
		private String categoria;
		private String proveedor;
		private String imagenRuta;
		private String estado;

		public Producto() {}

		
		public Producto(int id, String nombre, String descripcion, double precio, int stock, String categoria,
				String proveedor, String imagenRuta, String estado) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.precio = precio;
			this.stock = stock;
			this.categoria = categoria;
			this.proveedor = proveedor;
			this.imagenRuta = imagenRuta;
			this.estado = estado;
		}


		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}

		public String getProveedor() {
			return proveedor;
		}

		public void setProveedor(String proveedor) {
			this.proveedor = proveedor;
		}

		public String getImagenRuta() {
			return imagenRuta;
		}

		public void setImagenRuta(String imagenRuta) {
			this.imagenRuta = imagenRuta;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}
		
		
}