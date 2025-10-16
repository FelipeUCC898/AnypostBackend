package com.anypost.integration.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class FirebaseAdminProvider {

    private FirebaseApp firebaseApp;

    @PostConstruct
    public void init() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            try (InputStream serviceAccount =
                         new ClassPathResource("config/firebase-admin.json").getInputStream()) {

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                firebaseApp = FirebaseApp.initializeApp(options);
                log.info("✅ Firebase Admin initialized successfully (classpath).");
            } catch (IOException e) {
                log.error("❌ Could not initialize Firebase Admin - config/firebase-admin.json not found in classpath", e);
                throw e;
            }
        }
    }

    public FirebaseApp getFirebaseApp() {
        return firebaseApp;
    }
}