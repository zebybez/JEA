package domain.DTO;

import java.util.List;

public class BaseDTO {

    private List<String> links;

    public void addLink(String link){
        this.links.add(link);
    }

    public String[] getLinks(){
        return (String[]) this.links.toArray();
    }
}
