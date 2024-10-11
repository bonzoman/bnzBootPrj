package java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class RxjavaTest {
	
	public RxjavaTest(){
	}
	
	public static void main(String[] args) throws Exception {
		
		RxjavaTest mc = new RxjavaTest();
		mc.dd();
		
//		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1"); 
//		myList 
//		     .stream() // 스트림 생성
//		     // 중간 연산들
//		     .filter(s -> s.startsWith("c")) 
//		     .map(String::toUpperCase) 
//		     .sorted()
//		     // 단말 연산
//		     .forEach(System.out::println); 
	}

	

	
	
	
	/**
	 * @throws Exception
	 */
	public void dd() throws Exception 
	{
		Supplier<Double> supplier = () -> {
		    try {
		        Thread.sleep(2000);
		    } catch (Exception e){
		        e.printStackTrace();
		    }
		 
		    return 1000.0;
		};
		 
		
		List<Supplier<Double>> supplierList = IntStream.range(0, 5).mapToObj(n -> supplier).collect(Collectors.toList());
		 
//		supplierList.parallelStream().
//		        map(Supplier::get).
//		        reduce(Double::sum).
//		        ifPresent(System.out::println);
		 
		// CompletableFuture 를 이용한 비동기적으로 처리
		{
		    final Executor executor = Executors.newFixedThreadPool(Math.min(supplierList.size(), 5), r -> {
		                Thread t = new Thread(r);
		                // 데몬 스레드 정의
		                // 일반 스레드가 실행 중일 때 자바 프로그램은 종료되지 않음 -> 어떤 이벤트를 한없이 기다리면서 종료되지 않은 일반 자바 스레드가 있으면 문제
		                // 데몬 스레드는 자바 프로그램이 종료될 때 종료
		                t.setDaemon(true);
		                return t;
		            });
		 
		    List<CompletableFuture<Double>> completableFutures = supplierList.stream().
		            map(CompletableFuture::supplyAsync).
		            collect(Collectors.toList());
		 
		    // join 메소드는 모든 비동기 동작이 끝나길 기다립니다.
		    completableFutures.stream().
		            map(CompletableFuture::join).
		            reduce(Double::sum).
		            ifPresent(System.out::println);
		}
		 
		// 병렬스트림 걸린 시간 : 26066초
		// CompletableFuture 사용 걸린 시간 : 2015초
	}
	
	/**
	 * @throws Exception
	 */
	public void cc() throws Exception 
	{
		Supplier<Double> supplier = () -> {
		    try {
		        Thread.sleep(2000);
		    } catch (Exception e){
		        e.printStackTrace();
		    }
		 
		    return 1000.0;
		};
		 
		List<Supplier<Double>> supplierList = Arrays.asList(supplier, supplier, supplier, supplier);
		 
//		 병렬 스트림을 이용. 각 태스크를 병렬로 하여 성능을 높이자.
//		supplierList.parallelStream()
//		    .map(Supplier::get)
//		    .reduce(Double::sum)
//		    .ifPresent(System.out::println);
		 
		// CompletableFuture 를 이용한 비동기적으로 처리
		{
		    List<CompletableFuture<Double>> completableFutures = supplierList.stream().
		            map(CompletableFuture::supplyAsync).
		            collect(Collectors.toList());
		 
		    
		    completableFutures.stream().
		            map(CompletableFuture::join).// join 메소드는 모든 비동기 동작이 끝나길 기다립니다.
		            reduce(Double::sum).
		            ifPresent(System.out::println);
		}
	}

	/**
	 * @throws Exception
	 */
	public void bb() throws Exception 
	{
		Future<Double> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e){
                e.printStackTrace();
            }
 
            return 1000.0;
        });
 
		System.out.println("비동기 처리를 하는 동안 다른 일처리.");
		 
		try {
		    // 타임아웃 3초로 지정.
		    System.out.println("결과 : " + future.get(3000, TimeUnit.MILLISECONDS));
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	public void aa() throws Exception
	{
		ExecutorService executorService = Executors.newCachedThreadPool();

		Future<Double> future = executorService.submit(() -> {
		    Thread.sleep(2000);
		    return 1000.0;
		});
		
		System.out.println("비동기 처리를 하는 동안 다른 일처리.");
		 
		try {
		    // 타임아웃 3초로 지정.
		    System.out.println("결과 : " + future.get(3000, TimeUnit.MILLISECONDS));
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
