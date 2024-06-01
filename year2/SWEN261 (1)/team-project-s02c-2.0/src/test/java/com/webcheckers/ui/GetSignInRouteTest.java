package com.webcheckers.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;


import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-Tier")
public class GetSignInRouteTest {

    private GetSignInRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        CuT = new GetSignInRoute(engine);
    }

    /**
     * Test that the constructor doesn't allow null template engine
     */
    @Test
    public void engine_null(){
        assertThrows(NullPointerException.class, () -> {
            new GetSignInRoute(null);}, "GetSignInRoute allowed null template engine");
    }

    @Test
    public void valid_handle(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        // Analyze the content in the view model
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(GetSignInRoute.TITLE_ATTR, GetSignInRoute.TITLE);

        testHelper.assertViewName(GetSignInRoute.VIEW_NAME);
    }

}
