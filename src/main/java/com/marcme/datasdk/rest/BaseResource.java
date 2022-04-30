package com.marcme.datasdk.rest;

import com.marcme.datasdk.controller.Dto;
import com.marcme.datasdk.controller.EntityController;
import com.marcme.datasdk.domain.BaseNode;
import com.marcme.datasdk.repository.BaseReactiveRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

/**
 * Basic implementation of a resource rest controller in order to provide some default implementation for the
 * inheriting controllers. These are mostly the default methods of the different implementations of jpa-controller.
 */
@SuppressWarnings("unchecked")
public class BaseResource<Returntype extends Dto,Identifier, DataAccessObject extends BaseNode> implements Resource<Returntype, Identifier> {

    protected final EntityController<DataAccessObject, Identifier> controller;

    /**
     * Public default constructor which is mandatory for the inheritance
     *
     * @param repository The implementation of {@link EntityController} to use
     */
    protected BaseResource(BaseReactiveRepository<DataAccessObject, Identifier> repository) {
        this.controller = new EntityController<DataAccessObject, Identifier>(repository);
    }

    @Override
    public Returntype update(Returntype entity) {
        return create(entity);
    }

    @Override
    public Returntype find(@PathVariable(name = "id") Identifier id) {
        ModelMapper modelMapper = new ModelMapper();
        Mono<DataAccessObject> basicNode = controller.findById(id);
        return (Returntype) modelMapper.map(basicNode, getClass().getAnnotations()[0].getClass());
    }

    @Override
    public Returntype create(@RequestBody Returntype entity) {
        ModelMapper modelMapper = new ModelMapper();
        Annotation dao = modelMapper.map(entity, getClass().getAnnotations()[1].getClass());
        Mono<DataAccessObject> basicNode = controller.saveOrUpdate((DataAccessObject)modelMapper.map(entity, dao.getClass()));
        return (Returntype) modelMapper.map(basicNode, getClass().getAnnotations()[0].getClass());
    }

    @Override
    public boolean delete(@PathVariable(name = "id") Identifier id) {
        return controller.deleteById(id);
    }

}

