package ist.challange.wahyubudiyanto.web.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ResponseHandler {

    public static ResponseEntity<Object> response(String message, HttpStatus status, Object responObject) {

        HashMap<String, Object> objMap = new HashMap<String, Object>();
        objMap.put("message", message);
        objMap.put("status", status);
        objMap.put("data", responObject);

        return new ResponseEntity<Object>(objMap, status);

    }

    public static ResponseEntity<Object> response(Object responObject) {

        HashMap<String, Object> objMap = new HashMap<String, Object>();
        objMap.put("data", responObject);

        return new ResponseEntity<Object>(objMap, HttpStatus.OK);

    }
}
