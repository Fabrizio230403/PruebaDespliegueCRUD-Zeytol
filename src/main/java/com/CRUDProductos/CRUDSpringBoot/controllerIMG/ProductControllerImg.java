package com.CRUDProductos.CRUDSpringBoot.controllerIMG;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;


@RestController
@RequestMapping("/uploads")
public class ProductControllerImg {
	  @GetMapping("/{fileName:.+}")
	    public Resource getImage(@PathVariable String fileName) {
	        File file = new File("uploads/" + fileName);
	        return new FileSystemResource(file);
	    }
}


 