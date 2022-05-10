package com.marcme.datasdk.repository;

import com.marcme.datasdk.domain.BaseNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;


/**
 * Generic implementation for a neo4j database. It provides the inherited and custom functionality to manage and
 * query neo4j databases. It is implemented as a {@link ReactiveNeo4jRepository} to provides Flux and Mono to the
 * client.
 */
public interface BaseReactiveRepository<T extends BaseNode, K> extends Neo4jRepository<T, K>
{
}