/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package instinct.service.exception;

/**
 * MalFormedApplicationException indicates that the application from Catfish is incomplete/incorrect due to the lack of some columns/attributes.
 * 
 * @author guoqing
 * 
 */
public class MalFormedApplicationException extends Exception {
    private static final long serialVersionUID = 609315627740039301L;
   
    public MalFormedApplicationException() {

    }

    public MalFormedApplicationException(String message, Throwable t) {
        super(message, t);
    }
}
