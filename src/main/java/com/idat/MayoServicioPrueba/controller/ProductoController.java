package com.idat.MayoServicioPrueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idat.MayoServicioPrueba.model.Productos;
import com.idat.MayoServicioPrueba.service.ProductoService;

@Controller
@RequestMapping("/producto/v1")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@RequestMapping(path = "/listar",method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Productos>> listar() {
		return new ResponseEntity<List<Productos>>( service.listarProductos(),HttpStatus.OK);
	}
	
	@RequestMapping(path =  "/guardar",method = RequestMethod.POST)
	public ResponseEntity<Void>  guardar(@RequestBody Productos producto){
		service.guardarProducto(producto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	
	@RequestMapping(path = "/eliminar{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarProductos(@PathVariable Integer id){
		Productos p = service.obtenerProductoId(id);
		if( p != null) {
			service.eliminarProducto(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/actualizar",method = RequestMethod.PUT)
	public ResponseEntity<Void> actualizar(@RequestBody Productos productos){
		Productos p = service.obtenerProductoId(productos.getIdProducto());
		if(p != null) {
			service.actualizarProducto(productos);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(path = "/buscarId{id}",method = RequestMethod.GET)
	public ResponseEntity<Productos> listarId(@PathVariable Integer id){
		Productos p = service.obtenerProductoId(id);
		if(p != null) {
			return new ResponseEntity<Productos>(service.obtenerProductoId(id),HttpStatus.OK);
		}
		return new ResponseEntity<Productos>(HttpStatus.NOT_FOUND);
		
	}

}
