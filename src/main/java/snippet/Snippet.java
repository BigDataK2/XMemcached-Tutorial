package snippet;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil; 
public class Snippet {
	 public static void main(String[] args) throws IOException {
	        MemcachedClientBuilder builder = 
	                         new XMemcachedClientBuilder(AddrUtil.
	                        		 getAddresses("127.0.0.1:11211"), new int[]{1});
	        MemcachedClient memcachedClient = builder.build();
	        
	        try {
	            //it means the Key's value is key1, 0 stands for it never expire, "Hello World" is the cached value
	        	memcachedClient.set("key1", 0, "Hello World!");
	            
	            String value = memcachedClient.get("key1");
	            System.out.println("Cache value of key1 is£º" + value);
	            memcachedClient.delete("key1");
	            System.out.println("cache for key1 is deleted.");
	            value = memcachedClient.get("key1");
	            if (value != null) {
					System.out.println("Cache value of key1 is: " + value);
				}
	            else{
	            	System.out.println("key1's cache does not exist.");
	            }
				memcachedClient.shutdown();
	        }
	        catch (MemcachedException e) {

	            System.err.println("MemcachedClient operation fail");

	            e.printStackTrace();

	        } catch (TimeoutException e) {

	            System.err.println("MemcachedClient operation timeout");

	            e.printStackTrace();

	        } catch (InterruptedException e) {

	            // ignore

	        }catch (IOException e) {

	            System.err.println("Shutdown MemcachedClient fail");

	            e.printStackTrace();

	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}

