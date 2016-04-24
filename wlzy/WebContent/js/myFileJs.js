/*******************************************/
var teacherFile0 = $(".teacherFile:eq(0)");
var teacherFile1 = $(".teacherFile:eq(1)");
var teacherFile2 = $(".teacherFile:eq(2)");
var teacherFile3 = $(".teacherFile:eq(3)");
var teacherFile4 = $(".teacherFile:eq(4)");
teacherFile0.mouseover(function() {
	$(".teacherFile").css("background-color", "#cceeaa");
	teacherFile0.css("background-color", "orange");
	$("#divCenter div").css("display", "none");
	$("#divCenter #notice").css("display", "block");
});
teacherFile1.mouseover(function() {
	$(".teacherFile").css("background-color", "#cceeaa");
	teacherFile1.css("background-color", "orange");
	$("#divCenter div").css("display", "none");
	$("#divCenter #vedio").css("display", "block");
});
teacherFile2.mouseover(function() {
	$(".teacherFile").css("background-color", "#cceeaa");
	teacherFile2.css("background-color", "orange");
	$("#divCenter div").css("display", "none");
	$("#divCenter #doc").css("display", "block");
});
teacherFile3.mouseover(function() {
	$(".teacherFile").css("background-color", "#cceeaa");
	teacherFile3.css("background-color", "orange");
	$("#divCenter div").css("display", "none");
	$("#divCenter #audio").css("display", "block");
});
teacherFile4.mouseover(function() {
	$(".teacherFile").css("background-color", "#cceeaa");
	teacherFile4.css("background-color", "orange");
	$("#divCenter div").css("display", "none");
	$("#divCenter #image").css("display", "block");
});
/*******************************************/