	

<html>
 
   <head>
      <meta charset="UTF-8">
      <title>Upload Multiple Files</title>
   </head>
    
   <body>
<h1>Spring Multiple File Upload example</h1>

<form method="post" action="/MultipleFile" 
		modelAttribute="uploadForm" enctype="multipart/form-data">

	<p>Select files to upload. Press Add button to add more file inputs.</p>

	<input id="addFile" type="button" value="Add File" />
	<table id="fileTable">
		<tr>
			<td><input name="files[0]" type="file" /></td>
		</tr>
		<tr>
			<td><input name="files[1]" type="file" /></td>
		</tr>
	</table>
	<br/><input type="submit" value="submit" />
</form>
</body>
</html>
