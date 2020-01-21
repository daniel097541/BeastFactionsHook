package info.beastsoftware.hookcore.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public interface CachedManager<K, T> {

    LoadingCache<K, T> getCache();

    Actioner<K, T> getActioner();


    default LoadingCache<K, T> init(boolean weakKeys, boolean weakValues, long expireAfterAccess, long expireAfterWrite) {

        CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder();

        if (weakKeys) {
            builder.weakKeys();
        }

        if (weakValues) {
            builder.weakValues();
        }

        if (expireAfterAccess > 0) {
            builder.expireAfterAccess(expireAfterAccess, TimeUnit.SECONDS);
        }

        if (expireAfterWrite > 0) {
            builder.expireAfterWrite(expireAfterWrite, TimeUnit.SECONDS);
        }

        LoadingCache<K, T> cache = builder
                .removalListener((RemovalListener<K, T>) notification -> {
                    K key = notification.getKey();
                    this.getActioner().destroyByIdentifier(key);
                })
                .build(new CacheLoader<K, T>() {
                    @Override
                    public T load(K key) {
                        return getActioner().spawn(key);
                    }
                });

        return cache;
    }

    default T get(K identifier) {
        try {
            return getCache().get(identifier);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    default void remove(K identifier) {
        this.getCache().invalidate(identifier);
    }


    default Set<T> getAll() {
        return new HashSet<>(this.getCache()
                .asMap()
                .values());
    }

    default void bulkRemove(Collection<K> keys) {
        this.getCache().invalidateAll(keys);
    }

    default Set<T> getBulk(Iterable<K> keys){
        try {
            return new HashSet<>(this.getCache().getAll(keys).values());
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
