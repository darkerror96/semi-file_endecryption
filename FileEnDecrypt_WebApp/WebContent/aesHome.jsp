<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
/* body {
	background: black;
}

h1 {
	text-align: center;
	color: white;
}

div.form {
	display: block;
	text-align: center;
	color: white;
}

form {
	margin-left: 34%;
	margin-right: 25%;
	width: 50%;
}

input[type=submit] {
	border: black;
	font-size: 20px;
	padding: 5px 22px;
	color: black;
	background-color: red;
	border-radius: 8px;
	cursor: pointer;
}

input[type=submit]:hover {
	border: black;
	font-size: 20px;
	padding: 5px 22px;
	color: white;
	background-color: green;
	border-radius: 8px;
	cursor: pointer;
} */
</style>

<title>File En/Decryptor</title>
<script type="text/javascript">

function checkFlag() {
		<%String msgErr = (String) session.getAttribute("fileFlag");%>
		var msg = "<%=msgErr%>";
		if (msg !== "null") {
			<%session.setAttribute("fileFlag", "null");%>
			alert(msg);
		}
	}
</script>
</head>
<body onLoad="checkFlag()">
	<hr color="white">
	<h1>File En/Decryptor</h1>
	<hr color="white">
	<br>
	<br>
	<div class="form">
		<form method="POST" enctype="multipart/form-data" action="UploadFile"
			autocomplete="off">
			<table>
				<tr>
					<td><input type="radio" name="radioBtn" value="0" checked>
						Encrypt<br>
					<input type="radio" name="radioBtn" value="1"> Decrypt</td>
				</tr>

				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>

				<tr>
					<td>Select File: <input type="file" name="upFile">
					</td>
				</tr>

				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>

				<tr>
					<td>Enter Key: <input type="text" name="key"
						placeholder="Secret Key" required />
					</td>
				</tr>

				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>

				<tr>
					<td><input type="submit" value="Upload" /></td>
				</tr>

				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>

				<tr>
					<td><a
						href="http://localhost:8080/FileEnDecrypt_WebApp/Files/Encrypt/EncryptedFile.txt"
						download>Download Encrypted File</a></td>
				</tr>

				<tr>
					<td><a
						href="http://localhost:8080/FileEnDecrypt_WebApp/Files/Decrypt/DecryptedFile.txt"
						download>Download Decrypted File</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>