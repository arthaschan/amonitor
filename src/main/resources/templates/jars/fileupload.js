// ����ͼƬ�ϴ�
function setFileUpload() {
	new AjaxUpload($('#upload'), {
		data:{
			"filenameId":$('#clientNo').val(),
			"width":153,
			"height":54,
		},
		action: "../common/uploadTempImg.do",
		name: "fileUpload",
		onSubmit: function(file, ext){
			if (!(ext && /^(gif|jpg|jpeg|png)$/.test(ext))){
	            // extension is not allowed
				alert("ֻ���ϴ�gif|jpg|jpeg|png��ʽ�ļ���");
				return false;
			}
			var  fileInput = document.getElementsByName('fileUpload')[0];
			  if(!/msie/.test(navigator.userAgent.toLowerCase())){
				if(fileInput!=null && fileInput.value!=""){
					if(fileInput.files[0].size>1*1048576){
			        	alert("�ϴ���˾LogoͼƬ�ļ����ܳ���1M��");
			        	return false;
			        }
				}
			}
			//disable���������ť
			$('#upload').attr("disabled",true);
			$('#delPic').attr("disabled",true);
			$('#upload').linkbutton({text:'�ϴ���...'});
		},
		onComplete: function(file, data){
			$('#upload').attr("disabled",false);
			$('#delPic').attr("disabled",false);
			$('#upload').linkbutton({text:'�ϴ�Logo'});
			
			data = (eval("("+data+")")).data;
			console.log(data);
			if (hasSucc_body_msg(data)) {
	        	$('#delPic').css('display','');
				//ˢ��ͼƬ��url
				data = data.data;
				$('#imgpic').attr("src", data+"?timestamp=" + (new Date()).valueOf());
				$('#clientLogo').val(data.substring(data.lastIndexOf('.'), data.length));
			} else {
				alert(data.error);
			}
		}
	});
}

// ɾ���ϴ�����ʱ�ļ�
function deletePic() {
	var ext = $("#clientLogo").val();
	var filename=$("#clientNo").val()+ext;
	if (HRMCommon.isEmpty(ext)) {
		filename = "";
	}
	
	if(ext==null || ext==""){
		$.messager.alert('��ʾ', '�ϴ�ͼƬ������ˢ�º����ԣ�', 'info');
		return false;
	}
	var filename=$("#clientNo").val()+ext;
	$.messager.confirm('��ʾ','��ȷ��Ҫɾ����',function(r){
		if (r){
			$.ajax({ // 4.�ύ
			    type: "post",   
			    url: "../common/deleteTempFile.do",
				data: {"fileName":filename, "toPath":"comp_images"},  
			    datatype: "json",
			    success:function(data){ // 5.���ؽ��
					$('#imgpic').attr("src", "");
					$('#clientLogo').val("");
					$('#delPic').css('display','none');
			    }
			});
		}
	});
}
