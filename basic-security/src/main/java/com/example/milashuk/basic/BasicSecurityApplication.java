package com.example.milashuk.basic;

import com.example.milashuk.basic.ustil.AsyncUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.concurrent.*;


@SpringBootApplication
@RestController
@EnableAsync
public class BasicSecurityApplication {

    @Autowired
    private AsyncUtil asyncUtil;

    public static void main(String[] args) {
        SpringApplication.run(BasicSecurityApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }

    @GetMapping("/ciao")
    public String ciao() throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("I am inside task");
            return authentication.getName();
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService = new DelegatingSecurityContextExecutorService(executorService);
        try {
            System.out.println("Ready to submit task");
            String result = "Ciao, " + executorService.submit(callable).get() + "!";
            System.out.println("Ready to return:" + result);
            return result;
        } finally {
            System.out.println("I am in finally");
            executorService.shutdown();
        }
    }

    @GetMapping("/goodbye")
    public String goodBye() {
        asyncUtil.asyncMethod();
        return "Goodbye";
    }
}
