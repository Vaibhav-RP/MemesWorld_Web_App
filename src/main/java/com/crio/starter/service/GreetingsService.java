package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.repository.GreetingsRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingsService {

  @Autowired
  private  GreetingsRepository greetingsRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

    
    public boolean addMemetoRepo(GreetingsEntity memedto) {
      Query query = new Query(); // Query object
      // comparing memedto values to the database values.
      // Adding values to the criteria object.
      query.addCriteria(Criteria.where("name").is(memedto.getName()));
      query.addCriteria(Criteria.where("url").is(memedto.getUrl()));
      query.addCriteria(Criteria.where("caption").is(memedto.getCaption()));
      // Duplicacy check
      boolean isMemeAlreadyExist = mongoTemplate.exists(query, "greetings"); // mongo template.
  
      if (isMemeAlreadyExist == true) {
        return true;
      } else
        greetingsRepository.save(memedto);
      return false;
    }
  

  public Optional<GreetingsEntity> getMemeById(String id) {
    return greetingsRepository.findById(id);
  }


  public List<GreetingsEntity> getMemes() {
    Query query = new Query();
    query.limit(100);
    query.with(Sort.by(Sort.Direction.DESC, "id"));
    List<GreetingsEntity> memes = mongoTemplate.find(query,GreetingsEntity.class);
    return memes;
  }

  
  public GreetingsEntity updateMemeById(GreetingsEntity entity,String id) {
   Optional<GreetingsEntity> meme = greetingsRepository.findById(id);
    if(greetingsRepository.existsById(id)){  
      if(entity.getName() != null && !entity.getName().isEmpty())
          meme.get().setName(entity.getName());

      if(entity.getUrl() != null && !entity.getUrl().isEmpty())
          meme.get().setUrl(entity.getUrl()); 

      if(entity.getCaption() != null && !entity.getCaption().isEmpty())
          meme.get().setCaption(entity.getCaption());         
    }
    System.out.println(meme.toString());
    return greetingsRepository.save(meme.get());
  }

  
  public void deleteMemeById(String id) {
     greetingsRepository.deleteById(id);
  }

}
