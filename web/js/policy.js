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
                    //document.getElementById("demo").innerHTML = xhttp.responseText;
                    alert(xhttp.responseText);
                }
            };
            xhttp.open("GET", "DynamicPolicyView?action=View&fileName=" + fileName + "&key=" + key, true);
            xhttp.send();
        }
        if ($(this).attr('value') == "Update")
        {
            if (key == "")
            {
                alert("Please Enter Key First");
                return false;
            }
            window.location.href = "dpChange.jsp?key=" + key + "&fileName=" + fileName;
        }
    });
});