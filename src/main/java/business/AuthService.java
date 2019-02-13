package business;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import util.security.Payload;

import javax.ejb.Stateless;

@Stateless
public class AuthService {
    public AuthService() {
    }

    public Payload getPayloadByCredentials(String email, String password){
      //  Payload payload = new Payload();
        throw new NotImplementedException();
    }


}
