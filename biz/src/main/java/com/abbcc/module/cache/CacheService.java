package com.abbcc.module.cache;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.util.Assert;

//import com.danga.MemCached.MemCachedClient;
//import com.danga.MemCached.SockIOPool;


public class CacheService {   
    private Log logger = LogFactory.getLog(getClass());   
  
    private Cache localCache;   
  
    String cacheServerList;   
  
    String cacheServerWeights;   
  
    public Cache getLocalCache() {
		return localCache;
	}
	public void setLocalCache(Cache localCache) {
		this.localCache = localCache;
	}
	public String getCacheServerList() {
		return cacheServerList;
	}
	public void setCacheServerList(String cacheServerList) {
		this.cacheServerList = cacheServerList;
	}
	public String getCacheServerWeights() {
		return cacheServerWeights;
	}
	public void setCacheServerWeights(String cacheServerWeights) {
		this.cacheServerWeights = cacheServerWeights;
	}
	public boolean isCacheCluster() {
		return cacheCluster;
	}
	public void setCacheCluster(boolean cacheCluster) {
		this.cacheCluster = cacheCluster;
	}

	boolean cacheCluster = true;   
  
    int initialConnections = 10;   
  
    int minSpareConnections = 5;   
  
    int maxSpareConnections = 50;   
  
    long maxIdleTime = 1000 * 60 * 30; // 30 minutes   
  
    long maxBusyTime = 1000 * 60 * 5; // 5 minutes   
  
    long maintThreadSleep = 1000 * 5; // 5 seconds   
  
    int socketTimeOut = 1000 * 3; // 3 seconds to block on reads   
  
    int socketConnectTO = 1000 * 3; // 3 seconds to block on initial   
                                    // connections. If 0, then will use blocking   
                                    // connect (default)   
  
    boolean failover = false; // turn off auto-failover in event of server   
                                // down   
  
    boolean nagleAlg = false; // turn off Nagle's algorithm on all sockets in   
                                // pool   
  
//    MemCachedClient mc;
  
    public CacheService(){   
//        mc = new MemCachedClient();
//        mc.setCompressEnable(false);
    }   
    /**  
     * 放入  
     *   
     */  
    public void put(String key, Object obj) {   
        Assert.hasText(key);   
        Assert.notNull(obj);   
        logger.debug("put cache-->!"+key);
        Assert.notNull(localCache);   
        if (this.cacheCluster) {   
//            mc.set(key, obj);
        } else {   
            Element element = new Element(key, (Serializable) obj);   
            localCache.put(element);   
        }   
    }   
    /**  
     * 删除   
     */  
    public void remove(String key){   
        Assert.hasText(key);   
        Assert.notNull(localCache);   
        logger.debug("remove cache-->!"+key);
        if (this.cacheCluster) {   
//            mc.delete(key);
        }else{   
            localCache.remove(key);   
        }   
    }   
    /**  
     * 得到  
     */  
    public Object get(String key) {   
        Assert.hasText(key);   
        Assert.notNull(localCache);   
        logger.debug("get from cache-->!"+key);
        Object rt = null;   
        if (this.cacheCluster) {   
//            rt = mc.get(key);
        } else {   
            Element element = null;   
            try {   
                element = localCache.get(key);   
            } catch (CacheException cacheException) {   
                throw new DataRetrievalFailureException("Cache failure: "  
                        + cacheException.getMessage());   
            }   
            if(element != null)   
                rt = element.getValue();   
        }   
        return rt;   
    }   
    /**  
     * 判断是否存在  
     *   
     */  
    public boolean exist(String key){   
        Assert.hasText(key);   
        Assert.notNull(localCache);   
        if (this.cacheCluster) {   
            return false;//return mc.keyExists(key);
        }else{   
            return this.localCache.isKeyInCache(key);   
        }   
    }   
    private Integer[]  split(String cacheServerWeights){
    	 String[] cacheServerWeightsList = cacheServerWeights.split(",");
    	    Integer[] weights = new  Integer[cacheServerWeightsList.length] ;
    	    for(int i=0;i<weights.length;i++){
    	    	weights[i]=new Integer(cacheServerWeightsList[i]);
    	    	
    	    }
    	return weights;
    }
    
    /**
     * 初始化
     */
    private void init() {   
        if (this.cacheCluster) {   
            String[] serverlist = cacheServerList.split(",");   
            Integer[] weights = this.split(cacheServerWeights);   
            // initialize the pool for memcache servers   
//            SockIOPool pool = SockIOPool.getInstance();
//            pool.setServers(serverlist);
//            pool.setWeights(weights);
//            pool.setInitConn(initialConnections);
//            pool.setMinConn(minSpareConnections);
//            pool.setMaxConn(maxSpareConnections);
//            pool.setMaxIdle(maxIdleTime);
//            pool.setMaxBusyTime(maxBusyTime);
//            pool.setMaintSleep(maintThreadSleep);
//            pool.setSocketTO(socketTimeOut);
//            pool.setSocketConnectTO(socketConnectTO);
//            pool.setNagle(nagleAlg);
//            pool.setHashingAlg(SockIOPool.NEW_COMPAT_HASH);
//            pool.initialize();
//            logger.info("初始化memcached pool!");
        }   
    }   
  
    private void destory() {   
        if (this.cacheCluster) {   
//            SockIOPool.getInstance().shutDown();
        }   
    } 
    public String getKey() {   
  
        String key = "";   
            try {   
            	for(int i=0;i<localCache.getKeys().size();i++){
            		key = key+localCache.getKeys().get(i).toString()+","; 
            	}
                  
            } catch (CacheException cacheException) {   
                throw new DataRetrievalFailureException("Cache failure: "  
                        + cacheException.getMessage());   
            }   
        return key;   
    }   
    
   
}  
