
$(document).ready(function () {
    if(typeof(EventSource) !== "undefined") {
        var source = new EventSource("http://localhost:8081/advertising/stand/connection");
       source.onmessage = function() {
            location.reload();
        };
    }
})
