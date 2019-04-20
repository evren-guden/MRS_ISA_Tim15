function getFormData(formId)
{
    var formData = {};
    var s_data = $(formId).serializeArray();

    for(var i = 0; i<s_data.length; i++){
        var record = s_data[i];
        formData[record.name] = record.value;
    }
    
    return formData;
}

function util_login()
{	
	sessionStorage.setItem('openLoginForm',true);
	window.location.href = "index.html";
}