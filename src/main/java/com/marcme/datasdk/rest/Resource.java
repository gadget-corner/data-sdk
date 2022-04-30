package com.marcme.datasdk.rest;

import com.marcme.datasdk.controller.Dto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * FIXME JavaDoc missing
 */
public interface Resource<DataTransferObject extends Dto, Identifier>
{
    /**
     * TODO JavaDoc
     *
     * @param id
     * @return
     */
    @GetMapping("find/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    DataTransferObject find(@PathVariable(name = "id") Identifier id);

    /**
     * TODO JavaDoc
     *
     * @param entity
     * @return
     */
    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.OK)
    DataTransferObject update(@RequestBody DataTransferObject entity);

    /**
     * TODO JavaDoc
     *
     * @param entity
     * @return
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    DataTransferObject create(@RequestBody DataTransferObject entity);

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    boolean delete(@PathVariable(name = "id") Identifier id);
}
