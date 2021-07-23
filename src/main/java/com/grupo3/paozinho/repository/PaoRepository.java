package com.grupo3.paozinho.repository;

import com.grupo3.paozinho.model.Pao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaoRepository extends MongoRepository<Pao, String> {

}
