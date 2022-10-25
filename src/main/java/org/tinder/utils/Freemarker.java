package org.tinder.utils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.tinder.ServerApp;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Freemarker {
    private final Configuration config;

    public Freemarker() {
        this.config = new Configuration(Configuration.VERSION_2_3_28) {{
            setClassForTemplateLoading(ServerApp.class, "/templates");
            setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
            setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            setLogTemplateExceptions(false);
            setWrapUncheckedExceptions(true);
        }};
    }

    public void render(final String templateFile, final Map<String, Object> data, final HttpServletResponse resp) {
        try {
            resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            config.getTemplate(templateFile).process(data, resp.getWriter());
        } catch (TemplateException | IOException e) {
            throw new IllegalArgumentException("Unexpected value", e);
        }
    }
}
