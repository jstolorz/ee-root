var wsUri = "ws://localhost:8080/ee-websocket-basic/hello";

            function init() {
                output = document.getElementById("output");
            }
            function send_message() {
                websocket = new WebSocket(wsUri);
                websocket.onopen = function(evt) {
                    onOpen(evt)
                };
                websocket.onmessage = function(evt) {
                    onMessage(evt)
                };
                websocket.onerror = function(evt) {
                    onError(evt)
                };
            }
            function onOpen(evt) {
                writeToScreen("Connected to Endpoint!");
                doSend(textID.value);
            }
            function onMessage(evt) {
                writeToScreen("Message Received: " + evt.data);
            }
            function onError(evt) {
                writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
            }
            function doSend(message) {
                writeToScreen("Message Sent: " + message);
                websocket.send(message);
                // You can close communication at any time
                //websocket.close();
            }
            function writeToScreen(message) {
                var pre = document.createElement("p");
                pre.style.wordWrap = "break-word";
                pre.innerHTML = message;

                output.appendChild(pre);
            }

            window.addEventListener("load", init, false);