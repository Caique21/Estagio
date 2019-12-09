/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var dep;
var cur;
var divClone = $("#ultima-importacao").clone();


function changeDepartamento() 
{
    var dep = $("#sel-dep").val();
    
    var obj = $("#hosting-plan").find("option[value='" + dep + "']");
    
    if(obj != null && obj.length > 0)
    {
        document.getElementById("fileInput").disabled = false;
        document.getElementById("ultima-importacao").hidden = false;
        document.getElementById("label-ultimas-importacoes-qualis").hidden = false;
        carregaQualis();
    }
    else
    {
        document.getElementById("fileInput").disabled = true;
        document.getElementById("ultima-importacao").hidden = true;
        document.getElementById("label-ultimas-importacoes-qualis").hidden = true;
    }
}

function carregaQualis()
{
    event.preventDefault(); // evita refresh da tela
    let frm = $("#importar_qualis");    //form
    var d = document.getElementById("sel-dep").value;
    
    jQuery.ajax(
    {
        type: "GET",
        url: "svlQualis?evento=get;dep="+d,
        data: frm.serialize(),
        success: function (data) 
        {        
            $('#ultima-importacao').html(data);
            document.getElementById("label-ultimas-importacoes-qualis").hidden = false;
                
        },
        error: function (jqXHR, textStatus, errorThrown) 
        {
            alert("Impossível carregar as informações");
            document.getElementById("label-ultimas-importacoes-qualis").hidden = true;
        }
    });
}

function ImportarQualis()
{
    event.preventDefault();
    
    var form = $('#importar_qualis')[0];
    
    var data = new FormData(form);
    
    $.ajax(
    {
        type: "POST",
        enctype: 'multipart/form-data',
        url: "svlQualis",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) 
        {
            if(data === "gravou")
                carregaQualis();
            else
                alert("Não foi possivel gravar o arquivo");
        },
        error: function (jqXHR, exception) 
        {
            var msg = '';
            if (jqXHR.status === 0) 
                msg = 'Not connect.\n Verify Network.';
            else if (jqXHR.status == 404) 
                msg = 'Requested page not found. [404]';
            else if (jqXHR.status == 500) 
                msg = 'Internal Server Error [500].';
            else if (exception === 'timeout') 
                msg = 'Time out error.';
            else if (exception === 'abort') 
                msg = 'Ajax request aborted.';
            else 
                msg = 'Uncaught Error.\n' + jqXHR.responseText;
            
            alert(msg);
        }
    });
}
 
function clickVisualizar(content)
{
    event.preventDefault();
    content = content + "";
    var nome = content.substring(0,content.indexOf(";"));
    
    $.ajax(
    {
        url: 'svlQualis?evento=down;'+content + ";" + $("#sel-dep").val(),
        method: 'GET',
        xhrFields: 
        {
            responseType: '.xls'
        },
        success: function (data) 
        {
            var a = document.createElement('a');
            var binaryData = [];
            binaryData.push(data);
            var url = window.URL.createObjectURL(new Blob(binaryData, {type: ".xls/.xslx"}));
            a.href = url;
            a.download = nome;
            document.body.append(a);
            a.click();
            a.remove();
            window.URL.revokeObjectURL(url);
        }
    });
}

function clickremover(nome) 
{
    event.preventDefault();
    
    var form = $('#importar_qualis')[0];
      
    var data = new FormData(form);
    
    $.ajax(
    {
        type: "POST",
        enctype: 'multipart/form-data',
        url: "svlQualis?evento=del;"+nome,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) 
        {
            if(data === 'ok')
                carregaQualis();
            else
                alert("Erro na exclusão");
        },
        error: function (jqXHR, exception) 
        {
            var msg = '';
            if (jqXHR.status === 0) 
                msg = 'Not connect.\n Verify Network.';
            else if (jqXHR.status == 404) 
                msg = 'Requested page not found. [404]';
            else if (jqXHR.status == 500) 
                msg = 'Internal Server Error [500].';
            else if (exception === 'timeout') 
                msg = 'Time out error.';
            else if (exception === 'abort') 
                msg = 'Ajax request aborted.';
            else 
                msg = 'Uncaught Error.\n' + jqXHR.responseText;
            
            alert(msg);
            
        }
    });
}