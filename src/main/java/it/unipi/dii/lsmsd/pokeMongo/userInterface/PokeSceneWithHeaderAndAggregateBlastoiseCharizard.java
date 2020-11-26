package it.unipi.dii.lsmsd.pokeMongo.userInterface;

/**
 * This class create a sort of double inheritance because it instantiates a
 * <code>PokeSceneWithBlastoiseCharizard</code>. This class should be used to be extended by scenes that want
 * the header containing the username and the number of pokeball hold (this one only for normal user), and the
 * background with the two pokemon displayed.
 */
public class PokeSceneWithHeaderAndAggregateBlastoiseCharizard extends PokeSceneWithHeader {
    /**
     * Simply instantiate a <code>PokeSceneWithBlastoiseCharizard</code> class.
     */
    public PokeSceneWithHeaderAndAggregateBlastoiseCharizard() {
        PokeScene pk = new PokeSceneWithBlastoiseCharizard();
    }
}
