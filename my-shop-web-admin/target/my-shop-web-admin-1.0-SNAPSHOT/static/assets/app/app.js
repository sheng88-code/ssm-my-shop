

var App=function () {

    //iCheck
    var _masterCheckbox=null;
    var _checkbox=null;

    //用户存放id的数组
    var _idArray;

    //默认的Dropzone参数
    var defaultDropzoneOpts={
        url: "",
        paramName:"dropFile",
        maxFiles:1,//一次性上传的文件数量上限
        maxFilesize: 2, //文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", //上传的类型
        addRemoveLinks:true,
        parallelUploads: 1,//一次上传的文件数量
        dictDefaultMessage:'拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传"+this.maxFiles+"个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage:"浏览器不受支持",
        dictFileTooBig:"文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    }

    /**
     * 私有方法初始花iCheck
     */
    var handlerInitCheckbox=function () {
        //激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        //获取控制端Checkbox
        _masterCheckbox=$('input[type="checkbox"].minimal.icheck-master');

        //获取全部Checkbox集合
        _checkbox=$('input[type="checkbox"].minimal');
    };

    /**
     * 全选功能
     */
    var handlerCheckboxAll=function () {
        _masterCheckbox.on("ifClicked",function (e) {
            console.log(e.target.checked);
            //返回true代表没有选中
            if(e.target.checked){
                _checkbox.iCheck("uncheck");
            }
            //选中状态
            else {
                _checkbox.iCheck("check");
            }
        });
    }

    /**
     * 实现单个删除
     * @param url
     * @param id
     * @param msg
     */
   var handlerDeleteSingle=function (url,id,msg) {

        //可选参数
       if (!msg){msg=null;}
       //将ID放入数组中，以便和批量删除通用
       _idArray=new Array();
       _idArray.push(id);

       $("#modal-message").html(msg==null?"您确认删除数据项？":msg);
       $("#modal-default").modal("show");
       //绑定删除事件
       $("#btnModalOk").bind("click",function () {
            handlerDeleteData(url);
       })

   }

    /**
     * 批量删除
     */
    var handlerDeleteMulti=function (url) {
        _idArray=new Array();

        //将选中元素的id放入数组中
        _checkbox.each(function () {
            var _id=$(this).attr("id");

            if(_id!=null&&_id!="undefined"&&$(this).is(":checked")){
                _idArray.push(_id);
            }
        });

        /**
         *判断用户是否选择数据项(===为绝对等于)
         */
        if(_idArray.length===0){
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项");
        }
        else {
            $("#modal-message").html("您确认删除该项数据？");
        }

        //点击删除按钮时，弹出模态框
        $("#modal-default").modal("show");

        //如果用户选择了数据项则调用删除方法
        $("#btnModalOk").bind("click",function () {
            handlerDeleteData(url);
        });

        /**
         * 当前私有函数的私有函数，删除数据
         */

    };

    /**
     * AJAX异步刷新
     * @param url
     */
    var handlerDeleteData=function (url) {

        $("#modal-default").modal("hide");

        //如果没有选择数据项则关闭模态框
        if(_idArray.length===0){
            //...
        }
        //删除操作
        else {
            setTimeout(function () {
                $.ajax({
                    "url":url,
                    "type":"POST",
                    "data":{"ids":_idArray.toString()},
                    "dataType":"JSON",
                    "success":function (data) {
                        //请求成功后，无论是成功或是失败都需要弹出模态框进行提示，所以这里需要先解绑原来的click事件
                        $("#btnModalOk").unbind("click");

                        //请求成功，刷新页面，删除成功
                        if(data.status===200){
                            $("#btnModalOk").bind("click",function () {
                                window.location.reload();
                            });
                        }

                        //请求失败，删除失败
                        else {
                            //确认按钮的事件改为隐藏模态框
                            $("#btnModalOk").bind("click",function () {
                                $("#modal-default").modal("hide");
                            });
                        }

                        //因为无论如何都需要提示信息，所以这里的模态框必须调用的
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            },500);

        }



    }

    /**
     * 初始化DataTables
     */
    var handlerInitDataTables=function (url,columns) {

        var _dataTable=$("#dataTable").DataTable({
            "paging":true,
            "info":true,
            "lengthChange":false,
            "ordering":false,
            "processing":true,
            "searching":false,
            "serverSide":true,
            "deferRender":true,
            "ajax":{
                "url":url
            },
            "columns": columns,
            "language":{
                "processing": "处理中...",
                "lengthMenu": "显示 _MENU_ 项结果",
                "zeroRecords": "没有匹配结果",
                "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "infoFiltered": "(由 _MAX_ 项结果过滤)",
                "infoPostFix": "",
                "search": "搜索:",
                "searchPlaceholder": "搜索...",
                "url": "",
                "emptyTable": "表中数据为空",
                "loadingRecords": "载入中...",
                "infoThousands": ",",
                "paginate": {
                    "first": "首页",
                    "previous": "上页",
                    "next": "下页",
                    "last": "末页"
                },
                "aria": {
                    "paginate": {
                        "first": "首页",
                        "previous": "上页",
                        "next": "下页",
                        "last": "末页"
                    },
                    "sortAscending": "以升序排列此列",
                    "sortDescending": "以降序排列此列"
                },
                "thousands": "."
            },
            "drawCallback": function( settings ) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        })

        return _dataTable;
    };

    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail=function (url) {
        //这里是通过Ajax请求的html的方式将jsp装进模态框中
        $.ajax({
            url:url,
            type:"get",
            dataType:"html",
            success:function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    /**
     * 初始化zTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTree=function (url,autoParam,callback) {
        var setting = {
            //防止多选
            view:{
                selectedMulti:false
            },
            async: {
                enable: true,
                url:url,
                autoParam:autoParam//自动往后台传

            }
        };
        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModalOk").bind("click",function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree"),
                nodes = zTree.getSelectedNodes();



            //未选择
            if (nodes.length == 0) {
                alert("请先选择一个节点");
            }
            //已选择
            else {
                callback(nodes);

            }
        })
    }

    /**
     *初始化Dropzone
     */
    var handlerInitDropzone=function (opts) {
        //关闭Dropzone的自动发现的功能
        Dropzone.autoDiscover = false;
        $.extend(defaultDropzoneOpts,opts);

        var myDropzone = new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    }


    return{
        //初始化
        init:function(){

            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        //批量删除
        deleMutil:function (url) {
            handlerDeleteMulti(url);
        },
        //初始化DataTables
        initDataTables:function (url,columns) {
            return handlerInitDataTables(url,columns);
        },
        //初始化zTree
        initZTree:function(url,autoParam,callback){
            handlerInitZTree(url,autoParam,callback);
        },
        //初始化Dropzone
        initDropzone:function(opts){
          handlerInitDropzone(opts);
        },
        //显示详情
        showDetail:function (url) {
            handlerShowDetail(url);
        },
        //删除单个数据
        deleteSingle:function (url,id,msg) {
            handlerDeleteSingle(url,id,msg);
        }
    }
}();



$(document).ready(function () {
    App.init();
})