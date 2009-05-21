#include <IE.au3>

		Func _IEAGetCollection(ByRef $o_object, $i_index = -1)
			If Not IsObj($o_object) Then
			__IEErrorNotify("Error", "_IETableGetCollection", "$_IEStatus_InvalidDataType")
			SetError($_IEStatus_InvalidDataType, 1)
			Return 0
			EndIf
			;
			$i_index = Number($i_index)
			Select
			Case $i_index = -1
			SetError($_IEStatus_Success)
			SetExtended($o_object.GetElementsByTagName("a" ).length)
			Return $o_object.GetElementsByTagName("a")
			Case $i_index > -1 And $i_index < $o_object.GetElementsByTagName("a" ).length
			SetError($_IEStatus_Success)
			SetExtended($o_object.GetElementsByTagName("a" ).length)
			Return $o_object.GetElementsByTagName("a" ).item($i_index)
			Case $i_index < -1
			__IEErrorNotify("Error", "_IETableGetCollection", "$_IEStatus_InvalidValue", "$i_index < -1")
			SetError($_IEStatus_InvalidValue, 2)
			Return 0
			Case Else
			__IEErrorNotify("Warning", "_IETableGetCollection", "$_IEStatus_NoMatch")
			SetError($_IEStatus_NoMatch, 1)
			Return 0
			EndSelect
		EndFunc   ;==>_IETableGetCollection

		Func readfile($filename)
			 $file = FileOpen($filename, 0)

			; Check if file opened for reading OK
			If $file = -1 Then
				MsgBox(0, "Error", "Unable to open file.")
				Exit
			EndIf

			; Read in lines of text until the EOF is reached
			Local $line		
			While 1
				$line &= FileReadLine($file) & ";"
				If @error = -1 Then ExitLoop
				;MsgBox(0, "Line read:", $line)
			Wend
			FileClose($file)
			return $line
		EndFunc; end funciton here



		Func getlink($link)
			$oIE = _IECreate ("http://www.guru.net.vn/media.aspx", 1, 1, 0)
			_IELoadWait ($oIE)
			$divcontenner=_IEGetObjById($oIE,"txtUrl") 
			_IEFormElementSetValue($divcontenner,$link)
			;$input=_IEAGetCollection($oIE,0)
			$divupload=_IEGetObjById($oIE,"cmdDownload") 
			If _IEAction($divupload,"click") = 0 Then
				_IEAction($divupload,"back")
				MsgBox(1,"link here","tieu rui");
			;code lay link sau khi web tra ve
			;$link=_IEGetObjById($oIE,"lbLink");
			;MsgBox(1,"link here",$link);
			Else
				;_IELoadWait ($oIE)
				$span = _IEGetObjById($oIE,"lblLink")
				If  $span = 0 Then
				_IELoadWait ($oIE)
				;_IEAction($divupload,"back")
				;MsgBox(1,"link here","tieu rui");
				Else
				$a= _IEAGetCollection($span,0)
				MsgBox(1,"",$a.href)
				EndIf
		EndIf
			
		EndFunc; end fucntion here

		;read links in file and get links 
		$links=readfile("link.txt")		
		$Array=StringSplit($links,";")
		For $i=1 To 2 Step 1
			getlink($Array[$i])
		
		Next
