package mil.army.usace.hec.cwms.http.client;

/**
 * Base for PATCH endpoint inputs that support the {@code collection-patch-strategy} header.
 * Subclasses add their own parameters in {@link #buildInputParameters(HttpRequestBuilder)}.
 * If no strategy is set, the header is omitted and the server default applies.
 */
public abstract class CollectionPatchEndpointInput<T extends CollectionPatchEndpointInput<T>> extends EndpointInput {
    public static final String COLLECTION_PATCH_STRATEGY_HEADER = "collection-patch-strategy";

    private CollectionPatchStrategy collectionPatchStrategy;

    public T collectionPatch(CollectionPatchStrategy collectionPatchStrategy) {
        this.collectionPatchStrategy = collectionPatchStrategy;
        return self();
    }

    protected abstract T self();

    protected abstract HttpRequestBuilder buildInputParameters(HttpRequestBuilder httpRequestBuilder);

    @Override
    public final HttpRequestBuilder addInputParameters(HttpRequestBuilder httpRequestBuilder) {
        String strategy = collectionPatchStrategy == null ? null : collectionPatchStrategy.name();
        return buildInputParameters(httpRequestBuilder)
            .addQueryParameter(COLLECTION_PATCH_STRATEGY_HEADER, strategy);
    }
}
