package ir.asta.tutorial.dl.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@XStreamAlias("config")
@Getter
@Setter
public class SystemSettingConfig {
    @XStreamAsAttribute
    private String validFileTypes;
    @XStreamAsAttribute
    private Long maxSizeKB;

}
