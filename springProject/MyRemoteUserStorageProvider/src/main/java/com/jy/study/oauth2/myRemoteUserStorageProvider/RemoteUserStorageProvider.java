package com.jy.study.oauth2.myRemoteUserStorageProvider;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

/**
 * SPI(Storage Provider Interface) 구현체
 */
public class RemoteUserStorageProvider implements
        //storage provider에 대한 기본적인 인터페이스
        UserStorageProvider,
        //사용자 검색에 대한 인터페이스
        UserLookupProvider,
        //사용자 자격 증명 인터페이스
        CredentialInputValidator {

    private KeycloakSession session;

    private ComponentModel model;

    private UsersApiService usersApiService;

    public RemoteUserStorageProvider(KeycloakSession session, ComponentModel model, UsersApiService usersApiService) {
        this.session = session;
        this.model = model;
        this.usersApiService = usersApiService;
    }

    @Override
    public void close() {

    }

    @Override
    public UserModel getUserById(String id, RealmModel realm) {
        return null;
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realm) {
        UserModel returnValue =null;

        User user = usersApiService.getUserDetails(username);

        if (user != null) {
            returnValue = createUserModel(username, realm);
        }

        return null;
    }

    private UserModel createUserModel(String username, RealmModel realm) {
        return new AbstractUserAdapter(session, realm, model) {
            @Override
            public String getUsername() {
                return username;
            }
        };
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realm) {
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        if (!supportsCredentialType(credentialType))
            return false;

        return getCredentialStore().getStoredCredentialsByTypeStream(realm, user, credentialType).count() != 0;
    }

    private UserCredentialStore getCredentialStore() {
        return session.userCredentialManager();
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        VerifyPasswordResponse verifyPasswordResponse = usersApiService.verifyUserPassword(user.getUsername(),
                credentialInput.getChallengeResponse());

        if (verifyPasswordResponse == null)
            return false;

        return verifyPasswordResponse.getResult();
    }
}

