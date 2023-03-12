package com.crio.starter.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "greetings")
@CompoundIndex(def = "{'url':1}", unique = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GreetingsEntity {
  @Id // marks fields as primary key. It will be generated automatically.
  private String id;

  // @NotNull
  @NotBlank // to make these validation annotation working you have to use the annotation
            // @Valid.
  private String name;

  // @NotEmpty
  @NotBlank // to make these validation annotation working you have to use the annotation
            // @Valid.
  private String url;

  @NotBlank // to make these validation annotation working you have to use the annotation
            // @Valid.
  private String caption;
}
