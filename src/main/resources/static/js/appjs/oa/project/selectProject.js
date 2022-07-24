var selectBtn = "";
var openProject = function(_selectBtn) {
	if (!_selectBtn) {
		_selectBtn = "";
	}
	selectBtn = _selectBtn;
	layerOpen({
		type : 2,
		title : "选择项目",
		area : [ '300px', '450px' ],
		content : "/oa/project/selectProjectWin"
	})
}
function loadProject(projectId,projectNo, projectName) {
	if (!selectBtn) {
		selectBtn = "";
	}
	if($(selectBtn + "input[name='projectId']")){
		$(selectBtn + "input[name='projectId']").val(projectId);
	}
	if($(selectBtn + "input[name='projectNo']")){
		$(selectBtn + "input[name='projectNo']").val(projectNo);
	}
	if($(selectBtn + "input[name='projectName']")){
		$(selectBtn + "input[name='projectName']").val(projectName);
	}
}