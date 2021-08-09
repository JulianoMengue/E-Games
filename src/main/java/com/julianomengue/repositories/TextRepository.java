package com.julianomengue.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.julianomengue.classes.Text;

public interface TextRepository extends MongoRepository<Text, String> {

}
