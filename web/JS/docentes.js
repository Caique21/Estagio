/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('.tooltipped').tooltip();
});
        

function atualizaDocente()
{
    var departamentos = ["Cartografia","Educação","Educação Física","Estatística","Física","Fisioterapia","Geografia",
        "Matemática e Computação","Planejamento, Urbanismo e Ambiente","Quimica e Bioquimica"];
    
    for(i = 0 ; i < departamentos.length; i++)
    {
        jQuery.ajax(
        {
            type: "GET",
            url: "svlDocente?evento=refresh;"+departamentos[i],
            success: function (data)
            {
                alert("achou");
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                alert("Impossível carregar as informações");
            }
        });
    }
}

function ImportarCurriculo()
{
    event.preventDefault();
    
    var form = $('#docente')[0];
    
    var data = new FormData(form);
    
    $.ajax(
    {
        type: "POST",
        enctype: 'multipart/form-data',
        url: "svlDocente",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) 
        {
            if(data !== "salvou")
                alert("Não foi possivel gravar o arquivo");
            else
                alert("Curriculo atualizado");
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

function changeDepartamento()
{
    var dep = $("#sel-dep").val();
    
    var obj = $("#hosting-plan").find("option[value='" + dep + "']");
    
    if(obj != null && obj.length > 0)
    {
        document.getElementById("div-docentes").hidden = false;
        
        event.preventDefault();
        
        let frm = $("#docente");
        var d = document.getElementById("sel-dep").value;
    
        jQuery.ajax(
        {
            type: "GET",
            url: "svlDocente?evento=get;dep="+d,
            data: frm.serialize(),
            success: function (data) 
            {        
                $('#table-docente-body').html(data);

            },
            error: function (jqXHR, textStatus, errorThrown) 
            {
                alert("Impossível carregar as informações");
            }
        });
    }
    else
    {
        document.getElementById("div-docentes").hidden = true;
    }
}

function carregaDocentes()
{
    event.preventDefault(); // evita refresh da tela
    let frm = $("#docente");    //form
    var d = document.getElementById("sel-dep").value;
    
    jQuery.ajax(
    {
        type: "GET",
        url: "svlQualis?evento=get;dep="+d,
        data: frm.serialize(),
        success: function (data) 
        {        
            $('#ultima-importacao').html(data);
            if ($('#ultima-importacao').is(':empty'))
            {
                alert("Não foi encontrado nenhum arquivo");
            }
            else   
                document.getElementById("label-ultimas-importacoes-qualis").hidden = false;
                
        },
        error: function (jqXHR, textStatus, errorThrown) 
        {
            alert("Impossível carregar as informações");
            document.getElementById("label-ultimas-importacoes-qualis").hidden = true;
        }
    });
}

var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++)
{
    coll[i].addEventListener("click", function () 
    {
        this.classList.toggle("active");
        var content = this.nextElementSibling;
        if (content.style.display === "block") {
            content.style.display = "none";
        } else {
            content.style.display = "block";
        }
    });
}

function clickDocente(nome)
{
    document.getElementById("informacoes-docente").hidden = false;
    $("#identificacao-lattes").html("Currículo Lattes de "+nome);
    $('#nome-docente').val(nome);
    
    event.preventDefault(); // evita refresh da tela
    let frm = $("#docente");    //form
    var d = document.getElementById("nome-docente").value;
    var dep = document.getElementById("sel-dep").value;
    
    jQuery.ajax(
    {
        type: "GET",
        url: "svlDocente?evento=getDocumento;doc="+d+";"+dep,
        data: frm.serialize(),
        success: function (data) 
        {        
            $('#tabela_curriculo-tr').html(data);
        },
        error: function (jqXHR, textStatus, errorThrown) 
        {
            alert("Impossível carregar as informações");
        }
    });
}

function clickCurriculoTab(aux)
{
    var tes;
    if(document.getElementsByClassName("ativo").length !== 0)
        document.getElementsByClassName("ativo")[0].style.visibility = 'hidden';
        
    if(aux === 'dados_gerais')
    {
        document.getElementById("identificacao").hidden = false;
        document.getElementById("identificacao").className += " ativo";
    }
    else if(aux === 'formacao')
        alert("ok");
}

function baixarCurriculo()
{
    event.preventDefault();
    var nome = $('#nome-docente').val();
    
    $.ajax(
    {
        url: 'svlDocente?evento=down;'+nome,
        method: 'GET',
        xhrFields: 
        {
            responseType: '.xml'
        },
        success: function (data) 
        {
            var a = document.createElement('a');
            var binaryData = [];
            binaryData.push(data);
            var url = window.URL.createObjectURL(new Blob(binaryData, {type: ".xml"}));
            a.href = url;
            a.download = "Curriculo_"+nome+".xml";
            document.body.append(a);
            a.click();
            a.remove();
            window.URL.revokeObjectURL(url);
        }
    });
}

function removerCurriculo()
{
    event.preventDefault();
    
    var form = $('#docente')[0];
      
    var data = new FormData(form);
    
    $.ajax(
    {
        type: "POST",
        enctype: 'multipart/form-data',
        url: "svlDocente?evento=del;",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) 
        {
            if(data !== 'ok')
                alert("Erro na exclusão");
            else
                alert("Arquivo excluído com sucesso");
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

function clickRadio(tipo)
{
    $('#hosting-plan').find('option').remove();
    if(tipo.toString()==="Departamento")
    {
        document.getElementById("lista-docentes").hidden = false;
        $('#sel-dep').attr("placeholder","Selecione um Departamento");
        $('#hosting-plan').append(`<option value="${'Cartografia'}"></option>`); 
        $('#hosting-plan').append(`<option value="${'Educação'}"></option>`); 
        $('#hosting-plan').append(`<option value="${'Educação Física'}"></option>`); 
        $('#hosting-plan').append(`<option value="${'Estatística'}"></option>`); 
        $('#hosting-plan').append(`<option value="${'Física'}"></option>`); 
        $('#hosting-plan').append(`<option value="${'Fisioterapia'}"></option>`); 
        $('#hosting-plan').append(`<option value="${'Geografia'}"></option>`); 
        $('#hosting-plan').append(`<option value="${'Matemática e Computação'}"></option>`); 
        $('#hosting-plan').append(`<option value="${'Planejamento, Urbanismo e Ambiente'}"></option>`); 
        $('#hosting-plan').append(`<option value="${'Quimica e Bioquimica'}"></option>`); 
    }
    else if(tipo.toString()==="Nome")
    {
        $('#sel-dep').attr("placeholder","Digite o nome do Docente");
        document.getElementById("lista-docentes").hidden = true;
    }
    else if(tipo.toString()==="Matrícula")
    {
        $('#sel-dep').attr("placeholder","Digite a Matrícula do Docente");
        document.getElementById("lista-docentes").hidden = true;
    }
}



