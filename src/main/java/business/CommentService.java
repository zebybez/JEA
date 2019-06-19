package business;

import javax.ejb.Stateless;

@Stateless
public class CommentService implements business.interfaces.CommentService {
    public void testMethod() {
        System.out.println("do nothing");
    }
}
