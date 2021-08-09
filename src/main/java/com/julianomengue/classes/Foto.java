package com.julianomengue.classes;

import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

public class Foto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	private Binary fotoBinary;

	private String fotoString;

	public Foto() {
	}

	public Foto(Binary fotoBinary, String name) {
		this.name = name;
		this.fotoBinary = fotoBinary;
	}

	public Foto(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Binary getFotoBinary() {
		return fotoBinary;
	}

	public void setFotoBinary(Binary fotoBinary) {
		this.fotoBinary = fotoBinary;
	}

	public String getFotoString() {
		return fotoString;
	}

	public void setFotoString(String fotoString) {
		this.fotoString = fotoString;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
