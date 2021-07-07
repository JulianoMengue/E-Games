package com.julianomengue.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.julianomengue.classes.Game;
import com.julianomengue.services.GameService;

@Controller
@RequestMapping("/")
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping()
	public String games(Model model) {
		List<Game> games = this.gameService.findAll();
		games = this.gameService.gamesReady(games);
		model.addAttribute("games", games);
		return "games/games";
	}

	@GetMapping("/add")
	public String add(Model model) {
		Game game = new Game();
		model.addAttribute("game", game);
		return "games/add-game";
	}

	@RequestMapping("/addGame")
	public String addGame(@RequestParam("image1") MultipartFile image1, @RequestParam("image2") MultipartFile image2,
			@RequestParam("image3") MultipartFile image3, @RequestParam("image4") MultipartFile image4,
			@RequestParam("name") String name, @RequestParam("type") String type, @RequestParam("id") String id,
			@RequestParam("platform") String platform, @RequestParam("price") String price,
			@RequestParam("textArea") String overview, Model model) throws Exception {
		this.gameService.addGame(id,image1, image2, image3, image4, name, type, platform, price, overview);
		return "redirect:/";
	}

	@GetMapping("/showGame")
	public String showGame(Model model, @RequestParam String id) {
		Game game = new Game();
		game = this.gameService.findById(id);
		game.setFotos(this.gameService.binaryToStringList(game.getFotos()));
		model.addAttribute("game", game);
		return "games/game";
	}

	@GetMapping("/buyGame")
	public String buyGame(Model model, @RequestParam String id) {
		Game game = new Game();
		game = this.gameService.findById(id);
		for (int i = 0; i < game.getFotos().size(); i++) {
			game.getFotos().get(i)
					.setFotoString(this.gameService.binaryToString(game.getFotos().get(i)).getFotoString());
		}
		model.addAttribute("game", game);
		return "games/buy-game";
	}

	@GetMapping("/delete")
	public String delete(Model model, @RequestParam String id) {
		Game game = new Game();
		game = this.gameService.findById(id);
		this.gameService.delete(game);
		return "redirect:/";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam String id) {
		Game game = new Game();
		game = this.gameService.findById(id);
		model.addAttribute("game", game);
		return "games/add-game";
	}

}
