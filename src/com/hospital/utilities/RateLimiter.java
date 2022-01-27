package com.hospital.utilities;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class RateLimiter {
	
	private final long maxTokens;
    private long availableTokens;

    /**
     * Creates instance of rate limiter which provides guarantee that consumption rate will be >= tokens/periodMillis
     */
    public RateLimiter(long tokens, long periodMillis, ScheduledExecutorService scheduler) {
        long millisToRefillOneToken = periodMillis / tokens;
        scheduler.scheduleAtFixedRate(this::addToken, periodMillis, millisToRefillOneToken, TimeUnit.MILLISECONDS);

        this.maxTokens = tokens;
        this.availableTokens = tokens;
    }
    
  

    synchronized private void addToken() {
        availableTokens = Math.min(maxTokens, availableTokens + 1);
    }

    synchronized public boolean tryConsume(int numberTokens) {
        if (availableTokens < numberTokens) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean shouldConsume (int request, int time) {
    	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        RateLimiter limiter = new RateLimiter(request, time, scheduler);

        long startMillis = System.currentTimeMillis();
        int consumed = 0;
        while (System.currentTimeMillis() - startMillis < time && consumed < request) {
            if (limiter.tryConsume(consumed)) {
                consumed++;
                return true;
            }
        }
        scheduler.shutdown();
        return false;
   	 
    }
    
    private static final class Selftest {

        public static void main(String[] args) {
//            // 100 tokens per 1 second
//            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//            RateLimiter limiter = new RateLimiter(100, 1000, scheduler);
//
//            long startMillis = System.currentTimeMillis();
//            long consumed = 0;
//            while (System.currentTimeMillis() - startMillis < 10000) {
//                if (limiter.tryConsume(1)) {
//                    consumed++;
//                    System.out.println("true");
//                }
//            }
//            scheduler.shutdown();
//            System.out.println(consumed + " false");
        	
        	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    		int request = 2;
    		int period = 10000;
            RateLimiter limiter = new RateLimiter(request, period, scheduler);

            long startMillis = System.currentTimeMillis();
            int consumed = 0;
            while (System.currentTimeMillis() - startMillis < period && consumed < request) {
                if (limiter.tryConsume(consumed)) {
                    consumed++;
                    System.out.println("true");
                }
            }
            scheduler.shutdown();
            System.out.println(consumed + " false");	
        }

    }

}
