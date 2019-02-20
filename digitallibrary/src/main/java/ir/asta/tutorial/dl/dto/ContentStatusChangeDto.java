package ir.asta.tutorial.dl.dto;

import ir.asta.tutorial.dl.entities.ContentStatus;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @EqualsAndHashCode(of = "id", doNotUseGetters = true, callSuper = false)
public class ContentStatusChangeDto implements Serializable {
    protected List<String> pkArray= new ArrayList<>();
    private ContentStatus newStatus;
}
