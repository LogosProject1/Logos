<template>
  <div id="main-container" class="container">
    <div id="join" v-if="!sessionCamera">
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

    <div id="session" v-if="sessionCamera">
      <div id="session-header">
        <h1 id="session-title">{{ mySessionId }}</h1>
        <input
          class="btn btn-large btn-danger"
          type="button"
          id="buttonLeaveSession"
          @click="leaveSession"
          value="Leave session"
        />
        <input
          id="buttonScreenShare"
          type="button"
          @click="screenShare"
          value="screen share"
        />
      </div>
      <div class="row">
        <div class="row panel panel-default">
          <div class="panel-heading">User Screens</div>
          <div id="screen-share-container">
            <!--여기에 스크린 쉐어가 들어감-->
          </div>
        </div>
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
  "Bearer eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjU4NzMxNzY1NzkzLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTg3MzUzNjUsInN1YiI6ImFjY2Vzcy10b2tlbiIsImVtYWlsIjoic3NkQGZzLmNvbSIsIm5hbWUiOiJ0ZXN0IiwidHlwZSI6IlVTRVIifQ._Xze71ercVgRePCGPkU6_rZ0O4_IxBWGONg3Xweh93PcTDD6ewPGoHoe3N5flgxWTFWbnCBol6mUOPuqrREW1g";

const OPENVIDU_API_SERVER_URL = "https://localhost:8082";

export default {
  name: "App",
  components: {
    UserVideo,
    ChatMessage,
  },
  data() {
    return {
      OVCamera: undefined,
      OVScreen: undefined,
      sessionCamera: undefined,
      sessionScreen: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      webCamToken: undefined,
      screenToken: undefined,
      subscribers: [],
      screenSubscribers: [],
      mySessionId: "52552152843524282555",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
      chatInput: undefined,
      screensharing: false,
      chatMessageList: [],
    };
  },
  methods: {
    screenShare() {
      var publisherScreen = this.OVScreen.initPublisher(
        "screen-share-container",
        {
          videoSource: "screen",
        }
      );

      publisherScreen.once("accessAllowed", () => {
        document.getElementById("buttonScreenShare").style.visibility =
          "hidden";
        this.screensharing = true;
        // It is very important to define what to do when the stream ends.
        publisherScreen.stream
          .getMediaStream()
          .getVideoTracks()[0]
          .addEventListener("ended", () => {
            console.log('User pressed the "Stop sharing" button');
            this.sessionScreen.unpublish(publisherScreen);
            document.getElementById("buttonScreenShare").style.visibility =
              "visible";

            this.screensharing = false;
          });
        this.sessionScreen.publish(publisherScreen);
      });

      publisherScreen.on("videoElementCreated", function (event) {
        event.element["muted"] = true;
      });

      publisherScreen.once("accessDenied", () => {
        console.error("Screen Share: Access Denied");
      });
    },
    sendMessage() {
      if (this.chatInput === "" || this.chatInput === undefined) return;
      this.sessionCamera
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
      this.OVCamera = new OpenVidu();
      this.OVScreen = new OpenVidu();

      // --- Init a session ---
      this.sessionCamera = this.OVCamera.initSession();
      this.sessionScreen = this.OVScreen.initSession();

      this.sessionCamera.on("signal:my-chat", (event) => {
        this.chatMessageList.push(event);
        console.log(event.data); // Message
        console.log(event.from); // Connection object of the sender
        console.log(event.type); // The type of message ("my-chat")
      });
      // --- Specify the actions when events take place in the session ---
      // On every new Stream received...
      this.sessionCamera.on("streamCreated", (event) => {
        // Subscribe to the Stream to receive it. HTML video will be appended to element with 'container-cameras' id
        if (event.stream.typeOfVideo == "CAMERA") {
          var subscriber = this.sessionCamera.subscribe(
            event.stream,
            undefined
          );
          this.subscribers.push(subscriber);
        }
      });

      this.sessionScreen.on("streamCreated", (event) => {
        // Subscribe to the Stream to receive it. HTML video will be appended to element with 'container-cameras' id
        if (event.stream.typeOfVideo == "SCREEN") {
          this.sessionScreen.subscribe(event.stream, "screen-share-container");
        }
      });

      // On every Stream destroyed...
      this.sessionCamera.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });
      // On every asynchronous exception...
      this.sessionCamera.on("exception", ({ exception }) => {
        console.warn(exception);
      });
      // --- Connect to the session with a valid user token ---
      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then(() => {
        console.log("getCameraToken Successful", this.webCamToken);
        console.log("getScreenToken Successful", this.screenToken);
        this.sessionCamera
          .connect(this.webCamToken, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---
            let publisher = this.OVCamera.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "600x320", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });
            this.mainStreamManager = publisher;
            this.publisher = publisher;
            // --- Publish your stream ---
            this.sessionCamera.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });

        this.sessionScreen
          .connect(this.screenToken, { clientData: this.myUserName })
          .then(() => {
            document.getElementById("buttonScreenShare").style.visibility =
              "visible";
            console.log("Session screen connected");
          })
          .catch((error) => {
            console.warn(
              "There was an error connecting to the session for screen share:",
              error.code,
              error.message
            );
          });
      });
      window.addEventListener("beforeunload", this.leaveSession);
    },
    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.sessionCamera) this.sessionCamera.disconnect();
      if (this.sessionScreen) this.sessionScreen.disconnect();
      this.sessionCamera = undefined;
      this.sessionScreen = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;
      axios.post(
        `${OPENVIDU_API_SERVER_URL}/remove-user`,
        JSON.stringify({
          knowledgeId: this.mySessionId,
          webCamToken: this.webCamToken,
          screenToken: this.screenToken,
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
          this.webCamToken = data.webCamToken;
          this.screenToken = data.screenToken;
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
#screen-share-container video {
  position: relative;
  float: left;
  width: 50%;
  cursor: pointer;
}
</style>
