package spring.basic.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
  /**
   * 
   * @param request
   * @param response
   * @param handler: 컨트롤러  
   * @param ex: 컨트롤러에서 발생한 예외
   * @return
   */
  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    try {
      /**
       * IllegalArgumentException 이 발생하면,
       * response.sendError(400) 를 호출해서 HTTP 상태코드를 400으로 지정하고,
       * 빈 ModelAndView 를 반환
       */
      if (ex instanceof IllegalArgumentException) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        return new ModelAndView();
      }
    } catch (IOException e) {
      log.error("resolver ex", e);
    }
    return null;
  }
}
