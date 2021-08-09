package com.julianomengue.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.julianomengue.classes.Foto;
import com.julianomengue.classes.Game;
import com.julianomengue.repositories.GameRepository;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepo;

	@Autowired
	private GameService gameService;

	public Game insert(Game game) {
		return this.gameRepo.insert(game);
	}

	public Game save(Game game) {
		return this.gameRepo.save(game);
	}

	public List<Game> findAll() {
		return this.gameRepo.findAll();
	}

	public Game findById(String id) {
		return this.gameRepo.findById(id).get();
	}

	public void delete(Game game) {
		this.gameRepo.delete(game);
	}

	public Foto binaryToString(Foto foto) {
		foto.setFotoString(Base64.getEncoder().encodeToString(foto.getFotoBinary().getData()));
		return foto;
	}

	public List<Foto> binaryToStringList(List<Foto> fotos) {
		for (int i = 0; i < fotos.size(); i++) {
			fotos.get(i).setFotoString(this.gameService.binaryToString(fotos.get(i)).getFotoString());
		}
		return fotos;
	}

	public List<Game> gamesReady(List<Game> games) {
		List<Foto> fotosBinary = new ArrayList<Foto>();
		for (int i = 0; i < games.size(); i++) {
			fotosBinary = games.get(i).getFotos();
			for (int y = 0; y < fotosBinary.size(); y++) {
				games.get(i).getFotos().get(y)
						.setFotoString(this.gameService.binaryToString(fotosBinary.get(y)).getFotoString());
			}
		}
		return games;
	}

	public void addGame(String id, MultipartFile image1, MultipartFile image2, MultipartFile image3,
			MultipartFile image4, String name, String type, String platform, String price, String overview)
			throws IOException {
		List<Foto> fotos = new ArrayList<Foto>();
		Foto foto1 = new Foto(new Binary(BsonBinarySubType.BINARY, image1.getBytes()), image1.getOriginalFilename());
		Foto foto2 = new Foto(new Binary(BsonBinarySubType.BINARY, image2.getBytes()), image2.getOriginalFilename());
		Foto foto3 = new Foto(new Binary(BsonBinarySubType.BINARY, image3.getBytes()), image3.getOriginalFilename());
		Foto foto4 = new Foto(new Binary(BsonBinarySubType.BINARY, image4.getBytes()), image4.getOriginalFilename());
		fotos.add(foto1);
		fotos.add(foto2);
		fotos.add(foto3);
		fotos.add(foto4);
		Game game = new Game(id, name, type, platform, price, overview, fotos);
		this.gameService.save(game);
	}

}
