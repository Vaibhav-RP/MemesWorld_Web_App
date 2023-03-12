package com.crio.starter.controller;


import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.service.GreetingsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;



@AutoConfigureMockMvc
@SpringBootTest
class GreetingsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper mapper;

  @MockBean
  private GreetingsService greetingsService;

  
  GreetingsEntity meme1 = new GreetingsEntity("01", "XYZ", "https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png", "Meme 1");
  GreetingsEntity meme2 = new GreetingsEntity("02", "ABC", "https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png", "Meme 2");
  GreetingsEntity meme3 = new GreetingsEntity("03", "vaibhav patil", "https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png", "meme 3");


  @Test
  public void post_Api_CreateRecord_success() throws Exception {
        List<GreetingsEntity> memes = new ArrayList<>(Arrays.asList(meme1, meme2, meme3));
        GreetingsEntity newMeme = new GreetingsEntity("03", "vaibhav patil", "https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png", "meme 3");
  
        Mockito.when(greetingsService.addMemetoRepo(newMeme)).thenReturn(true);
  
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/memes/")
              .contentType(MediaType.APPLICATION_JSON)
              .accept(MediaType.APPLICATION_JSON)
              .content(this.mapper.writeValueAsString(newMeme));
  
        mockMvc.perform(mockRequest)
              .andExpect(status().isOk())
              .andExpect(jsonPath("$", notNullValue()))
              .andExpect(jsonPath("$.name", is("vaibhav patil")))
              .andExpect(jsonPath("$.url", is("https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png")))
              .andExpect(jsonPath("$.caption", is("meme 3")));
  }



  @Test
  public void getMemesById_Not_Found() throws Exception {
      Mockito.when(greetingsService.getMemeById("01")).thenReturn(null);
  
      mockMvc.perform(MockMvcRequestBuilders
              .get("/memes/01")
              .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isNotFound());
  }

  @Test
  public void getLatestRecords_success() throws Exception {
        List<GreetingsEntity> memes = new ArrayList<>(Arrays.asList(meme3, meme2, meme1));
      
        Mockito.when(greetingsService.getMemes()).thenReturn(memes);
      
        mockMvc.perform(MockMvcRequestBuilders
              .get("/memes/")
              .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$", hasSize(3)))
              .andExpect(jsonPath("$[0].name", is("vaibhav patil")))
              .andExpect(jsonPath("$[1].name", is("ABC")))
              .andExpect(jsonPath("$[2].name", is("XYZ")));
  }
}
//   @Test
//   public void getMemesById_success() throws Exception {
//         List<GreetingsEntity> memes = new ArrayList<>(Arrays.asList(meme1, meme2, meme3));

//         Mockito.when(greetingsService.getMemeById(memes.get(0).getId())).thenReturn(memes.get(0));
  
//         mockMvc.perform(MockMvcRequestBuilders
//               .get("/memes/01")
//               .contentType(MediaType.APPLICATION_JSON))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$", notNullValue()))
//               .andExpect(jsonPath("$.name", is("XYZ")))
//               .andExpect(jsonPath("$.url", is("https://cwod-assessment-images.s3.ap-south-1.amazonaws.com/images/130.png")))
//               .andExpect(jsonPath("$.caption", is("meme 1")));
//   }


// @Test
  // void sayHello() throws Exception {
  //   //given
  //   Mockito.doReturn(new ResponseDto("Hello Java"))
  //       .when(greetingsService).getMessage("001");

  //   // when
  //   URI uri = UriComponentsBuilder
  //       .fromPath("/say-hello")
  //       .queryParam("messageId", "001")
  //       .build().toUri();

  //   MockHttpServletResponse response = mockMvc.perform(
  //       get(uri.toString()).accept(APPLICATION_JSON_VALUE)
  //   ).andReturn().getResponse();

  //   //then
  //   String responseStr = response.getContentAsString();
  //   ObjectMapper mapper = new ObjectMapper();
  //   ResponseDto responseDto = mapper.readValue(responseStr, ResponseDto.class);
  //   ResponseDto ref = new ResponseDto("Hello Java");

  //   assertEquals(responseDto, ref);
  //   Mockito.verify(greetingsService, Mockito.times(1)).getMessage("001");
  // }

 
