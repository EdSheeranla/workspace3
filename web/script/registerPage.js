/**
 * Created by admin on 2017/3/8.
 */
function  registerValidate() {
    var username=document.getElementById("username").value;
    if(username==null || username ==""){
        alert("用户名不能为空");
        return false;
    }
}