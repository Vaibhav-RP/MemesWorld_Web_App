package com.crio.starter.controller;

import lombok.RequiredArgsConstructor;
import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.PostMemeResponse;
import com.crio.starter.repository.GreetingsRepository;
import com.crio.starter.service.GreetingsService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsController {

  private final GreetingsService greetingsService;
  private final GreetingsRepository greetingsRepository;

 

  
  @PostMapping("/memes/")
  public ResponseEntity<PostMemeResponse> addMeme(@Valid @RequestBody GreetingsEntity entity) {  
    boolean duplicate = greetingsService.addMemetoRepo(entity); // Dupliate check by service class method.
    PostMemeResponse response = new PostMemeResponse(entity.getId()); // set Id value by all argument constructor.

    if (duplicate == false) {
      return new ResponseEntity<>(response, HttpStatus.CREATED); // If not duplicate
    } else
      return new ResponseEntity<>(HttpStatus.CONFLICT); // If Duplicate.
  }



  @GetMapping("/memes")
  public ResponseEntity<List<GreetingsEntity>> getTopHundredMemes() {
    if(greetingsRepository.findAll().isEmpty()){
      return ResponseEntity.ok().body(greetingsRepository.findAll());
    }
    return ResponseEntity.ok().body(greetingsService.getMemes());
  }
  

  @GetMapping("/memes/{id}")
  public ResponseEntity<GreetingsEntity> findMemeById(@PathVariable("id") String memeid) {

    Optional<GreetingsEntity> meme = greetingsService.getMemeById(memeid);

    if (meme.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else
      return new ResponseEntity<>(meme.get(), HttpStatus.CREATED);
  }

  
  @PutMapping("/update/{id}")
  public ResponseEntity<GreetingsEntity> updateMemes(@RequestBody GreetingsEntity entity, @PathVariable String id) {
    if(greetingsRepository.existsById(id)){
      return ResponseEntity.ok().body(greetingsService.updateMemeById(entity,id));
    }  
    else{
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "entity not found"
      );  
    }
   
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteMemes(@PathVariable String id) {
    if(greetingsRepository.existsById(id)){
      greetingsService.deleteMemeById(id);
    return ResponseEntity.ok().body("Meme is successfully deleted");
    }  
    else{
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND, "entity not found"
      );  
    }
   
  }
  
}
