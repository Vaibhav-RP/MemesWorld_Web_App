package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.repository.GreetingsRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class GreetingsServiceTest {

  @InjectMocks
  private GreetingsService greetingsService;

  @Mock
  private GreetingsRepository greetingsRepository;
  
  GreetingsEntity meme1 = new GreetingsEntity("01","vaibhav","https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png","this is meme 1");
  GreetingsEntity meme2 = new GreetingsEntity("02","ABC","https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png","this is meme 2");
  GreetingsEntity meme3 = new GreetingsEntity("03","XYZ","https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png","this is meme 3");


  // @Test
  // public void getMemeById() throws Exception {

  //     GreetingsEntity meme = new GreetingsEntity("01","vaibhav","https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png","this is meme 1");
  //     Mockito.when(greetingsRepository.findByid("01")).thenReturn(meme);

  //     GreetingsEntity actRecords = greetingsService.getMemeById("01");

  //     assertEquals(meme.getName(), actRecords.getName());
  //     assertEquals(meme.getUrl(), actRecords.getUrl());
  //     assertEquals(meme.getCaption(), actRecords.getCaption());
  // }    
  

  // @Test
  // public void saveMeme(){
  //   List<GreetingsEntity> memeList = new ArrayList<>(Arrays.asList(meme1, meme2, meme3));
  //   GreetingsEntity newMeme = new GreetingsEntity("01","vaibhav","https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png","this is meme 1");
  //   Mockito.when(greetingsRepository.save(newMeme)).thenReturn(memeList.get(0));

  //   boolean actRecords = greetingsService.addMemetoRepo(newMeme);

  //   assertEquals(newMeme.getName(), actRecords.getName());
  //   assertEquals(newMeme.getUrl(), actRecords.getUrl());
  //   assertEquals(newMeme.getCaption(), actRecords.getCaption());
  // }
  
}
// @Test
  // void getMessage() {
  //   GreetingsEntity greetingsEntity = getGreeting("001", "Welcome");
  //   Mockito.doReturn(greetingsEntity)
  //       .when(greetingsRepository).findByExtId("001");
  //   ResponseDto responseDto = greetingsService.getMessage("001");

  //   ResponseDto expected = new ResponseDto("Welcome");
  //   assertEquals(expected, responseDto);

  // }