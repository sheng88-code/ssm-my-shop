/**
 * 函数对象
 */
var Validate=function () {
    /**
     * 初始化 jQuery validation(私有化)
     */
    var handlerInitValidate=function () {
        //初始化jQuery validate
        $("#inputForm").validate({
            rules : {
                "phone" : {
                    required : true,
                    number : true,//期望的是true,如果为false则展示提示信息
                    mobilephone : true//期望的是true,如果为false则展示提示信息
                }
            },
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement:function (error,element) {
                element.parent().parent().attr("class","form-group has-error");
                error.insertAfter(element);
            }
        });
    }
    /**
     * 追加正则表达式判断
     */
    var handlerInitCustomValidate=function () {
        //追加正则表达式判断
        jQuery.validator.addMethod("mobilephone", function(value, element) {
            var length = value.length;
            var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
    }

    /**
     * 公有
     */
    return{
        init:function () {
            handlerInitCustomValidate();
            handlerInitValidate();
        }
    }
}();
/**
 * 引用了这个文件的路径就可以直接引用
 */
$(document).ready(function () {
    Validate.init();
})