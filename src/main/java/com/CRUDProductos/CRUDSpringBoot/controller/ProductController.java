package com.CRUDProductos.CRUDSpringBoot.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.CRUDProductos.CRUDSpringBoot.interfaceService.IproductoService;
import com.CRUDProductos.CRUDSpringBoot.modelo.Producto;
 
import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductController {
	
	@Autowired
	private IproductoService service;
	
	private static final String UPLOAD_DIR = "uploads/"; // Cambia a un directorio accesible
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Producto>productos=service.listar();
		model.addAttribute("productos",productos);
		return "gestionProducto";
	}
	
	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("producto", new Producto());
		return "formularioProducto";
	}
	
	@PostMapping("/save")
	public String save(Producto producto, @RequestParam("foto") MultipartFile foto, Model model) {
	    try {
	        if (foto != null && !foto.isEmpty()) {
	            String fileName = System.currentTimeMillis() + "_" + foto.getOriginalFilename(); // Renombrar archivo para evitar conflictos
	            Path filePath = Paths.get(UPLOAD_DIR, fileName);

	            // Asegúrate de que el directorio existe
	            Files.createDirectories(filePath.getParent()); // Crea el directorio si no existe

	            Files.write(filePath, foto.getBytes());
	            producto.setImagenRuta("uploads/" + fileName); // Ruta relativa para acceder desde el navegador
	        } else {
	            // Si no se proporciona una nueva foto, conservar la foto existente
	            Optional<Producto> productoExistente = service.listarId(producto.getId());
	            if (productoExistente.isPresent()) {
	                producto.setImagenRuta(productoExistente.get().getImagenRuta());
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        model.addAttribute("producto", producto); // Mantén los datos del cliente para mostrar en el formulario
	        return "formularioProducto"; // Regresa al formulario para corregir el error
	    }

	    
	    service.save(producto);

	    return "redirect:/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editarProducto(@PathVariable int id, Model model) {
	    Optional<Producto> producto = service.listarId(id);
	    if (producto.isPresent()) {
	        model.addAttribute("producto", producto.get());
	    } else {
	        // Manejar el caso donde el cliente no existe (opcional)
	        return "redirect:/error"; // o cualquier otra lógica que desees
	    }
	    return "formularioProducto"; // Asegúrate de que este es el nombre correcto de tu plantilla
	}
	
	
	@GetMapping("/eliminar/{id}")
	public String delete(Model model, @PathVariable int id) {
	    Optional<Producto> producto = service.listarId(id);
	    if (producto.isPresent()) {
	        String fotoRuta = producto.get().getImagenRuta();
	        System.out.println("Ruta de la foto: " + fotoRuta);  
	        if (fotoRuta != null && !fotoRuta.isEmpty()) {
	            Path path = Paths.get(fotoRuta);  
	            try {
	                Files.deleteIfExists(path);
	                System.out.println("Imagen eliminada: " + path.toString());  
	            } catch (IOException e) {
	                e.printStackTrace();
	                model.addAttribute("error", "Error al eliminar la imagen: " + e.getMessage());
	                return "redirect:/listar"; // Redirigir en caso de error
	            }
	        }
	    }

	    System.out.println("Eliminando producto con ID: " + id);  
	    service.delete(id);
	    return "redirect:/listar";
	}
	
}




 
	
	 
	 
	
	 
	
	 

	 
	
	 
	
 