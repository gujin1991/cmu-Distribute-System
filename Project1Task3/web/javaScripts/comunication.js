/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showHint()
        {
        
        var xmlhttp;
        var firstOperand = document.getElementById("userInput").value;
        if (firstOperand.length===0)
          { 
            document.getElementById("result").value="";
            return;
          }
        if (window.XMLHttpRequest)
          {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
          }
        else
          {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
          }
         
        console.log("good");
        xmlhttp.onreadystatechange=function()
          {
          if (xmlhttp.readyState===4 && xmlhttp.status===200)
            {
            //document.getElementById("result").value=xmlhttp.responseText;
            
            //console.log(xmlhttp.responseText);
            document.getElementById("result").value=xmlhttp.responseText;
            //return false;
            }
          }
        
        xmlhttp.open("GET","Palin?userInput=" + firstOperand +"&result=",true);
        xmlhttp.send();
        
        }


