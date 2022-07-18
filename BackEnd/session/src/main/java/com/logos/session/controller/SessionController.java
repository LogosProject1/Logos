package com.logos.session.controller;


import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
public class SessionController {
    private final OpenVidu openVidu;

    private Map<String, Session> mapSessions = new ConcurrentHashMap<>();

    private Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new ConcurrentHashMap<>();

    @PostMapping("/session")
    public ResponseEntity<Map<String,Object>> joinSession(@RequestBody String knowledgeId, HttpServletRequest req){
        Map<String, Object> resultMap = new HashMap<>();

        try {
            checkUserLogged(req);
        } catch (Exception e) {
            return getErrorResponse(e, resultMap);
        }

        OpenViduRole role = OpenViduRole.PUBLISHER;

        String serverData = "{\"serverData\": \"" + req.getAttribute("Email") + "\"}";

        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();

        if (this.mapSessions.get(knowledgeId) != null) {
            System.out.println("Existing session " + knowledgeId);
            try {
                // Generate a new Connection with the recently created connectionProperties
                String token = this.mapSessions.get(knowledgeId).createConnection(connectionProperties).getToken();

                // Update our collection storing the new token
                this.mapSessionNamesTokens.get(knowledgeId).put(token, role);

                // Prepare the response with the token
                resultMap.put("token", token);

                // Return the response to the client
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            } catch (OpenViduJavaClientException e1) {
                return getErrorResponse(e1, resultMap);
            } catch (OpenViduHttpException e2) {
                if (404 == e2.getStatus()) {
                    // Invalid sessionId (user left unexpectedly). Session object is not valid
                    // anymore. Clean collections and continue as new session
                    this.mapSessions.remove(knowledgeId);
                    this.mapSessionNamesTokens.remove(knowledgeId);
                }
            }

        }

        // New session
        System.out.println("New session " + knowledgeId);
        try {

            // Create a new OpenVidu Session
            Session session = this.openVidu.createSession();
            // Generate a new Connection with the recently created connectionProperties
            String token = session.createConnection(connectionProperties).getToken();

            // Store the session and the token in our collections
            this.mapSessions.put(knowledgeId, session);
            this.mapSessionNamesTokens.put(knowledgeId, new ConcurrentHashMap<>());
            this.mapSessionNamesTokens.get(knowledgeId).put(token, role);

            // Prepare the response with the token
            resultMap.put("token", token);

            // Return the response to the client
            return new ResponseEntity<>(resultMap, HttpStatus.OK);

        } catch (Exception e) {
            // If error generate an error message and return it to client
            return getErrorResponse(e, resultMap);
        }
    }

    @PostMapping("/remove-user")
    public ResponseEntity<Map<String, Object>> removeUser(@RequestBody String knowledgeId, @RequestBody String token, HttpServletRequest req)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            checkUserLogged(req);
        } catch (Exception e) {
            return getErrorResponse(e, resultMap);
        }
        System.out.println("Removing user | {sessionName, token}=" + knowledgeId + ", " + token);

        // If the session exists
        if (this.mapSessions.get(knowledgeId) != null && this.mapSessionNamesTokens.get(knowledgeId) != null) {

            // If the token exists
            if (this.mapSessionNamesTokens.get(knowledgeId).remove(token) != null) {
                // User left the session
                if (this.mapSessionNamesTokens.get(knowledgeId).isEmpty()) {
                    // Last user left: session must be removed
                    this.mapSessions.remove(knowledgeId);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                // The TOKEN wasn't valid
                System.out.println("Problems in the app server: the TOKEN wasn't valid");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            // The SESSION does not exist
            System.out.println("Problems in the app server: the SESSION does not exist");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<Map<String, Object>> getErrorResponse(Exception e, Map<String, Object> resultMap) {
        resultMap.put("cause", e.getCause());
        resultMap.put("error", e.getMessage());
        resultMap.put("exception", e.getClass());
        return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void checkUserLogged(HttpServletRequest req) throws Exception {
        if (req.getAttribute("Email") == null) {
            throw new Exception("User not logged");
        }
    }
}
