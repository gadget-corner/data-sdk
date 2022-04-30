package com.marcme.datasdk.domain;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Node;

@Node("users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserNode extends BaseNode
{

}
