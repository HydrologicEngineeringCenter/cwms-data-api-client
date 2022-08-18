package mil.army.usace.hec.cwms.http.client;

import java.io.IOException;
import mil.army.usace.hec.cwms.http.client.request.HttpRequestMethod;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

final class CwmsHttpLoggingInterceptor implements Interceptor {

    private static final HttpLoggingInterceptor.Logger LOGGER = HttpLoggingInterceptor.Logger.DEFAULT;
    private final HttpLoggingInterceptor delegate;

    CwmsHttpLoggingInterceptor() {
        this.delegate = new HttpLoggingInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        final Request copy = request.newBuilder().build();
        if (HttpRequestMethod.POST.getName().equalsIgnoreCase(request.method())) {
            try (final Buffer buffer = new Buffer()) {
                RequestBody body = copy.body();
                if (body != null) {
                    body.writeTo(buffer);
                    String bodyString = buffer.readUtf8();
                    LOGGER.log("--> " + HttpRequestMethod.POST.getName() + " Body: \n"
                        + bodyString);
                }
            }
        }
        return delegate.intercept(chain);
    }

    public void level(HttpLoggingInterceptor.Level level) {
        delegate.level(level);
    }


}
