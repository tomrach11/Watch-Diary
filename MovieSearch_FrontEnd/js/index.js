$(document).ready(function ()
{
    getMovies();
});

function addListItem(text)
{
    var node = document.createElement("li");
    var textNode = document.createTextNode(text);
    node.appendChild(textNode);
    document.getElementById("actualList").appendChild(node);
}

function getMovies()
{
    $.ajax({url: 'http://localhost:8080/api/movies'}).done(data => useMovies(data));
}

function useMovies(data)
{
    data.forEach(movie => {
        addListItem(movie.title);
    });
}