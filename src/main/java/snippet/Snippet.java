package snippet;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil; 
public class Snippet {
	 public static void main(String[] args) throws Exception {
	        MemcachedClientBuilder builder = 
	                         new XMemcachedClientBuilder(AddrUtil.
	                        		 getAddresses("127.0.0.1:11211"), new int[]{1});
	        MemcachedClient memcachedClient = builder.build();
	        
	        testXMemcachedClient(memcachedClient);
	    }

	private static void testXMemcachedClient(MemcachedClient memcachedClient) throws Exception {
		try {
			//it means remove all the cache
			StringBuilder sb = new StringBuilder();
			memcachedClient.flushAll();
		    //it means the Key's value is key1, 0 stands for it never expire, "Hello World" is the cached value
			memcachedClient.set("key1", 0, "Hello World!");
		    
		    String value = memcachedClient.get("key1");
		    System.out.println(sb.append("Cache value of key1 is£º").append(value).toString());
		    sb.setLength(0);
		    if (!memcachedClient.add("hello", 0, "Chris")) {
		        System.err.println("replace error");
		    }
		    
		    else
		    {
		    	System.out.println(sb.append("Cache value of hello is£º").append(value));
		    	sb.setLength(0);
		    }
		    if (!memcachedClient.replace("hello", 0, "James")) {
		        System.err.println("replace error");
		    }
		    System.out.println(sb.append("Cache value of hello is£º").append(memcachedClient.get("hello")).toString() );
		    sb.setLength(0);
		    memcachedClient.append("hello", " is good!");
		    System.out.println(sb.append("Cache value of hello is£º").append(memcachedClient.get("hello")).toString() );
		    sb.setLength(0);
		    memcachedClient.prepend("hello", "Hello, ");
		    System.out.println(sb.append("Cache value of hello is£º").append(memcachedClient.get("hello")).toString() );
		    sb.setLength(0);
		    memcachedClient.delete("key1");
		    System.out.println("cache for key1 is deleted.");
		    if (memcachedClient.get("key1") != null) {
				System.out.println(sb.append("Cache value of key1 is£º").append(memcachedClient.get("key1")).toString() );
				sb.setLength(0);
			}
		    else{
		    	System.out.println("key1's cache does not exist.");
		    }
			memcachedClient.shutdown();
		}
		catch (MemcachedException e) {

		    System.err.println("MemcachedClient operation fail");

		    e.printStackTrace();
		    throw e;

		} catch (TimeoutException e) {

		    System.err.println("MemcachedClient operation timeout");

		    e.printStackTrace();
		    throw e;

		} catch (InterruptedException e) {

		    // ignore

		}catch (IOException e) {

		    System.err.println("Shutdown MemcachedClient fail");

		    e.printStackTrace();
		    throw e;
		}
		catch (Exception e) {
		    e.printStackTrace();
		    throw e;
		}
	}
}

