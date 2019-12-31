package info.beastsoftware.hookcore.cache;

import com.google.common.cache.LoadingCache;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CachedManagerImpl<K, T> implements CachedManager<K, T> {
    private final LoadingCache<K, T> cache;
    private final Actioner<K, T> actioner;

    public CachedManagerImpl(Actioner<K, T> actioner) {
        this.actioner = actioner;
        this.cache = init(false, false, 0L, 0L);
    }

    public CachedManagerImpl(Actioner<K, T> actioner, long expireAfterAccess, long expireAfterWrite) {
        this.actioner = actioner;
        this.cache = this.init(true, true, expireAfterAccess, expireAfterWrite);
    }
}
