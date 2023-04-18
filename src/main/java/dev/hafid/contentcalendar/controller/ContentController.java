package dev.hafid.contentcalendar.controller;

import dev.hafid.contentcalendar.model.Content;
import dev.hafid.contentcalendar.model.Status;
import dev.hafid.contentcalendar.model.Type;
import dev.hafid.contentcalendar.repository.ContentCollectionRepository;
import dev.hafid.contentcalendar.repository.ContentJdbcTemplateRepository;
import dev.hafid.contentcalendar.repository.ContentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
    private final ContentRepository repository;

    //private final ContentJdbcTemplateRepository repository;


    public ContentController(ContentRepository repository) {
        this.repository = repository;
    }

    // make a request and find all the peaces of content in the system

    @GetMapping("") // empty path
    public List<Content> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not Found"));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create (@Valid @RequestBody Content content){
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content,@PathVariable Integer id){
        Optional<Content> existingContent = repository.findById(id);
            if (existingContent.isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not Found");
            }
            repository.save(content);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword){
        return repository.findAllByTitleContains(keyword);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findByStatus(@PathVariable Status status){
        return repository.listByStatus(status);
    }


}
