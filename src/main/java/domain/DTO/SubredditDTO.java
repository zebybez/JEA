package domain.DTO;

import java.util.List;

public class SubredditDTO extends BaseDTO {
    private Long id;
    private String name;
    private String rules;

    public SubredditDTO() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
