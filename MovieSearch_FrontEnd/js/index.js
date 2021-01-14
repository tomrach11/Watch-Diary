$(document).ready(function ()
{
    displayMovies();
});

function addListItem(text)
{
    var node = document.createElement("li");
    var textNode = document.createTextNode(text);
    node.appendChild(textNode);
    document.getElementById("list").appendChild(node);
}

async function getAllMovies()
{
    let url = 'http://localhost:8080/api/movies';
    try
    {
        let res = await fetch(url);
        return await res.json();
    }
    catch(error)
    {
        console.log(error);
    }
}

async function displayMovies()
{
    window.alert("here");
    let movies = await getAllMovies();

    movies.forEach(movie => {
        addListItem(movie.title);
    })
}