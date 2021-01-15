var movieInfo;

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
    movieInfo = data;
    data.forEach(movie => 
    {
        addListItem(movie.title);
    });

    setRightSideText(0);
}

document.getElementById("actualList").addEventListener("click",function(e)
{
    if(e.target && e.target.nodeName == "LI")
    {
        for(i = 0; i < movieInfo.length; i++)
        {
            if(movieInfo[i].title === e.target.innerText)
            {
                setRightSideText(i);
                break;
            }
        }
    }
});

function setRightSideText(index)
{
    document.getElementById("title").innerText = "Movie Title: " + movieInfo[index].title;
    document.getElementById("notes").innerText = "Notes: " + movieInfo[index].note;
    document.getElementById("rating").innerText = "Rating: " + movieInfo[index].rating;
    document.getElementById("viewDate").innerText = "Viewdate: " + movieInfo[index].viewDate;
}