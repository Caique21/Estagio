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
    }
    else
    {
        document.getElementById("div-docentes").hidden = true;
    }
}

