const submitButton = document.getElementById("submit-button");
submitButton.disabled = true;

function validate() {
    const login = document.getElementById("login").value;
    const pass = document.getElementById("pass").value;
    const loginREGEX = /[\w@\.-][^<>{}\[\]"~;$^%?#&]{1,20}$/;
    const passREGEX = /^([\w^<>{}\[\]"~;$^%?#&]{4,20})$/;
    if (!(login).match(loginREGEX)) {
        document.getElementById("err-login-fn").innerHTML="Enter valid login";
        return false;
    }
    else if (!(pass).match(passREGEX)) {
        document.getElementById("err-pass-fn").innerHTML="Enter valid password (at least 4 symbols)";
        return false;
    } else {
        submitButton.disabled = false;
        document.getElementById("err-login-fn").innerHTML="";
        document.getElementById("err-pass-fn").innerHTML="";
        return true;
    }
}
</scrip