<template>
  <b-container>
    <b-row class="mid-container">
      <b-col cols="4" md="3">
        <b-row class="process-num">01.</b-row>
        <b-row class="process-tag">결제 금액</b-row>
      </b-col>
      <b-col cols="14" md="9">
        <b-row class="price-point-area">
          <b-col cols="2">
            <b-form-radio
              v-model="amount"
              class="price-point-radio"
              name="check-button"
              button
              button-variant="outline-primary"
              value="1000"
            >
              <div class="price-point">
                <span class="amount">1000</span
                ><span class="name"><span>LP</span></span>
                <div class="price"><span>1,000원</span></div>
              </div>
            </b-form-radio>
          </b-col>
          <b-col cols="2">
            <b-form-radio
              v-model="amount"
              name="check-button"
              button
              button-variant="outline-primary"
              value="5000"
            >
              <div class="price-point">
                <span class="amount">5000</span
                ><span class="name"><span>LP</span></span>
                <div class="price"><span>5,000원</span></div>
              </div>
            </b-form-radio>
          </b-col>
          <b-col cols="2">
            <b-form-radio
              v-model="amount"
              name="check-button"
              button
              button-variant="outline-primary"
              value="10000"
            >
              <div class="price-point">
                <span class="amount">10000</span
                ><span class="name"><span>LP</span></span>
                <div class="price"><span>10,000원</span></div>
              </div>
            </b-form-radio>
          </b-col>
          <b-col cols="2">
            <b-form-radio
              v-model="amount"
              name="check-button"
              button
              button-variant="outline-primary"
              value="30000"
            >
              <div class="price-point">
                <span class="amount">30000</span
                ><span class="name"><span>LP</span></span>
                <div class="price"><span>30,000원</span></div>
              </div>
            </b-form-radio>
          </b-col>
          <b-col cols="2">
            <b-form-radio
              v-model="amount"
              name="check-button"
              button
              button-variant="outline-primary"
              value="50000"
            >
              <div class="price-point">
                <span class="amount">50000</span
                ><span class="name"><span>LP</span></span>
                <div class="price"><span>50,000원</span></div>
              </div>
            </b-form-radio>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
    <b-row class="mid-container">
      <b-col cols="4" md="3">
        <b-row class="process-num">02.</b-row>
        <b-row class="process-tag">결제 수단</b-row>
      </b-col>
      <b-col cols="14" md="9">
        <b-row>
          <b-col>
            <label
              ><b-form-radio
                class="payment-radio"
                v-model="payment"
                title="카카오페이"
                name="mobile-payment"
                @change.native="paymentClickEvent"
                value="kakaopay"
                button
                button-variant="outlined-dark"
                >카카오페이
                <img
                  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACEAAAAOCAYAAAChHnWMAAACt0lEQVRIS8WVbSzVURjAf9fl0tIiVqx2a9X0odVk2WqsLjNJq1F6G1u0jFVrFCaJEak2mdbaWmqTbEV5mYkrXJPCfLi9UKQINSV96ItV9639na64966tzfJ8Ov/nec5zfud5OX8Z00SpxLW4kEyVPxHA0um2WVqPj47REpdISk0Dg+aYMvOi4ByBCfHUAk6zdODfwhjVGk6G7KNQcpqEyD6F79lU2gH5fwCYOiKvkPi0HK5LEHb6T3yQy/E0W/UGKCmD6jowGCA4AI5EwbzfOSq9D87zQa0Bkwny0sFlIRTfhYeNsEIJGUkwNg73qiD5mLjuxStwIBxWLp/i0AfsxVOWeJSQy1nUmdU/deAbBC9ezcyJchl0P4YFzrDjIDS1QtBWaGiBjd7Q+ABW+4LPetC0gcofYqNgdzQMPxOwSm9Ql0Ow6k/slqfkyzRVVKr8CDOri+5AbKLtoiTEQUGOgOgfgDedcOI0FJXCxDD09EJPH9y4DdpuGNKCuxdkp4JCASlZ8G0AnBxnxB+Qmb6gBbzN6qRMyL9mG2JXCFSXCIi3g9DXAenn4dJVaK0Bv1AIC4WhEXg/DGO9EH4IRj6C0Qheq6DsplXsCSuI3n5Y6y82WUp9GWwLEBCSX1MlbNkJbq5wOBIyLkDXI5Au0tYhILq0sHk7yGTQqRals5AJq3JIDhW1EBEj6ijJIhfIPQPx0eJbglA3g5MTuLtBUwU4KsAnEL7/gA3rYPQzvG4HowHcvMQYfu0He3sriAGrxpRc6pshMg7Kb4kUeiyeudncEz1PQG4HdnYisDRVJiM4OIi1vRwGh2HNJshNg+Tj1tmdbExbI9r/DjyWiEmwJXtiRE88b7Ftn64N2S/642WrgLMQMaKS8l8fK50OpEoprINanaLTi/6SymUp0x+rSducP9tmwrn6gf0CYUX7e8900REAAAAASUVORK5CYII="
                  alt="kakaopay"
                />
              </b-form-radio>
            </label>
          </b-col>
          <b-col>
            <label
              ><b-form-radio
                class="payment-radio"
                v-model="payment"
                title="토스"
                name="mobile-payment"
                @change.native="paymentClickEvent"
                value="toss"
                button
                button-variant="outlined-dark"
                >토스
                <img
                  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACEAAAANCAIAAACo6JB1AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3FpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTQyIDc5LjE2MDkyNCwgMjAxNy8wNy8xMy0wMTowNjozOSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDoxNGM3NTI2ZS1kZTQ3LWIxNDgtOWM5ZS02NzhhYzMwOGI4OWUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6MkQwQTc5MEQxNUZEMTFFOEI4QTBCM0FCRjM1NURFQzEiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6MkQwQTc5MEMxNUZEMTFFOEI4QTBCM0FCRjM1NURFQzEiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjE0Yzc1MjZlLWRlNDctYjE0OC05YzllLTY3OGFjMzA4Yjg5ZSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDoxNGM3NTI2ZS1kZTQ3LWIxNDgtOWM5ZS02NzhhYzMwOGI4OWUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz65hovaAAACtUlEQVR42mLM6b9/6MInBhxgfZuakhQHA2WA6fqD73ikKbcACBgv3fkKsoqJ4dbjHw3zngDZ3pYCUa4i//8zsLEyqstxUm4Hi64yF4Q1f9trCCPSRURHCSq4+/THvWc/vnz3W4iPxc6Az99GEK7zw5e/e8583HHyw9+//9VkOdL9xYFqgOKfvv7ddPT9mRtfGRkYlKXZMwLEGf8DHQwGLgXXX3/4jRwHvuU3Hr38hewibUXOZfWqIAs+/3EuuP7nL7Lk/+MzdN58/APUBQweuKioAAsTVP4/A8QCUQFWiAVAKyEWmGny+FgJ8nMzA9lX739fvPMNkJE/6SHQAkbG/6m+ov42AhrynDv7NLk4mFoWAkObkZOdMT9UwkqXx1CNe12rGgvEjmuwmNdRAkXAvWc/IFZWxUqHOwtDQsY+5yqQceTSp1h3kc/fgF74z8gIirCcYAm4qznYgI7+z8PJoq/CleQtCk1XEOrq/W+w0ADFRPfy5xAuxAIgEOBhhmpgAoVDZ6YckPz373/J1If6CZfyJz4AxgpQREGSHUgC3ZfUfhco3rHkGcKOK/egdugogvzx5Rs0pIEegjBOX/8CYRiqghyhKsNxZJp2cYSktCgbkHvg/Kfle98CGUXhktu6NVJ8xSBhu3zPG2Dkw+y4Dw8rkBHwJDth1Yv95z4t3vE6u+8+xBOx7qAQyOm7v3TXmzgP0SmFChCVv37///Xnf1jtrQu3v+YGS5RESkHE//z9D0pXQE9EN90B8tlYGE/P0YXIAX2KmdIXVCkDo7F02qNdpz6gSV1coIcpzsfNfHCKNijO7z77KSnMxsrCCAkouB5g2i+f/ggSk+3pskAvigmyArkZ/mJ6ylwTVz///ec/MzNjY5KMr7UgRFxLgQPodSAbmMYak2VN1LmB0YfIH7QDAAEGADNbElCBxO/nAAAAAElFTkSuQmCC"
                  alt="toss"
                />
              </b-form-radio>
            </label>
          </b-col>
          <b-col>
            <label
              ><b-form-radio
                class="payment-radio"
                v-model="payment"
                title="페이코"
                name="mobile-payment"
                @change.native="paymentClickEvent"
                value="payco"
                button
                button-variant="outlined-dark"
                >페이코
                <img
                  src="data:image/png;base64,/9j/4AAQSkZJRgABAQEAYABgAAD/4RDoRXhpZgAATU0AKgAAAAgABQESAAMAAAABAAEAAAE7AAIAAAAGAAAIVodpAAQAAAABAAAIXJydAAEAAAAMAAAQ1OocAAcAAAgMAAAASgAAAAAc6gAAAAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFJFQUNIAAAFkAMAAgAAABQAABCqkAQAAgAAABQAABC+kpEAAgAAAAMyNQAAkpIAAgAAAAMyNQAA6hwABwAACAwAAAieAAAAABzqAAAACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMjAyMjowNzoyOSAxNDoxNjoxNgAyMDIyOjA3OjI5IDE0OjE2OjE2AAAAUgBFAEEAQwBIAAAA/+ELGGh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8APD94cGFja2V0IGJlZ2luPSfvu78nIGlkPSdXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQnPz4NCjx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iPjxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+PHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9InV1aWQ6ZmFmNWJkZDUtYmEzZC0xMWRhLWFkMzEtZDMzZDc1MTgyZjFiIiB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iLz48cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0idXVpZDpmYWY1YmRkNS1iYTNkLTExZGEtYWQzMS1kMzNkNzUxODJmMWIiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyI+PHhtcDpDcmVhdGVEYXRlPjIwMjItMDctMjlUMTQ6MTY6MTYuMjQ2PC94bXA6Q3JlYXRlRGF0ZT48L3JkZjpEZXNjcmlwdGlvbj48cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0idXVpZDpmYWY1YmRkNS1iYTNkLTExZGEtYWQzMS1kMzNkNzUxODJmMWIiIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyI+PGRjOmNyZWF0b3I+PHJkZjpTZXEgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj48cmRmOmxpPlJFQUNIPC9yZGY6bGk+PC9yZGY6U2VxPg0KCQkJPC9kYzpjcmVhdG9yPjwvcmRmOkRlc2NyaXB0aW9uPjwvcmRmOlJERj48L3g6eG1wbWV0YT4NCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgPD94cGFja2V0IGVuZD0ndyc/Pv/bAEMAAgEBAgEBAgICAgICAgIDBQMDAwMDBgQEAwUHBgcHBwYHBwgJCwkICAoIBwcKDQoKCwwMDAwHCQ4PDQwOCwwMDP/bAEMBAgICAwMDBgMDBgwIBwgMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDP/AABEIAA0AIQMBIgACEQEDEQH/xAAfAAABBQEBAQEBAQAAAAAAAAAAAQIDBAUGBwgJCgv/xAC1EAACAQMDAgQDBQUEBAAAAX0BAgMABBEFEiExQQYTUWEHInEUMoGRoQgjQrHBFVLR8CQzYnKCCQoWFxgZGiUmJygpKjQ1Njc4OTpDREVGR0hJSlNUVVZXWFlaY2RlZmdoaWpzdHV2d3h5eoOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4eLj5OXm5+jp6vHy8/T19vf4+fr/xAAfAQADAQEBAQEBAQEBAAAAAAAAAQIDBAUGBwgJCgv/xAC1EQACAQIEBAMEBwUEBAABAncAAQIDEQQFITEGEkFRB2FxEyIygQgUQpGhscEJIzNS8BVictEKFiQ04SXxFxgZGiYnKCkqNTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqCg4SFhoeIiYqSk5SVlpeYmZqio6Slpqeoqaqys7S1tre4ubrCw8TFxsfIycrS09TV1tfY2dri4+Tl5ufo6ery8/T19vf4+fr/2gAMAwEAAhEDEQA/AMn4X/sI6T48/ZOsviRqPjDUtDk1OPW3iZ9Ntn0qyfTlBSG4me6SffdOyxRCCCZt7gbD36L4g/8ABKDxF8O38L6fqGs3mn+IPF1q2mWFvqWjyWNnL4nWGC5GjC6kYRsk0MzxwXSlopLi2mj+ULur5ntPiH4gsIfDscOuapFH4RvH1DQ0W5bbo9y8kcjzW4ziN2kijYsuCWjU9hU2g/FPxR4X1G5vNO8Ra1ZXd7qtvrtxNFduslxqEDySQ3bnOWmR5ZGVzlgZGOeTX4nHEYPlUZUneyu7vsru3m9u2+uy/wBXK+S8UuvKrRzCKjzTag6cWknJ+zi5WvaEWubS8muX3dZy9g8Z/sqfDr4UeH7fVvEXxO1zUtJ1XWLnw1pt74V8MxanDcahZxWxvZMzXkGbZJrkRw4Hm3Co0ioq7dzh+xhoZP8Awi//AAmWsf8ACzv+EM/4Tr7EdBQeH/sf9n/2j9n+3/afM8z7P/y28jyPN/db8/PXmHwx/aJ+IHwVttRh8H+OPFXhiLVm8y8TTNTmthcSYx5h2sP3mDjzBhwOM0H9on4gt8K/+EHPjjxY3g3bs/sU6pMbLZnd5fl7seXu+by/ubucZ5pe3wb1dP5a/nzdejtptaW5f9kcTRfLHGJ2fxNQV10biqNvdWkoqX7x+8p0rcr4X+0F/wBv/vk0VPsorzNT9AvHsf/Z"
                />
              </b-form-radio>
            </label>
          </b-col>
        </b-row>
      </b-col>
    </b-row>
    <b-row class="mid-container">
      <b-col cols="4" md="3">
        <b-row class="process-num">03.</b-row>
        <b-row class="process-tag">정책 동의</b-row>
      </b-col>
      <b-col cols="14" md="9">
        <div class="notice-box">
          <div v-html="legal" />
          <div class="controls">
            <div>
              <input type="checkbox" id="agreeBtn" />
              <span class="legal-checkbox">
                <label for="agreeBtn"
                  ><a href="#/">상품,가격 및 유효기간</a
                  ><span>을 확인하였으며, </span
                  ><a
                    href="https://legal.kr.riotgames.com/league/payment"
                    target="_blank"
                    rel="noopener noreferrer"
                    >계약 관련 고지 사항</a
                  ><span>과 </span
                  ><a
                    href="https://legal.kr.riotgames.com/league/rp"
                    target="_blank"
                    rel="noopener noreferrer"
                    >Logos 가상 재화 정책</a
                  ><span> 및 결제 진행에 동의합니다.</span></label
                >
              </span>
            </div>
          </div>
        </div>
      </b-col>
    </b-row>
    <b-row class="mid-container">
      <b-col cols="4" md="3">
        <b-row class="process-num">04.</b-row>
        <b-row class="process-tag">결제 정보</b-row>
      </b-col>
      <b-col cols="14" md="9" class="payment-overall">
        <b-row>결제 항목 : Logos LP POINT {{ amount }} </b-row>
        <b-row
          >결제 금액 :
          {{
            amount.slice(0, amount.length - 3) +
            "," +
            amount.slice(-3, amount.length)
          }}원</b-row
        >
        <b-row>결제 수단 : {{ showPayment }}</b-row>
      </b-col>
    </b-row>
    <b-row class="mid-container">
      <b-col md="4" offset-md="4">
        <div class="button-row">
          <div class="btn-pay">
            <button class="btn btn-danger" @click="requestPay">결제하기</button>
          </div>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      amount: "1000",
      payment: "kakaopay",
      pg: "kakaopay",
      showPayment: "카카오페이",
      legal: `결제를 위해서는 카카오톡 또는 카카오페이 모바일 앱이 필요합니다.<br><br>
      카카오페이 고객센터 : 1644-7405<br><br><br>`,
    };
  },
  methods: {
    setLegal(payment) {
      switch (payment) {
        case "kakaopay":
          return `결제를 위해서는 카카오톡 또는 카카오페이 모바일 앱이 필요합니다.<br><br>
      카카오페이 고객센터 : 1644-7405<br><br><br>`;
        case "toss":
          return `RP충전은 Toss 앱이 설치되어 있어야만 결제 가능합니다.<br><br>
Toss서비스 이용을 위해서는 해당 서비스의 회원가입이 필요합니다.<br><br>
Toss는 휴대폰에서 간편하게 송금할 수 있는 (주)비바리퍼블리카의 서비스 입니다.<br><br><br>`;
        case "payco":
          return `휴대폰과 카드 명의자가 동일해야 결제 가능하며, 결제금액 제한은 각 카드사 정책을 따릅니다.<br><br>
사용 가능한 결제 수단은 결제화면 하단에서 확인할 수 있습니다.<br><br>
PAYCO 관련 혜택은 PAYCO 결제화면 내 안내를 통해 확인해주시기 바랍니다.<br><br><br>
`;
        default:
          return "";
      }
    },
    setPg(payment) {
      switch (payment) {
        case "kakaopay":
          return "kakaopay";
        case "toss":
          return "tosspay";
        case "payco":
          return "payco";
        default:
          return "";
      }
    },
    setPayment(payment) {
      switch (payment) {
        case "kakaopay":
          return "카카오페이";
        case "toss":
          return "토스";
        case "payco":
          return "페이코";
        default:
          return "";
      }
    },
    paymentClickEvent(event) {
      this.pg = this.setPg(event.target.value);
      this.showPayment = this.setPayment(event.target.value);
      this.legal = this.setLegal(event.target.value);
    },
    requestPay: function () {
      var IMP = window.IMP; // 생략 가능
      IMP.init("imp85880830"); // 예: imp00000000
      // IMP.request_pay(param, callback) 결제창 호출
      // axios로 백엔드에 주문 테이블에 주문 레코드 만들고 ID 받아오기
      // 보낼 정보 amount
      IMP.request_pay(
        {
          // param
          pg: this.pg,
          pay_method: "card",
          quota: "일시불",
<<<<<<< HEAD
          merchant_uid: "12123323",
=======
          merchant_uid: "121asdfasd2323",
>>>>>>> 4aa4c10eb833782892de29a6c357866a1458bd38
          name: "Logos LP Point " + this.amount,
          amount: this.amount,
          buyer_email: "test@test.com",
          buyer_name: "몰루",
          buyer_tel: "123-123-1231",
          buyer_addr: "",
          buyer_postcode: "",
        },
        (rsp) => {
          // callback
          if (rsp.success) {
            // 결제 성공 시 로직,
            // 백엔드에서 받았던 주문 ID 다시 백엔드로 보내서 위변조 검증
            // 결제번호(imp_uid)와 주문번호(merchant_uid) 보내기
            console.log(IMP);
            console.log(rsp);
            console.log("결제 성공");
          } else {
            console.log(IMP);
            console.log(rsp);
            // 결제 실패 시 로직,
            // 안내 후 다시 이전 페이지로 돌려보냄
            console.log("결제 실패");
          }
        }
      );
    },
  },
};
</script>
<style>
.mid-container {
  margin-bottom: 20px;
}
/* .price-point.active {
  color: #dc0000;
  border: 2px solid #dc0000;
} */

.payment-radio {
  padding-top: 20px;
  font-size: 40px;
  font-family: "Noto Sans KR", sans-serif;
}
.price-point-area {
  margin-top: 35px;
}
.price-point {
  padding: 10px;
  font-size: 20px;
  font-family: "Noto Sans KR", sans-serif;
}
.process-num {
  font-family: "Black Han Sans", sans-serif;
  font-size: 20px;
  text-shadow: 2px 2px 2px #efefef;
  color: rgba(136, 136, 136, 0.7);
}
.process-tag {
  font-family: "Black Han Sans", sans-serif;
  font-size: 45px;
}
.notice-box {
  font-size: 15px;
  font-family: "Noto Sans KR", sans-serif;
  padding-top: 40px;
}
.legal-checkbox {
  font-size: 15px;
  font-family: "Do Hyeon", sans-serif;
  padding: 10px;
}
.payment-overall {
  padding: 30px;
  font-size: 15px;
  font-family: "Noto Sans KR", sans-serif;
}
.title {
  font-family: "Black Han Sans", sans-serif;
  font-size: 50px;
  text-align: "center";
}
@import url("https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Noto+Sans+KR&display=swap");
</style>
