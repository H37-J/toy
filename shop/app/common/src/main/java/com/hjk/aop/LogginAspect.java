// package com.hjk.aop;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.aspectj.lang.ProceedingJoinPoint;
// import org.aspectj.lang.annotation.Around;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Pointcut;
// import org.springframework.stereotype.Component;
// import org.springframework.web.context.request.RequestContextHolder;
// import org.springframework.web.context.request.ServletRequestAttributes;

// import javax.servlet.http.HttpServletRequest;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.hjk.utils.DateUtils;

// import java.io.File;
// import java.net.InetAddress;
// import java.net.UnknownHostException;
// import java.time.LocalDateTime;
// import java.util.*;

// @Aspect
// @Slf4j
// @Component
// public class LogginAspect {

//     private static final String UNKNOWN = "unknown";
    

//     @Pointcut("execution (* com.hjk.controller.*Controller.*(..))")
//     public void log() {
//     }
 
//     @Around("log()")
//     public Object aroundLog(ProceedingJoinPoint point) throws Throwable {

//         ObjectMapper mapper = new ObjectMapper();
//         ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//         HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
 
//         long startTime = System.currentTimeMillis(); //실행시간 측정 시작
//         Object result = point.proceed(); //접속
//         String userAgent = request.getHeader("User-Agent"); //브라우저

//         final Log l = Log.builder().threadId(Long.toString(Thread.currentThread().getId()))
//         .threadName(Thread.currentThread().getName())
//         .ip(getIp(request)).url(request.getRequestURL().toString())
//         .httpMethod(request.getMethod())
//         .timeCost((System.currentTimeMillis() - startTime) / 1000.0)
//         .createdAt(DateUtils.LocalDateFormat(LocalDateTime.now()))
//         .userAgent(userAgent)
//         .build();

//         log.info("요청 로그 : {}", l.toString());

//         Map<Integer,Object> add_map = new HashMap<>();
//         Map<Integer,Object> current_map = mapper.readValue(new File("log.json"),Map.class);
 
//         int num= 1;
 
//         if(!current_map.isEmpty()) {
//             for (Map.Entry<Integer, Object> entry : current_map.entrySet()) {
//                 add_map.put(num, entry.getValue());
//                 num++;
//             }
//         }
 
//         add_map.put(num,l);
//         mapper.writerWithDefaultPrettyPrinter().writeValue(new File("log.json"), add_map);
 
//         return result;
//     }



//     public static String getIp(HttpServletRequest request) {
//         String ip = request.getHeader("x-forwarded-for");
//         if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//             ip = request.getHeader("Proxy-Client-IP");
//         }
//         if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//             ip = request.getHeader("WL-Proxy-Client-IP");
//         }
//         if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
//             ip = request.getRemoteAddr();
//         }
//         String comma = ",";
//         String localhost = "127.0.0.1";
//         if (ip.contains(comma)) {
//             ip = ip.split(",")[0];
//         }
//         if (localhost.equals(ip)) {
//             try {
//                 ip = InetAddress.getLocalHost().getHostAddress();
//             } catch (UnknownHostException e) {
//                 log.error(e.getMessage(), e);
//             }
//         }
//         return ip;
//     }


//     @Builder
//     @NoArgsConstructor
//     @AllArgsConstructor
//     @Getter
//     static class Log {
 
//         private String threadId;
 
//         private String threadName;

//         protected String createdAt;
 
//         private String ip;
 
//         private String url;
 
//         private String httpMethod;
 
//         private Double timeCost;

//         private String userAgent;
 
//         @Override
//         public String toString() {
//             return "스레드: " + this.threadId + "," + "스레드네임: " + this.threadName + "," + "아이피: " + this.ip + "," + "URI: "
//                     + this.url + "," + "메소드: " + this.httpMethod + ", 클래스 메소드: " + ", 파라미터: "
//                     +", 실행시간: " + this.timeCost + ", 유저정보: " + this.userAgent + ", 요청시간: " + this.createdAt;
//         }
//     }

// }

