package com.CRUDProductos.CRUDSpringBoot.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.CRUDProductos.CRUDSpringBoot.interfaceService.IproductoService;
import com.CRUDProductos.CRUDSpringBoot.modelo.Producto;


@RestController
@RequestMapping("/api/productos")
public class ProductoApiController {
	
	@Autowired
	private IproductoService service;
	
	@GetMapping
	public List<Producto> listarProductos(){
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id){
		Optional <Producto> producto = service.listarId(id);
		return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	//Este método recibe un objeto Producto en JSON sin manejo de archivos.
	
	/*@PostMapping
	public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
	    Cliente nuevoCliente = service.saveAndReturnCliente(cliente);
	    return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
	}*/
	
	
	//Recibe parámetros individuales de un Cliente y un archivo de imagen
	
	@PostMapping
	public ResponseEntity<String> guardarProducto(
	        @RequestParam("nombre") String nombre,
	        @RequestParam("descripcion") String descripcion,
	        @RequestParam("precio") double precio,
	        @RequestParam("stock") int stock,
	        @RequestParam("categoria") String categoria,
	        @RequestParam("proveedor") String proveedor,
	        @RequestParam("estado") String estado,
	        @RequestParam(value = "imagenRuta", required = false) MultipartFile imagenRuta) {

	    Producto producto = new Producto();
	    producto.setNombre(nombre);
	    producto.setDescripcion(descripcion);
	    producto.setPrecio(precio);
	    producto.setStock(stock);
	    producto.setCategoria(categoria);
	    producto.setProveedor(proveedor);
	    producto.setEstado(estado);

	    // Guardar la imagen si se proporciona
	    if (imagenRuta != null && !imagenRuta.isEmpty()) {
	        try {
	            // Define la carpeta de destino
	            String fileName = System.currentTimeMillis() + "_" + imagenRuta.getOriginalFilename();
	            Path filePath = Paths.get("uploads/", fileName);

	            // Asegúrate de que la carpeta existe
	            Files.createDirectories(filePath.getParent());
	            Files.write(filePath, imagenRuta.getBytes());

	            // Establece la ruta de la imagen en el cliente
	            producto.setImagenRuta("uploads/" + fileName);
	        } catch (IOException e) {
	            e.printStackTrace();
                return new ResponseEntity<>("Error al guardar la imagen", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    service.saveAndReturnProducto(producto);
        return new ResponseEntity<>("Registro guardado correctamente", HttpStatus.CREATED);
	}

	// Método actualiza usando un objeto Cliente
	
	/*@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
	    Optional<Cliente> clienteExistente = service.listarId(id);
	    if (clienteExistente.isPresent()) {
	        cliente.setId(id);
	        Cliente clienteActualizado = service.saveAndReturnCliente(cliente);
	        return ResponseEntity.ok(clienteActualizado);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}*/
	
	
	//Método que actualiza el cliente y permite cargar una imagen opcional
	
	@PutMapping("/{id}")
	public ResponseEntity<String> actualizarProducto(
	        @PathVariable int id,
	        @RequestParam("nombre") String nombre,
	        @RequestParam("descripcion") String descripcion,
	        @RequestParam("precio") double precio,
	        @RequestParam("stock") int stock,
	        @RequestParam("categoria") String categoria,
	        @RequestParam("proveedor") String proveedor,
	        @RequestParam("estado") String estado,
	        @RequestParam(value = "imagenRuta", required = false) MultipartFile imagenRuta) {

	    Optional<Producto> productoExistente = service.listarId(id);
	    if (productoExistente.isPresent()) {
	        Producto producto = productoExistente.get();
	        producto.setNombre(nombre);
	        producto.setDescripcion(descripcion);
	        producto.setPrecio(precio);
	        producto.setStock(stock);
		    producto.setCategoria(categoria);
		    producto.setProveedor(proveedor);
		    producto.setEstado(estado);

	         
	        if (imagenRuta != null && !imagenRuta.isEmpty()) {
	            try {
	                 
	                String fileName = System.currentTimeMillis() + "_" + imagenRuta.getOriginalFilename();
	                Path filePath = Paths.get("uploads/", fileName);
	                 
	                Files.createDirectories(filePath.getParent());
	                Files.write(filePath, imagenRuta.getBytes());
	                 
	                producto.setImagenRuta("uploads/" + fileName);
	            } catch (IOException e) {
	                e.printStackTrace();
	                return new ResponseEntity<>("Error al guardar la imagen", HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        }

	        service.saveAndReturnProducto(producto);
            return ResponseEntity.ok("Registro actualizado correctamente");
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        Optional<Producto> producto = service.listarId(id);
        if (producto.isPresent()) {
            service.delete(id);
            return ResponseEntity.ok("Registro eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
