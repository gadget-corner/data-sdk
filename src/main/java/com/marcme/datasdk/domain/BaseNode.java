package com.marcme.datasdk.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDateTime;

@Node
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseNode<Identifier>
{
  @Id
  @EqualsAndHashCode.Include
  protected Identifier id;

  @CreatedDate
  protected LocalDateTime created;

  @LastModifiedDate
  protected LocalDateTime modified;

}
