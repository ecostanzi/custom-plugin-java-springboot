package eu.miaplatform.customplugin.springboot;

import eu.miaplatform.customplugin.CustomPluginHeadersPropagator;
import eu.miaplatform.customplugin.HeadersPropagatorFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class CPRequest implements RequestPlatformInfo {

    private HttpServletRequest request;
    private Options options;
    private Map<String, String> headers;
    private CustomPluginHeadersPropagator headersPropagator;

    public CPRequest(HttpServletRequest request, Options options) {
        this.request = request;
        this.options = options;
        this.headers = Utils.getHeadersInfo(request);
        this.headersPropagator = HeadersPropagatorFactory.getCustomPluginHeadersPropagator();
        headers.forEach(headersPropagator::add);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public CustomPluginHeadersPropagator getHeadersPropagator() {
        return headersPropagator;
    }

    public String getUserId() {
        return Utils.getUserId(options, headers);
    }

    @Override
    public List<String> getGroups() {
        return Utils.getGroups(this.options, this.headers);
    }

    @Override
    public String getClientType() {
        return Utils.getClientType(this.options, this.headers);
    }

    @Override
    public boolean isFromBackOffice() {
        return Utils.isFromBackOffice(this.options, this.headers);
    }
}
