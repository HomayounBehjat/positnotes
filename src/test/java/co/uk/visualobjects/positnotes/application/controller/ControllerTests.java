package co.uk.visualobjects.positnotes.application.controller;

import co.uk.visualobjects.positnotes.application.configuration.ApplicationProperties;
import co.uk.visualobjects.positnotes.application.model.User;
import org.junit.Before;
import org.mockito.Mock;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import static org.mockito.Mockito.when;

@EnableConfigurationProperties(ApplicationProperties.class)
public abstract class ControllerTests {
    protected interface TestData {
        long USER_ID = 10L;
    }

    @Mock protected Authentication authentication;

    protected User user;

    @Before
    public void setup() {
        user = User.Builder.aUser()
                .withEmail("UnitTestUser")
                .withUserId(TestData.USER_ID)
                .build();
        when(authentication.getPrincipal()).thenReturn(user);

        when(authentication.getAuthorities()).thenAnswer(ioc -> AuthorityUtils.createAuthorityList("ROLE_USER"));
        when(authentication.isAuthenticated()).thenReturn(true);
    }
}
