package com.julianomengue.classes;

import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

public class Foto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Binary fotobinary;

	private String fotoString;

	public Foto() {
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

	public Binary getFotobinary() {
		return fotobinary;
	}

	public void setFotobinary(Binary fotobinary) {
		this.fotobinary = fotobinary;
	}

	public String getFotoString() {
		return fotoString;
	}

	public void setFotoString(String fotoString) {
		this.fotoString = fotoString;
	}

}
