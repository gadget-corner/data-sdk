package com.marcme.datasdk.repository;

import com.marcme.datasdk.domain.BaseNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Generic implementation for a neo4j database. It provides the inherited and custom functionality to manage and
 * query neo4j databases. It is implemented as a common {@link Repository}.
 */
public interface BaseRepository<T extends BaseNode, K> extends Neo4jRepository<T, K>
{
}