package com.idat.MayoServicioPrueba.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.aspectj.weaver.tools.Trace;

@Entity
@Table(name = "Cliente")
public class Cliente {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	private String cliente;
	private Integer celular;
	
	@OneToMany(mappedBy = "cliente")
	private List<Item> item;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(
			name = "producto_clientes",
			joinColumns = @JoinColumn(
					name = "id_producto",
					nullable = false,
					unique = true,
					foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_producto) references Productos (id_producto)") 
					),
			inverseJoinColumns = @JoinColumn(
					name = "id_cliente",
					nullable = false,
					unique = true,
					foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key (id_cliente) references Cliente (id_cliente)") 
					)
	)
	private List<Productos> producto = new ArrayList<>();;
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Integer getCelular() {
		return celular;
	}
	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	
}
