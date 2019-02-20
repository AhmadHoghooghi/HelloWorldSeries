package ir.asta.tutorial.dl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContentStatusChangeResultDto {
    private int successfulChanges;
    private int failedChanges;

    public ContentStatusChangeResultDto() {
        successfulChanges = 0;
        failedChanges =0;
    }

    public void increaseSuccessChangesNum(){
        successfulChanges++;
    }

    public void increaseFailedChangesNum(){
        failedChanges++;
    }
}
