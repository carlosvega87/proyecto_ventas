package com.distribuida.model;

import jakarta.persistence.*;

@Entity
@Table(name="productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_producto")
    private int idProducto;
    @Column (name = "nombre")
    private String nombre;
    @Column (name = "precio")
    private double precio;
    @Column (name = "stock")
    private int stock;
    @Column (name = "fecha_registro")
    private String fechaRegistro;
    @Column (name = "estado")
    private String estado;
//inyeccion de dependencias
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
public Productos(){}
    public Productos(int idProducto, String nombre, double precio, int stock, String fechaRegistro, String estado, Categoria categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.categoria = categoria;
    }


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", estado='" + estado + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
