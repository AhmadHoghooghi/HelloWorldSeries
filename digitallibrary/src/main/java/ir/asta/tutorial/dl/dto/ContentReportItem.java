package ir.asta.tutorial.dl.dto;

import ir.asta.tutorial.dl.entities.ContentTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentReportItem {

    private ContentTypeEntity contentType;
    private long count;

}
