#include <IE.au3>

		Func _IEAGetCollection(ByRef $tag_parent, $i_index = -1)
			;MsgBox(1,"",$tag_parent)
			;Sleep(5000)
			$begin = TimerInit()			
			Local $obj
			While Not IsObj($obj)
				$obj=$tag_parent.GetElementsByTagName("a" ).item($i_index)
				$dif = TimerDiff($begin)
				If $dif/1000 >5 Then
					Return ""
				EndIf
			Wend
			
			If Not IsObj($tag_parent) Then
				__IEErrorNotify("Error", "_IETableGetCollection", "$_IEStatus_InvalidDataType")
				SetError($_IEStatus_InvalidDataType, 1)
				Return ""
			EndIf
				$i_index = Number($i_index)
			Select
			Case $i_index = -1
				SetError($_IEStatus_Success)
				SetExtended($tag_parent.GetElementsByTagName("a" ).length)
				Return $tag_parent.GetElementsByTagName("a").href
			Case $i_index > -1 And $i_index < $tag_parent.GetElementsByTagName("a" ).length
				SetError($_IEStatus_Success)
				SetExtended($tag_parent.GetElementsByTagName("a" ).length)
				Return $tag_parent.GetElementsByTagName("a" ).item($i_index).href
			Case $i_index < -1
				__IEErrorNotify("Error", "_IETableGetCollection", "$_IEStatus_InvalidValue", "$i_index < -1")
				SetError($_IEStatus_InvalidValue, 2)
				Return ""
			Case Else
				__IEErrorNotify("Warning", "_IETableGetCollection", "$_IEStatus_NoMatch")
				SetError($_IEStatus_NoMatch, 1)
				Return ""
			EndSelect
		EndFunc   ;==>_IETableGetCollection

		Func readfile($filename)
			$begin = TimerInit()
			Local $file = -1
			While ($file = -1)
				$file = FileOpen($filename, 0)
				$dif = TimerDiff($begin)
				If $dif/1000>5 Then
					Return ""
				EndIf
			Wend
			
			; Read in lines of text until the EOF is reached
			Local $line
			Local $lineAll				
			While 1
				$line = FileReadLine($file)				
				If $line = "" Or $line =" " Or $line ="@CRLF" Then
					$lineAll &=""
				Else
					$lineAll &=$line & ";"
				EndIf
				If @error = -1 Then ExitLoop
				;MsgBox(0, "Line read:", $line)
			Wend
			FileClose($file)
			return $lineAll
		EndFunc; end funciton here

	Func writefile($filename,$data)
			$begin = TimerInit()
			Local $file = -1
			While ($file = -1)
				$file = FileOpen($filename, 2)
				$dif = TimerDiff($begin)
				If $dif/1000>5 Then
					FileWriteLine($file,"")						
					FileClose($file)
					Return
				EndIf
			Wend			
			FileWriteLine($file,$data)						
			FileClose($file)

	EndFunc; end funciton here


		Func getlink($link)
			$oIE = _IECreate ("http://vnnsearch.com/tool/grablink.html#chatroom", 1, 1, 1,1)
			_IELoadWait ($oIE)
			$divcontenner=_IEGetObjById($oIE,"url") 
			_IEFormElementSetValue($divcontenner,$link)
			;$input=_IEAGetCollection($oIE,0)
			$divupload=_IEGetObjById($oIE,"btnGrabLink") 
			If _IEAction($divupload,"click") = 0 Then
				Return ""
			;code lay link sau khi web tra ve			
			Else
				;_IELoadWait ($oIE)
				;if has Error on server,return Error
				$span = _IEGetObjById($oIE,"GrabLink_msg")
				;MsgBox(1,"link here",$span.outerHTML)
				If  $span = 0 Then
				;_IELoadWait ($oIE)
				;_IEAction($divupload,"back")
				;MsgBox(1,"link here","tieu rui");
				Return ""
				Else
					$a = _IEAGetCollection($span,0)
					;MsgBox(1,"",$a)
					If $a="" Then
						Return ""
					Else
						Return $a	
					EndIf
				EndIf
			;EndIf
		EndIf
			
		EndFunc; end fucntion here

		;read links in file and get links 
		;$f = FileFindFirstFile("link.txt")  
		$links=readfile("c:/link.txt")
		If $links ="" Then
			writefile("c:/media.txt","")
		Else
		;$links=StringRegExpReplace($links,"[@CRLF]","")	
		$Array=StringSplit($links,"[;]")
		;MsgBox(1,"link here",$Array[1]);$Array[0] is sum item in this Array
		$media=getlink($Array[1])
		;MsgBox(1,"link here",$media)
		;$f = FileFindFirstFile("media.txt")  
		writefile("c:/media.txt",$media)
		EndIf
		