<template>
  <div id="main-container" class="container">
    <div id="join" v-if="!session">
      <div id="img-div">
        <img src="resources/images/openvidu_grey_bg_transp_cropped.png" />
      </div>
      <div id="join-dialog" class="jumbotron vertical-center">
        <h1>Join a video session</h1>
        <div class="form-group">
          <p>
            <label>Participant</label>
            <input
              v-model="myUserName"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p>
            <label>Session</label>
            <input
              v-model="mySessionId"
              class="form-control"
              type="text"
              required
            />
          </p>
          <p class="text-center">
            <button class="btn btn-lg btn-success" @click="joinSession()">
              Join!
            </button>
          </p>
        </div>
      </div>
    </div>

    <div id="session" v-if="session">
      <div id="session-header">
        <h1 id="session-title">{{ mySessionId }}</h1>
        <input
          class="btn btn-large btn-danger"
          type="button"
          id="buttonLeaveSession"
          @click="leaveSession"
          value="Leave session"
        />
      </div>
      <div class="row">
        <div id="video-container" class="col-md-8">
          <user-video
            :stream-manager="publisher"
            @click.native="updateMainVideoStreamManager(publisher)"
          />
          <user-video
            v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
            :stream-manager="sub"
            @click.native="updateMainVideoStreamManager(sub)"
          />
        </div>
        <div id="chat-panel" class="col-md-4">
          <input type="text" />
          <input type="button" @click="sendMessage" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "./components/UserVideo";

axios.defaults.headers.post["Content-Type"] = "application/json";
axios.defaults.headers.post["Authorization"] =
  "Bearer eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjU4MzgxMzQxNzIzLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTgzODQ5NDEsInN1YiI6ImFjY2Vzcy10b2tlbiIsImVtYWlsIjoic3NkQGZzLmNvbSIsIm5hbWUiOiJhc2RmYXNkIiwidHlwZSI6IlVTRVIifQ.M7eVKMZOrNwoxiRk2ORs1LKnhae6qMibcCdwvYy_bVrKRWcoOj7EYsTI0SWiyWf-toZUYYPgMkimY0IhZYZu1w";

const OPENVIDU_API_SERVER_URL = "https://localhost:8082";

export default {
  name: "App",
  components: {
    UserVideo,
  },
  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      token: undefined,
      subscribers: [],
      mySessionId: "52552152843524282555",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
    };
  },
  methods: {
    sendMessage() {
      this.session
        .signal({
          data: "하위", // Any string (optional)
          to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
          type: "my-chat", // The type of message (optional)
        })
        .then(() => {
          console.log("Message successfully sent");
          //채팅서버에도 똑같은 시그널을 보내서 DB에 저장
          //채팅 내용,유저 이름,세션아이디 시간
        })
        .catch((error) => {
          console.error(error);
        });
    },
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();
      // --- Init a session ---
      this.session = this.OV.initSession();

      this.session.on("signal:my-chat", (event) => {
        console.log(event.data); // Message
        console.log(event.from); // Connection object of the sender
        console.log(event.type); // The type of message ("my-chat")
      });
      // --- Specify the actions when events take place in the session ---
      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });
      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });
      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });
      // --- Connect to the session with a valid user token ---
      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then(() => {
        this.session
          .connect(this.token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "600x320", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: true, // Whether to mirror your local video or not
            });
            this.mainStreamManager = publisher;
            this.publisher = publisher;
            // --- Publish your stream ---
            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });
      window.addEventListener("beforeunload", this.leaveSession);
    },
    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();
      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      axios.post(
        `${OPENVIDU_API_SERVER_URL}/remove-user`,
        JSON.stringify({
          knowledgeId: this.mySessionId,
          token: this.token,
        })
      );
      window.removeEventListener("beforeunload", this.leaveSession);
    },
    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },
    async getToken(mySessionId) {
      // Video-call chosen by the user
      await axios
        .post(`${OPENVIDU_API_SERVER_URL}/session`, {
          knowledgeId: mySessionId,
        })
        .then((response) => response.data)
        .then((data) => {
          this.token = data.token;
        });
    },
  },
};
</script>
