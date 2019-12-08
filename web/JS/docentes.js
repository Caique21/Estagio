/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function ImportarCurriculo()
{
    
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
                $("#ultima-importacao").replaceWith(divClone.clone()); 
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
    $("#identificacao-lattes").html("Currículo Lates de "+nome);
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

function baixarCurriculo(conteudo)
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
            responseType: '.xml'
        },
        success: function (data) 
        {
            var a = document.createElement('a');
            var binaryData = [];
            binaryData.push(data);
            var url = window.URL.createObjectURL(new Blob(binaryData, {type: ".xml"}));
            a.href = url;
            a.download = nome;
            document.body.append(a);
            a.click();
            a.remove();
            window.URL.revokeObjectURL(url);
        }
    });
}

function removerCurriculo()
{
    
}

