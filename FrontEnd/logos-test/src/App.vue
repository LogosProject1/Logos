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
        <div
          id="chat-container"
          class="panel-container"
          style="
            flex-direction: column;
            box-sizing: border-box;
            display: flex;
            place-content: stretch space-evenly;
            align-items: stretch;
          "
        >
          <div
            class="panel-header-container"
            style="
              place-content: center flex-start;
              align-items: center;
              flex-direction: row;
              box-sizing: border-box;
              display: flex;
              flex: 1 1 55px;
              max-height: 55px;
              min-height: 55px;
            "
          >
            <h3 class="panel-title">Chat</h3>
            <button
              mattooltip="Close"
              aria-describedby="cdk-describedby-message-13"
              cdk-describedby-host="0"
            >
              <span
                matripple=""
                class="mat-ripple mat-button-ripple mat-button-ripple-round"
              ></span>
              <span class="mat-button-focus-overlay"></span></button
            ><!---->
          </div>
          <div
            class="text-container"
            style="
              flex: 1 1 20px;
              box-sizing: border-box;
              max-height: 20px;
              min-height: 20px;
            "
          >
            <p class="text-info">
              Messages will be removed at the end of the session
            </p>
          </div>
          <div class="messages-container">
            <chat-message
              v-for="(chatData, index) in chatMessageList"
              v-bind:chatData="chatData"
              :key="index"
            />
          </div>
          <div
            class="input-container"
            style="
              place-content: stretch space-evenly;
              align-items: stretch;
              flex-direction: row;
              box-sizing: border-box;
              display: flex;
              flex: 1 1 55px;
              max-height: 55px;
              min-height: 55px;
              order: 3;
            "
          >
            <textarea
              maxlength="500"
              rows="4"
              placeholder="Send a message"
              autocomplete="off"
              id="chat-input"
              v-model="chatInput"
            ></textarea
            ><button
              mat-icon-button=""
              id="send-btn"
              class="mat-focus-indicator mat-icon-button mat-button-base"
              @click="sendMessage"
            >
              <span class="mat-button-wrapper"
                ><mat-icon
                  role="img"
                  mattooltip="Send"
                  class="mat-icon notranslate mat-tooltip-trigger material-icons mat-icon-no-color"
                  aria-hidden="true"
                  data-mat-icon-type="font"
                  aria-describedby="cdk-describedby-message-14"
                  cdk-describedby-host="0"
                  >send</mat-icon
                ><!----></span
              ><span
                matripple=""
                class="mat-ripple mat-button-ripple mat-button-ripple-round"
              ></span
              ><span class="mat-button-focus-overlay"></span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "./components/UserVideo";
import ChatMessage from "./components/ChatMessage";

axios.defaults.headers.post["Content-Type"] = "application/json";
axios.defaults.headers.post["Authorization"] =
  "Bearer eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjU4MzgxOTkxNzM2LCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTgzODU1OTEsInN1YiI6ImFjY2Vzcy10b2tlbiIsImVtYWlsIjoic3NkQGZzLmNvbSIsIm5hbWUiOiJ0ZXN0IiwidHlwZSI6IlVTRVIifQ.VYkGE6SeZOXJWOErbFodwZhYZBnWd8IpfjScxjPj6OkNeZywvfeGDaizlsKRQm6Dtu2IRfkmXYHGqpetjVE93w";

const OPENVIDU_API_SERVER_URL = "https://localhost:8082";

export default {
  name: "App",
  components: {
    UserVideo,
    ChatMessage,
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
      chatInput: undefined,
      chatMessageList: [],
    };
  },
  methods: {
    sendMessage() {
      if (this.chatInput === "" || this.chatInput === undefined) return;
      this.session
        .signal({
          data: this.chatInput, // Any string (optional)
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
      this.chatInput = "";
    },
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();
      // --- Init a session ---
      this.session = this.OV.initSession();

      this.session.on("signal:my-chat", (event) => {
        this.chatMessageList.push(event);
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
              resolution: "320x240", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
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
      this.chatMessageList = [];
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
<style>
.messages-container {
  height: 75%;
  place-content: stretch space-evenly;
  align-items: stretch;
  flex-direction: row;
  box-sizing: border-box;
  display: flex;
  flex: 1 1 100%;
  max-height: 75%;
}
.text-container {
  background-color: var(--ov-light-color);
  color: var(--ov-panel-text-color);
  text-align: center;
  font-size: 12px;
}
.text-info {
  margin: 3px;
}
.messages-container {
  display: block !important;
  overflow-y: auto;
  overflow-x: hidden;
  padding: 10px;
}
.input-container {
  height: 25px;
  display: flex;
  background-color: var(--ov-light-color);
  padding: 10px;
  margin: 10px;
  border-radius: var(--ov-panel-radius);
}
.input-container textarea {
  width: 100%;
  height: 16px;
  margin: auto;
  background-color: transparent;
  display: block;
  border: none;
  padding: 0;
  word-wrap: break-word;
  white-space: pre-wrap;
  resize: none;
  outline: none;
  box-shadow: none;
  font-family: Roboto, RobotoDraft, Helvetica, Arial, sans-serif;
}
.message {
  position: relative;
  padding: 3px 0;
}
.msg-detail {
  width: 95%;
  display: inline-block;
}
.msg-detail p {
  margin: 0;
  font-size: 14px;
}
.nickname-container p {
  font-size: 13px;
  font-weight: 700;
  color: var(--ov-panel-text-color);
}
.msg-content {
  position: relative;
  border-radius: var(--ov-panel-radius);
  padding: 8px;
  color: #000;
  width: auto;
  max-width: 95%;
  font-size: 13px;
  word-break: break-all;
}
#send-btn {
  border-radius: var(--ov-buttons-radius);
}
.message.left .msg-detail .nickname-container {
  text-align: left;
}
.message.left .msg-detail .msg-content {
  float: left;
}
.message.right .msg-detail .nickname-container {
  text-align: right;
}
.message.right .msg-detail .msg-content {
  float: right;
}
a:-webkit-any-link {
  color: #1a73e8;
}
</style>
