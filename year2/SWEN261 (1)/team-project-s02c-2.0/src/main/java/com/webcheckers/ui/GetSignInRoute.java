package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * the {@code get /signin} route handler
 * @author <a href= 'mailto:jec5704@rit.edu'>Julio Cuello</a>
 */
public class GetSignInRoute implements Route {
    public static final String TITLE_ATTR = "title";
    public static final String TITLE = "Sign-in";
    public static final String VIEW_NAME = "signin.ftl";

    private final TemplateEngine templateEngine;

    /**
     * The constructor for the {@code GET /signin} route handler.
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    GetSignInRoute(final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
    }

    @Override
    public String handle(Request request, Response response) {
        final Session httpSession = request.session();
        //build the View-model
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, TITLE);

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }

}
