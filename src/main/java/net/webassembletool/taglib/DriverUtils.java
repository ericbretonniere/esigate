/**
 * 
 */
package net.webassembletool.taglib;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import net.webassembletool.Driver;
import net.webassembletool.DriverFactory;
import net.webassembletool.RenderingException;

/**
 * Utility class used by all tags to access to the Driver
 * 
 * @author Fran�ois-Xavier Bonnet
 */
public class DriverUtils {
    public final static String getBaseUrl(String provider) {
        return DriverFactory.getInstance(provider).getBaseURL();
    }

    public final static void renderBlock(String provider, String page,
            String name, PageContext pageContext,
            Map<String, String> replaceRules, Map<String, String> parameters)
            throws JspException, RenderingException {

        try {
            Driver driver = DriverFactory.getInstance(provider);
            boolean propagateJsessionId = ((HttpServletResponse) pageContext
                    .getResponse()).encodeURL("/").contains("jsessionid");
            driver.renderBlock(page, name, pageContext.getOut(),
                    (HttpServletRequest) pageContext.getRequest(),
                    replaceRules, parameters, propagateJsessionId);
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    public final static void renderTemplate(String provider, String page,
            String name, PageContext pageContext, Map<String, String> params,
            Map<String, String> replaceRules, Map<String, String> parameters)
            throws JspException, RenderingException {

        try {
            Driver driver = DriverFactory.getInstance(provider);
            boolean propagateJsessionId = ((HttpServletResponse) pageContext
                    .getResponse()).encodeURL("/").contains("jsessionid");
            driver.renderTemplate(page, name, pageContext.getOut(),
                    (HttpServletRequest) pageContext.getRequest(), params,
                    replaceRules, parameters, propagateJsessionId);
        } catch (IOException e) {
            throw new JspException(e);
        }
    }

    public final static void renderXml(String provider, String source,
            String xpath, String template, PageContext pageContext)
            throws JspException, RenderingException {

        try {
            Driver driver = DriverFactory.getInstance(provider);
            driver.renderXml(source, xpath, template, pageContext.getOut(),
                    (HttpServletRequest) pageContext.getRequest(), pageContext
                            .getServletContext());
        } catch (IOException e) {
            throw new JspException(e);
        }
    }
}