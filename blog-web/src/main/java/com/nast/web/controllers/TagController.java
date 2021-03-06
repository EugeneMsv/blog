package com.nast.web.controllers;

import com.nast.domain.entities.Tag;
import com.nast.domain.profiling.LogLevel;
import com.nast.domain.profiling.LoggableCall;
import com.nast.domain.services.TagService;
import com.nast.web.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Deprecated
@RestController
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @LoggableCall(logLevel = LogLevel.INFO, showArgs = true, timeRecord = true, showOutput = true)
    @RequestMapping(method = RequestMethod.GET)
    public Page<Tag> read(Pageable pageable) {
        return tagService.findAll(pageable);
    }

    @LoggableCall(logLevel = LogLevel.INFO, showArgs = true, timeRecord = true, showOutput = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tag> readById(@PathVariable Long id) {
        return tagService.findOne(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new DataNotFoundException(HttpStatus.NOT_FOUND, "Тег c id = " + id + " не найден"));
    }

    @LoggableCall(logLevel = LogLevel.INFO, showArgs = true, timeRecord = true, showOutput = true)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Tag add(@RequestBody Tag input) {
        return tagService.save(input);
    }
}
