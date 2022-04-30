package com.marcme.datasdk.repository;

import com.marcme.datasdk.controller.Dto;
import com.marcme.datasdk.domain.BaseNode;
import org.neo4j.driver.internal.shaded.reactor.core.publisher.Flux;
import org.springframework.data.domain.Example;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Generic implementation for a neo4j database. It provides the inherited and custom functionality to manage and
 * query neo4j databases. It is implemented as a {@link ReactiveNeo4jRepository} to provides Flux and Mono to the
 * client.
 */
public interface BaseReactiveRepository<T extends BaseNode, K> extends ReactiveNeo4jRepository<T, K>
{
  @Override
  <S extends T> reactor.core.publisher.Flux<S> findAll(Example<S> example);

}