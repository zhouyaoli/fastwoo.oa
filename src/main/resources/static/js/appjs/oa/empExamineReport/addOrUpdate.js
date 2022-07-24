$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		saveOrUpdate();
	}
});
function saveOrUpdate() {
	sendAjaxRequest("/oa/empExamineReport/saveOrUpdate",$('#signupForm').serialize(),false,false,false, function(data) {
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
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}