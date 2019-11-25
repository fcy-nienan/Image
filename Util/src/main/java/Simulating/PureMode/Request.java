package Simulating.PureMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private static Logger logger = Logger.getLogger(Request.class.getName());
    private IP src;
    private IP dst;
    private Object data;
    public static void main(String args[]) throws Exception {

    }
}
