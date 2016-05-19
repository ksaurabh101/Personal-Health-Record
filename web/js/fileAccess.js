$(document).ready(function () {
    $("#message").hide();
    $("input").click(function () {
        var key = $("#key").val();
        var fileName = $(this).attr('name');
        if ($(this).attr('value') == "View")
        {
            if (key == "")
            {
                alert("Please Enter Key First");
                return false;
            }
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
//                    document.getElementById("message").innerHTML = xhttp.responseText;
//                    $("#message").show();
                    alert(xhttp.responseText);
                }
            };
            xhttp.open("GET", "FileAccess?action=View&fileName=" + fileName + "&key=" + key, true);
            xhttp.send();
        }
        if ($(this).attr('value') == "Remove")
        {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    alert(xhttp.responseText);
                }
            };
            xhttp.open("GET", "FileAccess?action=Remove&fileName=" + fileName + "&key=" + key, true);
            xhttp.send();
            location.reload();
//            window.location.href = "PatientRemovePHR.jsp";
        }
//        if ($(this).attr('value') == "Update")
//        {
//            if (key == "")
//            {
//                alert("Please Enter Key First");
//                return false;
//            }
//            var xhttp = new XMLHttpRequest();
//            xhttp.onreadystatechange = function () {
//                if (xhttp.readyState == 4 && xhttp.status == 200) {
//                    alert(xhttp.responseText);
//                }
//            };
//            xhttp.open("GET", "FileAccess?action=Update&fileName=" + fileName + "&key=" + key , true);
//            xhttp.send();
//            location.reload();
//        }
        if ($(this).attr('value') == "Download")
        {
            if (key == "")
            {
                alert("Please Enter Key First");
                return false;
            }
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    alert(xhttp.responseText);
                }
            };
            xhttp.open("GET", "FileAccess?action=Download&fileName=" + fileName + "&key=" + key, true);
            xhttp.send();
        }

        if ($(this).attr('value') == "Read")
        {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
//                    document.getElementById("message").innerHTML = xhttp.responseText;
//                    $("#message").show();
                    alert(xhttp.responseText);
                }
            };
            xhttp.open("GET", "FileAccess?action=Read&fileName=" + fileName, true);
            xhttp.send();
        }
        if ($(this).attr('value') == "Write")
        {
            var str = fileName.toString();
            var index = str.lastIndexOf(".");
            var type = str.substring(index + 1);
            if (type != "txt")
            {
                alert("This File is not Writable..");
            }
            else {

                window.location.href = "UserWritePHR.jsp?fileName=" + fileName;
            }

        }
        if ($(this).attr('name') == "status")
        {
            var value = $(this).attr('value');
            var appointmentNo = $(this).attr('id');
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
//                    document.getElementById("message").innerHTML = xhttp.responseText;
//                    $("#message").show();
                    alert(xhttp.responseText);
                }
            };
            xhttp.open("GET", "FileAccess?action=Status&value=" + value + "&appointmentNo=" + appointmentNo, true);
            xhttp.send();
        }
        //RemoveUser.jsp...
        if ($(this).attr('value') == "RemoveUser") {
            var userId = $(this).attr('name');
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    alert(xhttp.responseText);
                }
            };
            xhttp.open("GET", "RemoveUser?userID=" + userId, true);
            xhttp.send();
            window.location.href = "RemoveUser.jsp";

        }
        //RemovePatient.jsp
        if ($(this).attr('value') == "RemovePatient") {
            var patientID = $(this).attr('name');
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (xhttp.readyState == 4 && xhttp.status == 200) {
                    alert(xhttp.responseText);
                }
            };
            xhttp.open("GET", "RemovePatient?patientID=" + patientID, true);
            xhttp.send();
            window.location.href = "RemovePatient.jsp";
        }

    });
});