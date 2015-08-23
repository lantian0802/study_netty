var socket;
if(!window.WebSocket) {
    window.WebSocket = window.MozWebSocket;
}
if(window.WebSocket) {
    socket = new WebSocket("ws://localhost:8080/websocket");
}