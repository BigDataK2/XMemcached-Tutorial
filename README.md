# XMemcachedTutorial

It is a Demo of using Memcached.

## Memcached
Memcached is a free & open source, high-performance, distributed memory object caching system. It is an in-memory key-value store for arbitrary data (strings, objects) from results of database calls, API calls, or page rendering. It focuses on solving the problem of keep the cache consistent across all servers, make maximum usage of the caching space across all servers. The total usable cache size will increase when the server increases.

Memcached support major operations as below:

  
Operation | Description
--- | ---
get | Reads a value.
set | Set a key unconditionally.
add | Add a new key.
replace   | Overwrite existing key.
append    | Append data to existing key.
prepend | Prepend data to the existing key.
delete | Delete existing key.
flush all | Invalidte spcific items immediately.
version | Print server version.
quit | Quit the session.

## Overview of Memcached clients for Java  

There are three main implementation of Memcached clients for Java    
1. java-memcached   
2. spymemcached  
3. xmemcached  

1) MemcachedClient is the earliest Memcached client using Java IO. The drawback of this client is that it does not perform well for intensive IO operations since it is NOT using Java.NIO. Detail of this client could be found in https://github.com/gwhalin/Memcached-Java-Client/wiki

2) Spymemcached is a simple, asynchronous, single-threaded memcached client written in java and it is nio based. Only single thread will  be allocated to a given MemcachedClient regardless of threads using the client, or servers to which the client is connected. Detail could be found in https://code.google.com/p/spymemcached/

3) XMemcached is a high performance, easy to use multithreaded memcached client in java. It's nio based and was carefully turned to get top performance.  https://code.google.com/p/xmemcached/

Based on the benchmark testing sepcified in this link, http://xmemcached.googlecode.com/svn/trunk/benchmark/benchmark.html, I decided to adopt XMemcached.

## XMemcached Application

Setup of Setup Memcached and work with Memcached Client via XMemcached. 

1. Download and install the memcached server and start the server. Detail could be found in http://memcached.org/downloads  

2. Download the Maven project and run the application.

3. The application will show the result of get, set, delete, append, prepend, replace and flushall.
