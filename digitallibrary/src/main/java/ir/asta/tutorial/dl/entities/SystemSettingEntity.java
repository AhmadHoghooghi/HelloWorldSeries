package ir.asta.tutorial.dl.entities;

import ir.asta.wise.core.data.annotation.EditorType;
import ir.asta.wise.core.data.annotation.PropertyGenDirective;
import ir.asta.wise.core.data.annotation.SearchMethod;
import ir.asta.wise.core.data.jpa.XmlAttributeConverter;
import ir.asta.wise.core.datamanagement.AbstractBaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "DL_System_Setting")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id", doNotUseGetters=true, callSuper=false)
public class SystemSettingEntity extends AbstractBaseEntity<String> {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "uuid")
    @GeneratedValue(generator = "generator")
    private java.lang.String id;

    @Basic
    @Column(name = "config_xml",length = 5000)
    @Convert(converter = SystemSettingConverter.class)
    private SystemSettingConfig config;

    @Override
    public String getPk() {
        return getId();
    }

    @Override
    public void setPk(String pk) {
        setId(pk);
    }

    public static class SystemSettingConverter extends XmlAttributeConverter<SystemSettingConfig>{
        @Override
        protected Class<?>[] annotatedClasses() {
            return new Class[]{SystemSettingConfig.class};
        }
    }
}
