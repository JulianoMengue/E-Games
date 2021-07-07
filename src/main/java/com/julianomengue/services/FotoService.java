package com.julianomengue.services;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julianomengue.classes.Foto;
import com.julianomengue.repositories.FotoRepository;

@Service
public class FotoService {

	@Autowired
	private FotoRepository fotoRepo;

	public Foto insert(Foto foto) {
		return this.fotoRepo.insert(foto);
	}

	public Foto findById(String id) {
		return this.fotoRepo.findById(id).get();
	}

	public Foto binaryToString(Foto foto) {
		foto.setFotoString(Base64.getEncoder().encodeToString(foto.getFotobinary().getData()));
		return foto;
	}

}
