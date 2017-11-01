<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Matches List</title>
</head>
<body>
    <div style="width: 100%;">
        <table border=1 align="center">
        <#list matchesList as match>
            <tr>
                <td>${match.matchId}</td>
                <td>${match.league}</td>
                <td>${match.matchTeam}</td>
            </tr>
        </#list>
        </table>
    </div>
</body>
</html>