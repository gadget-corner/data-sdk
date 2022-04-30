package com.marcme.datasdk.controller;

import com.marcme.datasdk.domain.BaseNode;
import com.marcme.datasdk.repository.BaseReactiveRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

/**
 * Controller interface which is injected into the Resource in order to have a separation of layers.
 * The controller is mostly used for and the place where to implement the business logic of an application.
 * <p>
 * The controller may inject or use other services itself and is responsible for business logic with all steps
 * required. Implementing classes may have custom implementation or may provide further functionality, but the base
 * interface is the place to put the basic contract of the service to.
 * <p>
 */
@Slf4j
@Transactional
@RequiredArgsConstructor
public class EntityController<DataAccessObject extends BaseNode, Identifier>
{
  /* The repository to use to interact with the db */
  @Autowired
  private final BaseReactiveRepository<DataAccessObject, Identifier> repository;

  /**
   * Find the requested entity by the identifier.
   *
   * @param id The identifier to use to find the entity
   * @return The entity which is identified by the specified value or null in case of an error.
   */
  public Mono<DataAccessObject> findById(final Identifier id)
  {
    Mono<DataAccessObject> result = null;

    try
    {
      result = repository.findById(id);
    }
    catch (Exception exception)
    {
      log.error(exception.getMessage(), exception);
    }

    return result;
  }

  /**
   * Create a new or update an existing entity.
   *
   * @param entity The entity to use.
   * @return The entity which was created or null in case of an error.
   */
  public Mono<DataAccessObject> saveOrUpdate(@NonNull final DataAccessObject entity)
  {
    Mono<DataAccessObject> result = null;

    try
    {
      result = repository.save(entity);
    }
    catch (Exception exception)
    {
      log.error(exception.getMessage(), exception);
    }

    return result;
  }

  /**
   * Delete the requested entity related to the specified identifier.
   *
   * @param item The item to delete in the db.
   * @return A flag indicating whether the object was deleted or not.
   */
  public boolean delete(@NonNull final DataAccessObject item)
  {
    boolean result;

    try
    {
      repository.deleteById((Identifier) item.getId());
      result = true;
    }
    catch (Exception exception)
    {
      result = false;
      log.error(exception.getMessage(), exception);
    }

    return result;
  }

  /**
   * Delete the requested entity related to the specified identifier.
   *
   * @param id The id to delete in the db.
   * @return A flag indicating whether the object was deleted or not.
   */
  public boolean deleteById(@NonNull final Identifier id)
  {
    boolean result;

    try
    {
      repository.deleteById(id);
      result = true;
    }
    catch (Exception exception)
    {
      result = false;
      log.error(exception.getMessage(), exception);
    }

    return result;
  }

  /**
   * Check whether an item exists which is matching the id or not.
   *
   * @param id The identifier to use.
   * @return A flag indicating if it exists or not.
   */
  public Mono<Boolean> exists(Identifier id)
  {
    return repository.existsById(id);
  }

  /**
   * Find an entity by example
   *
   * @param item The example to use to find matching entities
   * @return The entities matched the example
   */
  public Flux<DataAccessObject> findByExample(Dto item)
  {
    ModelMapper modelMapper = new ModelMapper();
    Annotation annotation = getClass().getAnnotations()[0];
    DataAccessObject mappedItem = (DataAccessObject) modelMapper.map(item, annotation.getClass());
    Example<DataAccessObject> example = Example.of(mappedItem, ExampleMatcher.matching());
    Flux<DataAccessObject> result = null;
    try
    {
      result = repository.findAll(example);
    }
    catch (Exception exception)
    {
      log.error(exception.getMessage(), exception);
    }

    return result;
  }
}
