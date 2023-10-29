// model subject
package sit.int202.simplefri.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class Subject { // model data stored
    private String id  ;
    private String title  ;
    private double credit ;
}
