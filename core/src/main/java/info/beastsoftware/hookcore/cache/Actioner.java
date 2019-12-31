package info.beastsoftware.hookcore.cache;

public interface Actioner<K,T> {
    T spawn(K identifier);
    void destroyByIdentifier(K identifier);
}
