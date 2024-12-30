package com.example.PR6;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class Pr6Application {

    public static void main(String[] args) {
        SpringApplication.run(Pr6Application.class, args);
    }
}

@Component
class DailyTask {

    @Scheduled(cron = "0 0 10 * * ?")
    public void performDailyTask() {
        System.out.println("Щоденна задача виконана о 10:00");
    }
}

@Component
class RepeatedTask implements CommandLineRunner {

    private int secondsPassed = 0;

    @Override
    public void run(String... args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            secondsPassed += 3;
            BigInteger factorial = calculateFactorial(secondsPassed);
            System.out.println("Пройшло " + secondsPassed + " секунд. " + secondsPassed + "! = " + factorial);
        }, 2, 3, TimeUnit.SECONDS);
    }

    private BigInteger calculateFactorial(int number) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
