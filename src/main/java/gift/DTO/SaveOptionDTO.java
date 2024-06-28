package gift.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SaveOptionDTO {
    int id;
    String option;

    public int getId() {
        return id;
    }

    public String getOption() {
        return option;
    }

    public SaveOptionDTO(int id, String option) {
        this.id = id;
        this.option = option;
    }
}