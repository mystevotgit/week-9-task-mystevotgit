const signupbtn = document.getElementById("signup")
var activatemodal = document.querySelector(".modal")
var modalclose = document.querySelector(".modal-close")

signupbtn.addEventListener("click", function(){
    activatemodal.classList.add("modal-active")
});

modalclose.addEventListener("click", function(){
    activatemodal.classList.remove("modal-active")
})
