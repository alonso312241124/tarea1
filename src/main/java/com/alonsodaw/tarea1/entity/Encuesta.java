package com.alonsodaw.tarea1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "encuestas")
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(min = 2, message = "El nombre debe tener al menos dos caracteres")
    private String nombre;

    @NotEmpty(message = "El apellido no puede estar vacío") // Corregido de 'nombre' a 'apellido'
    @Size(min = 2, message = "El apellido debe tener al menos dos caracteres")
    private String apellidos;

    @NotEmpty(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe tener un formato válido")
    private String email;

    @NotNull(message = "La edad no puede estar vacía") // Cambiado a @NotNull
    @Min(value = 18, message = "La edad no puede ser menor a 18")
    private Integer edad;

    @NotEmpty(message = "El teléfono no puede estar vacío")
    private String telefono;

    @NotNull(message = "La fecha no puede estar vacía")
    @PastOrPresent(message = "La fecha debe ser igual o anterior al día de hoy")
    private LocalDate fecha;

    @NotEmpty(message = "El motivo de la visita no puede estar vacío")
    private String motivoVisita;

    private boolean restaurante;
    private boolean gimnasio;
    private boolean spa;
    private boolean piscina;
    private boolean roomService;

    @NotEmpty(message = "El nivel de satisfacción no puede estar vacío")
    private String nivelSatisfaccion;

    private String otrosComentarios;

    public Encuesta(Long id, String nombre, String apellidos, String email, Integer edad, String telefono, LocalDate fecha, String motivoVisita, boolean restaurante, boolean gimnasio, boolean spa, boolean piscina, boolean roomService, String nivelSatisfaccion, String otrosComentarios) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.edad = edad;
        this.telefono = telefono;
        this.fecha = fecha;
        this.motivoVisita = motivoVisita;
        this.restaurante = restaurante;
        this.gimnasio = gimnasio;
        this.spa = spa;
        this.piscina = piscina;
        this.roomService = roomService;
        this.nivelSatisfaccion = nivelSatisfaccion;
        this.otrosComentarios = otrosComentarios;
    }

    public Encuesta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMotivoVisita() {
        return motivoVisita;
    }

    public void setMotivoVisita(String motivoVisita) {
        this.motivoVisita = motivoVisita;
    }

    public boolean isRestaurante() {
        return restaurante;
    }

    public void setRestaurante(boolean restaurante) {
        this.restaurante = restaurante;
    }

    public boolean isGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(boolean gimnasio) {
        this.gimnasio = gimnasio;
    }

    public boolean isPiscina() {
        return piscina;
    }

    public void setPiscina(boolean piscina) {
        this.piscina = piscina;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public String getNivelSatisfaccion() {
        return nivelSatisfaccion;
    }

    public void setNivelSatisfaccion(String nivelSatisfaccion) {
        this.nivelSatisfaccion = nivelSatisfaccion;
    }

    public String getOtrosComentarios() {
        return otrosComentarios;
    }

    public void setOtrosComentarios(String otrosComentarios) {
        this.otrosComentarios = otrosComentarios;
    }

    @Override
    public String toString() {
        return "Encuesta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", fecha=" + fecha +
                ", motivoVisita='" + motivoVisita + '\'' +
                ", restaurante=" + restaurante +
                ", gimnasio=" + gimnasio +
                ", spa=" + spa +
                ", piscina=" + piscina +
                ", roomService=" + roomService +
                ", nivelSatisfaccion='" + nivelSatisfaccion + '\'' +
                ", otrosComentarios='" + otrosComentarios + '\'' +
                '}';
    }
}
