var FV = (function() {
    var FormVerify = function(elTarget) {
        this.elForm = elTarget;
        this.elId = this.elForm.querySelector("input[name=userId]");
        this.elPw1 = this.elForm.querySelector("input[name=password]");
        this.elPw2 = this.elForm.querySelector("input[name=checkPassword]");
        this.elName = this.elForm.querySelector("input[name=name]");
        this.elPhone = this.elForm.querySelector("input[name=phone]");
        this.elAddress = this.elForm.querySelector("input[name=address]");
        this.elSubmit = this.elForm.querySelector("input[type=submit]");
    }

    FormVerify.prototype.validate_when_submit = function() {
        this.elSubmit.addEventListener("click", function(e) {

        }, false);
    };

    return FormVerify;
})();

//service code
(function() {
    var elTarget = document.querySelector("#signup_form");
    var oFV = new FV(elTarget);
    oFV.validate_when_submit();

})();