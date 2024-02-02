/* Faculty Operations */

//addFaculty
$("#addFaculty").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "faculty/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addFaculty").trigger("reset");
			}
		},
		success: function(data) {
			$("#facultyResponce").hide();
			$("#facultyResponce").html(data);
			$("#facultyResponce").fadeIn("slow");
		}
	});
});

// getFaculty Model data
$(document).on("click", ".updFac", function() {
	let fId = $(this).data("fid");
	$.ajax({
		url: "faculty/getFacultyById/" + fId,
		type: "GET",
		success: function(data) {
			if (data == null) {
				$("#editFacultyBody").html("<p class='text-danger'>No Data Found</p>")
			} else {
				$("#updFacEnroll").val(data.facultyEnrollment);
				$("#updFacEmail").val(data.facultyEmail);
				$("#updFacName").val(data.facultyName);
				$("#updFacPass").val(data.facultyPassword);
				$("#hdnFid").val(data.facultyId);
			}
		}
	});
});
// updates Faculty Data
$("#editFacultyForm").on("submit", function(e) {
	e.preventDefault();
	let fId = $("#hdnFid").val();
	$.ajax({
		url: "faculty/updateFacultyById/" + fId,
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updFacRes").hide();
			$("#updFacRes").html(data);
			$("#updFacRes").fadeIn("slow");
		}
	})
});

// Delete Faculty
$(document).on("click", ".delFac", function() {
	let fid = $(this).data("fid");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "faculty/deleteFacultyById/" + fid,
					type: "DELETE",
					success: function(data) {
						if (data == 1) {
							$.alert('Faculty Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Faculty");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});

/* Course Operations */

//add Course
$("#addCourse").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "course/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addCourse").trigger("reset");
			}
		},
		success: function(data) {
			$("#courseResponce").hide();
			$("#courseResponce").html(data);
			$("#courseResponce").fadeIn("slow");
		}
	});
});

//getCourseData
$(document).on("click", ".updCourse", function() {
	let cid = $(this).data("cid");
	$.ajax({
		url: "course/get/" + cid,
		type: "GET",
		success: function(data) {
			if (data == null) {
				$("#updCourseRes").html("<p class='text-danger'>No data Found</p>");
			} else {
				$("#updCourse").val(data.courseName);
				$("#hdnCid").val(data.courseId);
			}
		}
	});
});

//updateCourse
$("#editCourseForm").on("submit", function(e) {
	e.preventDefault();
	let cid = $("#hdnCid").val();
	$.ajax({
		url: "course/update/" + cid,
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updCourseRes").hide();
			$("#updCourseRes").html(data);
			$("#updCourseRes").fadeIn("slow");
		}
	});
});

//Delete Course
$(document).on("click", ".delCourse", function() {
	let cid = $(this).data("cid");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "course/delete/" + cid,
					type: "DELETE",
					success: function(data) {
						if (data == 1) {
							$.alert('Course Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Course");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});


/* Subject Operations */

//add Subject
$("#addSubject").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "subjects/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addSubject").trigger("reset");
			}
		},
		success: function(data) {
			$("#subjectResponse").hide();
			$("#subjectResponse").html(data);
			$("#subjectResponse").fadeIn("slow");
		}
	});
});

//getSubjectData
$(document).on("click", ".updSubject", function() {
	let sid = $(this).data("sid");
	$.ajax({
		url: "subjects/get/" + sid,
		type: "GET",
		success: function(data) {
			if (data == null) {
				$("#updSubjectRes").html("<p class='text-danger'>No data Found</p>");
			} else {
				$("#updSubject").val(data.subjectName);
				$("#hdnSid").val(data.subjectId);
			}
		}
	});
});

//update Subject
$("#editSubjectForm").on("submit", function(e) {
	e.preventDefault();
	let sid = $("#hdnSid").val();
	$.ajax({
		url: "subjects/update/" + sid,
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updSubjectRes").hide();
			$("#updSubjectRes").html(data);
			$("#updSubjectRes").fadeIn("slow");
		}
	});
});

//Delete Subject
$(document).on("click", ".delSubject", function() {
	let sid = $(this).data("sid");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "subjects/delete/" + sid,
					type: "DELETE",
					success: function(data) {
						if (data == 1) {
							$.alert('Subject Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Subject");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});


/* Semester Operations */

//add Semester
$("#addSemester").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "semester/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addSemester").trigger("reset");
			}
		},
		success: function(data) {
			$("#semesterResponse").hide();
			$("#semesterResponse").html(data);
			$("#semesterResponse").fadeIn("slow");
		}
	});
});

