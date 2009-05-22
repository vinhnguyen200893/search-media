	var ajaxObj = new XMLHTTP("mybic_server.php");
	ajaxObj.debug=0;
	ajaxObj.method = "TEXT";
	ajaxObj.abort_timeout = 60000; 
	
	var last_info = '1';
	var last_tab = '1';
	var best_cargado = '0';

  function vdTop(num) {  	
	    scroll(0,0);	    
	    var url_value = unescape(document.getElementById('top_'+num+'_url').innerHTML);
    	document.forms['chooser_form'].url.value=url_value;
 
  	 	botonDownload();
  }
  
  function vdView(num) {  	
	    
	    var url_value = document.getElementById('top_'+num+'_url').innerHTML;    	
  	 	
  	 	
  	 	
  	 	var direc = './vd/view.php?lang=es&url=' + url_value;
  	 	window.open(direc,'Help','fullscreen=no,toolbar=no,status=no,menubar=no,scrollbars=no,resizable=yes,directories=no,location=no,width=600,height=275,left=0,top=100,screenX=0,screenY=100');return false;   
   	 	  
  	 	 
  }


	function ch_best(pag) {              
     	
      if (last_tab=='2')
      {          	     	
      	document.getElementById('progress2').style.display = 'inline';
     		ajaxObj.call("action=testbest&pag="+pag, resp2); 
     	}
      else if (last_tab=='3') 
      { 
      	document.getElementById('progress3').style.display = 'inline';         	     	
     		ajaxObj.call("action=testtop&pag="+pag, resp2); 
     	}
     	else if (last_tab=='9')
      {          	     	
      	document.getElementById('progress2').style.display = 'inline';
     		ajaxObj.call("action=testbest&xxx=1&pag="+pag, resp2); 
     	}
	}	
	 
	function ch_pestagna(tab) {             
     document.getElementById('tab_'+last_tab).className = 'pestagna_inactiva2';
     document.getElementById('tab_'+tab).className = 'pestagna_activa2';
     document.getElementById('panel_'+last_tab).className = 'panel_invisible';
     document.getElementById('panel_'+tab).className = 'panel_visible';     
     if (last_tab=='3')  
     {
     	var element = document.getElementById('top20top_span');
      element.innerHTML = '';             	
     }
     if (last_tab=='2')  
     {
     	var element = document.getElementById('top20best_span');
      element.innerHTML = '';             	
     }
     
     last_tab = tab;     
     if (tab=='2')
     {          	
     	document.getElementById('progress2').style.display = 'inline';
     	ajaxObj.call("action=testbest&pag=1&", resp2); 
     }
     if (tab=='9')
     {          	
     	document.getElementById('progress2').style.display = 'inline';
     	ajaxObj.call("action=testbest&pag=1&xxx=1", resp2); 
     }
     if (tab=='3')  
     {     
     	document.getElementById('progress3').style.display = 'inline';
     	ajaxObj.call("action=testtop&pag=1&", resp2); 
     }
	}	

	function change_info(info) {
	  info = info + 1;
	  document.getElementById('eje_'+last_info).style.display = 'none';
	  document.getElementById('eje_'+info).style.display = 'inline';
	  document.getElementById('info_'+last_info).style.display = 'none';
	  document.getElementById('info_'+info).style.display = 'inline';
	  last_info = info;
	}


	function resp2(resp) {
		var progre = 'progress2';
		var tope = 'top20best_span';
		if (last_tab=='3')
		{
  	 	progre = 'progress3';
  	 	tope = 'top20top_span';
		}
		
		if(resp) 
		{
			if(resp.errors) 
			{
				document.getElementById(progre).style.display = 'none';         
				var element = document.getElementById(tope);
      	element.innerHTML = 'ERROR';
			} 
			else 
			{				
				document.getElementById(progre).style.display = 'none';         
				var element = document.getElementById(tope);
      	element.innerHTML = resp;      	
      	/*scroll(0,150)*/
			}
		} 
		else 
		{
				document.getElementById(progre).style.display = 'none';         
				var element = document.getElementById(tope);
      	element.innerHTML = 'ERROR';
		}
	}	 
	
	function resp(resp) {
		if(resp) 
		{
			if(resp.errors) 
			{
				document.getElementById('download_details2').innerHTML = 'ERROR.';          		
		    document.getElementById('download_details2').style.display = 'block';     
		    document.getElementById('progress').style.display = 'none';         
			} 
			else 
			{				
				document.getElementById('download_details2').innerHTML = resp;          		
		    document.getElementById('download_details2').style.display = 'block';     
		    document.getElementById('progress').style.display = 'none';         
		    document.forms['chooser_form'].url.value="";
			}
		} 
		else 
		{
				document.getElementById('download_details2').innerHTML = 'ERROR.';          		
		    document.getElementById('download_details2').style.display = 'block';     
		    document.getElementById('progress').style.display = 'none';         
		}
	}	 
		
  function cerrarDowload() {
			document.getElementById('cuadro_descargas').style.display = 'none';
    	document.getElementById('download_details2').style.display = 'none';
    	document.getElementById('progress').style.display = 'none';  	
  }
  
	function botonDownload() {
		
		url_value = document.forms['chooser_form'].url.value;
		if(url_value == "") {
    	alert('Please enter a URL first.');document.forms['chooser_form'].url.focus();
    }
  	else {
			document.getElementById('cuadro_descargas').style.display = 'block';
    	document.getElementById('download_details2').style.display = 'none';
    	document.getElementById('progress').style.display = 'inline';
  	 	var form_vars = ajaxObj.getForm('chooser_form');
      if (url_value.indexOf('youtube.com/')==-1)
 			{ 
  	 		ajaxObj.call("action=test"+form_vars, resp); 
  		}
  		else
 			{ 
  	 		ajaxObj.call("action=ytest"+form_vars, resp); 
  		}
  	}
	}
