package com.nast.web;

import com.nast.aspects.LogLevel;
import com.nast.aspects.LoggableCall;
import com.nast.domain.entities.QTag;
import com.nast.domain.entities.Tag;
import com.nast.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        return tagService.findAll(QTag.tag.isNotNull(), pageable);
    }

    @LoggableCall(logLevel = LogLevel.INFO, showArgs = true, timeRecord = true, showOutput = true)
    @RequestMapping(value = "/{tagId}", method = RequestMethod.GET)
    public Tag readById(@PathVariable Long tagId) {
        return tagService.findOne(tagId);
    }

    @LoggableCall(logLevel = LogLevel.INFO, showArgs = true, timeRecord = true, showOutput = true)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Tag add(@RequestBody Tag input) {
        return tagService.save(input);
    }
}
