package Simulating.PureMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.logging.Logger;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private static Logger logger = Logger.getLogger(Response.class.getName());
    private IP src;
    private IP dst;
    private Object data;
}
