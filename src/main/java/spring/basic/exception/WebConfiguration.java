package spring.basic.exception;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import spring.basic.exception.filter.LogFilter;
import spring.basic.exception.interceptor.LogInterceptor;
import spring.basic.exception.resolver.MyHandlerExceptionResolver;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

//  @Bean
  public FilterRegistrationBean logFilter() {
    FilterRegistrationBean<Filter> frb = new FilterRegistrationBean<>();
    frb.setFilter(new LogFilter());
    frb.setOrder(1);
    frb.addUrlPatterns("/*");

    /**
     * 기본값: frb.setDispatcherTypes(DispatcherType.REQUEST);
     */
    // 에러 요청에 대한 로그도 남기고 싶을 경우
    frb.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
    // 에러 요청만 로그로 남기고 싶을 경우
    // frb.setDispatcherTypes(DispatcherType.ERROR);
    return frb;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns(
                    "/css/**", "/*.ico"
                    , "/error", "/error-page/**"
            );
    // excludePathPatterns 에 속한 주소를 제외한 나머지 주소에 로그가 찍힌다.
  }

  @Override
  public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    resolvers.add(new MyHandlerExceptionResolver());
  }
}
