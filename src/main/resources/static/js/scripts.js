function passwordShowToggle(inputId, iconId) {
    var x = document.getElementById(inputId);
    var y = document.getElementById(iconId);
    if (x.type === "password") {
        x.type = "text";
        y.className = "fa fa-eye-slash";
    } else {
        x.type = "password";
        y.className = "fa fa-eye";
    }
}