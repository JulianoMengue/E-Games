package com.julianomengue.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julianomengue.classes.Game;

public interface GameRepository extends MongoRepository<Game, String> {

}
