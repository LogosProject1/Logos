package com.logos.session.controller;


import com.logos.session.domain.Knowledge;
import com.logos.session.dto.SessionJoinDto;
import com.logos.session.dto.SessionRemoveUserDto;
import com.logos.session.repository.KnowledgeRepository;
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
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
public class SessionController {
    private final OpenVidu openVidu;

    private final KnowledgeRepository knowledgeRepository;

    private Map<String, Session> mapSessions = new ConcurrentHashMap<>();

    private Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new ConcurrentHashMap<>();

    @PostMapping("/session")
    public ResponseEntity<Map<String,Object>> joinSession(@RequestBody SessionJoinDto sessionJoinDto, HttpServletRequest req) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        
        try {
            checkUserLogged(req);
        } catch (Exception e) {
            return getErrorResponse(e, resultMap);
        }


        OpenViduRole role = checkSessionOwner(sessionJoinDto.getKnowledgeId(), (String)req.getAttribute("Email"));

        String serverData = "{\"serverData\": \"" + req.getAttribute("Name") + "\"}";

        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();

        if (this.mapSessions.get(sessionJoinDto.getKnowledgeId()) != null) {
            System.out.println("Existing session " + sessionJoinDto.getKnowledgeId());
            try {
                // Generate a new Connection with the recently created connectionProperties
                String webCamToken = this.mapSessions.get(sessionJoinDto.getKnowledgeId()).createConnection(connectionProperties).getToken();
                String screenToken = this.mapSessions.get(sessionJoinDto.getKnowledgeId()).createConnection(connectionProperties).getToken();

                // Update our collection storing the new token
                this.mapSessionNamesTokens.get(sessionJoinDto.getKnowledgeId()).put(webCamToken, role);
                this.mapSessionNamesTokens.get(sessionJoinDto.getKnowledgeId()).put(screenToken, role);

                // Prepare the response with the token
                resultMap.put("webCamToken", webCamToken);
                resultMap.put("screenToken", screenToken);

                // Return the response to the client
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            } catch (OpenViduJavaClientException e1) {
                return getErrorResponse(e1, resultMap);
            } catch (OpenViduHttpException e2) {
                if (404 == e2.getStatus()) {
                    // Invalid sessionId (user left unexpectedly). Session object is not valid
                    // anymore. Clean collections and continue as new session
                    this.mapSessions.remove(sessionJoinDto.getKnowledgeId());
                    this.mapSessionNamesTokens.remove(sessionJoinDto.getKnowledgeId());
                }
            }

        }

        // New session
        System.out.println("New session " + sessionJoinDto.getKnowledgeId());
        try {

            // Create a new OpenVidu Session
            Session session = this.openVidu.createSession();
            // Generate a new Connection with the recently created connectionProperties
            String webCamToken = session.createConnection(connectionProperties).getToken();
            String screenToken = session.createConnection(connectionProperties).getToken();

            // Store the session and the token in our collections
            this.mapSessions.put(sessionJoinDto.getKnowledgeId(), session);
            this.mapSessionNamesTokens.put(sessionJoinDto.getKnowledgeId(), new ConcurrentHashMap<>());
            this.mapSessionNamesTokens.get(sessionJoinDto.getKnowledgeId()).put(webCamToken, role);
            this.mapSessionNamesTokens.get(sessionJoinDto.getKnowledgeId()).put(screenToken, role);

            // Prepare the response with the token
            resultMap.put("webCamToken", webCamToken);
            resultMap.put("screenToken", screenToken);

            // Return the response to the client
            return new ResponseEntity<>(resultMap, HttpStatus.OK);

        } catch (Exception e) {
            // If error generate an error message and return it to client
            return getErrorResponse(e, resultMap);
        }
    }

    private OpenViduRole checkSessionOwner(String knowledgeId, String email) throws Exception {
        Optional<Knowledge> byId = knowledgeRepository.findById(knowledgeId);
        if(byId.isEmpty()) throw new Exception("Not Exists Knowledge");

        return OpenViduRole.PUBLISHER;
    }

    @PostMapping("/remove-user")
    public ResponseEntity<Map<String, Object>> removeUser(@RequestBody SessionRemoveUserDto sessionRemoveUserDto, HttpServletRequest req)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            checkUserLogged(req);
        } catch (Exception e) {
            return getErrorResponse(e, resultMap);
        }
        System.out.println("Removing user | {sessionName, token}=" + sessionRemoveUserDto.getKnowledgeId() + ", " + sessionRemoveUserDto.getToken());

        // If the session exists
        if (this.mapSessions.get(sessionRemoveUserDto.getKnowledgeId()) != null
                && this.mapSessionNamesTokens.get(sessionRemoveUserDto.getKnowledgeId()) != null) {
            // If the token exists
            if (this.mapSessionNamesTokens.get(sessionRemoveUserDto.getKnowledgeId())
                    .remove(sessionRemoveUserDto.getToken()) != null) {
                // User left the session
                if (this.mapSessionNamesTokens.get(sessionRemoveUserDto.getKnowledgeId()).isEmpty()) {
                    // Last user left: session must be removed
                    this.mapSessions.remove(sessionRemoveUserDto.getKnowledgeId());
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
