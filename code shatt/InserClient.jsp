<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Style.css">
    <title>Document</title>
</head>
<body>
    <form action="">
        <div id="Champ">
             <h1>Bonjour cher Client</h1>
                <hr>
            <h4>Veiller completer les champs ci-desous</h4>
            <img src="logo.png" alt="" width="50px" height="50px">
            <p><input type="text" name="Nom" id="" placeholder="Entrer votre Nom"></p>
            <p><input type="text" name="Prenom" id="" placeholder="Entrer votre Prenom"></p>
            <p><input type="text" name="Nom" id="" placeholder="Entrer le matricule de votre voiture (000WWT)" ></p>
            <p> Marque : 
                <select name="Marque" id="">
                    <option value="1">Audi</option>
                    <option value="2">BMW</option>
                    <option value="3">Merco</option>
                </select> 
            </p>
            <button type="submit">S'incrire</button>
            
        </div>
    </form>
    <script>
        $(document).ready(function(){
            $("#addBtn").click(function(){
                var selectedValue = $("#list option:select").val();
                var total = $("#total").text();
                total = parseInt(total) + parseInt(selectedValue);
                $("#total").text(total);
            });
        });
    </script>
</body>
</html>