/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package instinct.service.exception;

/**
 * InstinctServiceException indicates that the Instinct server is in trouble 
 * or the response from Instinct server is incorrect due to unrecognizable configuration.
 *
 * @author guoqing
 *
 */
public class InstinctServiceException extends Exception {
    private static final long serialVersionUID = -8746953581164081851L;
    
    public InstinctServiceException() {

    }

    public InstinctServiceException(String message) {
        super(message);
    }
    
    public InstinctServiceException(String message, Throwable t) {
        super(message, t);
    }
}
