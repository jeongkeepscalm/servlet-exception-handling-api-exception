package spring.basic.exception.servlet;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ErrorPageController {

  // RequestDispatcher 상수로 정의되어 있음
  public static final String ERROR_EXCEPTION = "jakarta.servlet.error.exception";
  public static final String ERROR_EXCEPTION_TYPE = "jakarta.servlet.error.exception_type";
  public static final String ERROR_MESSAGE = "jakarta.servlet.error.message";
  public static final String ERROR_REQUEST_URI = "jakarta.servlet.error.request_uri";
  public static final String ERROR_SERVLET_NAME = "jakarta.servlet.error.servlet_name";
  public static final String ERROR_STATUS_CODE = "jakarta.servlet.error.status_code";

  @GetMapping("/error-page/404")
  public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
    log.info(":: error page 404");
    printErrorInfo(request);
    return "error-page/404";
  }

  @GetMapping("/error-page/500")
  public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
    log.info(":: error page 500");
    printErrorInfo(request);
    return "error-page/500";
  }

  private void printErrorInfo(HttpServletRequest request) {
    Object errorException = request.getAttribute(ERROR_EXCEPTION);
    Object errorException_type = request.getAttribute(ERROR_EXCEPTION_TYPE);
    Object error_message = request.getAttribute(ERROR_MESSAGE);
    Object error_request_uri = request.getAttribute(ERROR_REQUEST_URI);
    Object error_servlet_name = request.getAttribute(ERROR_SERVLET_NAME);
    Object error_status_code = request.getAttribute(ERROR_STATUS_CODE);

    DispatcherType dispatcherType = request.getDispatcherType();

    log.info("errorException: {}", errorException);
    log.info("errorException_type: {}", errorException_type);
    log.info("error_message: {}", error_message);
    log.info("error_request_uri: {}", error_request_uri);
    log.info("error_servlet_name: {}", error_servlet_name);
    log.info("error_status_code: {}", error_status_code);
    log.info("dispatcherType: {}", dispatcherType);

    // 상수 변경: javax → jakarta
    // request.getAttributeNames().asIterator().forEachRemaining(v -> log.info(v));

  }

}
