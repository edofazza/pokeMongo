package it.unipi.dii.lsmsd.pokeMongo.javaFXextensions.cache;

/**
 * EXAMPLE OF USAGE OF ImageCache
 *
 * There's associated a Singleton Pattern, will be returned the same instance.
 * When you need an Image from the web:
 *
 * ImageCache imageCache = new ImageCache(); //Should be already initialized
 * Image image = (Image)imageCache.getDataIfPresent(urlOfTheImage);
 * ImageView imageView = new ImageView(image); //or just imageView.setImage(image) if already initialized
 */
public interface PokeMongoCache {
    /**
     * Initialize a Cache system, should be used at the beginning of the running program
     */
    public void initializeCache();

    /**
     * Perform a fetch into the Cache, if there is present the object associated to the url,
     * will be returned the cached version.
     * If not, will be made a request into the web,then the Object will be cached and returned
     * @param url  Url of the resource to be obtained
     * @return Object associated to the url
     */
    public Object getDataIfPresent(String url);
}
