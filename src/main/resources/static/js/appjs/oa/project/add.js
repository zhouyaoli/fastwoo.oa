$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	sendAjaxRequest("/oa/project/save", $('#signupForm').serialize(), false, false, false, function(data) {
		if (data.code == 0) {
			parent.layer.msg("操作成功");
			parent.reLoad();
			var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
			parent.layer.close(index);

		} else {
			parent.layer.alert(data.msg)
		}

	});

}
function validateRule() {
	debugger
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			no : {
				required : true,
				minlength : 2,
				remote : {
					url : "/oa/project/exit", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
						no : function() {
							return $("#no").val();
						}
					}
				}
			},
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入项目名称"
			},
			no : {
				required : icon + "请输入您的项目编号",
				minlength : icon + "项目编号必须两个字符以上",
				remote : icon + "项目编号已经存在"
			},
		}
	})
}