//getSemesterData
$(document).on("click", ".updSem", function() {
	let sid = $(this).data("sid");
	$.ajax({
		url: "semester/get/" + sid,
		type: "GET",
		success: function(data) {
			if (data == null) {
				$("#updSemRes").html("<p class='text-danger'>No data Found</p>");
			} else {
				$("#updSem").val(data.semesterName);
				$("#hdnSemid").val(data.semesterId);
			}
		}
	});
});

//update Semester
$("#editSemForm").on("submit", function(e) {
	e.preventDefault();
	let sid = $("#hdnSemid").val();
	$.ajax({
		url: "semester/update/" + sid,
		type: "PUT",
		data: $(this).serialize(),
		success: function(data) {
			$("#updSemRes").hide();
			$("#updSemRes").html(data);
			$("#updSemRes").fadeIn("slow");
		}
	});
});

//Delete Semester
$(document).on("click", ".delSem", function() {
	let sid = $(this).data("sid");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "semester/delete/" + sid,
					type: "DELETE",
					success: function(data) {
						if (data == 1) {
							$.alert('Semester Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Semester");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});

/* Mapping Operations */

//add Mapping
$("#addMapping").on("submit", function(e) {
	e.preventDefault();
	$.ajax({
		url: "mapping/add",
		type: "POST",
		data: $(this).serialize(),
		statusCode: {
			200: function() {
				$("#addMapping").trigger("reset");
			}
		},
		success: function(data) {
			$("#mappingResponse").hide();
			$("#mappingResponse").html(data);
			$("#mappingResponse").fadeIn("slow");
		}
	});
});

//Delete Mapping
$(document).on("click", ".delMapping", function() {
	let mid = $(this).data("mid");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "mapping/delete/" + mid,
					type: "DELETE",
					success: function(data) {
						if (data == 1) {
							$.alert('Mapping Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Mapping");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});

/* Batch Operations */
//add Batch
$("#addBatch").on("submit",function(e){
	e.preventDefault();
	$.ajax({
		url:"batch/add",
		type:"POST",
		data:$(this).serialize(),
		statusCode:{
			200:function(){
				$("#addBatch").trigger("reset");
			}
		},
		success:function(data){
			$("#batchResponce").hide();
			$("#batchResponce").html(data);
			$("#batchResponce").fadeIn("slow");
		}
	});
});


// getBatchData
$(document).on("click", ".updBatch", function() {
	let bid = $(this).data("bid");
	$.ajax({
		url: "batch/get/" + bid,
		type: "GET",
		success: function(data) {
			console.log(data);
			if (data == null) {
				$("#updBatchRes").html("<p class='text-danger'>No data Found</p>");
			} else {
				$("#updBatch").val(data.batchName);
				$("#hdnBid").val(data.id);
			}
		}
	});
});

// Update Batch
$("#editBatchForm").on("submit",function(e){
	let bid=$("#hdnBid").val();
	e.preventDefault();
	$.ajax({
		url:"batch/update/"+bid,
		type:"PUT",
		data:$(this).serialize(),
		success:function(data){
			$("#updBatchRes").hide();
			$("#updBatchRes").html(data);
			$("#updBatchRes").fadeIn("slow");
		}
	});
});

// Delete Batch
$(document).on("click", ".delBatch", function() {
	let bid = $(this).data("bid");
	let element = this;
	$.confirm({
		title: '<p><small>Do yo Really Want To Delete?</small>',
		buttons: {
			confirm: function() {
				$.ajax({
					url: "batch/delete/" + bid,
					type: "DELETE",
					success: function(data) {
						if (data == 1) {
							$.alert('Batch Deleted SuccessFully');
							$(element).closest("tr").fadeOut();
						} else {
							$.alert("Internal Error Can't Delete Batch");
						}
					}
				});
			},
			cancel: function() {
				$.alert('Operation Aborted!');
			}
		}
	});
});

$("#addStudent").on("submit",function(e){
	e.preventDefault();
	$.ajax({
		url:"student/add",
		type:"POST",
		data:$(this).serialize(),
		statusCode:{
			200:function(){
				$("#addStudent").trigger("reset");
			}
		},
		success:function(data){
			$("#studentResponse").hide();
			$("#studentResponse").html(data);
			$("#studentResponse").fadeIn("slow");
		}
	});
});