package agusev.parser.http.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "agusev.parser.http.controller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
