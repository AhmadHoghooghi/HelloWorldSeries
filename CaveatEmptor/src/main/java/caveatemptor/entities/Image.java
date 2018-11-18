package caveatemptor.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Image {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String fileName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(title, image.title) &&
                Objects.equals(fileName, image.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, fileName);
    }
}
